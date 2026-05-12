package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // リクエストパラメータの取得
        String entYearStr = req.getParameter("f1"); // 入学年度
        String classNum = req.getParameter("f2");   // クラス
        String subjectCd = req.getParameter("f3");  // 科目
        String numStr = req.getParameter("f4");     // 回数

        // プルダウンメニュー用のデータを準備
        ClassNumDao cDao = new ClassNumDao();
        SubjectDao sDao = new SubjectDao();
        
        // 入学年度リスト（現在年から前後数年）
        List<Integer> entYearSet = new ArrayList<>();
        int currentYear = LocalDate.now().getYear();
        for (int i = currentYear - 5; i <= currentYear + 1; i++) {
            entYearSet.add(i);
        }

        req.setAttribute("ent_year_set", entYearSet);
        req.setAttribute("class_num_set", cDao.filter(teacher.getSchool()));
        req.setAttribute("subject_set", sDao.filter(teacher.getSchool()));

        // 全ての検索条件が指定されている場合のみ検索実行
        if (entYearStr != null && classNum != null && subjectCd != null && numStr != null &&
            !entYearStr.equals("0") && !classNum.equals("0") && !subjectCd.equals("0") && !numStr.equals("0")) {
            
            int entYear = Integer.parseInt(entYearStr);
            int num = Integer.parseInt(numStr);
            
            Subject subject = sDao.get(subjectCd, teacher.getSchool());
            TestDao tDao = new TestDao();
            
            // 指定条件の成績リスト（学生情報を含む）を取得
            // ※TestDao.filter()を呼び出す。学生が存在し成績が未登録の場合はPOINTが空で戻る想定
            List<Test> tests = tDao.filter(entYear, classNum, subject, num, teacher.getSchool());
            
            req.setAttribute("tests", tests);
            req.setAttribute("subject", subject);
        }

        // JSPへフォワード
        req.getRequestDispatcher("test_regist.jsp").forward(req, res);
    }
}
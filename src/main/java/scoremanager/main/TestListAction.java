package scoremanager.main;

import java.util.List;

import javax.security.auth.Subject;

import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // 検索パラメーターの取得
        String entYearStr = req.getParameter("f1"); // 入学年度
        String classNum = req.getParameter("f2");   // クラス
        String subjectCd = req.getParameter("f3");  // 科目

        // DAOの準備
        ClassNumDao cDao = new ClassNumDao();
        SubjectDao sDao = new SubjectDao();
        TestDao tDao = new TestDao();

        // プルダウンメニュー用のリストを取得してセット
        req.setAttribute("class_num_set", cDao.filter(teacher.getSchool()));
        req.setAttribute("subject_set", sDao.filter(teacher.getSchool()));

        // 検索条件がすべて揃っている場合のみ検索を実行
        if (entYearStr != null && classNum != null && subjectCd != null && 
            !entYearStr.equals("0") && !classNum.equals("0") && !subjectCd.equals("0")) {
            
            int entYear = Integer.parseInt(entYearStr);
            Subject subject = new Subject();
            subject.setCd(subjectCd);

            List<Test> tests = tDao.filter(entYear, classNum, subject, teacher.getSchool());
            req.setAttribute("tests", tests);
        }

        req.getRequestDispatcher("test_list.jsp").forward(req, res);
    }
}
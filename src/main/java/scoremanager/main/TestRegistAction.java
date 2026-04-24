package scoremanager.main;

import bean.Teacher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;
// 必要なDaoやBeanをインポート

public class TestRegistAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // 画面のセレクトボックス用のデータを取得
        // 例: ClassNumDao, SubjectDao などを使用してリストを取得しセット
        // req.setAttribute("class_num_set", classList);
        // req.setAttribute("subject_set", subjectList);

        String classNum = req.getParameter("f1");
        String subjectCd = req.getParameter("f2");

        if (classNum != null && subjectCd != null) {
            // 検索ボタンが押された場合、該当クラスの学生一覧と現在の成績を取得してセット
            // TestDao dao = new TestDao();
            // List<Test> students = dao.filter(classNum, subjectCd, teacher.getSchool());
            // req.setAttribute("students", students);
        }

        req.getRequestDispatcher("test_regist.jsp").forward(req, res);
    }
}
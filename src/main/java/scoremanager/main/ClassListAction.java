package scoremanager.main;

import java.util.List;

import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class ClassListAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // セッションからログインユーザー（教員）情報を取得
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // DAOを初期化して、その教員の学校に紐づくクラス一覧を取得
        ClassNumDao cDao = new ClassNumDao();
        List<String> list = cDao.filter(teacher.getSchool());

        // JSP（class_list.jsp）で表示するためにリクエスト属性にセット
        req.setAttribute("class_nums", list);

        // クラス一覧画面へフォワード
        req.getRequestDispatcher("class_list.jsp").forward(req, res);
    }
}
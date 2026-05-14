package scoremanager.main;

import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class ClassDeleteExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        String classNum = req.getParameter("class_num");

        ClassNumDao cDao = new ClassNumDao();
        
        // 学生がいるかチェック
        if (cDao.hasStudents(classNum, teacher.getSchool())) {
            // エラーを抱えて一覧に戻す
            req.setAttribute("error", "学生が所属しているため、クラス " + classNum + " は削除できません。");
        } else {
            // 削除実行
            cDao.delete(classNum, teacher.getSchool());
        }

        // 一覧を再取得して表示
        req.setAttribute("class_nums", cDao.filter(teacher.getSchool()));
        req.getRequestDispatcher("class_list.jsp").forward(req, res);
    }
}
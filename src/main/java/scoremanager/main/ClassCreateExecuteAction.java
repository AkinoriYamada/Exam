package scoremanager.main;

import bean.ClassNum;
import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class ClassCreateExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        String classNum = req.getParameter("class_num");

        if (classNum == null || classNum.isEmpty()) {
            req.setAttribute("error", "クラス番号を入力してください");
            req.getRequestDispatcher("class_create.jsp").forward(req, res);
            return;
        }

        ClassNumDao cDao = new ClassNumDao();
        
        // 既存のDaoのメソッドに合わせて、重複チェックを行う
        if (cDao.get(classNum, teacher.getSchool()) != null) {
            req.setAttribute("error", "そのクラス番号は既に登録されています");
            req.getRequestDispatcher("class_create.jsp").forward(req, res);
            return;
        }

        // Beanにセットして保存
        ClassNum cBean = new ClassNum();
        cBean.setSchool(teacher.getSchool());
        cBean.setClass_num(classNum);
        
        cDao.save(cBean);

        res.sendRedirect("ClassList.action"); // 登録成功したら一覧へ
    }
}
package scoremanager.main;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        String cd = req.getParameter("cd");
        String name = req.getParameter("name");

        if (name != null && !name.isEmpty()) {
            SubjectDao sDao = new SubjectDao();
            Subject subject = sDao.get(cd, teacher.getSchool());
            
            if (subject != null) {
                subject.setName(name);
                sDao.save(subject);
                req.getRequestDispatcher("subject_update_done.jsp").forward(req, res);
            } else {
                req.setAttribute("error", "変更対象の科目が存在しません");
                req.setAttribute("cd", cd);
                req.getRequestDispatcher("subject_update.jsp").forward(req, res);
            }
        } else {
            req.setAttribute("error", "科目名を入力してください");
            req.setAttribute("cd", cd);
            req.getRequestDispatcher("subject_update.jsp").forward(req, res);
        }
    }
}
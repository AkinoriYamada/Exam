package scoremanager.main;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        String cd = req.getParameter("cd");
        String name = req.getParameter("name");
        
        // バリデーションチェック（コード3文字、名前必須）
        if (cd != null && cd.length() == 3 && name != null && !name.isEmpty()) {
            SubjectDao sDao = new SubjectDao();
            Subject existing = sDao.get(cd, teacher.getSchool());
            
            if (existing == null) {
                // 登録処理
                Subject subject = new Subject();
                subject.setCd(cd);
                subject.setName(name);
                subject.setSchool(teacher.getSchool());
                sDao.save(subject);
                req.getRequestDispatcher("subject_create_done.jsp").forward(req, res);
            } else {
                req.setAttribute("error", "科目コードが重複しています");
                req.setAttribute("cd", cd);
                req.setAttribute("name", name);
                req.getRequestDispatcher("subject_create.jsp").forward(req, res);
            }
        } else {
            req.setAttribute("error", "科目コードは3文字、科目名は必須です");
            req.setAttribute("cd", cd);
            req.setAttribute("name", name);
            req.getRequestDispatcher("subject_create.jsp").forward(req, res);
        }
    }
}
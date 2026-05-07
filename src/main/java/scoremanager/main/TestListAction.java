package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.TestListStudent;
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.StudentDao;
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

        String f = req.getParameter("f");

        // プルダウン用のリストを準備
        ClassNumDao cDao = new ClassNumDao();
        SubjectDao sDao = new SubjectDao();
        
        List<Integer> entYearSet = new ArrayList<>();
        int currentYear = LocalDate.now().getYear();
        for (int i = currentYear - 10; i <= currentYear + 1; i++) {
            entYearSet.add(i);
        }
        req.setAttribute("ent_year_set", entYearSet);
        req.setAttribute("class_num_set", cDao.filter(teacher.getSchool()));
        req.setAttribute("subject_set", sDao.filter(teacher.getSchool()));

        TestDao tDao = new TestDao();

        // 1. 科目・クラス検索 (f=sj)
        if ("sj".equals(f)) {
            String entYearStr = req.getParameter("f1");
            String classNum = req.getParameter("f2");
            String subjectCd = req.getParameter("f3");

            if (entYearStr != null && classNum != null && subjectCd != null &&
                !entYearStr.equals("0") && !classNum.equals("0") && !subjectCd.equals("0")) {
                
                Subject subject = sDao.get(subjectCd, teacher.getSchool());
                List<TestListSubject> subjects = tDao.filter(Integer.parseInt(entYearStr), classNum, subject, teacher.getSchool());
                
                req.setAttribute("tests", subjects);
                req.setAttribute("subject", subject);
                
                if (subjects == null || subjects.isEmpty()) {
                    req.setAttribute("error", "検索条件に該当する成績情報が存在しません");
                }
            } else {
                req.setAttribute("error", "入学年度とクラスと科目を選択してください");
            }
            req.getRequestDispatcher("test_list_subject.jsp").forward(req, res);
            return;
        }
        
        // 2. 学生番号検索 (f=st)
        else if ("st".equals(f)) {
            String studentNo = req.getParameter("f4");
            
            if (studentNo != null && !studentNo.isEmpty()) {
                StudentDao studentDao = new StudentDao();
                Student student = studentDao.get(studentNo); // ※DAOによっては studentDao.get(studentNo, teacher.getSchool()) になる場合があります
                
                if (student != null) {
                    req.setAttribute("student", student);
                    List<TestListStudent> studentTests = tDao.filter(student);
                    
                    if (studentTests != null && !studentTests.isEmpty()) {
                        req.setAttribute("studentTests", studentTests);
                    } else {
                        req.setAttribute("error", "学生番号に該当する成績情報が存在しません");
                    }
                } else {
                    req.setAttribute("error", "学生情報が存在しませんでした");
                }
            } else {
                req.setAttribute("error", "学生番号を入力してください");
            }
            req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
            return;
        }

        // 初期表示 (パラメータなし)
        req.getRequestDispatcher("test_list.jsp").forward(req, res);
    }
}
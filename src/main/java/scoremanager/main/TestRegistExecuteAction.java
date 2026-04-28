package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // 配列形式で点数と学生番号を受け取る
        String[] studentNoArray = req.getParameterValues("student_no");
        String[] pointArray = req.getParameterValues("point");
        String subjectCd = req.getParameter("subject_cd");
        int num = Integer.parseInt(req.getParameter("num"));

        List<Test> testList = new ArrayList<>();
        
        if (studentNoArray != null) {
            for (int i = 0; i < studentNoArray.length; i++) {
                Test test = new Test();
                
                // 学生情報をセット
                Student student = new Student();
                student.setNo(studentNoArray[i]);
                test.setStudent(student);

                // 科目情報をセット
                Subject subject = new Subject();
                subject.setCd(subjectCd);
                test.setSubject(subject);
                
                // 学校、クラス、回数をセット
                test.setSchool(teacher.getSchool());
                test.setNo(num);
                // クラス番号は画面遷移時のパラメータまたはhiddenから取得可能だが、設計上は学生に紐づく
                // 必要に応じて req.getParameter("class_num") から取得してセット
                
                // 得点のセット（空欄や不正値のバリデーション）
                try {
                    if (pointArray[i] != null && !pointArray[i].isEmpty()) {
                        int point = Integer.parseInt(pointArray[i]);
                        if (point >= 0 && point <= 100) {
                            test.setPoint(point);
                        } else {
                            throw new Exception("点数範囲外");
                        }
                    } else {
                        // 点数が未入力の場合はリストに加えない（または削除扱いにする仕様なら別途対応）
                        continue;
                    }
                } catch (Exception e) {
                    req.setAttribute("error", "0～100の範囲で数字を入力してください");
                    req.getRequestDispatcher("TestRegist.action").forward(req, res);
                    return;
                }
                
                testList.add(test);
            }
        }

        TestDao tDao = new TestDao();
        tDao.save(testList);

        // 完了画面へ
        req.getRequestDispatcher("test_regist_done.jsp").forward(req, res);
    }
}
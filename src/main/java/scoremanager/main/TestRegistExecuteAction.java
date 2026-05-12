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

        // 設計書に合わせたパラメータ名で受け取る
        String[] studentNoArray = req.getParameterValues("regist"); // 学生番号リスト
        String subjectCd = req.getParameter("subject_cd"); // 科目コード
        int num = Integer.parseInt(req.getParameter("count")); // 実施回数

        List<Test> testList = new ArrayList<>();
        
        if (studentNoArray != null) {
            for (String studentNo : studentNoArray) {
                // 点数は個別のname属性 (point_学生番号) から取得
                String pointStr = req.getParameter("point_" + studentNo);
                
                Test test = new Test();
                
                Student student = new Student();
                student.setNo(studentNo);
                test.setStudent(student);

                Subject subject = new Subject();
                subject.setCd(subjectCd);
                test.setSubject(subject);
                
                test.setSchool(teacher.getSchool());
                test.setNo(num);
                
                try {
                    // 入力がある場合のみ処理
                    if (pointStr != null && !pointStr.isEmpty()) {
                        int point = Integer.parseInt(pointStr);
                        if (point >= 0 && point <= 100) {
                            test.setPoint(point);
                        } else {
                            throw new Exception("点数範囲外");
                        }
                        testList.add(test); // 有効な点数のみリスト追加
                    }
                } catch (Exception e) {
                    req.setAttribute("error", "0～100の範囲で数字を入力してください");
                    req.getRequestDispatcher("TestRegist.action").forward(req, res);
                    return;
                }
            }
        }

        TestDao tDao = new TestDao();
        tDao.save(testList);

        req.getRequestDispatcher("test_regist_done.jsp").forward(req, res);
    }
}
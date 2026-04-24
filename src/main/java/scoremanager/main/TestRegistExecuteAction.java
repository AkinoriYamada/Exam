package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Subject;

import bean.Student;
import bean.Test;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestRegistExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // JSPのフォームから配列として送信されるデータを受け取る
        String[] studentNoArray = req.getParameterValues("student_no");
        String[] pointArray = req.getParameterValues("point");
        String subjectCd = req.getParameter("subject_cd");
        // String no = req.getParameter("no"); // 回数などが必要な場合

        List<Test> testList = new ArrayList<>();
        
        if (studentNoArray != null) {
            for (int i = 0; i < studentNoArray.length; i++) {
                Test test = new Test();
                Student student = new Student();
                student.setNo(studentNoArray[i]);
                test.setStudent(student);

                Subject subject = new Subject();
                subject.setCd(subjectCd);
                test.setSubject(subject);
                
                // 空欄チェックをして点数をセット
                if (pointArray[i] != null && !pointArray[i].isEmpty()) {
                    test.setPoint(Integer.parseInt(pointArray[i]));
                } else {
                    test.setPoint(0); // 未入力のデフォルト値扱い
                }
                test.setNo(1); // ここは要件に合わせて変更（第何回のテストか等）
                
                testList.add(test);
            }
        }

        TestDao dao = new TestDao();
        dao.save(testList);

        // 完了画面へフォワード
        req.getRequestDispatcher("test_regist_done.jsp").forward(req, res);
    }
}
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

        // 設計書の画面項目（test_regist.jsp）に合わせた name属性 で受け取る
        String[] studentNoArray = req.getParameterValues("regist"); // 学生番号リスト
        String subjectCd = req.getParameter("subject_cd"); // 科目コード
        String countStr = req.getParameter("count"); // 実施回数

        if (studentNoArray == null || subjectCd == null || countStr == null) {
            req.setAttribute("error", "登録処理に失敗しました。もう一度やり直してください。");
            req.getRequestDispatcher("TestRegist.action").forward(req, res);
            return;
        }

        int num = Integer.parseInt(countStr);
        List<Test> testList = new ArrayList<>();

        for (String studentNo : studentNoArray) {
            String pointStr = req.getParameter("point_" + studentNo);
            
            // 空欄でなく、点数が入力されている場合のみ処理
            if (pointStr != null && !pointStr.isEmpty()) {
                try {
                    int point = Integer.parseInt(pointStr);
                    
                    if (point >= 0 && point <= 100) {
                        Test test = new Test();
                        
                        // ★重要：ここで必ず学生情報をセットする（今回のエラーの解決ポイント）★
                        Student student = new Student();
                        student.setNo(studentNo);
                        test.setStudent(student);

                        Subject subject = new Subject();
                        subject.setCd(subjectCd);
                        test.setSubject(subject);
                        
                        test.setSchool(teacher.getSchool());
                        test.setNo(num);
                        test.setPoint(point);
                        
                        testList.add(test); // リストに追加
                    } else {
                        req.setAttribute("error", "0～100の範囲で数字を入力してください");
                        req.getRequestDispatcher("TestRegist.action").forward(req, res);
                        return;
                    }
                } catch (NumberFormatException e) {
                    req.setAttribute("error", "点数は数字で入力してください");
                    req.getRequestDispatcher("TestRegist.action").forward(req, res);
                    return;
                }
            }
        }

        // 保存対象のデータが1件以上あればDaoで保存
        if (!testList.isEmpty()) {
            TestDao tDao = new TestDao();
            tDao.save(testList);
        }

        // 完了画面へフォワード
        req.getRequestDispatcher("test_regist_done.jsp").forward(req, res);
    }
}
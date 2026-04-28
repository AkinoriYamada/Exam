package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;
import bean.TestListStudent;
import bean.TestListSubject;

public class TestDao extends Dao {

    /**
     * 【成績登録用】
     * 入学年度、クラス、科目、回数を指定して成績リストを取得する
     * (引数が5つのバージョン)
     */
    public List<Test> filter(int entYear, String classNum, Subject subject, int num, School school) throws Exception {
        List<Test> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;

        // 学生をベースに、特定の科目・回数の点数を結合して取得する（点数がない学生も表示するためLEFT JOIN）
        String sql = "SELECT s.NO as STUDENT_NO, s.NAME, s.ENT_YEAR, s.CLASS_NUM, t.POINT " +
                     "FROM STUDENT s " +
                     "LEFT JOIN TEST t ON s.NO = t.STUDENT_NO " +
                     "  AND t.SUBJECT_CD = ? AND t.NO = ? AND t.SCHOOL_CD = ? " +
                     "WHERE s.ENT_YEAR = ? AND s.CLASS_NUM = ? AND s.SCHOOL_CD = ? " +
                     "ORDER BY s.NO ASC";

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, subject.getCd());
            statement.setInt(2, num);
            statement.setString(3, school.getCd());
            statement.setInt(4, entYear);
            statement.setString(5, classNum);
            statement.setString(6, school.getCd());
            ResultSet rSet = statement.executeQuery();

            while (rSet.next()) {
                Test test = new Test();
                Student student = new Student();
                student.setNo(rSet.getString("STUDENT_NO"));
                student.setName(rSet.getString("NAME"));
                student.setEntYear(rSet.getInt("ENT_YEAR"));
                student.setClassNum(rSet.getString("CLASS_NUM"));
                test.setStudent(student);
                
                test.setSubject(subject);
                test.setSchool(school);
                test.setNo(num);
                test.setClassNum(rSet.getString("CLASS_NUM"));
                
                int point = rSet.getInt("POINT");
                if (rSet.wasNull()) {
                    test.setPoint(-1); // 未登録の場合は-1をセット
                } else {
                    test.setPoint(point);
                }
                list.add(test);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) statement.close();
            connection.close();
        }
        return list;
    }

    /**
     * 【成績参照：科目別用】
     * 1回目と2回目の点数を横並びにするためのメソッド
     */
    public List<TestListSubject> filter(int entYear, String classNum, Subject subject, School school) throws Exception {
        Map<String, TestListSubject> map = new LinkedHashMap<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;

        String sql = "SELECT s.NO as STUDENT_NO, s.NAME, s.ENT_YEAR, s.CLASS_NUM, t.NO, t.POINT " +
                     "FROM STUDENT s " +
                     "LEFT JOIN TEST t ON s.NO = t.STUDENT_NO AND t.SUBJECT_CD = ? AND t.SCHOOL_CD = ? " +
                     "WHERE s.ENT_YEAR = ? AND s.CLASS_NUM = ? AND s.SCHOOL_CD = ? " +
                     "ORDER BY s.NO ASC, t.NO ASC";

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, subject.getCd());
            statement.setString(2, school.getCd());
            statement.setInt(3, entYear);
            statement.setString(4, classNum);
            statement.setString(5, school.getCd());
            ResultSet rSet = statement.executeQuery();

            while (rSet.next()) {
                String studentNo = rSet.getString("STUDENT_NO");
                TestListSubject item = map.get(studentNo);
                if (item == null) {
                    item = new TestListSubject();
                    item.setEntYear(rSet.getInt("ENT_YEAR"));
                    item.setStudentNo(studentNo);
                    item.setStudentName(rSet.getString("NAME"));
                    item.setClassNum(rSet.getString("CLASS_NUM"));
                    map.put(studentNo, item);
                }
                
                int no = rSet.getInt("NO");
                int point = rSet.getInt("POINT");
                if (!rSet.wasNull()) {
                    item.putPoint(no, point);
                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) statement.close();
            connection.close();
        }
        return new ArrayList<>(map.values());
    }

    /**
     * 【成績参照：学生別用】
     */
    public List<TestListStudent> filter(Student student) throws Exception {
        List<TestListStudent> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;

        String sql = "SELECT sub.NAME as SUBJECT_NAME, sub.CD as SUBJECT_CD, t.NO, t.POINT " +
                     "FROM TEST t " +
                     "JOIN SUBJECT sub ON t.SUBJECT_CD = sub.CD AND t.SCHOOL_CD = sub.SCHOOL_CD " +
                     "WHERE t.STUDENT_NO = ? " +
                     "ORDER BY sub.CD ASC, t.NO ASC";

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, student.getNo());
            ResultSet rSet = statement.executeQuery();

            while (rSet.next()) {
                TestListStudent item = new TestListStudent();
                item.setSubjectName(rSet.getString("SUBJECT_NAME"));
                item.setSubjectCd(rSet.getString("SUBJECT_CD"));
                item.setNum(rSet.getInt("NO"));
                item.setPoint(rSet.getInt("POINT"));
                list.add(item);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) statement.close();
            connection.close();
        }
        return list;
    }

    /**
     * 成績保存メソッド
     */
    public boolean save(List<Test> testList) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;

        try {
            String sql = "MERGE INTO TEST (STUDENT_NO, SUBJECT_CD, SCHOOL_CD, NO, POINT) KEY(STUDENT_NO, SUBJECT_CD, NO) VALUES (?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);

            for (Test test : testList) {
                statement.setString(1, test.getStudent().getNo());
                statement.setString(2, test.getSubject().getCd());
                statement.setString(3, test.getSchool().getCd());
                statement.setInt(4, test.getNo());
                statement.setInt(5, test.getPoint());
                statement.addBatch();
                count++;
            }
            int[] results = statement.executeBatch();
            return results.length == count;
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) statement.close();
            connection.close();
        }
    }
}
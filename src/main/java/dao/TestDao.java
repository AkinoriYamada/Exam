package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Subject;

import bean.School;
import bean.Student;
import bean.Test;

public class TestDao extends Dao {

    /**
     * 指定された条件で成績一覧を取得する
     */
    public List<Test> filter(int entYear, String classNum, Subject subject, School school) throws Exception {
        List<Test> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;

        // 学生テーブルと結合して、入学年度やクラスで絞り込む
        String sql = "SELECT t.STUDENT_NO, s.NAME, t.CLASS_NUM, t.NO, t.POINT " +
                     "FROM TEST t " +
                     "JOIN STUDENT s ON t.STUDENT_NO = s.NO " +
                     "WHERE s.ENT_YEAR = ? AND t.CLASS_NUM = ? AND t.SUBJECT_CD = ? AND t.SCHOOL_CD = ? " +
                     "ORDER BY t.STUDENT_NO ASC, t.NO ASC";

        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, entYear);
            statement.setString(2, classNum);
            statement.setString(3, subject.getCd());
            statement.setString(4, school.getCd());
            ResultSet rSet = statement.executeQuery();

            while (rSet.next()) {
                Test test = new Test();
                Student student = new Student();
                student.setNo(rSet.getString("STUDENT_NO"));
                student.setName(rSet.getString("NAME"));
                test.setStudent(student);
                test.setClassNum(rSet.getString("CLASS_NUM"));
                test.setNo(rSet.getInt("NO"));
                test.setPoint(rSet.getInt("POINT"));
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
}
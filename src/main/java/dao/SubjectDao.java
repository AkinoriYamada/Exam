package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDao extends Dao {

    /**
     * 科目コードと学校コードから、科目を1件だけ取得する（科目変更・削除画面などで使用）
     */
    public Subject get(String cd, School school) throws Exception {
        Subject subject = null;
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            String sql = "SELECT * FROM SUBJECT WHERE CD = ? AND SCHOOL_CD = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, cd);
            statement.setString(2, school.getCd());
            ResultSet rSet = statement.executeQuery();

            if (rSet.next()) {
                subject = new Subject();
                subject.setCd(rSet.getString("CD"));
                subject.setName(rSet.getString("NAME"));
                subject.setSchool(school);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) statement.close();
            connection.close();
        }
        return subject;
    }

    /**
     * ログインしている教員の学校に紐づく、すべての科目を一覧取得する（科目管理一覧などで使用）
     */
    public List<Subject> filter(School school) throws Exception {
        List<Subject> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            // 科目コードの昇順で取得
            String sql = "SELECT * FROM SUBJECT WHERE SCHOOL_CD = ? ORDER BY CD ASC";
            statement = connection.prepareStatement(sql);
            statement.setString(1, school.getCd());
            ResultSet rSet = statement.executeQuery();

            while (rSet.next()) {
                Subject subject = new Subject();
                subject.setCd(rSet.getString("CD"));
                subject.setName(rSet.getString("NAME"));
                subject.setSchool(school);
                list.add(subject);
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
     * 科目情報を保存・更新する（科目登録・科目変更アクションで使用）
     * ※H2データベースのMERGE文を使い、データがなければINSERT、あればUPDATEを行う
     */
    public boolean save(Subject subject) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;

        try {
            String sql = "MERGE INTO SUBJECT (SCHOOL_CD, CD, NAME) KEY(SCHOOL_CD, CD) VALUES (?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, subject.getSchool().getCd());
            statement.setString(2, subject.getCd());
            statement.setString(3, subject.getName());
            
            count = statement.executeUpdate();
            
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) statement.close();
            connection.close();
        }
        return count > 0;
    }

    /**
     * 科目情報を削除する（科目削除アクションで使用）
     */
    public boolean delete(Subject subject) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;

        try {
            String sql = "DELETE FROM SUBJECT WHERE CD = ? AND SCHOOL_CD = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, subject.getCd());
            statement.setString(2, subject.getSchool().getCd());
            
            count = statement.executeUpdate();
            
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) statement.close();
            connection.close();
        }
        return count > 0;
    }
}
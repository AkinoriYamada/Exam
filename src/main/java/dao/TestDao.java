package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import bean.Test;

public class TestDao extends Dao {
    
    // 成績を保存・更新するメソッド
    public boolean save(List<Test> testList) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;

        try {
            // 既にデータがある場合はUPDATE、無い場合はINSERTする処理（MERGE文などの例）
            // H2データベースのMERGE文を利用する想定
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
            if (statement != null) {
                statement.close();
            }
            connection.close();
        }
    }
}
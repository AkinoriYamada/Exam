package bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class TestListSubject implements Serializable {
    private int entYear;
    private String studentNo;
    private String studentName;
    private String classNum;
    private Map<Integer, Integer> points = new HashMap<>();

    public int getEntYear() { return entYear; }
    public void setEntYear(int entYear) { this.entYear = entYear; }

    public String getStudentNo() { return studentNo; }
    public void setStudentNo(String studentNo) { this.studentNo = studentNo; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getClassNum() { return classNum; }
    public void setClassNum(String classNum) { this.classNum = classNum; }

    // JSPから ${student.getPoint(1)} のように呼び出せるようにする
    public String getPoint(int key) {
        if (!points.containsKey(key)) {
            return "-";
        }
        return String.valueOf(points.get(key));
    }
    public void putPoint(int key, int value) {
        points.put(key, value);
    }
}
package vn.edu.student;

public class Record {
    private String studentId;
    private String name;
    private String major;
    public Record(String studentId, String name, String major) {
        this.studentId = studentId;
        this.name = name;
        this.major = major;
    }
    @Override
    public String toString() {
        return "Student[studentId=" + studentId + ", name=" + name + ", major=" + major + "]";
    }
}
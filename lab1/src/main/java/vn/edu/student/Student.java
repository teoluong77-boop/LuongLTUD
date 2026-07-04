package vn.edu.student;
public class Student {
    private String studentId;
    private String name;
    private String major;

    public Student(String studentId, String name, String major) {
        this.studentId = studentId;
        this.name = name;
        this.major = major;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", major='" + major + '\'' +
                '}';
    }
}
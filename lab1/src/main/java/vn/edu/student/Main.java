package vn.edu.student;

public class Main {
    public static void main(String[] args) {
        Student student = new Student("24150010", "Trần Minh Lương", "241401");
        System.out.println(student);
        var studentRecord = new Record("24150010", "Trần Minh Lương", "CNTT");
        System.out.println(studentRecord);
    }
}
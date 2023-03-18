package util;

import java.io.PrintWriter;

public class TA extends Student{

    String name;
    int id;

    String phone;

    public TA(String name, String studentID, String teacherID, String phone) {
        super(name, studentID, phone);
        this.name = name;
        this.phone = phone;
        id = Math.max(Integer.parseInt(studentID), Integer.parseInt(teacherID));
    }

    @Override
    public void csvPrintln(PrintWriter out) {
        out.println(name + "," + id + "," + phone);
    }
}

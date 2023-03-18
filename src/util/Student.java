package util;

import java.io.PrintWriter;

public class Student implements CSVPrintable{
    private String name;
    private String id;

    private long phone;

    public Student(String name, String ID, String phone){
        this.name = name;
        this.id = ID;
        this.phone = Long.parseLong(phone);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getID() {
        return Integer.parseInt(id);
    }

    @Override
    public void csvPrintln(PrintWriter out) {
        out.println(name + "," + id + "," + phone);
    }
}

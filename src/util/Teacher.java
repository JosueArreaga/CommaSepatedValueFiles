package util;

import java.io.PrintWriter;

public class Teacher implements CSVPrintable{

    private String name;
    private String id;

    private int phone;

    public Teacher(String name, String id, String phone){
        this.name = name;
        this.id = id;
        phoneToString(phone);
    }

    private void phoneToString(String phone) {
        this.phone = Integer.parseInt(phone.substring(6, 10));
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

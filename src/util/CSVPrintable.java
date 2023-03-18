package util;
import java.io.PrintWriter;
public interface CSVPrintable {
    public String getName();
    public int getID();
    public void csvPrintln(PrintWriter out);
}

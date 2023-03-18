package driver;
import java.util.*;
import java.io.*;
import util.*;
public class Main {

    /**
     * COP 3337
     * Professor Kianoush Gomboli
     * Project Number 3: PA3 Draft
     * By: Josue David Arreaga
     * Description: This project allows the user to input profiles and add them to a file calles out.csv.
     *              in the beginning we ask the user to enter how many profiles they would like to add.
     *              Afterwords for each profile added such as "teacher Alex,Martinez 0 98765 3053489999"
     *              we validate the data. If the profile passes all checks then we display the information according
     *              to the instructions of the person type. There are three types: Teacher, Student, and TA
     */

    /*
        Samples of input with their output:
            teacher Alex,Martinez 0 98765 3053489999 -> Alex Martinez,98765,9999
            student Rose,Gonzales 56789 0 9876543210 -> Rose Gonzales,56789,9876543210
            TA John,Cruz 88888 99999 1234567890      -> John Cruz,99999,1234567890
     */


    /*
        This method verifies all the data requirements for the profile such as making sure the teachers, ta, and students only have
        an ID length of only 5 or 0. It also checks that the phone number is of length 10 and other features specific of each class: Teacher, Student, TA
     */
    private static boolean verify(String position, String name, String studentID, String teacherID, String phone){

        if (phone.length() != 10)
            return false;


        try{
            String [] nameTokens = name.split(",");
            if(nameTokens.length != 2)
                return false;
        } catch (Exception e) {
            System.out.println("Must have two names");
            return false;
        }


        String [] IDs = new String[] {studentID, teacherID};

        for (String id : IDs) {
            if (!id.equalsIgnoreCase("0")) {
                if (id.length() == 5) {
                    for (int j = 0; j < 5; j++) {
                        if (!Character.isDigit(id.charAt(j)))
                            return false;
                    }
                } else
                    return false;
            }
        }

        position = position.toLowerCase();
        switch(position){
            case "student":
                if (!teacherID.equalsIgnoreCase("0") || studentID.equalsIgnoreCase("0"))
                    return false;

                break;
            case "teacher":
                if(!studentID.equalsIgnoreCase("0") || teacherID.equalsIgnoreCase("0"))
                    return false;
                break;
            case "ta":
                if(teacherID.equalsIgnoreCase("0") || studentID.equalsIgnoreCase("0"))
                    return false;
                break;
            default:
                return false;
        }

        return true;
    }

    /*
        This method creates the CSVPrintable constructor for each person : Teacher, Student or TA. It passes information such as
        the name, required id, and phone number. It also returns a CVSPrintable object.
     */
    private static CSVPrintable createPerson(String position, String name,
                                             String studentID, String teacherID,
                                             String phone){
        String [] nameTokens = name.split(",");
        name = nameTokens[0] + " " + nameTokens[1];

        CSVPrintable person;
        switch(position){
            case "teacher":
                person = new Teacher(name, teacherID, phone);

                break;
            case "TA":
                person = new TA(name,studentID, teacherID,phone);
                break;
            default:
                person = new Student(name, studentID, phone);
        }
        return person;
    }
    public static final String OUTPUT_PATH = System.getProperty("user.dir") + "/output/";
    /*
        This method creates a PrintWriter, and Scanner for user input. It firsts asks the user how many profiles it wants to add to our file.
        Afterwards it begins a for loop that constantly checks the person validation. If the format is in the wrong order it allows the user to renter
        the information. At the end after all the profiles have been properly entered they are passed on to a file as Comma Separated Value. 
     */
    public static void main(String[] args) throws IOException{
        // write your code here
        PrintWriter p = new PrintWriter(OUTPUT_PATH + "out.csv");
        CSVPrintable person;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the number of persons: ");

        int n;
        try{
            n = Integer.parseInt(scanner.nextLine());
        } catch (Exception e){
            System.out.println("ENTER AN INTEGER");
            n = Integer.parseInt(scanner.nextLine());
        }

        while(n < 0){
            System.out.println("A Negative number is entered. Please try again!");
            n = scanner.nextInt();
        }

        System.out.println("Enter the information of the first person ");

        for(int i = 0; i < n;i++){

            if(i >= 1)
                System.out.println("Enter the information of person " + (i + 1));

            String personInfo = scanner.nextLine();
            String[] tokens = personInfo.split(" ");

            if(tokens.length != 5){
                System.out.println("Incorrect Format: renter the information for person " + (i + 1));
                i--;
                continue;
            }

            String position = tokens[0];//scanner.next();
            String name = tokens[1];//scanner.next();
            String studentID = tokens[2];//scanner.next();
            String teacherID = tokens[3];// scanner.next();
            String phone = tokens[4];// scanner.next();
            if(!verify(position, name, studentID, teacherID, phone)){
                System.out.println("Incorrect Format: renter the information for person " + (i + 1));
                i--;
                continue;
            }
            person = createPerson(position, name, studentID, teacherID, phone);
            person.csvPrintln(p);
        }
        p.close();

    }
}
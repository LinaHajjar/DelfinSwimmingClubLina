package main_package.other;

import main_package.people.Employee;
import main_package.people.Member;
import main_package.people.SwimmingDisciplin;
import main_package.people.SwimmingResult;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Filehandler {

    //read from file Employee
    public static void loadEmployeeTxt(ArrayList<Employee> employees) throws FileNotFoundException {
        File employeeFile = new File("src/main_package/Db/EmployeeList.txt");
        Scanner scan = new Scanner(employeeFile);
        while (scan.hasNextLine()) {
            String[] employeeData = scan.nextLine().split(",");

            String employeeName = employeeData[0];
            String employeePhoneNumber = employeeData[1];
            String employeeAddress = employeeData[2];
            LocalDate employeeDateOfBirth =LocalDate.parse(employeeData[3]);
            //int employeeAge = Integer.parseInt(employeeData[3]);
            int employeeAccesGroup = Integer.parseInt(employeeData[4]);
            String employeePassword = employeeData[5];
            String employeeTitle = employeeData[6];
            String employeeUserName = employeeData[7];
            employees.add(new Employee(employeeName, employeePhoneNumber, employeeAddress, employeeDateOfBirth, employeeAccesGroup, employeePassword, employeeTitle, employeeUserName));

        }
    } //end of loadEmployeeTxt


    public static void writeToFileMember(ArrayList<Member> members)throws IOException{
        BufferedWriter out = new BufferedWriter(new FileWriter("src/main_package/db/MemberList.txt"));
        for (Member member:members){
            out.write(member.toPrint());
        }
        out.close();
    }

    public static void writeToFileSwimmingResult(ArrayList<SwimmingResult> swimmingResults)throws IOException{
        BufferedWriter out = new BufferedWriter(new FileWriter("src/main_package/db/SwimResultList.txt"));
        for (SwimmingResult swR:swimmingResults){
            out.write(swR.toPrint());
        }
        out.close();
    }
    //read from file member
    public static void loadMemberTxt(ArrayList<Member> members) throws FileNotFoundException {
        File memberFile = new File("src/main_package/db/MemberList.txt");
        Scanner scan = new Scanner(memberFile);
        Member member;
        while (scan.hasNextLine()) {
            String[] memberData = scan.nextLine().split(",");
            String memberName = memberData[0];
            String memberPhoneNumber = memberData[1];
            String memberAddress = memberData[2];
            LocalDate dateOfBirth =LocalDate.parse(memberData[3]);
            //int memberAge = Integer.parseInt(memberData[3]);
            int memberNr = Integer.parseInt(memberData[4]);
            double kontingent = Double.parseDouble(memberData[5]);
            boolean aktiv = Boolean.parseBoolean(memberData[6]);
            member = new Member(memberName, memberPhoneNumber, memberAddress, dateOfBirth, memberNr, kontingent, aktiv);
            members.add(member);
        } //end of loadMemberTxt
    }//end of loadMemberTxt

    // Mangler save to file methods here.


    public static ArrayList<SwimmingResult> readFromFileSwimResult () throws FileNotFoundException {
        Scanner scan=new Scanner(new File("src/main_package/db/SwimResultList.txt"));
        ArrayList<SwimmingResult> swimmingResults=new ArrayList<>();
        while (scan.hasNextLine()){
            String line=scan.nextLine();
            Scanner linescan=new Scanner(line);
            int memberNr=0;
            while ((linescan.hasNext()) && !linescan.hasNext(";")){
                memberNr=linescan.nextInt();
            }
            linescan.next();

            SwimmingDisciplin disciplin= SwimmingDisciplin.valueOf("BUTTERFLY");
            while ((linescan.hasNext()) && !linescan.hasNext(";")){
                disciplin= SwimmingDisciplin.valueOf(linescan.next());
            }
            linescan.next();

            int time=0;
            while ((linescan.hasNext()) && !linescan.hasNext(";")){
                time= linescan.nextInt();
            }
            linescan.next();

            LocalDate dateOfResult=LocalDate.now();
            while (linescan.hasNext() && !linescan.hasNext(";")){
                dateOfResult= LocalDate.parse(linescan.next());
            }
            linescan.next();

            SwimmingResult newSwimminResult = new SwimmingResult(memberNr, disciplin, time, dateOfResult);
            swimmingResults.add(newSwimminResult);

        }//end while
        return swimmingResults;

    }//end readFromFileSwimResult

}//end of class

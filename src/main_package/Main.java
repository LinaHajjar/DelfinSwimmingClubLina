package main_package;

import main_package.other.Contingent;
import main_package.other.ContingentMethods;
import main_package.other.Filehandler;
import main_package.other.Login;
import main_package.people.*;

import java.io.*;
import java.util.*;

import static main_package.other.Filehandler.readFromFileSwimResult;

public class Main {

    public static void main(String[] args) throws IOException {
        //making a list for the members
        ArrayList<Member> members = new ArrayList<>();

        //Reading employees from EmployeeList.txt and adding them into the ArrayList employees
        Filehandler.loadMemberTxt(members);

        //making a list for contingent overview
        ArrayList<Contingent> memberContingent = new ArrayList<>();
        //Reads Contingent.txt and adds them to the ArrayList memberContingent
        Filehandler.loadContingentTxt(memberContingent);

        //Checks and updates Contingent.txt if any of the Members has outstanding debt/in Arrears
        ContingentMethods.updateArrears(memberContingent, members);

        //making a list for the employees
        ArrayList<Employee> employees = new ArrayList<>();

        //Reading employees from EmployeeList.txt and adding them into the ArrayList employees
        Filehandler.loadEmployeeTxt(employees);

        //Reading swimmingResults from SwimResultList and saving them into an ArrayList
        ArrayList <SwimmingResult> swimmingResults=Filehandler.readFromFileSwimResult ();

        //pair competition members with their results
        ArrayList<CompetitionMember>compMembers =PersonMethods.pairMemberWithResults(members,swimmingResults);
        //make login object

        Login newLogin = new Login(employees);

        Employee currentUser = Login.attemptLogin(newLogin.makeLogins(),employees);
        UI.userRole(currentUser, members, employees,memberContingent,compMembers);
    }
}

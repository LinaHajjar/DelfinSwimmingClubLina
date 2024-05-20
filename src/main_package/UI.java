package main_package;

import main_package.other.Filehandler;
import main_package.other.Util;
import main_package.people.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static main_package.people.MemberMethods.printNumberedMemberNames;

public class UI {

    public static void userRole(Employee currentUser, ArrayList<Member> members)throws IOException{

        switch(currentUser.getAccesGroup()){
            case 1:
                foremanMenu(currentUser, members);
                break;
            case 2:
                accountantMenu(currentUser);
                break;
            case 3:
                trainerMenu(currentUser);
                break;
            default:

        }
    }

    public static void foremanMenu(Employee currentUser, ArrayList<Member> members)throws IOException{
        System.out.println("""
                           Here are your options:
                           1. Create a new member
                           2. Print out the list of members
                           3. Edit one of members
                           4. Delete one of members
                           5. Exit
                           """);
        int nav = Util.getIntInput("Enter the number from the list: ", "wrong input, only a number between 1 and 5",1,5);
        Scanner input = new Scanner(System.in);

        //input.nextLine(); // consume the newline
        switch(nav){
            case 1:
                System.out.println("Create a member");
                MemberMethods.createMember(input, members);
                foremanMenu(currentUser, members);
                break;
            case 2:
                System.out.println("Print the member list");
                MemberMethods.printTheMemberList(members);
                foremanMenu(currentUser, members);
                break;
            case 3:
                System.out.println("Edit one of the members");
                MemberMethods.editMember(input, members);
                foremanMenu(currentUser, members);
                break;
            case 4:
                System.out.println("Delete one of the members");
                MemberMethods.deleteMember(input, members);
                foremanMenu(currentUser, members);
                break;
            case 5:
                System.out.println("The program is closing.");
                System.exit(0);
            default:
                System.out.println("wrong input, you should only choose a number between 1 and 5");
                foremanMenu(currentUser, members);
        }
    }//end of foremanMenu


    public static void accountantMenu(Employee employee) throws IOException{
        System.out.println("""
                           Here are your options:
                           1. Calculate the membership fees.
                           2. see an overview of the expected annual dues payments.
                           3. see a list of members who are in arrears (restance) with their membership fees.
                           4. Delete members who are in arrears (restance).
                           5. Exit
                           """);
        int nav = Util.getIntInput("Enter the number from the list: ", "wrong input, only a number between 1 and 5",1,5);
        Scanner input = new Scanner(System.in);
        ArrayList<Member> members = new ArrayList<>();
        Filehandler.loadMemberTxt(members);

        switch(nav){
            case 1: //Calculate the membership fees.
                System.out.println("you chose option 1");
                int memberNrToCaculateFees=Util.intPrompt("what is the number of the member you want to calculate the fees?: ");
                Member memberToCalculateTheFees = MemberMethods.findMemberByNumber(memberNrToCaculateFees,members);
                double membershipFees=MemberMethods.calculateMembershipFees(memberToCalculateTheFees);
                System.out.println("the membership fees for the member: " +memberToCalculateTheFees.getName()+ "\n are: " +membershipFees+ " Kr");
                accountantMenu(employee);
                break;
            case 2: //see an overview of the expected annual dues payments
                System.out.println("you chose option 2");
                accountantMenu(employee);
                break;
            case 3: //see a list of members who are in arrears (restance) with their membership fees.
                System.out.println("you chose option 3");
                accountantMenu(employee);
                break;
            case 4: //Delete members who are in arrears (restance).
                System.out.println("you chose option 4");
                accountantMenu(employee);
                break;
            case 5:
                System.exit(0);
                break;
            default:
                System.out.println("wrong input, only a number between 1 and 5");
                accountantMenu(employee);
        }
    }

    public static void trainerMenu(Employee trainer) throws IOException{
        ArrayList <SwimmingResult> swimmingResults=Filehandler.readFromFileSwimResult ();
        ArrayList<CompetitionMember>competitionMembers=new ArrayList<>();
        System.out.println("""
                           Here are your options:
                           1. register a swimmer's best training results and dates.
                           2. see a list of top 5 swimmers for each swimming discipline.
                           3. Exit
                           """);
        int nav = Util.getIntInput("Enter the number from the list: ", "wrong input, only a number between 1 and 5",1,5);
        Scanner input = new Scanner(System.in);

        switch(nav){
            case 1: //record each swimmer's best training results and dates.
                System.out.println("You chose option 1");
                SwimmingResult.registrerSwimResult(swimmingResults);
                trainerMenu(trainer);
                break;
            case 2:
                System.out.println("You chose option 2");
                CompetitionMember.bestFive(competitionMembers);
                trainerMenu(trainer);
                break;
            case 3:
                System.exit(0);
                break;
            default:
                System.out.println("wrong input, only a number between 1 and 3");
                trainerMenu(trainer);
        }
    }


}//end of class

package main_package;

import main_package.other.Filehandler;
import main_package.other.Util;
import main_package.people.*;

import java.io.IOException;
import java.time.LocalDate;
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
                createMember(input, members);
                foremanMenu(currentUser, members);
                break;
            case 2:
                System.out.println("Print the member list");
                MemberMethods.printTheMemberList(members);
                foremanMenu(currentUser, members);
                break;
            case 3:
                System.out.println("Edit one of the members");
                editMember(input, members);
                foremanMenu(currentUser, members);
                break;
            case 4:
                System.out.println("Delete one of the members");
                deleteMember(input, members);
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
                System.out.println("you chose option 1: Calculate the membership fees.");
                int memberNrToCaculateFees=Util.intPrompt("what is the number of the member you want to calculate the fees?: ");
                Member memberToCalculateTheFees = MemberMethods.findMemberByNumber(memberNrToCaculateFees,members);
                double membershipFees=MemberMethods.calculateMembershipFees(memberToCalculateTheFees);
                System.out.println("the membership fees for the member: " +memberToCalculateTheFees.getName()+ "\n are: " +membershipFees+ " Kr");
                accountantMenu(employee);
                break;
            case 2: //see an overview of the expected annual dues payments
                System.out.println("you chose option 2: see an overview of the expected annual dues payments.");
                accountantMenu(employee);
                break;
            case 3: //see a list of members who are in arrears (restance) with their membership fees.
                System.out.println("you chose option 3: see a list of members who are in arrears with their membership fees.");
                accountantMenu(employee);
                break;
            case 4: //Delete members who are in arrears (restance).
                System.out.println("you chose option 4: Delete members who are in arrears.");
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
        int nav = Util.getIntInput("Enter the number from the list: ", "wrong input, only a number between 1 and 3",1,3);
        Scanner input = new Scanner(System.in);

        switch(nav){
            case 1: //record each swimmer's best training results and dates.
                System.out.println("You chose option 1: register a swimmer's best training results and dates.");
                SwimmingResult.registrerSwimResult(swimmingResults);
                trainerMenu(trainer);
                break;
            case 2:
                System.out.println("You chose option 2: see a list of top 5 swimmers for each swimming discipline.");
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

    public static void createMember(Scanner input, ArrayList<Member> members) throws IOException {

        String newName = Util.stringPrompt("Enter the new member's name: ");

        System.out.println("Enter the date of birth in the form year-month-day:");
        LocalDate dateOfBirth= LocalDate.parse(input.next());
        //int newAge = input.nextInt();
        input.nextLine(); // Consume the newLine

        String newAddress = Util.stringPrompt("Enter the new address: ");

        String newPhoneNumber = Util.stringPrompt("Enter the new phone number: ");

        int newMemberNr = Member.calculateMemberNr(members);
        System.out.println("your new member has this member number: " +newMemberNr );

        //System.out.println("Enter kontingent: (comma is with , not .)");
        //double newKontingent = input.nextDouble();

        double newKontingent =0.0;
        System.out.println("Is the member active? (True/false):");
        boolean newAktiv = input.nextBoolean();

        Member newMember = new Member(newName, newPhoneNumber, newAddress, dateOfBirth, newMemberNr, newKontingent, newAktiv);
        newKontingent =MemberMethods.calculateMembershipFees(newMember);
        System.out.println("your new member: " +newName + " 's membership fees are: " + newKontingent + " Kr.");
        newMember.setKontingent(newKontingent);

        members.add(newMember); // Added to the ArrayList in main_package.Main.
        Filehandler.writeToFileMember(members);
        System.out.println("New person created successfully.");
        //writeMemberToFile(nyPerson);  //File handling
    }//end of createMember

    public static void editMember(Scanner input, ArrayList<Member> members) throws IOException{
        printNumberedMemberNames(members);

        System.out.println("Enter the number of the person you want to edit:");
        int memberNumber = input.nextInt();
        input.nextLine(); // consume the newline

        if (memberNumber < 1 || memberNumber > members.size()) {
            System.out.println("Invalid member number");
            return;
        }

        Member memberToEdit = members.get(memberNumber - 1);

        System.out.println("""
                           Which information do you want to change on the chosen person?
                           1. Name
                           2. Date of birth
                           3. Address
                           4. Phone Number
                           5. Active status
                           6. Cancel
                           """);

        int attributeChoice = input.nextInt();
        input.nextLine(); // Consume the newline

        switch (attributeChoice) {
            case 1:
                System.out.println("Enter new name:");
                memberToEdit.setName(input.nextLine());
                System.out.println("Name updated successfully.");
                break;
            case 2:
                System.out.println("Enter the new date of birth in the form year-month-date:");
                LocalDate dateOfBirth=LocalDate.parse(input.next());
                memberToEdit.setDateOfBirth(dateOfBirth);
                input.nextLine(); // consume the newline
                System.out.println("Age updated successfully.");
                break;
            case 3:
                System.out.println("Enter new address:");
                memberToEdit.setAddress(input.nextLine());
                System.out.println("Address updated successfully.");
                break;
            case 4:
                System.out.println("Enter new Phone number:");
                memberToEdit.setPhoneNumber(input.nextLine());
                System.out.println("Phone number updated successfully.");
                break;
            case 5:
                System.out.println("Is the member active? (true/false):");
                memberToEdit.setAktiv(input.nextBoolean());
                input.nextLine(); // Consume the newline
                System.out.println("Active status updated successfully.");
                break;
            case 6:
                System.out.println("Edit cancelled");
                return;
            default:
                System.out.println("Invalid choice");
        }
        Filehandler.writeToFileMember(members);
        System.out.println("the member is successfully updated.");
    }// end of editMember

    public static void deleteMember(Scanner input, ArrayList<Member> members) throws IOException{
        printNumberedMemberNames(members);

        //System.out.println("Enter the number of the person you want to delete:");
        int memberNumber = Util.intPrompt("Enter the number of the person you want to delete:");
        //input.nextLine(); // consume the newline

        if (memberNumber < 1 || memberNumber > members.size()) {
            System.out.println("Invalid person number.");
            return;
        }

        Member memberToDelete = members.remove(memberNumber - 1);
        Filehandler.writeToFileMember(members);
        System.out.println(memberToDelete.getName() + " deleted successfully.");
    }// end of deleteMember


}//end of class

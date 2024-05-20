package main_package.people;
//package main_package.main_package.other.Util;

import main_package.other.Filehandler;
import main_package.other.Util;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class MemberMethods {
    ArrayList<Member> listOfMembers;
    ArrayList<SwimmingResult>listOfResults;

    MemberMethods(ArrayList<Member>listOfMembers){
        this.listOfMembers=listOfMembers;
    }

    public static void getBestFive(ArrayList<CompetitionMember>members){
        String disciplin = Util.stringPrompt("Which disciplin");
        SwimmingDisciplin disciplin1= SwimmingDisciplin.valueOf(disciplin);
        List<Integer>competingMembers =null;
        HashMap<Integer, Integer>record=null;
        for(CompetitionMember cm:members){
            ArrayList<SwimmingResult> currentSwimmersResults=cm.getResults();
            for(SwimmingResult sr:currentSwimmersResults ){
                if(sr.getDiscipline().equals(disciplin1)){
                    record.put(cm.getMemberNr(),sr.getTime());
                    competingMembers.add(cm.getMemberNr());
                }

            }
        }
    }//end of getBestFive

    public static void createMember(Scanner input, ArrayList<Member> members) throws IOException {
        System.out.println("Enter the new member's name:");
        String newName = input.nextLine();

        System.out.println("Enter the date of birth in the form year-month-day:");
        LocalDate dateOfBirth= LocalDate.parse(input.next());
        //int newAge = input.nextInt();
        input.nextLine(); // Consume the newLine

        System.out.println("Enter address:");
        String newAddress = input.nextLine();

        System.out.println("Enter phone number:");
        String newPhoneNumber = input.nextLine();

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

    public static void printTheMemberList(ArrayList<Member> members) {
        if (members.isEmpty()) {
            System.out.println("No people found.");
            return;
        }

        for (Member member : members) {
            System.out.println(member);
        }
    }//end of printTheMemberList

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
                           2. Age
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
                System.out.println("Enter the new date of birth:");
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
    }
    // print the person list in a numbered list for edit and delete methods.
    public static void printNumberedMemberNames(ArrayList<Member> members) {
        for (int i = 0; i < members.size(); i++) {
            System.out.println((i + 1) + ". " + members.get(i).getName());
        }
    }

    public static void deleteMember(Scanner input, ArrayList<Member> members) throws IOException{
        printNumberedMemberNames(members);

        System.out.println("Enter the number of the person you want to delete:");
        int memberNumber = input.nextInt();
        input.nextLine(); // consume the newline

        if (memberNumber < 1 || memberNumber > members.size()) {
            System.out.println("Invalid person number.");
            return;
        }

        Member memberToDelete = members.remove(memberNumber - 1);
        Filehandler.writeToFileMember(members);
        System.out.println(memberToDelete.getName() + " deleted successfully.");
    }// end of deleteMember

    public static Member findMemberByNumber(int memberNrToCaculateFees, ArrayList<Member>members) {
        Member memberByNr=null;
        for (Member member : members) { //// Iterate through all members
                if (member.getMemberNr() == memberNrToCaculateFees) {
                    return memberByNr=member;
                }
        }
        return memberByNr;
    }

    public static double calculateMembershipFees(Member memberToCaculateFees) {

        if (memberToCaculateFees.isAktiv()) {
            /*”For aktive medlemmer er kontingentet for ungdomssvømmere (under 18 år)
             1000 kr. årligt, for seniorsvømmere (18 år og over) 1600 kr. årligt.
              For medlemmer over 60 år gives der 25 % rabat af seniortaksten.
              For passivt medlemskab er taksten 500 kr. årligt”*/
            int age = memberToCaculateFees.getAge();
            if (age < 18) {
                return 1000.0;
            } else if (age <= 60) {
                return (1600.0 * 0.75);
            } else {
                return 1600.0;
            }
        } else {//member is not aktive
            return 500.0;
        }//end of if memberToCaculateFees.isAktiv()

    }//end of calculateMembershipFees


}

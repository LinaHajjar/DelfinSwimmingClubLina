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





    public static void printTheMemberList(ArrayList<Member> members) {
        if (members.isEmpty()) {
            System.out.println("No people found.");
            return;
        }

        for (Member member : members) {
            System.out.println(member);
        }
    }//end of printTheMemberList



    // print the person list in a numbered list for edit and delete methods.
    public static void printNumberedMemberNames(ArrayList<Member> members) {
        for (int i = 0; i < members.size(); i++) {
            System.out.println((i + 1) + ". " + members.get(i).getName());
        }
    }


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

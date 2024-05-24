package main_package.other;

import main_package.people.Member;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class ContingentMethods {

    public static void updateArrears(ArrayList<Contingent> memberContingent, ArrayList<Member> memberList) throws IOException {
        for (Contingent member : memberContingent) {
            LocalDate today = LocalDate.now();
            LocalDate nextPaymentDate = member.getNextPayment();
            long days = ChronoUnit.DAYS.between(nextPaymentDate,today);
            if (days > 0) {
                for (Member memberIdCheck : memberList) {
                    if (member.getMemberNr() == memberIdCheck.getMemberNr()) {
                        member.setArrears(true);
                        double debt = memberIdCheck.getContingent() * 0.25;
                        int periods = (int) ((days/90)+1);
                        double newDebt = -debt*periods;
                        member.setDebt(newDebt);
                    }
                }
            }
        }
        Filehandler.writeToFileContingent(memberContingent);
    }

    public static void removeArrears(ArrayList<Contingent> memberContingent, ArrayList<Member> memberList) throws IOException {
        checkArrears(memberContingent, memberList);

        int ID = Util.intPrompt("Enter ID of member that you would like to remove debt from");

        for (Contingent contingentMemID : memberContingent) {
            if (ID == contingentMemID.getMemberNr()) {
                System.out.println("Enter how much debt you would like to remove from the current member");
                boolean check = true;
                while(check) {
                    double debtToRemove = Util.doublePrompt("Number cannot exceed: " + contingentMemID.getDebt()*-1);
                    if (debtToRemove > contingentMemID.getDebt()*-1) {
                        System.out.println("Number is too large try again");
                    } else if (debtToRemove <= 0) {
                        System.out.println("Cannot remove 0 or less try again");
                    }
                    else {
                        check = false;
                        double newDebt = contingentMemID.getDebt() + debtToRemove;
                        if (newDebt == 0.0) {
                            contingentMemID.setArrears(false);
                            contingentMemID.setDateOfPayment(LocalDate.now());
                            contingentMemID.setNextPayment(LocalDate.now().plusDays(90));
                        }
                        contingentMemID.setDebt(newDebt);
                        Filehandler.writeToFileContingent(memberContingent);
                    }
                }
            }
        }
    }
    public static void checkArrears(ArrayList<Contingent> memberContingent, ArrayList<Member> memberList) {
        for (Contingent checkArrears : memberContingent) {
            if (checkArrears.getArrears()) {
                for (Member members : memberList)
                    if (members.getMemberNr() == checkArrears.getMemberNr())
                        System.out.println(members);
                        System.out.println("-------------------------------------------");
                        System.out.println("      Payment information     ");
                        System.out.println("-------------------------------------------");
                        System.out.println(checkArrears);
                        System.out.println("___________________________________________");
                        }
                    }
                }
    public static void calculateRevenue (List<Member> members) {

        int juniorCount = 0;
        int seniorCount = 0;
        int verySeniorCount = 0;
        int passiveCount = 0;
        int juniorRevenue = 0;
        int seniorRevenue = 0;
        int verySeniorRevenue = 0;
        int passiveRevenue = 0;

        for (Member member : members) {
            if (member.isAktiv()) {
                if (member.getAge() < 18) {
                    juniorCount++;
                    juniorRevenue += 1000;
                } else if (member.getAge() >= 60) {
                    verySeniorCount++;
                    verySeniorRevenue += 1600 * 0.75;
                } else {
                    seniorCount++;
                    seniorRevenue += 1600;
                }
            } else {
                passiveCount++;
                passiveRevenue += 500;
            }
        }
        double totalRevenue = juniorRevenue + seniorRevenue + verySeniorRevenue + passiveRevenue;

        System.out.println("There are " + juniorCount + " junior members that generate " + juniorRevenue + " revenue");
        System.out.println("There are " + seniorCount + " senior members that generate " + seniorRevenue + " revenue");
        System.out.println("There are " + verySeniorCount + " very senior members that generate " + verySeniorRevenue + " revenue");
        System.out.println("There are " + passiveCount + " passive members that generate " + passiveRevenue + " revenue");
        System.out.println("The total yearly revenue is " + totalRevenue + " Kr.");
    }

}



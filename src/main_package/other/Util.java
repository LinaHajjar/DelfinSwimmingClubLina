package main_package.other;

import main_package.people.Member;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Util {

    public static int createMemberShipNumber(ArrayList<Member> members){
        int max = 0;
        for(Member m : members){
            if(m.getMemberNr() > max){
                max = m.getMemberNr();
            }
        }
        return max + 1;
    }



    public static boolean containsIgnoreCase(String s1,String s2){
        return s1.toLowerCase().contains(s2.toLowerCase());
    }


    public static String stringPrompt(String prompt){
        Scanner scan = new Scanner(System.in);
        System.out.println(prompt);
        String output=scan.nextLine();
        return output;
    }

    public static int intPrompt(String prompt)throws InputMismatchException {
        Scanner scan = new Scanner(System.in);
        System.out.println(prompt);
        int output = scan.nextInt();
        return output;
    }

    public static double doublePrompt(String prompt)throws InputMismatchException{
        Scanner scan = new Scanner(System.in);
        System.out.println(prompt);
        double output = scan.nextDouble();
        return output;
    }

    public static LocalDate LocalDateInput() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = null;
        do {
            String enterDate = stringPrompt("Enter the date of birth in the form (yyyy-MM-dd): ");
            try {
                date = LocalDate.parse(enterDate, dateFormatter);
            } catch (Exception e) {
                System.out.println("Invalid date format. Please enter date in yyyy-MM-dd format.");
            }
        } while (date == null);
        return date;
    }

    public static int getIntInputSwitch(String promptMessage, String errorMessage, int minNum, int maxNum) {
        Scanner scanner = new Scanner(System.in);
        int userInput = 0;
        boolean validInput = false;

        do {
            try {
                System.out.print(promptMessage);
                userInput = Integer.parseInt(scanner.nextLine());
                validInput = true;
                if (minNum < maxNum && (userInput > maxNum || userInput < minNum)) {
                    System.out.println(errorMessage);
                    validInput = false;
                }
            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
            }
        } while (!validInput);

        return userInput;
    }

    public static void calculateRevenue (List<Member> members){

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
        System.out.println("The total yearly revenue is " + totalRevenue);
    }


}

package main_package;

import main_package.other.Contingent;
import main_package.other.ContingentMethods;
import main_package.other.Filehandler;
import main_package.other.Util;
import main_package.people.CompetitionMember;
import main_package.people.Employee;
import main_package.people.Member;
import main_package.people.PersonMethods;
import main_package.people.SwimmingResult;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UI {

    public static void userRole(Employee currentUser, ArrayList<Member> members, ArrayList<Employee> employees, ArrayList<Contingent> contingents,ArrayList<CompetitionMember>compMembers) throws IOException {

        switch(currentUser.getAccesGroup()){
            case 1:
                foremanMenu(members, employees, contingents);
                break;
            case 2:
                accountantMenu(currentUser, contingents, members);
                break;
            case 3:
                trainerMenu(currentUser,members,compMembers);
                break;
            default:
                System.out.println("Invalid access group");
        }
    }

    // Menu for the Foreman. with CRUD for members and employees.
    public static void foremanMenu(ArrayList<Member> members, ArrayList<Employee> employees, ArrayList <Contingent> contingents) throws IOException {
        Scanner input = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("""
                               Foreman Menu
                               What do you want to do?
                               1. Manage members
                               2. Manage Employees
                               3. Exit the program.
                               """);
            try {
                int nav = input.nextInt();
                input.nextLine();

                switch(nav) {
                    case 1:
                        foremanMemberMenu(members, contingents);
                        break;
                    case 2:
                        foremanEmployeeMenu(employees);
                        break;
                    case 3:
                        System.out.println("The program is closing.");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid input. Please give me a number between 1 and 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                input.nextLine(); // consume invalid input.
            }
        }
    }

    // CRUD for Members
    public static void foremanMemberMenu(ArrayList<Member> members, ArrayList <Contingent> contingens) throws IOException {
        Scanner input = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("""
                           Here are your options:
                           1. Create a new member
                           2. Print out the list of members
                           3. Edit one of members
                           4. Delete one of members
                           7. Return to foreman menu
                           """);
            try {
                int nav = input.nextInt();
                input.nextLine(); // consume the newline

                switch(nav){
                    case 1:
                        System.out.println("Create a member");
                        PersonMethods.createMember(input, members, contingens);
                        break;
                    case 2:
                        System.out.println("Print the member list");
                        PersonMethods.printTheMemberList(members);
                        break;
                    case 3:
                        System.out.println("Edit one of the members");
                        PersonMethods.editMember(input, members);
                        break;
                    case 4:
                        System.out.println("Delete one of the members");
                        PersonMethods.deleteMember(input, members);
                        break;
                    case 7:
                        System.out.println("Going back to the foreman menu");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                input.nextLine(); // consume invalid input.
            }
        }
    }// End of Member Menu for the foreman

    // CRUD for Employees.
    public static void foremanEmployeeMenu(ArrayList<Employee> employees) throws IOException {
        Scanner input = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("""
                           Here are your options:
                           1. Create a new employee
                           2. Print out the list of employees
                           3. Edit one of the employees
                           4. Delete one of the employees
                           7. Return to foreman menu
                           """);
            try {
                int nav = input.nextInt();
                input.nextLine(); // consume the newline

                switch(nav){
                    case 1:
                        System.out.println("Create a new employee");
                        PersonMethods.createEmployee(input, employees);
                        break;
                    case 2:
                        System.out.println("Print the employee list");
                        PersonMethods.printTheEmployeeList(employees);
                        break;
                    case 3:
                        System.out.println("Edit one of the employees");
                        PersonMethods.editEmployee(input, employees);
                        break;
                    case 4:
                        System.out.println("Delete one of the employees");
                        PersonMethods.deleteEmployee(input, employees);
                        break;
                    case 7:
                        System.out.println("Going back to the foreman menu");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                input.nextLine(); // consume invalid input.
            }
        }
    }// End of Employee Menu for the foreman.

    public static void accountantMenu(Employee currentUser, ArrayList<Contingent> membersContingent, ArrayList<Member> member) throws IOException {
        //kald scanner class og brug den i stedet for at have scanner her
        int nav = 0;
        System.out.println("""
                    Here are your options:
                    1. See the expected yearly revenue
                    2. Members in arrears
                    3. Remove arrears
                    4. Exit
                    """);
        Scanner input = new Scanner(System.in);
        try {
            nav = input.nextInt();
            input.nextLine(); // consume the newline

            switch (nav) {
                case 1:
                    ContingentMethods.calculateRevenue(member);
                    System.out.println("Returning to your menu: ");
                    accountantMenu(currentUser, membersContingent, member);
                    System.out.println();
                    break;
                case 2:
                    ContingentMethods.checkArrears(membersContingent, member);
                    System.out.println("Returning to your menu: ");
                    accountantMenu(currentUser, membersContingent, member);
                    System.out.println();
                    break;
                case 3:
                    ContingentMethods.removeArrears(membersContingent, member);
                    System.out.println("Returning to your menu: ");
                    accountantMenu(currentUser, membersContingent, member);
                    System.out.println();
                    break;
                case 4:
                    System.out.println("Exit.");
                    System.exit(0);
                default:
                    System.out.println("you have entered a wrong number, please try again.");
                    accountantMenu(currentUser, membersContingent, member);
            }
        }catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            input.nextLine(); // consume invalid input.
        }
    }// end of accountantMenu

    public static void trainerMenu(Employee currentUser,ArrayList<Member> members,ArrayList<CompetitionMember>compMembers) throws IOException{
        ArrayList <SwimmingResult> swimmingResults= Filehandler.readFromFileSwimResult ();
        int nav;
        do {
            System.out.println("""
                    Here are your options:
                    1. View list of all swimmers 
                    2. View a swimmer's result in all disciplines
                    3. Register a swimmer's best result and date
                    4. View the top five results for a chosen discipline for each age group
                    5. Exit
                    """);
            nav = Util.getIntInputSwitch("choose a number from the list: ", "wrong input, only a number between 1 and 5: ", 1, 5);
            switch (nav) {
                case 1:
                    PersonMethods.viewAllMembers (members, compMembers);
                    //LASSE: View list of all swimmers

                    //choose between: all swimmers, junior- eller seniorsvømmer, motionist eller konkurrencesvømmer.
                    break;
                case 2: //LINA DONE
                    PersonMethods.seSwimmerResultAllDisciplines(members, swimmingResults);
                    break;
                case 3: //LINA DONE
                    System.out.println("Register a swimmer's best result and date");
                    PersonMethods.registrerSwimResult(swimmingResults);
                    break;
                case 4: //LASSE DONE
                    PersonMethods.getBestFive(compMembers);
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("you should only choose w number between 1 and 6.");
                    trainerMenu(currentUser,members,compMembers);
            }
        } while (nav != 5);
    }//end of trainer menu



}//end of class

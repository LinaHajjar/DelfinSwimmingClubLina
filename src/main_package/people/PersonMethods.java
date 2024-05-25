package main_package.people;
//package main_package.other.Util;

import main_package.other.*;
import main_package.other.Filehandler;
import main_package.other.Util;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

import static main_package.other.Util.*;

public class PersonMethods {

    //__________CompetitionMember_____________
    public static void getBestFive(ArrayList<CompetitionMember> members) {
        int agegroup = Util.getIntInputSwitch("Which agegroup?\n1 for senior\n2 for junior\n","Entered number is not one of the specified options",1,2);

        String disciplin = Util.stringPrompt("Which disciplin");
        SwimmingDisciplin disciplin1 = SwimmingDisciplin.valueOf(disciplin.toUpperCase());

        ArrayList<Integer> storedTimes = new ArrayList<>();
        ArrayList<CompetitionMember> bestPerformingMembersJunior = new ArrayList<>();
        ArrayList<CompetitionMember> bestPerformingMembersSenior = new ArrayList<>();
        HashMap<Integer, Integer> record = new HashMap<>();
        for (CompetitionMember cm : members) {

            ArrayList<SwimmingResult> currentSwimmersResults = cm.getResults();
            for (SwimmingResult sr : currentSwimmersResults) {

                if (sr.getDiscipline().equals(disciplin1)) {
                    record.put(sr.getTime(), cm.getMemberNr());
                    storedTimes.add(sr.getTime());

                }

            }
        }
        Collections.sort(storedTimes);
        for (int i = 0; i < storedTimes.size(); i++) {

            for (CompetitionMember cm : members) {
                if (cm.getMemberNr() == record.get(storedTimes.get(i)) && cm.getAge() > 18) {
                    bestPerformingMembersSenior.add(cm);
                } else if (cm.getMemberNr() == record.get(storedTimes.get(i)) && cm.getAge() <= 18) {
                    bestPerformingMembersJunior.add(cm);
                }
            }
        }

        if(agegroup==1){
            int length = (bestPerformingMembersSenior.size()<5)? bestPerformingMembersSenior.size() : 5;  // True : false
            if(bestPerformingMembersSenior.isEmpty()){
                System.out.println("No results found");
            }else{System.out.println("Here are the best 5 swimmers for the chosen disciplin and agegroup:\n");
                for(int j =0; j<length;j++) {
                    CompetitionMember currentMember= bestPerformingMembersSenior.get(j);
                    int time = 0;
                    for(SwimmingResult swr:currentMember.getResults()){
                        if(swr.getDiscipline()==disciplin1){
                            time=swr.getTime();
                        }
                    }

                    System.out.println("Navn: "+currentMember.getName()+"\n"+
                            "Medlemsnummer: "+currentMember.getMemberNr()+ "\n"+
                            "time: "+time+"ms\n");
                }
            }
        }
        else {
            int length = (bestPerformingMembersJunior.size()<5)? bestPerformingMembersJunior.size() : 5;  // True : false
            if(bestPerformingMembersJunior.isEmpty()){
                System.out.println("No results found");
            }
            else {
                System.out.println("Here are the best 5 swimmers for the chosen disciplin and agegroup:\n");
                for(int k=0; k<length;k++){
                    CompetitionMember currentMember= bestPerformingMembersJunior.get(k);
                    int time = 0;
                    for(SwimmingResult swr:currentMember.getResults()){
                        if(swr.getDiscipline()==disciplin1){
                            time=swr.getTime();
                        }
                    }

                    System.out.println("Navn: "+currentMember.getName()+"\n"+
                            "Medlemsnummer: "+currentMember.getMemberNr()+ "\n"+
                            "time: "+time+"ms\n");
                }
            }
        }
    }


    //___________MEMBER MENU___________________
    // Create a new member for the Member list
    public static void createMember(Scanner input, ArrayList<Member> members, ArrayList <Contingent> contingens) throws IOException {
        // Name input
        System.out.println("Enter name:");
        String newName = input.nextLine();

        // Date of Birth input
        //System.out.println("Enter date of birth in the form year-month-day:");
        //LocalDate dateOfBirth = LocalDate.parse(input.next());
        LocalDate dateOfBirth = Util.LocalDateInput();
        //input.nextLine(); // Consume the newLine

        // Address input
        System.out.println("Enter address:");
        String newAddress = input.nextLine();

        // Phone nr input
        System.out.println("Enter phone number:");
        String newPhoneNumber = input.nextLine();

        // Active or passive input
        System.out.println("Is the member active? (Yes/No):");
        boolean newAktiv = false;
        while (true) {
            String aktivInput = input.nextLine().trim();
            if (aktivInput.equalsIgnoreCase("yes")) {
                newAktiv = true;
                break;
            } else if (aktivInput.equalsIgnoreCase("no")) {
                break;
            } else {
                System.out.println("Invalid input. Please give me a 'Yes' or a 'no':");
            }
        }

        // Getting the new member a generated number.
        System.out.println("The member is getting an number...");

        int newMemberNr = Util.createMemberShipNumber(members);
        System.out.println("The new member got the number: " + newMemberNr);

        // Contingent of the member.
        System.out.println("The membership fees of the new member are: "); //metode der tildeler kontingenten (medlem's fees: Linas metode eller spørg i gruppen)
        // Finding out the age of the new member
        LocalDate now = LocalDate.now();
        int age = Period.between(dateOfBirth, now).getYears();
        //Give the member a contingent based of their age and membership status.
         /*”For aktive medlemmer er kontingentet for ungdomssvømmere (under 18 år)
             1000 kr. årligt, for seniorsvømmere (18 år og over) 1600 kr. årligt.
              For medlemmer over 60 år gives der 25 % rabat af seniortaksten.
              For passivt medlemskab er taksten 500 kr. årligt”*/
        double newKontingent = 0.0;

        if (!newAktiv) {
            System.out.println("Passive Membership for all age: 500 DKK per year.");
            newKontingent = 500;
        } else if (newAktiv) {
            if (age > 60) {
                System.out.println("Active Membership for the pensions over 60 years old: 1200 DKK per year.");
                newKontingent = 1600 * 0.75;
            } else if (18 <= age && age < 60) {
                System.out.println("Active Membership for Seniors between 18-59 years old: 1600 DKK per year.");
                newKontingent = 1600;
            } else if (18 > age) {
                System.out.println("Active Membership for juniors below 18 years old: 1000 DDK per year.");
                newKontingent = 1000;
            } else {
                System.out.println("Unable to determine membership type. Using default contingent.");
            }
        }
        Contingent newContigent = new Contingent(newMemberNr,LocalDate.now(), LocalDate.now().plusDays(90), false, newKontingent);

        Member newMember = new Member(newName, newPhoneNumber, newAddress, dateOfBirth, newMemberNr, newKontingent, newAktiv);
        members.add(newMember); // Added to the ArrayList in main_package.Main.


        Contingent newArrearsCheck = new Contingent(newMemberNr, LocalDate.now(),LocalDate.now().plusDays(90),false,0.0);
        contingens.add(newArrearsCheck);

        Filehandler.writeToFileMember(members);
        Filehandler.writeToFileContingent(contingens);

        System.out.println("New person created and saved successfully.");


    }//end of create member

    // Print the Member list to the output.
    public static void printTheMemberList(ArrayList<Member> members) {
        if (members.isEmpty()) {
            System.out.println("No people found.");
            return;
        }

        for (Member member : members) {
            System.out.println(member);
        }
        System.out.println("___________________________________________");
    }

    // print the member list in a numbered list for edit and delete members-methods.
    public static void printNumberedMemberNames(ArrayList<Member> members) {
        for (int i = 0; i < members.size(); i++) {
            System.out.println((i + 1) + ". " + members.get(i).getName());
        }
    }

    // Edit one of the members in the member list
    public static void editMember(Scanner input, ArrayList<Member> members) throws IOException {
        printNumberedMemberNames(members);

        System.out.println("Enter the number of the person you want to edit:");
        int memberNumber = input.nextInt();
        input.nextLine(); // consume the newline

        if (memberNumber < 1 || memberNumber > members.size()) {
            System.out.println("Invalid member number");
            return;
        }

        Member memberToEdit = members.get(memberNumber - 1);

        // display full information of the selected member
        System.out.println("You have selected the following member:");
        System.out.println(memberToEdit + "\n___________________________________________");

        System.out.println("""
                Which information do you want to change on the chosen person?
                1. Name
                2. Date Of Birth
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
                System.out.println("Name updated successfully.\n");
                break;
            case 2:
                //System.out.println("Enter new date of birth in the form year-month-day:");
                //LocalDate newdateOfBirth = LocalDate.parse(input.next());
                LocalDate newdateOfBirth = Util.LocalDateInput();
                memberToEdit.setDateOfBirth(newdateOfBirth);
                //input.nextLine(); // consume the newline
                System.out.println("Date of birth updated successfully.\n");
                break;
            case 3:
                System.out.println("Enter new address:");
                memberToEdit.setAddress(input.nextLine());
                System.out.println("Address updated successfully.\n");
                break;
            case 4:
                System.out.println("Enter new Phone number:");
                memberToEdit.setPhoneNumber(input.nextLine());
                System.out.println("Phone number updated successfully.\n");
                break;
            case 5:
                System.out.println("Is the member active? (true/false):");
                memberToEdit.setAktiv(input.nextBoolean());
                input.nextLine(); // Consume the newline
                System.out.println("Active status updated successfully.\n");
                break;
            case 6:
                System.out.println("Edit cancelled");
                return;
            default:
                System.out.println("Invalid choice, choose only a number between 1 and 6.\n");
        }
        Filehandler.writeToFileMember(members);
    }

    // delete one of the members in the member list
    public static void deleteMember(Scanner input, ArrayList<Member> members) throws IOException {
        printNumberedMemberNames(members);

        System.out.println("Enter the number of the person you want to delete:");
        int memberNumber = input.nextInt();
        input.nextLine(); // consume the newline

        if (memberNumber < 1 || memberNumber > members.size()) {
            System.out.println("Invalid person number.");
            return;
        }

        Member memberToDelete = members.remove(memberNumber - 1);
        System.out.println(memberToDelete.getName() + " deleted successfully.");
        Filehandler.writeToFileMember(members);
    }

    //____________EMPLOYEE MENU__________________
    // Create a new employee for the Employee list
    public static void createEmployee(Scanner input, ArrayList<Employee> employees) throws IOException {
        // Name input
        System.out.println("Enter name:");
        String newName = input.nextLine();

        // Date of Birth input
        //System.out.println("Enter date of birth in the form year-month-day:");
        //LocalDate dateOfBirth = LocalDate.parse(input.next());
        //input.nextLine(); // Consume the newLine
        LocalDate dateOfBirth = Util.LocalDateInput();

        // Address input
        System.out.println("Enter address:");
        String newAdress = input.nextLine();

        // Phone nr input
        System.out.println("Enter phone number:");
        String newPhoneNumber = input.nextLine();

        // Access group input
        System.out.println("""
                Choose a group for the employee to work in:
                1. Management
                2. Cashier
                3. Trainer
                """);
        int newAccesGroup = input.nextInt();
        input.nextLine(); // Consume the newLine

        // Username input
        System.out.println("Enter a username for the new employee");
        String newUsername = input.nextLine();

        // Password input
        System.out.println("Enter Password for the new employee");
        String newPassword = input.nextLine();

        // Jobtitel choice
        //Kun formand, træner, kasser? Er der andre?
        System.out.println("Choose which job titel to give to the new employee");
        String newJobtitle = input.nextLine();

        Employee newEmployee = new Employee(newName, newPhoneNumber, newAdress, dateOfBirth, newAccesGroup, newPassword, newJobtitle, newUsername);
        employees.add(newEmployee); // Added to the ArrayList in main_package.Main.

        Filehandler.writeToFileEmployee(employees);
        System.out.println("New person created and saved successfully.\n");
    }

    // Print the Employee list to the output.
    public static void printTheEmployeeList(ArrayList<Employee> employees) {
        if (employees.isEmpty()) {
            System.out.println("No people found.");
            return;
        }

        for (Employee employee : employees) {
            System.out.println(employee);
        }
        System.out.println("___________________________________________");
    }

    // Print the Employee list in a numbered list for edit and delete employee-methods
    public static void printNumberedEmployeeNames(ArrayList<Employee> employees) {
        for (int i = 0; i < employees.size(); i++) {
            System.out.println((i + 1) + ". " + employees.get(i).getName());
        }
    }

    // Edit one of the employee in the employee list
    public static void editEmployee(Scanner input, ArrayList<Employee> employees) throws IOException {
        printNumberedEmployeeNames(employees);

        // choose one employee to edit
        System.out.println("Enter the number of the person you want to edit:");
        int employeeNumber = input.nextInt();
        input.nextLine(); // consume the newline

        if (employeeNumber < 1 || employeeNumber > employees.size()) {
            System.out.println("Invalid member number");
            return;
        }

        Employee employeeToEdit = employees.get(employeeNumber - 1);

        // display full information of the selected member
        System.out.println("You have selected the following member:");
        System.out.println(employeeToEdit + "\n___________________________________________");

        System.out.println("""
                Which information do you want to change on the chosen employee?
                1. Name
                2. Date Of Birth
                3. Address
                4. Phone Number
                5. UserName
                6. Password (Need the user's password to process)
                7. Job Titel
                8. Access Group
                9. Cancel
                """);

        int attributeChoice = input.nextInt();
        input.nextLine(); // Consume the newline

        switch (attributeChoice) {
            case 1:
                System.out.println("Enter new name:");
                employeeToEdit.setName(input.nextLine());
                System.out.println("Name updated successfully.");
                break;
            case 2:
                //System.out.println("Enter new date of birth in the form year-month-day:");
                //LocalDate newdateOfBirth = LocalDate.parse(input.next());
                LocalDate newdateOfBirth = Util.LocalDateInput();
                employeeToEdit.setDateOfBirth(newdateOfBirth);
                //input.nextLine(); // consume the newline
                System.out.println("Date of birth updated successfully.");
                break;
            case 3:
                System.out.println("Enter new address:");
                employeeToEdit.setAddress(input.nextLine());
                System.out.println("Address updated successfully.");
                break;
            case 4:
                System.out.println("Enter new Phone number:");
                employeeToEdit.setPhoneNumber(input.nextLine());
                System.out.println("Phone number updated successfully.");
                break;
            case 5:
                System.out.println("Enter new Username:");
                employeeToEdit.setUsername(input.nextLine());
                System.out.println("Username updated successfully");
                break;
            case 6:
                System.out.println("Please enter the password to process:");
                String currentPassword = input.nextLine();
                if (currentPassword.equals(employeeToEdit.getPassword())) {
                    System.out.println("Enter new password:");
                    employeeToEdit.setPassword(input.nextLine());
                    System.out.println("Password updated successfully");
                } else {
                    System.out.println("Incorrect password. Unable to process");
                }
                break;
            case 7:
                System.out.println("Enter new job title:");
                employeeToEdit.setJobtitle(input.nextLine());
                System.out.println("Job title updated successfully.");
                break;
            case 8:
                System.out.println("""
                        Choose a new access group for the employee:
                        1. Management
                        2. Cashier
                        3. Trainer
                        """);
                int newAccessGroup = input.nextInt();
                input.nextLine(); // Consume the newline
                employeeToEdit.setAccesGroup(newAccessGroup);
                System.out.println("Access group updated successfully.");
                break;
            case 9:
                System.out.println("Edit cancelled");
                return;
            default:
                System.out.println("Invalid choice");
        }
        Filehandler.writeToFileEmployee(employees);
    }

    // Delete one of the employee in the employee list
    public static void deleteEmployee(Scanner input, ArrayList<Employee> employees) {
        printNumberedEmployeeNames(employees);

        System.out.println("Enter the number of the employee you want to delete:");
        int employeeNumber = input.nextInt();
        input.nextLine();

        if (employeeNumber < 1 || employeeNumber > employees.size()) {
            System.out.println("Invalid employee number.");
            return;
        }

        Employee employeeToDelete = employees.remove(employeeNumber - 1);
        System.out.println(employeeToDelete.getName() + " deleted successfully.");
        try {
            Filehandler.writeToFileEmployee(employees);
        } catch (IOException e) {
            System.out.println("Error saving changes");
        }
    }

    //__________________SwimResults_________________
    public static void registrerSwimResult(ArrayList<SwimmingResult> swimmingResults) throws IOException {
        Scanner scan = new Scanner(System.in);
        int memberNb = intPrompt("Enter the swimmer ID: ");
        String discipline = stringPrompt("Enter the discipline: ");
        SwimmingDisciplin disciplin = SwimmingDisciplin.valueOf(discipline.toUpperCase());
        int newTime = Integer.MAX_VALUE;
        LocalDate date = LocalDate.now();

        //forloop to search if the swimmer with this ID+disciplin already has an old time
        SwimmingResult resultExist = null;
        int j = 0;
        for (SwimmingResult swR : swimmingResults) {
            if ((swR.getMemberNr() == memberNb) && (swR.getDiscipline().equals(disciplin))) {
                resultExist = swR;
                j = 1;
                System.out.println("match found: the swimmer you are searching for is found: \n" + swR);
                newTime = intPrompt("Enter the swimmer new time: ");
                date = LocalDateInput();
                break;
            }
        }//end for

        if (j == 1) { //once the swimmer is found, we will compare the 2 times
            if (newTime < resultExist.getTime()) {
                resultExist.setTime(newTime);
                resultExist.setDateOfResult(date);
                System.out.println("the swimmer is updated with the new time and date");
            } else {
                System.out.println("the old time was better, the swimmer will not be updated");
            }
        } else {
            System.out.println("No matches found for the swimmer and disciplin you are looking for," +
                    "Do you want to add it? Yes / No");
            String svar = scan.nextLine();
            if (Util.containsIgnoreCase(svar, "yes")) {
                int time = intPrompt("Enter the swimmer's time: ");
                LocalDate dateOfResult = Util.LocalDateInput();
                SwimmingResult newSwimmingResult = new SwimmingResult(memberNb, disciplin, time, dateOfResult);
                swimmingResults.add(newSwimmingResult);
                System.out.println("the new swimming result is successfully added.");
            }

        }
        Filehandler.writeToFileSwimmingResult(swimmingResults);

    }//end of registrerSwimResult

    public static void seSwimmerResultAllDisciplines(ArrayList<Member> members, ArrayList<SwimmingResult> swimmingResults) {
        String name = Util.stringPrompt("what is the name of the swimmer you want to se results? ");
        ArrayList<Member> listOFSearchedMembers = new ArrayList<>();
        int test2 = 0;
        for (Member member : members) {
            if (Util.containsIgnoreCase(member.getName(), name)) {
                listOFSearchedMembers.add(member);
                test2 = 1;
            }
        }//end for
        if (test2 == 0) {
            System.out.println("No member with this name found \n");
        } else {
            System.out.println("here is a list of the members who have the same name you searched:");
            for (Member member : listOFSearchedMembers) {
                System.out.println("Member nr. " + member.getMemberNr() + " : " + member.getName() + "\n");
            }
            int numberToSee = Util.intPrompt("write the number of the member you want to se the results?\n");
            //Member memberToSee=null;
            String nameToSee = "";
            for (Member member : members) {
                if (member.getMemberNr() == numberToSee) {
                    //memberToSee=member;
                    nameToSee = member.getName();
                }
            }
            int test = 0;
            //SwimmingResult swimmingResultTest = null;
            ArrayList<SwimmingResult> swimmingResultsDisciplin = new ArrayList<>();
            for (SwimmingResult swR : swimmingResults) {
                if (numberToSee == swR.getMemberNr()) {
                    test = 1;
                    swimmingResultsDisciplin.add(swR);
                }
            }
            if (test == 1) {
                System.out.println("Here is a list of the results of the member: " + nameToSee + " , in all disciplines he/she participated:");

                for (SwimmingResult swR : swimmingResultsDisciplin) {
                    System.out.println("Name of the discipline: " + swR.getDiscipline() + "\n----------resul: " + swR.getTime() + "\n----------date of the resul: " + swR.getDateOfResult() + "\n");
                }
            } else {
                System.out.println("No swimming results found to the chosen member.\n");
            }
        }

    }//end of seSwimmerResultAllDisciplines

    public static ArrayList<CompetitionMember> pairMemberWithResults(ArrayList<Member> members, ArrayList<SwimmingResult> results) {

        ArrayList<CompetitionMember> compMembers = new ArrayList<>();
        SwimmingDisciplin discipline = null;
        for (Member m : members) {
            ArrayList<SwimmingResult> compMembersResults = new ArrayList<>();
            for (SwimmingResult swr : results) {
                if (m.getMemberNr() == swr.getMemberNr()) {
                    compMembersResults.add(swr);
                }
            }
            if (!compMembersResults.isEmpty()) {
                CompetitionMember compMember = new CompetitionMember(m.getName(), m.getPhoneNumber(), m.getAddress(), m.getDateOfBirth(), m.getMemberNr(), m.getContingent(), m.getAktiv(), compMembersResults);
                compMembers.add(compMember);

            }
        }
        return compMembers;
    }

    public static void viewAllMembers (ArrayList<Member> members, ArrayList<CompetitionMember> competitionMembers){
        Scanner scan = new Scanner(System.in);

        System.out.println("Do you want to look for a specific member type? yes/no");
        String choice = scan.nextLine();

        if (choice.equalsIgnoreCase("No")){
            for (Member member : members){
                System.out.println(member.shortPrint());
            }
        } else if (choice.equalsIgnoreCase("Yes")){
            System.out.println("Enter the membership type to filter: \n" +
                    "Junior\n" +
                    "Senior\n" +
                    "60+\n" +
                    "Competitive Swimmer\n"
            );
            String type = scan.nextLine();

            if (type.equalsIgnoreCase("Competitive Swimmer")){
                for (CompetitionMember competitionMember : competitionMembers){
                    System.out.println(competitionMember.shortPrint());
                }
            }

            for (Member member : members) {
                if (type.equalsIgnoreCase("Junior") && member.getAge() < 18){
                    System.out.println(member.shortPrint());
                } else if (type.equalsIgnoreCase("Senior") && member.getAge() >= 18 && member.getAge()< 60){
                    System.out.println(member.shortPrint());
                } else if (type.equalsIgnoreCase("60+") && member.getAge() >= 60){
                    System.out.println(member.shortPrint());
                }
            }
        }
    }

}//end of PersonMethods Class

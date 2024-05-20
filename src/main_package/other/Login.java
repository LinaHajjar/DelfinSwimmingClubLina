package main_package.other;

import main_package.people.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Login {
    ArrayList<Employee> users;
    // create hashmap
    HashMap<String,String> logininfo = new HashMap<String,String>();


    public Login(ArrayList<Employee> users){
        this.users=users;
    }

    public HashMap<String,String> makeLogins(){
        for(Employee user:users){
            //if (user instanceof employee){
            logininfo.put(user.getUsername(), user.getPassword());
            //}
        }
        return logininfo;
    }

    public static Employee attemptLogin(HashMap<String,String> logininfo,ArrayList<Employee>users){
        Scanner scan = new Scanner(System.in);
        String userName;
        //prompt for data
        do{
            System.out.println("Enter username: ");
            userName = scan.nextLine();

            //test if userName exists
            if(userExists(userName,logininfo)) {
                System.out.println("User exists");
                break;
            }
            System.out.println("User does not exist, try again");
        }while(!userExists(userName, logininfo));
        Employee currentUser;
        int loginAttempts=0;
        do {
            if (loginAttempts > 0) {
                System.out.println("Wrong attempts: "+loginAttempts);
            }
            System.out.println("Enter password: ");
            String passWord = scan.nextLine();
            currentUser = loginSucces(userName, passWord, logininfo,users);
            loginAttempts++;

        }while(loginAttempts<3 && currentUser==null);
        return currentUser;

    }


    public static Employee loginSucces(String userName,String enteredPassword,HashMap<String,String>logininfo,ArrayList<Employee>users){
        Employee currentUser=null;
        if(userExists(userName,logininfo)){
            if(logininfo.get(userName).equals(enteredPassword)){
                currentUser=getUser(userName,users);
                System.out.println("Successfully logged in");
                System.out.println("Welcome "+currentUser.getName());
                return currentUser;
            }
        }
        System.out.println("Wrong password, try again");
        return null;
    }

    public static boolean userExists(String userName, HashMap<String,String>logininfo){
        if(logininfo.containsKey(userName)){
            return true;
        }
        return false;
    }

    public static Employee getUser(String userName,ArrayList<Employee>users){
        for(Employee user:users){
            if(user.getUsername().equals(userName)){
                return user;
            }

        }
        return null;
    }

}
package main_package.people;

import java.time.LocalDate;

public class Employee extends Person {

    private int accesGroup;
    private String password;
    private String jobtitle;
    private String username;
    public Employee(String name, String phoneNumber, String adress, LocalDate dateOfBirth, int accesGroup, String password, String jobtitle, String username) {
        super(name, phoneNumber, adress, dateOfBirth);
        this.accesGroup = accesGroup;
        this.password = password;
        this.jobtitle = jobtitle;
        this.username = username;
    }

    public int getAccesGroup() {
        return accesGroup;
    }

    public void setAccesGroup(int accesGroup) {
        this.accesGroup = accesGroup;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    // Bare en anden eksempel.
    /*public String toString() {
        return super.toString() +
               "Access Group: " + accesGroup + "\n" +
               "Password: Nothing to show. Private" + "\n" +
               "Jobtitel: " + jobtitle + "\n" +
               "Username: " + username;
    }*/
}

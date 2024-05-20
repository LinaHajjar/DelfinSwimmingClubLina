package main_package.people;

import java.time.LocalDate;
import java.time.Period;

public class Person {

    private String name;
    private String phoneNumber;
    private String address;
    //private int age;

    private LocalDate dateOfBirth;
    public Person(String name, String phoneNumber, String address, LocalDate dateOfBirth){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /*public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }*/

    private int calculateAge () { // private because to be used only in this class
        LocalDate currentDate=LocalDate.now();
        Period period = Period.between(dateOfBirth, currentDate);
        return (period.getYears());
    }

    public int getAge(){
        return calculateAge();
    }




    @Override
    public String toString() {
        return  "Name         : " + name + "\n" +
                "Date of birth: " +dateOfBirth + "\n"+
                "Age          : " + getAge() + "\n" +
                "Address      : " + address + "\n" +
                "Phone number : " + phoneNumber;
    }
}

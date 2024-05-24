package main_package.people;

import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class Person {

    private String name;
    private String phoneNumber;
    private String address;
    private LocalDate dateOfBirth;
    //private int age;

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

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private int calculateAge () {
        LocalDate currentDate=LocalDate.now();
        Period period = Period.between(dateOfBirth, currentDate);
        return (period.getYears());
    }

    public int getAge(){
        return calculateAge();
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    @Override
    public String toString() {
        return "___________________________________________" + "\n" +
                "Name         : " + name + "\n" +
                "Date of birth: " + dateOfBirth + "\n" +
                "Address      : " + address + "\n" +
                "Phone number : " + phoneNumber + "\n";
    }
}

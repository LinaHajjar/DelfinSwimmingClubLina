package main_package.people;

import main_package.other.Contingent;

import java.time.LocalDate;

public class Member extends Person {
    private int memberNr;
    private double contingent;
    private boolean aktiv;


    public Member(String name, String phoneNumber, String address, LocalDate dateOfBirth, int memberNr, double contingent, boolean aktiv) {
        super(name, phoneNumber, address, dateOfBirth);
        this.memberNr = memberNr;
        this.contingent = contingent;
        this.aktiv = aktiv;
    }

    public int getMemberNr() {
        return memberNr;
    }

    public void setMemberNr(int memberNr) {
        this.memberNr = memberNr;
    }

    public double getContingent() {
        return contingent;
    }
    public boolean getAktiv() {
        return aktiv;
    }

    public boolean isAktiv() {
        return aktiv;
    }

    public void setAktiv(boolean aktiv) {
        this.aktiv = aktiv;
    }

    public String toString() {
        String aktivString = aktiv? "Aktivt medlem" : "Passivt medlem";  // True : false
        return super.toString() +
                "Member Number: " + memberNr + "\n" +
                "Contingent   : " + contingent + "\n" +
                aktivString;
    }

    public String shortPrint() {

        return "Member name: " + super.getName() + "\n" +
                "Member age: " + super.getAge() + "\n" +
                "Member Number: " + memberNr + "\n";
    }

    public String toPrint() {
        return (getName() + "," + getPhoneNumber()+ "," + getAddress() + "," + getDateOfBirth() + "," + memberNr + "," + getContingent() +"," + aktiv + "\n");
    }
}

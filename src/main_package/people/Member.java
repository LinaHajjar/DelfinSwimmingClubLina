package main_package.people;

import main_package.other.Filehandler;

import java.time.LocalDate;
import java.util.ArrayList;

public class Member extends Person {
    private int memberNr;
    private double kontingent;
    private boolean aktiv;

    ArrayList<Member> members = new ArrayList<>();
    public Member(String name, String phoneNumber, String address, LocalDate dateOfBirth, int memberNr, double kontingent, boolean aktiv) {
        super(name, phoneNumber, address, dateOfBirth);
        this.memberNr = memberNr;
        this.kontingent = kontingent;
        this.aktiv = aktiv;
    }

    public int getMemberNr() {
        return memberNr;
    }

    public void setMemberNr(int memberNr) {
        this.memberNr = memberNr;
    }

    public double getKontingent() {
        return kontingent;
    }

    public void setKontingent(double kontingent) {
        this.kontingent = kontingent;
    }

    public boolean isAktiv() {
        return aktiv;
    }

    public void setAktiv(boolean aktiv) {
        this.aktiv = aktiv;
    }

    public String toString() {
        String aktivString = aktiv? "Aktivt medlem" : "Passivt medlem";  // True : false
        return super.toString() + "\n" +
                "Member number: " + memberNr+ "\n" +
                "Kontingent   : " + kontingent + "\n" +
                aktivString + "\n";
    }
    public String toPrint() {
        return (getName() + "," + getPhoneNumber()+ "," + getAddress() + "," + getDateOfBirth() + "," + memberNr + "," + kontingent + "," + aktiv+"\n");
    }

    public static int calculateMemberNr(ArrayList<Member> members){
        int MemberNr = 0;
        for(Member member: members){
            if(member.getMemberNr()>MemberNr){
                MemberNr= member.getMemberNr();
            }
        }
        MemberNr+=1;
        return (MemberNr);
    }

}

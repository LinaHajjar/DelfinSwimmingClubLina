package main_package.other;

import java.time.LocalDate;

public class Contingent {

    private int memberNr;
    private LocalDate dateOfPayment; //I DAG
    private LocalDate nextpayment; //+90 dage
    private boolean arrears; //restance // boolean til om de er i restance eller ej
    private double debt;

    public Contingent(int memberNr, LocalDate dateOfPayment, LocalDate nextpayment, boolean arrears, double debt) {
        this.memberNr = memberNr;
        this.dateOfPayment = dateOfPayment;
        this.nextpayment = nextpayment;
        this.arrears = arrears;
        this.debt = debt;
    }

    public String toPrint() {
        return (getMemberNr() + "," + getDateOfPayment()+ "," + getNextPayment() + "," + getArrears() + "," + getDebt() + "\n");
    }

    public int getMemberNr(){
        return  this.memberNr;
    }

    public LocalDate getNextPayment(){
        return this.nextpayment;
    }

    public double getDebt(){
        return this.debt;
    }

    public double setDebt(double debt) {
        return this.debt = debt;
    }

    public LocalDate getDateOfPayment() {
        return this.dateOfPayment;
    }

    public LocalDate setDateOfPayment(LocalDate today) {
        return this.dateOfPayment = today;
    }

    public LocalDate setNextPayment(LocalDate in90days) {
        return this.nextpayment = in90days;
    }

    public boolean getArrears() {
        return this.arrears;
    }

    public boolean setArrears(boolean arrears) {
        return this.arrears = arrears;
    }

    @Override
    public String toString() {
        return "Membership Number: " + memberNr + "\n" +
                "First payment: " + dateOfPayment + "\n" +
                "Next payment: " + nextpayment + "\n" +
                "Is in arrears: " + arrears + "\n" +
                "Total debt: " + debt;

    }
}

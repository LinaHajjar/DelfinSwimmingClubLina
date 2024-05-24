package main_package.people;

import main_package.people.SwimmingDisciplin;

import java.time.LocalDate;
import java.util.ArrayList;

import static main_package.other.Util.*;

public class SwimmingResult {

    private int memberNr;
    private SwimmingDisciplin swimmingDiscipline;
    private int time;
    private LocalDate dateOfResult;
    private String CompetitionName;

    public SwimmingResult(int memberNr, SwimmingDisciplin swimmingDiscipline, int time, LocalDate dateOfResult) {
        this.memberNr=memberNr;
        this.swimmingDiscipline = swimmingDiscipline;
        this.time = time;
        this.dateOfResult = dateOfResult;
    }

    public int getMemberNr() {
        return memberNr;
    }

    public void setMemberNr(int memberNr) {
        this.memberNr = memberNr;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public LocalDate getDateOfResult() {
        return dateOfResult;
    }

    public void setDateOfResult(LocalDate dateOfResult) {
        this.dateOfResult = dateOfResult;
    }

    public void setSwimmingDiscipline(SwimmingDisciplin swimmingDiscipline) {
        this.swimmingDiscipline = swimmingDiscipline;
    }

    public String toPrint (){
        return (getMemberNr()+ " ; " +getDiscipline() + " ; "+time + " ; " + dateOfResult +" ;\n");
    }

    public String toString(){
        return ("Number of the swimmer: " + memberNr+
                "\nDisciplin            : " + swimmingDiscipline+
                "\nTime                 : " + time+
                "\nDate of the result   : "+dateOfResult +"\n");
    }

    public SwimmingDisciplin getDiscipline(){
        return swimmingDiscipline;
    }

    public int getTime(){
        return time;
    }

}



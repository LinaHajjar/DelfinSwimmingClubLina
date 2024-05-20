package main_package.people;

import main_package.other.Filehandler;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import static main_package.other.Util.*;

public class SwimmingResult {

        private int memberNr;
        private SwimmingDisciplin swimmingDiscipline;
        private int time;
        private LocalDate dateOfResult;

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

    public SwimmingDisciplin getDiscipline(){
            return swimmingDiscipline;
        }
        public static void registrerSwimResult(ArrayList <SwimmingResult> swimmingResults)throws IOException {
            int memberNb= intPrompt("Enter the swimmer ID: ");
            String discipline = stringPrompt("Enter the discipline: ");
            SwimmingDisciplin disciplin=disciplin= SwimmingDisciplin.valueOf(discipline);
            int newTime=Integer.MAX_VALUE;
            LocalDate date=LocalDate.now();

            //forloop to search if the swimmer with this ID+disciplin already has an old time
            SwimmingResult resultExist=null;
            int j=0;
            for (SwimmingResult swR: swimmingResults){
                if ((swR.getMemberNr()==memberNb)&&(swR.getDiscipline().equals(disciplin))){
                    resultExist=swR;
                    j=1;
                    System.out.println("match found: the swimmer you are searching for is found: " + swR);
                    newTime = intPrompt("Enter the swimmer new time: ");
                    date =LocalDateInput();
                    break;
                }
            }//end for

            if (j==1){ //once the swimmer is found, we will compare the 2 times
                if (newTime< resultExist.getTime()){
                    resultExist.setTime(newTime);
                    resultExist.setDateOfResult(date);
                    System.out.println("the swimmer is updated with the new time and date");
                } else {
                    System.out.println("the old time was better, the swimmer will not be updated");
                }
            } else {
                System.out.println("No matches found for the swimmer and disciplin you are looking for");
            }
            Filehandler.writeToFileSwimmingResult(swimmingResults);

        }//end of registrerSwimResult

    public int getTime(){
            return time;
        }

        public String toString(){
            return ("Number of the swimmer: " + memberNr+
                    "\nDisciplin            : " + swimmingDiscipline+
                    "\nTime                 : " + time+
                    "\nDate of the result   : "+dateOfResult +"\n");

        }

        public String toPrint(){
            return (memberNr + " ; "+ swimmingDiscipline + " ; " + time + " ; " + dateOfResult + " ;\n");
        }

    }



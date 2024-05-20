package main_package.people;

public class Disciplin {
    private SwimmingDisciplin disciplin;
    private int bestTime;
    private String competitionName;

    public Disciplin(SwimmingDisciplin disciplin, int bestTime, String competitionName ){
        this.disciplin=disciplin;
        this.bestTime=bestTime;
        this.competitionName=competitionName;
    }

    public SwimmingDisciplin getDisciplin() {
        return disciplin;
    }

    public void setDisciplin(SwimmingDisciplin disciplin) {
        this.disciplin = disciplin;
    }

    public int getBestTime() {
        return bestTime;
    }

    public void setBestTime(int bestTime) {
        this.bestTime = bestTime;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }
}

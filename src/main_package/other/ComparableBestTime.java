package main_package.other;

import main_package.people.CompetitionMember;
import main_package.people.SwimmingDisciplin;
import main_package.people.SwimmingResult;

import java.util.Comparator;

public class ComparableBestTime implements Comparator<CompetitionMember> {
    SwimmingDisciplin disciplin;

    public int compare(CompetitionMember member1, CompetitionMember member2) {
        SwimmingResult result1 = member1.returnBestResultsByDisciplin(disciplin);
        SwimmingResult result2 = member2.returnBestResultsByDisciplin(disciplin);

        if (result1 == null && result2 == null) {
            return 0;
        } else if (result1 == null) {
            return 1;
        } else if (result2 == null) {
            return -1;
        } else {
            return Double.compare(result1.getTime(), result2.getTime());
        }
    }
}

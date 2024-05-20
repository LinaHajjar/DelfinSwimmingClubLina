package main_package.other;

import main_package.people.Disciplin;

import java.util.Comparator;


public class ComparableDisciplines implements Comparator<Disciplin> {
    public int compare(Disciplin a, Disciplin b) {
        int diff = a.getBestTime() - b.getBestTime();
        if (diff == 0) {
            // Both are equal
            return 0;
        } else if (diff < 0) {
            // obj1 < obj2
            return -1;
        } else {
            // obj1 > obj2
            return 1;
        }
    }
}
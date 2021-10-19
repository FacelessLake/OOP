package ru.nsu.belozerov;

import java.util.ArrayList;
import java.util.List;

public class RecordBook {

    public enum Marks {
        Excellent(5),
        Good(4),
        Satisfactory(3),
        Poor(2),
        Credit(0);

        private int gradeNum;

        Marks(int mark) {
            gradeNum = mark;
        }

        public int getMark() {
            return gradeNum;
        }
    }

    private List<Marks> grades;
    private Marks qualiffTask;

    public RecordBook() {
        grades = new ArrayList<>();
        qualiffTask = Marks.Poor;
    }

    public void addMark(Marks mark) {
        grades.add(mark);
    }

    public void setQualiffTask(Marks qualiffTask) {
        this.qualiffTask = qualiffTask;
    }

    public double average() {
        int iter;
        int cnt = 0;
        double avg = 0;
        for (Marks num : grades) {
            iter = num.getMark();
            if (iter > 0) {
                avg += iter;
                cnt++;
            }
        }
        return avg / cnt;
    }

    public boolean redDiploma() {
        if (grades.contains(Marks.Satisfactory) || grades.contains((Marks.Poor)))
            return false;
        if (average() < 4.75)
            return false;
        return qualiffTask == Marks.Excellent;
    }
}

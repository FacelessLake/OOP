package ru.nsu.belozerov;

import java.util.HashMap;

public class RecordBook {

    public enum Marks {
        Excellent(5),
        Good(4),
        Satisfactory(3),
        Poor(2),
        Passed(0);

        private final int gradeNum;

        Marks(int mark) {
            gradeNum = mark;
        }

        public int getMark() {
            return gradeNum;
        }
    }

    private final HashMap<String, Marks> grades;
    private Marks qualifTask;
    private final String NAME;
    private final String SURNAME;
    private final String PATRONYMIC;
    private final int GROUP;

    public RecordBook(String name, String surname, String patronymic, int group) {
        this.NAME = name;
        this.SURNAME = surname;
        this.PATRONYMIC = patronymic;
        this.GROUP = group;
        grades = new HashMap<>();
        qualifTask = Marks.Poor;

    }

    public void addMark(String subject, Marks mark) {
        grades.put(subject, mark);
    }

    public void setQualifTask(Marks qualifTask) {
        this.qualifTask = qualifTask;
    }

    public double average() {
        int iter;
        int cnt = 0;
        double avg = 0;
        for (Marks num : grades.values()) {
            iter = num.getMark();
            if (iter > 0) {
                avg += iter;
                cnt++;
            }
        }
        return avg / cnt;
    }

    public boolean redDiploma() {
        if (grades.containsValue(Marks.Satisfactory) || grades.containsValue(Marks.Poor))
            return false;
        if (average() < 4.75)
            return false;
        return qualifTask == Marks.Excellent;
    }

    public boolean scholarship() {
        return !grades.containsValue(Marks.Satisfactory) && !grades.containsValue(Marks.Poor);

    }

    public boolean heightenedScholarship() {
        int cnt = 0;
        for (Marks num : grades.values()) {
            if (num == Marks.Good)
                cnt++;
        }
        return scholarship() && cnt < 3;
    }

    public void showRecordBook() {
        StringBuilder str = new StringBuilder("Full name: ");
        str.append(NAME);
        str.append(" ");
        str.append(SURNAME);
        str.append(" ");
        str.append(PATRONYMIC);
        System.out.println(str);

        str = new StringBuilder("Group: ");
        str.append(GROUP);
        System.out.println(str);
        System.out.println("========================================================");
        grades.entrySet().stream().map(grade -> grade.getKey() + ": " + grade.getValue()).forEach(System.out::println);
        System.out.println("========================================================");
        str = new StringBuilder("Qualification task: ");
        str.append(qualifTask);
        System.out.println(str);
        System.out.println();
    }
}

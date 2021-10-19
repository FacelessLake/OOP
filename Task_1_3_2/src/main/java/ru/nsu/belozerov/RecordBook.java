package ru.nsu.belozerov;

import java.util.HashMap;

public class RecordBook {

    public enum Marks {
        Excellent(5),
        Good(4),
        Satisfactory(3),
        Poor(2),
        Credit(0);

        private final int gradeNum;

        Marks(int mark) {
            gradeNum = mark;
        }

        public int getMark() {
            return gradeNum;
        }
    }

    private HashMap<String, Marks> grades;
    private Marks qualiffTask;
    private String name;
    private String surname;
    private String patronymic;
    private int group;

    public RecordBook(String name, String surname, String patronymic, int group) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.group = group;
        grades = new HashMap<>();
        qualiffTask = Marks.Poor;
    }

    public void addMark(String subject, Marks mark) {
        grades.put(subject, mark);
    }

    public void setQualifTask(Marks qualifTask) {
        this.qualiffTask = qualifTask;
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
        if (grades.containsValue(Marks.Satisfactory) || grades.containsValue((Marks.Poor)))
            return false;
        if (average() < 4.75)
            return false;
        return qualiffTask == Marks.Excellent;
    }

    public boolean scholarship() {
        return !grades.containsValue(Marks.Satisfactory) && !grades.containsValue((Marks.Poor));

    }

    public void showRecordBook() {
        System.out.println("Full name: " + name + " " + surname + " " + patronymic);
        System.out.println("Group: " + group);
        System.out.println("========================================================");
        for (HashMap.Entry grade : grades.entrySet()) {
            System.out.println(grade.getKey() + ": " + grade.getValue());
        }
        System.out.println("========================================================");
        System.out.println("Qualification task: " + qualiffTask);
    }
}

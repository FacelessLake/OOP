package ru.nsu.belozerov;

import java.util.HashMap;

/**
 * This class allows you to create a record book for student.
 */
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

    private Marks qualifTask;
    private final String NAME;
    private final String SURNAME;
    private final String PATRONYMIC;
    private final int GROUP;
    private int semesterNum;

    @SuppressWarnings("unchecked")
    private final HashMap<String, Marks>[] semester = (HashMap<String, Marks>[]) new HashMap[9];

    public RecordBook(String name, String surname, String patronymic, int group, int semesterNum) {
        this.NAME = name;
        this.SURNAME = surname;
        this.PATRONYMIC = patronymic;
        this.GROUP = group;
        this.semesterNum = semesterNum;
        qualifTask = Marks.Poor;
        int i = 0;
        for (HashMap<String, Marks> grades : semester) {
            grades = new HashMap<>();
            semester[i] = grades;
            i++;
        }
    }

    /**
     * Allows you to add a new Semester to ypi record book
     * @param newSemester - new semester, which you want to change or work with
     */
    public void setNewSemester(int newSemester) {
        semesterNum = newSemester;
    }

    /**
     * Allows you to add new subject with a mark
     * @param subject - name of subject
     * @param mark - grade for this subject
     */
    public void addMark(String subject, Marks mark) {
        semester[semesterNum].put(subject, mark);
    }

    /**
     * Allows you to change the grade for the Qualification task
     * @param qualifTask - mark for the Qualification task that you want to set
     */
    public void setQualifTask(Marks qualifTask) {
        this.qualifTask = qualifTask;
    }

    public double average() {
        int iter;
        int cnt = 0;
        double avg = 0;
        for (Marks num : semester[semesterNum].values()) {
            iter = num.getMark();
            if (iter > 0) {
                avg += iter;
                cnt++;
            }
        }
        return avg / cnt;
    }

    public boolean redDiploma() {
        if (semester[semesterNum].containsValue(Marks.Satisfactory) || semester[semesterNum].containsValue(Marks.Poor))
            return false;
        if (average() < 4.75)
            return false;
        return qualifTask == Marks.Excellent;
    }

    public boolean scholarship() {
        return !semester[semesterNum].containsValue(Marks.Satisfactory) && !semester[semesterNum].containsValue(Marks.Poor);

    }

    public boolean heightenedScholarship() {
        int cnt = 0;
        for (Marks num : semester[semesterNum].values()) {
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
        for (int i = 1; i < 9; i++) {
            if (!semester[i].isEmpty()) {
                str = new StringBuilder("Semester: ");
                str.append(i);
                System.out.println(str);
                System.out.println("========================================================");
                semester[semesterNum].entrySet().stream().map(grade -> grade.getKey() + ": " + grade.getValue()).forEach(System.out::println);
                System.out.println("========================================================");
            }
        }
        str = new StringBuilder("Qualification task: ");
        str.append(qualifTask);
        System.out.println(str);
        System.out.println();
    }
}

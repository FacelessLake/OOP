package ru.nsu.belozerov;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecordBookTest {

    RecordBook rb = new RecordBook("Simon", "WhiteLake", "Mikhailovich", 20214, 1);

    @BeforeEach
    public void myBook() {
        rb.addMark("PE", RecordBook.Marks.Excellent);
        rb.addMark("English", RecordBook.Marks.Excellent);
        rb.addMark("OOP", RecordBook.Marks.Excellent);
        rb.addMark("Introduction to AI", RecordBook.Marks.Excellent);
        rb.addMark("Computing Systems", RecordBook.Marks.Excellent);
        rb.addMark("Differential Equations", RecordBook.Marks.Good);
    }

    @AfterEach
    public void showTheGrades() {
        rb.showRecordBook();
    }

    @Test
    public void redDiploma_noQualifTask() {
        assertFalse(rb.redDiploma());
    }

    @Test
    public void redDiploma_giveQualifTask() {
        rb.setQualifTask(RecordBook.Marks.Excellent);
        assertTrue(rb.redDiploma());
    }

    @Test
    public void redDiploma_badMark() {
        rb.setQualifTask(RecordBook.Marks.Excellent);
        rb.addMark("Operating Systems", RecordBook.Marks.Satisfactory);

        assertFalse(rb.redDiploma());
    }

    @Test
    public void scholarship_allExc() {
        assertTrue(rb.scholarship());
    }

    @Test
    public void scholarship_badMark() {
        rb.addMark("Operating Systems", RecordBook.Marks.Satisfactory);
        assertFalse(rb.scholarship());
    }

    @Test
    public void heightenedScholarship_allExc() {
        assertTrue(rb.heightenedScholarship());
    }

    @Test
    public void heightenedScholarship_theeGood() {
        rb.addMark("Operating Systems", RecordBook.Marks.Good);
        rb.addMark("PE 2.0", RecordBook.Marks.Good);

        assertFalse(rb.heightenedScholarship());
    }

    @Test
    public void average_test() {
        rb.addMark("Operating Systems", RecordBook.Marks.Satisfactory);
        assertEquals(32.0 / 7.0, rb.average());
    }

    @Test
    public void average_withPassed() {
        rb.addMark("Operating Systems", RecordBook.Marks.Passed);
        assertEquals(29.0 / 6.0, rb.average());
    }

    @Test
    public void heightenedScholarship_twoSemesters() {
        assertTrue(rb.heightenedScholarship());
        rb.setNewSemester(2);
        rb.addMark("PE 2.0", RecordBook.Marks.Poor);
        rb.addMark("English 2.0", RecordBook.Marks.Excellent);
        rb.addMark("OOP 2.0", RecordBook.Marks.Excellent);
        rb.addMark("Introduction to AI 2.0", RecordBook.Marks.Excellent);
        rb.addMark("Computing Systems 2.0", RecordBook.Marks.Excellent);
        rb.addMark("Differential Equations 2.0", RecordBook.Marks.Good);
        assertFalse(rb.heightenedScholarship());
    }
}
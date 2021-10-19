package ru.nsu.belozerov;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecordBookTest {


    @Test
    public void myBook_test() {
        RecordBook rb = new RecordBook("Simon", "WhiteLake", "Mikhailovich", 20214);
        rb.addMark("Operating Systems", RecordBook.Marks.Satisfactory);
        rb.addMark("PE", RecordBook.Marks.Excellent);
        rb.addMark("English", RecordBook.Marks.Good);
        rb.addMark("OOP", RecordBook.Marks.Good);
        rb.addMark("Introduction to AI", RecordBook.Marks.Excellent);
        rb.addMark("Computing Systems", RecordBook.Marks.Excellent);
        rb.addMark("Differential Equations", RecordBook.Marks.Good);

        assertFalse(rb.redDiploma());

        rb.setQualifTask(RecordBook.Marks.Excellent);
        assertFalse(rb.redDiploma());
        assertFalse(rb.scholarship());

        rb.showRecordBook();
    }
}
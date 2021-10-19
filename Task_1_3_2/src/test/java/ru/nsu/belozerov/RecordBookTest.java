package ru.nsu.belozerov;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecordBookTest {

    private final RecordBook RB = new RecordBook();

    @Test
    public void average_test() {
        RB.addMark(RecordBook.Marks.Good);
        RB.addMark(RecordBook.Marks.Excellent);
        assertEquals (4.5, RB.average());
        assertEquals(false, RB.redDiploma());
    }
}
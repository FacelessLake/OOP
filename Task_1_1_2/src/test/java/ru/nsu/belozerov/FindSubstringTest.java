package ru.nsu.belozerov;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

class FindSubstringTest {
    public ArrayList<Integer> referenceFind(String input, String pattern) {
        int index = -1;

        ArrayList<Integer> answ = new ArrayList<>(0);
        do {
            index = input.indexOf(pattern, index);
            if (index == -1) {
                break;
            }
            answ.add(index);
            index++;
        } while (index != input.length());

        return (answ);
    }

    @Test
    void test_FindSmallFragment() throws IOException {

        FindSubstring fs = new FindSubstring();
        FindSubstringTest fst = new FindSubstringTest();

        String input1 = Files.readString(Paths.get("input.txt"));

        Reader input2 = new FileReader("input.txt");
        String pattern = "em";


        Assertions.assertEquals(fst.referenceFind(input1, pattern), fs.find(input2, pattern.toCharArray()));

        input2.close();
    }

    @Test
    void test_FindInLargeTextAnotherEncoding() throws IOException {

        FindSubstring fs = new FindSubstring();
        FindSubstringTest fst = new FindSubstringTest();

        String input1 = Files.readString(Paths.get("hamlet.txt"));

        Reader input2 = new FileReader("hamlet.txt");
        String pattern = "Hamlet";


        Assertions.assertEquals(fst.referenceFind(input1, pattern), fs.find(input2, pattern.toCharArray()));

        input2.close();
    }
}
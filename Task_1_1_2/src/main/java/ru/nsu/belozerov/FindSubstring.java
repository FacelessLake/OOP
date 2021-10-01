package ru.nsu.belozerov;

import java.io.Reader;
import java.util.Arrays;
import java.util.ArrayList;

public class FindSubstring {

    private int[] zFunction(char[] str, int strlen) {
        int[] arr = new int[strlen];

        int left = 0;
        int right = 0;

        for (int i = 1; i < strlen; i++) {
            arr[i] = right > i ? Math.min(arr[i - left], right - i) : 0;
            while (i + arr[i] < strlen && str[arr[i]] == str[i + arr[i]]) {
                ++arr[i];
            }
            if (i + arr[i] > right) {
                left = i;
                right = i + arr[i];
            }
        }
        return (arr);
    }

    public ArrayList<Integer> find(Reader input, char[] pattern) {

        int patternlen = pattern.length;

        int buflen = patternlen * 11;
        char[] buf = new char[buflen];

        int strlen = patternlen + buflen + 1;
        char[] str = new char[strlen];

        ArrayList<Integer> answer = new ArrayList<>(0);

        System.arraycopy(pattern, 0, str, 0, patternlen);
        str[patternlen] = '~';

        try {
            int cnt;
            int iter = 0;
            while (true) {
                if (iter == 0) { // only for first time
                    cnt = input.read(buf);
                    if (cnt == -1) {
                        break;
                    }
                }
                //copy whole block
                System.arraycopy(buf, 0, str, patternlen + 1, buflen);
                int[] result = zFunction(str, strlen);

                int lastIndex = 0;
                for (int i = patternlen + 1; i < strlen; i++) {
                    if (result[i] == patternlen) {
                        lastIndex = i - (patternlen + 1);
                        if (iter * buflen + lastIndex > 747) {
                            int a = 0;
                        }
                        answer.add(iter * buflen + lastIndex);
                    }
                }

                //left shift

                int shift = lastIndex + 2 * patternlen + 1;
                System.arraycopy(str, shift, str, patternlen + 1, strlen - shift);

                //get next part, then check block joint
                Arrays.fill(buf, '\0');
                cnt = input.read(buf);
                if (cnt == -1) {
                    break;
                }

                //feel the gap at the end
                System.arraycopy(buf, 0, str, strlen - (lastIndex + patternlen), (lastIndex + patternlen));
                result = zFunction(str, strlen);

                for (int i = patternlen + 1; i < strlen-(lastIndex + patternlen); i++) {
                    if (result[i] == patternlen) {
                        //lastIndex = i - (patternlen + 1);
                        if (iter * buflen + i + lastIndex + 1 - patternlen > 747) {
                            int a = 0;
                        }
                        answer.add(iter * buflen + i + lastIndex - 1);
                    }
                }

                iter++;
            }
            input.close();

        } catch (Exception e) {
            e.getStackTrace();
        }
        return (answer);
    }
}

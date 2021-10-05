package ru.nsu.belozerov;

import java.io.Reader;
import java.util.ArrayList;

public class FindSubstring {

    private int[] zFunction(char[] str, int strLen) {
        int[] arr = new int[strLen];

        int left = 0;
        int right = 0;

        for (int i = 1; i < strLen; i++) {
            arr[i] = right > i ? Math.min(arr[i - left], right - i) : 0;
            while (i + arr[i] < strLen && str[arr[i]] == str[i + arr[i]]) {
                ++arr[i];
            }
            if (i + arr[i] > right) {
                left = i;
                right = i + arr[i];
            }
        }
        return arr;
    }

    public ArrayList<Integer> find(Reader input, char[] pattern) {

        int patternLen = pattern.length;

        int strLen = patternLen * 14 + 1;
        char[] str = new char[strLen];

        ArrayList<Integer> answer = new ArrayList<>(0);

        System.arraycopy(pattern, 0, str, 0, patternLen);
        str[patternLen] = '~';

        try {
            int cnt;
            int symCnt = 0;
            int shift = 0;
            int N;
            while (true) {
                //copy whole block if there weren't any matching
                N = strLen;
                if (shift == 0) {
                    cnt = input.read(str, patternLen + 1, patternLen * 13);
                    if (cnt < patternLen * 13) {
                        N = cnt;
                    }
                } else {
                    //left shift
                    System.arraycopy(str, shift, str, patternLen + 1, strLen - shift);
                    //get next part, then feel the gap at the end and check block joint
                    cnt = input.read(str, patternLen + 1 + strLen - shift, shift - patternLen - 1);
                    cnt += strLen - shift;
                    if (cnt < shift - patternLen - 1) {
                        N = cnt;
                    }
                }
                if (cnt == -1) {
                    break;
                }
                shift = 0;
                int[] result = zFunction(str, strLen);
                for (int i = patternLen + 1; i < N; i++) {
                    if (result[i] == patternLen) {
                        answer.add(symCnt + i - patternLen - 1);
                        shift = 0;
                    } else {
                        if (i + result[i] == strLen) {
                            shift = i;
                            cnt = shift - patternLen - 1;
                        }
                    }
                }
                symCnt += cnt;
            }
            input.close();

        } catch (Exception e) {
            e.getStackTrace();
        }
        return answer;
    }
}

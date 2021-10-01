package ru.nsu.belozerov;

import java.io.Reader;
import java.util.Scanner;

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

    private void finder(char[] pattern, char[] buf) {

        int len1 = pattern.length;
        int len2 = buf.length;

        char[] str = new char[len1 + len2 + 1];
        System.arraycopy(pattern, 0, str, 0, len1);
        str[len1] = '~';
        System.arraycopy(buf, 0, str, len1 + 1, len2);

        int[] result = zFunction(str, len1 + len2 + 1);

        for (int i = 0; i < len1 + len2 + 1; i++) {
            if (result[i] == len1) {
                System.out.print(i - len1 - 1 + " ");
            }
        }
    }

    public static void find(Reader input) {
        Scanner in = new Scanner(System.in);

        char[] pattern = in.nextLine().toCharArray();
        char[] buf = new char[pattern.length * 11];

        try {
            int cnt = 0;

            while (true) {
                cnt = input.read(buf);
                if (cnt == -1) {
                    break;
                }
                FindSubstring fs = new FindSubstring();
                fs.finder(pattern, buf);
            }
            input.close();

        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}

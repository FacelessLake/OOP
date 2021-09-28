package ru.nsu.belozerov;

public class FindSubstring {
//    private int strlen;
//    private int[] arr = new int[strlen];
//
//    public FindSubstring(String[] str) {
//        strlen = str.length;
//    }

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

    public void find(char[] substr, char[] buf) {
        int len1 = substr.length;
        int len2 = buf.length;

        char[] str = new char[len1 + len2 + 1];
        System.arraycopy(substr, 0, str, 0, len1);
        str[len1] = '~';
        System.arraycopy(buf, 0, str, len1 + 1, len2);

        int[] result = zFunction(str, len1 + len2 + 1);

        for (int i=0; i < len1 + len2 + 1; i++) {
            if (result[i] != 0){
                System.out.print(i-len1-1+" ");
            }
        }
    }
}

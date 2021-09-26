package ru.nsu.belozerov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class HeapSortTest {

    HeapSort hs = new HeapSort();

    @Test
    void testSortUnsorted() {
        int[] arr1 = {5, 2, 3, 4, 1};
        hs.sort(arr1);

        int[] arr2 = {5, 2, 3, 4, 1};
        Arrays.sort(arr2);

        Assertions.assertArrayEquals(arr1, arr2);
    }

    @Test
    void testSortSorted() {
        int[] arr1 = {1, 2, 3, 4, 5};
        hs.sort(arr1);

        int[] arr2 = {1, 2, 3, 4, 5};
        Arrays.sort(arr2);

        Assertions.assertArrayEquals(arr1, arr2);
    }

    @Test
    void testSortWithNonPositive() {
        int[] arr1 = {1, -3, 2, -4, 0};
        hs.sort(arr1);

        int[] arr2 = {1, -3, 2, -4, 0};
        Arrays.sort(arr2);

        Assertions.assertArrayEquals(arr1, arr2);
    }

    @Test
    void testSortWithTwoEqual() {
        int[] arr1 = {5, 4, 2, 4, 1};
        hs.sort(arr1);

        int[] arr2 = {5, 4, 2, 4, 1};
        Arrays.sort(arr2);

        Assertions.assertArrayEquals(arr1, arr2);
    }

    @Test
    void testSortWithThreeEqual() {
        int[] arr1 = {4, 4, 2, 4, 1};
        hs.sort(arr1);

        int[] arr2 = {4, 4, 2, 4, 1};
        Arrays.sort(arr2);

        Assertions.assertArrayEquals(arr1, arr2);
    }

    @Test
    void testSortWithAllEqual() {
        int[] arr1 = {5, 5, 5, 5, 5};
        hs.sort(arr1);

        int[] arr2 = {5, 5, 5, 5, 5};
        Arrays.sort(arr2);

        Assertions.assertArrayEquals(arr1, arr2);
    }

    @Test
    void testSortWithEmptyArray() {
        int[] arr1 = {};
        hs.sort(arr1);

        int[] arr2 = {};
        Arrays.sort(arr2);

        Assertions.assertArrayEquals(arr1, arr2);
    }

    @Test
    void testSortWithBorderValues() {
        int[] arr1 = {Integer.MAX_VALUE, 5, 2, Integer.MIN_VALUE, 1};
        hs.sort(arr1);

        int[] arr2 = {Integer.MAX_VALUE, 5, 2, Integer.MIN_VALUE, 1};
        Arrays.sort(arr2);

        Assertions.assertArrayEquals(arr1, arr2);
    }
}
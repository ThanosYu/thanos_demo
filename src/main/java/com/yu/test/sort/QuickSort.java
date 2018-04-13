package com.yu.test.sort;

import java.util.Arrays;

/**
 * @author Thanos_Yu
 * @date 2018/3/30.
 */
public class QuickSort {

    private static void sort(int[] a, int low, int high) {
        if (low > high) {
            return;
        }
        int i, j, index;
        i = low;
        j = high;
        index = a[i];

        while (i < j) {
            while (i < j && a[j] > index) {
                j--;
            }
            if (i < j) {
                a[i++] = a[j];
            }
            while (i < j && a[i] < index) {
                i++;
            }
            if (i < j) {
                a[j--] = a[i];
            }
        }
        a[i] = index;
        sort(a, low, i - 1);
        sort(a, i + 1, high);
    }

    public static void main(String[] args) {
        int a[] = {9, 8, 5, 22, 8, 4, 3, 32, 55};
        sort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }
}

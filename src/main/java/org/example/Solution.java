package org.example;

public class Solution {
    public int findMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < 10; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }
}

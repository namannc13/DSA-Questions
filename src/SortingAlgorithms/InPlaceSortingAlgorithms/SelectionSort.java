package SortingAlgorithms.InPlaceSortingAlgorithms;

import java.util.Arrays;

public class SelectionSort {
    public static void main(String[] args) {

        int arr[] = new int[] { 12, 34, 5, 27, 36, 22 };
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }

        }
        System.out.println(Arrays.toString(arr));
    }
}




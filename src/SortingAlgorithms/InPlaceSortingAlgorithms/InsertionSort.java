package SortingAlgorithms.InPlaceSortingAlgorithms;

import java.util.Arrays;

public class InsertionSort {
    public static void main(String[] args) {

        int arr[] = new int[] { 1, 3, 7, 8, 6, 9 };
        System.out.println("Original Array : " + Arrays.toString(arr));

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                System.out.println("Something wrong at index : " + (i + 1));
                int prev = i;
                int temp = arr[i + 1];
                while (prev >= 0 && (arr[prev] > temp)) {
                    arr[prev + 1] = arr[prev];
                    prev--;
                }
                System.out.println("Pushed Array : " + Arrays.toString(arr));
                arr[prev + 1] = temp;

            }
        }
        System.out.println("Sorted Array : " + Arrays.toString(arr));

    }
}




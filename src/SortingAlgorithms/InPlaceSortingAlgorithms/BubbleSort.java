package SortingAlgorithms.InPlaceSortingAlgorithms;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {

        int arr[] = new int[] { 12, 44, 19, 66, 72, 23 };
        for (int j = 0; j < arr.length; j++) {
            boolean swapped = false;
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    swapped = true;
                }
            }
            System.out.println(Arrays.toString(arr));
            if(swapped == false) break;
        }
        
    }
}

package GreedyAlgorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class intervalScheduling {
    public static void main(String[] args) {
        int[][] arr = {
            {0,4},
                {1,2},
                {2,4},
            {3,5},
            {3,6},
                {5,6},
            {5,7},
                {6,7},
                {7,9},
            {8,10}
        };
        Arrays.sort(arr, Comparator.comparingInt(a -> a[1]));

        // Print the sorted array
        for (int[] innerArray : arr) {
            System.out.println(Arrays.toString(innerArray));
        }
        // [1, 2]q
        // [0, 4]
        // [2, 4]q
        // [3, 5]
        // [3, 6]
        // [5, 6]q
        // [5, 7]
        // [6, 7]q
        // [7, 9]q
        // [8, 10]
        intervalSchedulingAlgorithm(arr);
    }

    private static void intervalSchedulingAlgorithm(int[][] arr) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        int currentEnd = arr[0][1];
        for(int i = 1; i < arr.length; i++){
            if(arr[i][0] >= currentEnd){
                list.add(i);
                currentEnd = arr[i][1];
            }
        }
        
        for(int i = 0 ; i< list.size(); i++){
            System.out.print(Arrays.toString(arr[list.get(i)]) + " ");
        }
    }
}

package DP.Questions;

public class HouseRobber2 { // This is similar to the HouseRobber (maxSumWithNoAdjacent) question with just a slight modification that the first and the last element of the array are adjacent
    public static void main(String[] args) { 
        int[] arr = {1,2,3};
        int answer =  houseRobber2(arr);
        System.out.println(answer);
    }

    private static int houseRobber2(int[] arr) { // we will create two arrays in which the first array consists of elements of the original array except the first element and the second one consists of elements of the original array except the last element
        if(arr.length == 1) return arr[0]; // if the array has a single element

        int[] arr2 = new int[arr.length-1];
        int[] arr3 = new int[arr.length-1];

        for(int i = 0; i < arr2.length; i++){ //first array consists of elements of the original array except the first element
            arr2[i] = arr[i];
        }

        for(int i = 1; i <= arr2.length; i++){ //the second one consists of elements of the original array except the last element
            arr3[i-1] = arr[i];
        }

        int answer1 = maxSumWithNoAdjacent.maxSumWithNoAdjacentOptimal(arr2, arr2.length-1); // max sum with no adjacent excluding the last element
        int answer2 = maxSumWithNoAdjacent.maxSumWithNoAdjacentOptimal(arr3, arr3.length-1); // max sum with no adjacent excluding the first element

        return Math.max(answer1,answer2); // max sum with no adjacent keeping in mind that the first and the last element are adjacent
    }
}

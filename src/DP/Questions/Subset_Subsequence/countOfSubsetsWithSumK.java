package DP.Questions.Subset_Subsequence;

import java.util.Arrays;

public class countOfSubsetsWithSumK {
    public static void main(String[] args) { // this will not solve for array if it is 0 as elements
        int[] arr = {0,0,1};
        int target = 1;

        System.out.println(countOfSubsetsWithSumKRecursion(arr, arr.length-1, target)); // if no zero
        // if there are 0's in array // count the number of 0's
        int countZero = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == 0) countZero++;
        }
        System.out.println(Math.pow(2, countZero) * countOfSubsetsWithSumKRecursion(arr, arr.length-1, target)); // if zero

        int[][] dp = new int[arr.length][target+1];
        for(int i =0; i < dp.length; i++){
            Arrays.fill(dp[i], -1);
        }

        System.out.println(countOfSubsetsWithSumKRecursionDP(arr, arr.length-1, target, dp)); // if no zero
        System.out.println(Math.pow(2, countZero) * countOfSubsetsWithSumKRecursionDP(arr, arr.length-1, target, dp)); // if zero

        for(int i =0; i < dp.length; i++){
            Arrays.fill(dp[i], 0);
        }

        System.out.println(countOfSubsetsWithSumKTabulation(arr, target, dp)); // if no zero
        System.out.println(Math.pow(2, countZero) * countOfSubsetsWithSumKTabulation(arr, target, dp)); // if zero

        for(int i =0; i < dp.length; i++){
            System.out.println(Arrays.toString(dp[i])); 
        }
    }

    private static int countOfSubsetsWithSumKRecursionDP(int[] arr, int index, int target, int[][] dp) {
        if(target == 0) return 1;
        if(index == 0){
            if(arr[0] == target) return 1;
            return 0;
        }

        if(dp[index][target] != -1) return dp[index][target];

        int notPick = countOfSubsetsWithSumKRecursion(arr, index-1, target);
        int pick = 0;
        if(arr[index] <= target ) pick = countOfSubsetsWithSumKRecursion(arr, index-1, target-arr[index]);

        return dp[index][target] = notPick + pick;
    }

    private static int countOfSubsetsWithSumKRecursion(int[] arr, int index, int target) {
        if(target == 0) return 1;
        if(index == 0){
            if(arr[0] == target) return 1;
            return 0;
        }

        int notPick = countOfSubsetsWithSumKRecursion(arr, index-1, target);
        int pick = 0;
        if(arr[index] <= target ) pick = countOfSubsetsWithSumKRecursion(arr, index-1, target-arr[index]);

        return notPick + pick;
    }

    private static int countOfSubsetsWithSumKTabulation(int[] arr, int target, int[][] dp){
        for(int i =0; i< dp.length; i++){ // base case 1
            dp[i][0] = 1;
        }
        if(arr[0] <= target) dp[0][arr[0]] = 1; // base case 2

        for(int i =1; i< dp.length; i++){ // if the base case of index is done, then just start from 1
            for(int j = 1; j< dp[0].length; j++){
                int notPick = dp[i-1][j];
                int pick = 0;
                if(arr[i] <= j ) pick = dp[i-1][ j-arr[i]];

                dp[i][j] = notPick + pick;
            }
        }
        return dp[dp.length-1][target];
    }
}

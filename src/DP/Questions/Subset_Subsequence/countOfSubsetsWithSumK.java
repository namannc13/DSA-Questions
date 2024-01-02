package DP.Questions.Subset_Subsequence;

import java.util.Arrays;

public class countOfSubsetsWithSumK {
    public static void main(String[] args) {
        int[] arr = {1,1,1};
        int target = 2;

        System.out.println(countOfSubsetsWithSumKRecursion(arr, arr.length-1, target));

        int[][] dp = new int[arr.length][target+1];
        for(int i =0; i < dp.length; i++){
            Arrays.fill(dp[i], -1);
        }

        System.out.println(countOfSubsetsWithSumKRecursionDP(arr, arr.length-1, target, dp));

        for(int i =0; i < dp.length; i++){
            Arrays.fill(dp[i], 0);
        }

        System.out.println(countOfSubsetsWithSumKTabulation(arr, target, dp));

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

package DP.Questions.Subset_Subsequence;

import java.util.Arrays;

public class countOfSubsetsWithSumKIncludingZeroes {
    public static void main(String[] args) { // this will solve for array if it is 0 as elements
        int[] arr = {5,2,6,4};
        int target = 7;

        System.out.println(countOfSubsetsWithSumKIncludingZeroesRecursion(arr, arr.length-1, target));

        int[][] dp = new int[arr.length][target+1];
        for(int i =0; i < dp.length; i++){
            Arrays.fill(dp[i], -1);
        }

        System.out.println(countOfSubsetsWithSumKIncludingZeroesRecursionDP(arr, arr.length-1, target, dp));

        for(int i =0; i < dp.length; i++){
            Arrays.fill(dp[i], 0);
        }

        System.out.println(countOfSubsetsWithSumKIncludingZeroesTabulation(arr, target, dp)); 

        System.out.println(countOfSubsetsWithSumKIncludingZeroesOptimal(arr, target));
    }
    
    static int countOfSubsetsWithSumKIncludingZeroesRecursion(int[] arr, int index, int target) {
        if(index == 0){
            if(target == 0 && arr[index] == 0) return 2;
            if(target == 0) return 1;
            if(arr[index] == target) return 1;
            return 0;
        }

        int notPick = countOfSubsetsWithSumKIncludingZeroesRecursion(arr, index-1, target);
        int pick = 0;
        if(arr[index] <= target ) pick = countOfSubsetsWithSumKIncludingZeroesRecursion(arr, index-1, target-arr[index]);

        return notPick + pick;
    }

    static int countOfSubsetsWithSumKIncludingZeroesRecursionDP(int[] arr, int index, int target, int[][] dp) {
        if(index == 0){
            if(target == 0 && arr[index] == 0) return 2;
            if(target == 0) return 1;
            if(arr[index] == target) return 1;
            return 0;
        }

        if(dp[index][target] != -1) return dp[index][target];

        int notPick = countOfSubsetsWithSumKIncludingZeroesRecursionDP(arr, index-1, target,dp);
        int pick = 0;
        if(arr[index] <= target ) pick = countOfSubsetsWithSumKIncludingZeroesRecursionDP(arr, index-1, target-arr[index],dp);

        return dp[index][target] = notPick + pick;
    }

    static int countOfSubsetsWithSumKIncludingZeroesTabulation(int[] arr, int target, int[][] dp){
        if(arr[0] == 0) dp[0][0] = 2;
        else{
            dp[0][0] = 1;
        }
        if(arr[0] != 0 && arr[0] <= target) dp[0][arr[0]] = 1; // base case 2

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
    
    static int countOfSubsetsWithSumKIncludingZeroesOptimal(int[] arr, int target){
        int[] prev = new int[target+1];
        Arrays.fill(prev,0);
        int[] curr = new int[target+1];
        Arrays.fill(curr,0);
        if(arr[0] == 0) prev[0] = 2;
        else{
            prev[0] = 1;
        }
        if(arr[0] != 0 && arr[0] <= target) prev[arr[0]] = 1; // base case 2

        for(int i =1; i< arr.length; i++){ // if the base case of index is done, then just start from 1
            for(int j = 0; j<= target; j++){
                int notPick = prev[j];
                int pick = 0;
                if(arr[i] <= j ) pick = prev[ j-arr[i]];

                curr[j] = notPick + pick;
            }
            prev = Arrays.copyOf(curr, curr.length);
        }
        return prev[target];
    }

}

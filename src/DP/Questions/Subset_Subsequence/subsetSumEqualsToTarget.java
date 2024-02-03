package DP.Questions.Subset_Subsequence;

import java.util.Arrays;

public class subsetSumEqualsToTarget {
    public static void main(String[] args) {
        int[] arr = {1,1,1};
        int target = 2;

        System.out.println(subsetSumEqualsToTargetRecursion(arr,arr.length-1,target));

        int[][] dp = new int[arr.length][target+1];
        for (int i = 0; i < dp.length; i++) {
            for(int j= 0; j< target; j++){
                Arrays.fill(dp[i], -1);
            }
        }

        System.out.println(subsetSumEqualsToTargetDP(arr, arr.length-1, target, dp));

        boolean[][] dpBoolean = new boolean[arr.length][target+1];
        for(int i = 0; i< dpBoolean.length; i++){
            for(int j = 0; j<= target; j++){
                dpBoolean[i][j] = false;
            }
        }

        System.out.println(subsetSumEqualsToTargetTabulation(arr, target, dpBoolean));

        

        System.out.println(subsetSumEqualsToTargetOptimal(arr, target));
    }

    private static boolean subsetSumEqualsToTargetRecursion(int[] arr,int index, int target) {
        if(target == 0) return true;
        if(index == 0) return arr[index] == target;

        boolean notPick = subsetSumEqualsToTargetRecursion(arr, index-1, target);
        boolean Pick = false; 
        if(target >= arr[index]){ 
            Pick = subsetSumEqualsToTargetRecursion(arr, index-1, target - arr[index]);
        }

        return notPick || Pick;
    }

    private static boolean subsetSumEqualsToTargetDP(int[] arr, int index, int target, int[][] dp){
        if(target == 0) return true;
        if(index == 0) return arr[index] == target;

        if(dp[index][target] != -1){
            if(dp[index][target] == 1) return true;
            return false;
        }

        boolean notPick = subsetSumEqualsToTargetDP(arr, index-1, target,dp);
        boolean Pick = false; 
        if(target >= arr[index]){ 
            Pick = subsetSumEqualsToTargetDP(arr, index-1, target - arr[index],dp);
        }

        boolean answer = notPick || Pick;
        if(answer) dp[index][target] = 1;
        else dp[index][target] = 0;

        return answer;
    }

    static boolean subsetSumEqualsToTargetTabulation(int[] arr,int target, boolean[][] dpBoolean){
        //if target is 0, return true ( the recursion base case ), so what can the index possibly be ? when the target is 0? it can be anything from 0 to arr.lenght-1. so just do it , write it in the dp array
        for(int i = 0; i< arr.length; i++){
            dpBoolean[i][0] = true;
        }
        // if index is 0, if arr[index] is equal to the target then return true;
        // when index is 0, target can be anything from 1,2,3,4.... target ( not 0 because the first base case covers it) // target can be anything but it will be true only for arr[0]
        if(arr[0] <= target) dpBoolean[0][arr[0]] = true; // (arr[0] <= target) for cases when the arr is {100} .and we sent the target as half the sum plus 1 (50 + 1)

        for(int i = 1; i< arr.length; i++){ // for index
            for(int j = 1; j <= target; j++){ // for target
                boolean notPick = dpBoolean[i-1][j];
                boolean Pick = false; 
                if(j >= arr[i]){ 
                    Pick = dpBoolean[i-1][j - arr[i]];
                }
                dpBoolean[i][j] = notPick || Pick;
            }
        }
        return dpBoolean[arr.length-1][target]; 
    }

    static boolean subsetSumEqualsToTargetOptimal(int[] arr,int target){
        Boolean[] prev = new Boolean[target+1];
        Arrays.fill(prev, false);
        Boolean[] curr = new Boolean[target+1];
        Arrays.fill(curr, false);

        prev[0] = curr[0] = true;

        if(arr[0] <= target) prev[arr[0]] = true; // (arr[0] <= target) for cases when the arr is {100} .and we sent the target as half the sum plus 1 (50 + 1)

        for(int i = 1; i< arr.length; i++){ // for index
            for(int j = 1; j <= target; j++){ // for target
                boolean notPick = prev[j];
                boolean Pick = false; 
                if(j >= arr[i]){ 
                    Pick = prev[j - arr[i]];
                }
                curr[j] = notPick || Pick;
            }
            prev = Arrays.copyOf(curr, curr.length);
        }
        return prev[target]; 
    }
}

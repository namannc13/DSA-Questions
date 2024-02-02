package DP.Questions2D.LIS;

import java.util.Arrays;

public class longestIncSubSequence {
    public static void main(String[] args) {
        int[] arr = { 22, 9 , 5, 12, 15, 9, 21};

        System.out.println(longestIncSubSequenceRecursion(arr, 0, -1));

        int[][] dp  = new int[arr.length][arr.length+1]; // arr.length+1 as we are doing a index right shift
        for(int i = 0; i< dp.length; i++){
            Arrays.fill(dp[i],-1);
        }

        System.out.println(longestIncSubSequenceMemoization(arr, 0, -1, dp));

        int[][] dp1  = new int[arr.length+1][arr.length+1]; // arr.length+1 as we are doing a index right shift
        for(int i = 0; i< dp1.length; i++){
            Arrays.fill(dp1[i],0);
        }

        System.out.println(longestIncSubSequenceTabulation(arr, dp1));

        System.out.println(longestIncSubSequenceOptimal(arr));

        System.out.println(longestIncSubSequenceBest(arr));
    }

    private static int longestIncSubSequenceRecursion(int[] arr, int ind, int prevInd) {
        if(ind == arr.length) return 0;

        int notPick = 0 + longestIncSubSequenceRecursion(arr, ind+1, prevInd);
        int pick = Integer.MIN_VALUE;
        if(prevInd == -1 || arr[ind] > arr[prevInd]){
            pick = 1 + longestIncSubSequenceRecursion(arr, ind+1, ind);
        }

        return Math.max(notPick, pick);
    }

    private static int longestIncSubSequenceMemoization(int[] arr, int ind, int prevInd, int[][] dp) {
        if(ind == arr.length) return 0;

        if(dp[ind][prevInd+1] != -1) return dp[ind][prevInd+1]; // watch video if you forgot why we are taking prevInd + 1

        int notPick = 0 + longestIncSubSequenceMemoization(arr, ind+1, prevInd, dp);
        int pick = Integer.MIN_VALUE;
        if(prevInd == -1 || arr[ind] > arr[prevInd]){
            pick = 1 + longestIncSubSequenceMemoization(arr, ind+1, ind, dp);
        }

        return dp[ind][prevInd+1] = Math.max(notPick, pick);
    }

    public static int longestIncSubSequenceTabulation(int[] arr, int[][] dp){ // here we are right shifting the second parameter of 2d dp array 
        for(int index = arr.length-1; index>=0; index--){
            for(int prevInd = index-1; prevInd >= -1; prevInd--){
                int notPick = 0 + dp[index+1][prevInd+1];
                int pick = Integer.MIN_VALUE;
                if(prevInd == -1 || arr[index] > arr[prevInd]){
                    pick = 1 + dp[index+1][index+1];
                }
                dp[index][prevInd+1] = Math.max(notPick,pick);
            }
        }
        return dp[0][0];
    }

    public static int longestIncSubSequenceOptimal(int[] arr){ 
        int[] next = new int[arr.length+1];
        Arrays.fill(next,0);
        int[] curr = new int[arr.length+1];
        Arrays.fill(curr,0);

        for(int index = arr.length-1; index>=0; index--){
            for(int prevInd = index-1; prevInd >= -1; prevInd--){
                int notPick = 0 + next[prevInd+1];
                int pick = Integer.MIN_VALUE;
                if(prevInd == -1 || arr[index] > arr[prevInd]){
                    pick = 1 + next[index+1];
                }
                curr[prevInd+1] = Math.max(notPick,pick);
            }
            next = curr;
        }
        return curr[0];
    }

    public static int longestIncSubSequenceBest(int[] arr){
        int[] dp = new int[arr.length];
        Arrays.fill(dp,1);

        int maxi = 0;
        for(int i= 0; i< arr.length; i++){
            for(int j = 0; j< i; j++){
                if(arr[j] < arr[i]){
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
            maxi = Math.max(dp[i], maxi);
        }
        return maxi;
    }
}

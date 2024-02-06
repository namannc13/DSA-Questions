package DP.Questions.Subset_Subsequence;

import java.util.Arrays;

public class knapsack {
    public static void main(String[] args) {
        int n = 3;
        int[] val = {30,40,60};
        int[] wt = {3,2,5};

        int W = 6;

        System.out.println(knapsackRecursion(val, wt, n-1 , W));

        int[][] dp = new int[n][W+1];
        for(int i = 0 ; i <n; i++){
            Arrays.fill(dp[i], -1);
        }
        
        System.out.println(knapsackRecursionDP(val, wt, n-1, W, dp));
        
        for(int i = 0 ; i <n; i++){
            Arrays.fill(dp[i], 0);
        }
        for(int i = 0 ; i< n; i++){
            System.out.println(Arrays.toString(dp[i]));
        }
        System.out.println(knapsackTabulation(val, wt, W, dp));
        for(int i = 0 ; i< n; i++){
            System.out.println(Arrays.toString(dp[i]));
        }

        System.out.println(knapsackOptimal(val, wt, W));

        System.out.println(knapsackBest(val, wt, W));
    }

    public static int knapsackRecursion(int[] val, int[] wt, int n, int W){
        if(n == 0){
            if(wt[0] <= W) return val[0];
            return 0;
        }

        int notPick = 0 + knapsackRecursion(val, wt, n-1, W);
        int pick = Integer.MIN_VALUE;
        if(wt[n] <= W) pick = val[n] + knapsackRecursion(val, wt, n-1, W-wt[n]);

        return Math.max(notPick, pick);
    }

    public static int knapsackRecursionDP(int[] val, int[] wt, int n, int W,int[][] dp){
        if(n == 0){
            if(wt[0] <= W) return val[0];
            return 0;
        }
    
        if(dp[n][W] != -1) return dp[n][W];

        int notPick = 0 + knapsackRecursionDP(val, wt, n-1, W,dp);
        int pick = Integer.MIN_VALUE;
        if(wt[n] <= W) pick = val[n] + knapsackRecursionDP(val, wt, n-1, W-wt[n],dp);
    
        return dp[n][W] = Math.max(notPick, pick);
    }

    public static int knapsackTabulation(int[] val, int[] wt, int W, int[][] dp){
        for(int i = 0; i<dp[0].length; i++){
            if(i >= wt[0]) dp[0][i] = val[0];
        }
        // for(int j = wt[0]; j <= W; j++){
        //     dp[0][j] = val[0];
        // } // striver method

        for(int i = 1; i< dp.length; i++){
            for(int j = 0; j<= W; j++){
                int notPick = 0 + dp[i-1][j];
                int pick = Integer.MIN_VALUE;
                if(wt[i] <= j) pick = val[i] + dp[i-1][j-wt[i]];
            
                dp[i][j] = Math.max(notPick, pick);
            }
        }
        return dp[val.length-1][W];
    }

    public static int knapsackOptimal(int[] val, int[] wt, int W){
        int[] prev = new int[W+1];
        int[] curr= new int[W+1];

        for(int i = 0; i<prev.length; i++){
            if(i >= wt[0]) prev[i] = val[0];
        }

        for(int i = 1; i< val.length; i++){
            for(int j = 0; j<= W; j++){
                int notPick = 0 + prev[j];
                int pick = Integer.MIN_VALUE;
                if(wt[i] <= j) pick = val[i] + prev[j-wt[i]];
            
                curr[j] = Math.max(notPick, pick);
            }
            prev = Arrays.copyOf(curr, curr.length);
        }
        return prev[W];
    }

    public static int knapsackBest(int[] val, int[] wt, int W){
        int[] prev = new int[W+1];

        for(int i = 0; i<prev.length; i++){
            if(i >= wt[0]) prev[i] = val[0];
        }

        for(int i = 0; i<val.length; i++){
            for(int j = W; j>= 0; j--){
                int notPick = 0 + prev[j];
                int pick = Integer.MIN_VALUE;
                if(wt[i] <= j) pick = val[i] + prev[j-wt[i]];
            
                prev[j] = Math.max(notPick, pick);
            }
        }
        return prev[W];
    }
}
package DP.Questions.Subset_Subsequence;

import java.util.Arrays;

public class knapsackII {
    public static void main(String[] args) {
        int n = 3;
        int[] wt = {2,4,6};
        int[] val = {5,11,13};

        int W = 10;

        System.out.println(knapsackIIRecursion(val, wt, n-1 , W));

        int[][] dp = new int[n][W+1];
        for(int i = 0 ; i <n; i++){
            Arrays.fill(dp[i], -1);
        }
        
        System.out.println(knapsackIIRecursionDP(val, wt, n-1, W, dp));
        
        for(int i = 0 ; i <n; i++){
            Arrays.fill(dp[i], 0);
        }
       
        System.out.println(knapsackIITabulation(val, wt, W, dp));
       

        System.out.println(knapsackIIOptimal(val, wt, W));
    }

    public static int knapsackIIRecursion(int[] val, int[] wt, int n, int W){
        if(n == 0){
            return (W/wt[0])*val[0];
            
        }

        int notPick = 0 + knapsackIIRecursion(val, wt, n-1, W);
        int pick = 0;
        if(wt[n] <= W) pick = val[n] + knapsackIIRecursion(val, wt, n, W-wt[n]);

        return Math.max(notPick, pick);
    }

    public static int knapsackIIRecursionDP(int[] val, int[] wt, int n, int W,int[][] dp){
        if(n == 0){
            return (W/wt[0])*val[0];
           
        }
    
        if(dp[n][W] != -1) return dp[n][W];

        int notPick = 0 + knapsackIIRecursionDP(val, wt, n-1, W,dp);
        int pick = 0;
        if(wt[n] <= W) pick = val[n] + knapsackIIRecursionDP(val, wt, n, W-wt[n],dp);
    
        return dp[n][W] = Math.max(notPick, pick);
    }

    public static int knapsackIITabulation(int[] val, int[] wt, int W, int[][] dp){
        for(int i = 0; i<dp[0].length; i++){
            dp[0][i] = (i/wt[0])*val[0];
        }
        // for(int j = wt[0]; j <= W; j++){
        //     dp[0][j] = val[0];
        // } // striver method

        for(int i = 1; i< dp.length; i++){
            for(int j = 0; j<= W; j++){
                int notPick = 0 + dp[i-1][j];
                int pick = 0;
                if(wt[i] <= j) pick = val[i] + dp[i][j-wt[i]];
            
                dp[i][j] = Math.max(notPick, pick);
            }
        }
        return dp[val.length-1][W];
    }

    public static int knapsackIIOptimal(int[] val, int[] wt, int W){
        int[] prev = new int[W+1];
        int[] curr= new int[W+1];

        for(int i = 0; i<prev.length; i++){
            prev[i] = (i/wt[0])*val[0];
        }

        for(int i = 1; i< val.length; i++){
            for(int j = 0; j<= W; j++){
                int notPick = 0 + prev[j];
                int pick = 0;
                if(wt[i] <= j) pick = val[i] + curr[j-wt[i]];
            
                curr[j] = Math.max(notPick, pick);
            }
            prev = Arrays.copyOf(curr, curr.length);
        }
        return prev[W];
    }
}

package DP.Questions.Subset_Subsequence;

import java.util.Arrays;

public class coinChangeII {
    public static void main(String[] args) {
        int[] coins = {1,2,5};
        int target = 5;

        System.out.println(coinChangeIIRecursion(coins,coins.length-1,target));
        System.out.println();

        int[][] dp = new int[coins.length][target+1];
        for(int i = 0; i < coins.length; i++){
            Arrays.fill(dp[i], -1);
        }
        System.out.println(coinChangeIIRecursionDP(coins, coins.length-1, target,dp));
        System.out.println();
        
        for(int i = 0; i < coins.length; i++){
            Arrays.fill(dp[i], 0);
        }
        System.out.println(coinChangeIITabulation(coins,coins.length, target,dp));
        System.out.println();
        
        System.out.println(coinChangeIIOptimal(coins, coins.length, target));
        System.out.println();
        
    }

    
    public static int coinChangeIIRecursion(int[] coins, int index,int target){
        if(index == 0) {
            if(target % coins[0] == 0){
                return 1;
            }else return 0;
        }
        
        int notTake = coinChangeIIRecursion(coins, index-1, target);
        int take = 0;
        if(coins[index] <= target) take = coinChangeIIRecursion(coins, index, target-coins[index]);
        
        return notTake+take;
    }

    private static int coinChangeIIRecursionDP(int[] coins, int index, int target, int[][] dp) {
        if(index == 0) {
            if(target % coins[0] == 0){
                return 1;
            }else return 0;
        }
        
        if(dp[index][target] != -1) return dp[index][target];

        int notTake = coinChangeIIRecursionDP(coins, index-1, target,dp);
        int take = 0;
        if(coins[index] <= target) take = coinChangeIIRecursionDP(coins, index, target-coins[index],dp);
        
        return dp[index][target] = notTake+take;
    }

    private static int coinChangeIITabulation(int[] coins,int n, int target, int[][] dp) {
        for(int i = 0; i< dp[0].length; i++){
            if(i % coins[0] == 0) dp[0][i] = 1;
            else dp[0][i] = 0;
        }

        for(int i = 1; i< n; i++){
            for(int j = 0; j<= target; j++){
                int notTake = dp[i-1][j];
                int take = 0;
                if(coins[i] <= j) take = dp[i][j-coins[i]];
                
                dp[i][j] = notTake+take;
            }
        }

        return dp[n-1][target];
    }

    private static int coinChangeIIOptimal(int[] coins,int n, int target) {
        int[] prev = new int[target+1];
        int[] curr= new int[target+1];

        for(int i = 0; i< prev.length; i++){
            if(i % coins[0] == 0) prev[i] = 1;
            else prev[i] = 0;
        }

        for(int i = 1; i< n; i++){
            for(int j = 0; j<= target; j++){
                int notTake = prev[j];
                int take = 0;
                if(coins[i] <= j) take = curr[j-coins[i]];
                
                curr[j] = notTake+take;
            }
            prev = Arrays.copyOf(curr, curr.length);
        }

        return prev[target];
    }
}

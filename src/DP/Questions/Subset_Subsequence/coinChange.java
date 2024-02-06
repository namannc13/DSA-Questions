package DP.Questions.Subset_Subsequence;

import java.util.Arrays;

public class coinChange {
    public static void main(String[] args) {
        int[] coins = {2,1,3};
        int target = 4;

        int ans = coinChangeRecursion(coins,coins.length-1,target);
        if(ans >= Integer.MAX_VALUE - 1000){
            System.out.println(-1);
        }else System.out.println(ans);
        System.out.println();

        int[][] dp = new int[coins.length][target+1];
        for(int i = 0; i < coins.length; i++){
            Arrays.fill(dp[i], -1);
        }
         ans = coinChangeRecursionDP(coins, coins.length-1, target,dp);
        if(ans >= Integer.MAX_VALUE - 1000){
            System.out.println(-1);
        }else System.out.println(ans);
        System.out.println();
        
        for(int i = 0; i < coins.length; i++){
            Arrays.fill(dp[i], 0);
        }
         ans = coinChangeTabulation(coins,coins.length, target,dp);
        if(ans >= Integer.MAX_VALUE - 1000){
            System.out.println(-1);
        }else System.out.println(ans);
        System.out.println();
        
         ans = coinChangeOptimal(coins, coins.length, target);
        if(ans >= Integer.MAX_VALUE - 1000){
            System.out.println(-1);
        }else System.out.println(ans);
        System.out.println();
        
    }

    
    public static int coinChangeRecursion(int[] coins, int index,int target){
        if(index == 0) {
            if(target % coins[0] == 0){
                return target/coins[0];
            }else return Integer.MAX_VALUE - 1000;
        }
        
        int notTake = 0 + coinChangeRecursion(coins, index-1, target);
        int take = Integer.MAX_VALUE;
        if(coins[index] <= target) take = 1 + coinChangeRecursion(coins, index, target-coins[index]);
        
        return Math.min(notTake,take);
    }

    private static int coinChangeRecursionDP(int[] coins, int index, int target, int[][] dp) {
        if(index == 0) {
            if(target % coins[0] == 0){
                return target/coins[0];
            }else return Integer.MAX_VALUE - 1000;
        }
        
        if(dp[index][target] != -1) return dp[index][target];

        int notTake = 0 + coinChangeRecursionDP(coins, index-1, target,dp);
        int take = Integer.MAX_VALUE;
        if(coins[index] <= target) take = 1 + coinChangeRecursionDP(coins, index, target-coins[index],dp);
        
        return dp[index][target] = Math.min(notTake,take);
    }

    private static int coinChangeTabulation(int[] coins,int n, int target, int[][] dp) {
        if(coins.length == 1){
            if(target % coins[0] == 0){
                return target/coins[0];
            }else{
                return -1;
            }
        }
        for(int i = 0; i< dp[0].length; i++){
            if(i % coins[0] == 0) dp[0][i] = i/coins[0];
            else dp[0][i] = Integer.MAX_VALUE-1000;
        }

        for(int i = 1; i< n; i++){
            for(int j = 0; j<= target; j++){
                int notTake = 0 + dp[i-1][j];
                int take = Integer.MAX_VALUE;
                if(coins[i] <= j) take = 1 + dp[i][j-coins[i]];
                
                dp[i][j] = Math.min(notTake,take);
            }
        }

        return dp[n-1][target];
    }

    private static int coinChangeOptimal(int[] coins,int n, int target) {
        if(coins.length == 1){
            if(target % coins[0] == 0){
                return target/coins[0];
            }else{
                return -1;
            }
        }
        
        int[] prev = new int[target+1];
        int[] curr= new int[target+1];

        System.out.println(Arrays.toString(prev));
        
        for(int i = 0; i< prev.length; i++){
            if(i % coins[0] == 0) prev[i] = i/coins[0];
            else prev[i] = Integer.MAX_VALUE-1000;
        }
        System.out.println(Arrays.toString(prev));

        for(int i = 1; i< n; i++){
            for(int j = 0; j<= target; j++){
                int notTake = 0 + prev[j];
                int take = Integer.MAX_VALUE;
                if(coins[i] <= j) take = 1 + curr[j-coins[i]];
                
                curr[j] = Math.min(notTake,take);
            }
            prev = Arrays.copyOf(curr, curr.length);
        }

        return prev[target];
    }
}

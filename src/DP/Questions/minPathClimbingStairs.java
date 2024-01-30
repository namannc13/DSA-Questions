package DP.Questions;

import java.util.Arrays;

class minPathClimbingStairs {
    public static void main(String[] args) {
        int[] cost = {10,15};
        System.out.println(minCostClimbingStairs(cost));
    }
    public static int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length+1];
        Arrays.fill(dp, -1); 
        // return Math.min(minCostClimbingStairsRecursion(cost, cost.length-2,dp), minCostClimbingStairsRecursion(cost, cost.length-1,dp)); // question mentioned we can start at the first step or the second step
        // return Math.min(minCostClimbingStairsTabulation(cost,cost.length-1,dp),minCostClimbingStairsTabulation(cost,cost.length-2,dp)); // question mentioned we can start at the first step or the second step
        return Math.min(minCostClimbingStairsOptimal(cost,cost.length-1),minCostClimbingStairsOptimal(cost, cost.length-2)); // question mentioned we can start at the first step or the second step
    }
    public int minCostClimbingStairsRecursion(int[] cost, int n, int[] dp){
        if(n == 0) return cost[0];
        if(n == 1) return cost[1];
        if(n < 0) return 0;

        if(dp[n] != -1) return dp[n];

        int costL = cost[n] + minCostClimbingStairsRecursion(cost, n-1,dp);
        int costR = cost[n] + minCostClimbingStairsRecursion(cost, n-2,dp);

        return dp[n] = Math.min(costL,costR);
    }
    public int minCostClimbingStairsTabulation(int[] cost,int n,int[] dp ){
        dp[0] = cost[0];
        dp[1] = cost[1];

        for(int i = 2; i<= n; i++ ){
            int costL = cost[i] + dp[i-1];
            int costR = cost[i] + dp[i-2];
            dp[i] = Math.min(costL,costR);
        }

        return dp[n];
    }
    public static int minCostClimbingStairsOptimal(int[] cost, int n){
        int prev2 = cost[0];
        int prev1 = cost[1];

        if(cost.length == 2) return Math.min(prev2,prev1);

        for(int i =2; i<=n; i ++){
            int curi = prev1 + prev2;
            int costL = cost[i] + prev1;
            int costR = cost[i] + prev2;
            curi = Math.min(costL, costR);
            prev2 = prev1;
            prev1 = curi;
        }

        return prev1;
    }
}

package DP.PartitionDP;

import java.util.Arrays;

public class minimumCostToCutTheStick {
    public static void main(String[] args) {
        int[] cuts = {5,6,1,4,2};
        int n = 9;

        //modify the array
        int[] cuts2 = {0,5,6,1,4,2,n};
        Arrays.sort(cuts2);
        System.out.println(minimumCostToCutTheStickRecursion(cuts2, 1, cuts.length));

        int[][] dp = new int[cuts.length+1][cuts.length+1];
        for(int i = 0; i< dp.length; i++){
            Arrays.fill(dp[i],-1);
        }
        System.out.println(minimumCostToCutTheStickRecursionDP(cuts2, 1, cuts.length,dp));
        
        int[][] dp2 = new int[cuts.length+2][cuts.length+2];
        for(int i = 0; i< dp2.length; i++){
            Arrays.fill(dp2[i],0);
        }
        System.out.println(minimumCostToCutTheStickTabulation(cuts2,dp2));
        for(int i = 0; i< dp2.length; i++){
            System.out.println(Arrays.toString(dp2[i]));
        }
    }

    
    private static int minimumCostToCutTheStickRecursion(int[] cuts2, int i, int j) {
        if(i>j) return 0;
        
        int min = Integer.MAX_VALUE;
        for(int k = i; k <= j; k++){
            int cost = cuts2[j+1] - cuts2[i-1] + minimumCostToCutTheStickRecursion(cuts2, i, k-1) + minimumCostToCutTheStickRecursion(cuts2, k+1, j);
            min = Math.min(min, cost);
        }
        
        return min;
    }
    
    private static int minimumCostToCutTheStickRecursionDP(int[] cuts2, int i, int j, int[][] dp) {
        if(i>j) return 0;
        
        if(dp[i][j] != -1) return dp[i][j]; 
        
        int min = Integer.MAX_VALUE;
        for(int k = i; k <= j; k++){
            int cost = cuts2[j+1] - cuts2[i-1] + minimumCostToCutTheStickRecursion(cuts2, i, k-1) + minimumCostToCutTheStickRecursion(cuts2, k+1, j);
            min = Math.min(min, cost);
        }
        
        return dp[i][j] = min;
    }

    private static int minimumCostToCutTheStickTabulation(int[] cuts2, int[][] dp) {
        for(int i = dp.length-2; i > 0; i--){
            for(int j = 1; j<=cuts2.length-2; j++){
                if(i>j) continue;
                int min = Integer.MAX_VALUE;
                for(int k = i; k <= j; k++){
                    int cost = cuts2[j+1] - cuts2[i-1] + dp[i][k-1] + dp[k+1][j];
                    min = Math.min(min, cost);
                }
                
                dp[i][j] = min;
            }
        }
        return dp[1][cuts2.length-2];
    }
}

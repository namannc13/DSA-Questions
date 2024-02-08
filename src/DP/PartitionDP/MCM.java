package DP.PartitionDP;

import java.util.Arrays;

public class MCM {
    public static void main(String[] args) {
        int[] arr = {10,15,20,25};
        int n = 4;

        System.out.println(MCMRecursion(arr,1,n-1));

        int[][] dp = new int[n][n];
        for(int i = 0 ;i < n; i++){
            Arrays.fill(dp[i], -1);
        }
        System.out.println(MCMRecursionDP(arr, 1, n-1, dp));
        
        for(int i = 0 ;i < n; i++){
            Arrays.fill(dp[i], 0);
        }
        System.out.println(MCMTabulation(arr, dp));
    }

    private static int MCMRecursion(int[] arr, int i, int j) {
        if( i == j) return 0;

        int mini =Integer.MAX_VALUE-1000;
        for(int k = i; k<j; k++){
            int steps = arr[i-1] * arr[k] * arr[j] + MCMRecursion(arr, i, k) + MCMRecursion(arr, k+1, j);
            if(steps < mini) mini = steps;
        }
        return mini;
    }

    private static int MCMRecursionDP(int[] arr, int i, int j, int[][] dp) {
        if( i == j) return 0;

        if(dp[i][j] != -1) return dp[i][j];

        int mini =Integer.MAX_VALUE-1000;
        for(int k = i; k<j; k++){
            int steps = arr[i-1] * arr[k] * arr[j] + MCMRecursion(arr, i, k) + MCMRecursion(arr, k+1, j);
            if(steps < mini) mini = steps;
        }
        return dp[i][j] = mini;
    }

    private static int MCMTabulation(int[] arr, int[][] dp) {
        for(int i = 0; i<dp.length; i++){
            dp[i][i] = 0;
        }

        for(int i = dp.length-1; i> 0; i--){
            for(int j = i+1; j< dp.length; j++){
                int mini =Integer.MAX_VALUE-1000;
                for(int k = i; k<j; k++){
                    int steps = arr[i-1] * arr[k] * arr[j] + dp[i][k] + dp[k+1][j];
                    if(steps < mini) mini = steps;
                }
                dp[i][j] = mini;
            }
        }
        return dp[1][dp.length-1];
    }
}

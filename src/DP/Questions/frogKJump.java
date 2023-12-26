package DP.Questions;

import java.util.Arrays;

public class frogKJump {
    public static int frogJumpRecursion(int[] energy, int n, int k) {
        if (n == 0)
            return 0;

        int minSteps = Integer.MAX_VALUE;
        for (int j = 1; j <= k; j++) {
            if (n > j - 1) {
                int jump = frogJumpRecursion(energy, n - j, k) + Math.abs(energy[n] - energy[n - j]);
                minSteps = Math.min(minSteps, jump);
            }
        }
        return minSteps;
    }

    public static int frogJumpDP(int[] energy, int n, int[] dp, int k) {
        if (n == 0)
            return 0;

        if (dp[n] != -1)
            return dp[n];

        int minSteps = Integer.MAX_VALUE;
        for (int j = 1; j <= k; j++) {
            if (n > j - 1) {
                int jump = frogJumpDP(energy, n - j, dp, k) + Math.abs(energy[n] - energy[n - j]);
                minSteps = Math.min(minSteps, jump);
            }
        }
        return dp[n] = minSteps;
    }

    public static int frogJumpTabulation(int[] energy, int n, int[] dp, int k) {
        dp[0] = 0;

        // Tabulation -> bottom up
        for (int i = 1; i <= n; i++) {
            int minSteps = Integer.MAX_VALUE;
            for (int j = 1; j <= k; j++) {
                if (i > j - 1) {
                    int jump = dp[i-j] + Math.abs(energy[i] - energy[i - j]);
                    minSteps = Math.min(minSteps, jump);
                }
            }
            dp[i] = minSteps;
        }

        return dp[n];
    }

    public static void main(String[] args) {
        int[] energy = { 10, 40, 60, 30 ,35}; // cost of jumping from one rock to another
        int k = 3; // k is the minimum number of jumps that you can take
        System.out.println(frogJumpRecursion(energy, energy.length - 1, k));
        int[] dp = new int[5];
        // for( int i = 0; i < dp.length; i ++){
        // dp[i] = -1;
        // }
        Arrays.fill(dp, -1);
        System.out.println(frogJumpDP(energy, energy.length - 1, dp, k));
        System.out.println(frogJumpTabulation(energy, energy.length -1 , dp,k));
    }
}

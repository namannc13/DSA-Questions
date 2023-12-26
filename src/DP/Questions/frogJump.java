package DP.Questions;

import java.util.Arrays;

public class frogJump { // frog is on starting and wants to go to the last rock, what is the minimum cost it will take to reach the last rock // can only jump to next rock or to the nexttonext rock 
    public static int frogJumpRecursion(int[] energy, int n){
        if(n==0) return 0;

        int left = frogJumpRecursion(energy, n-1) + Math.abs(energy[n] - energy[n-1]);
        int right = Integer.MAX_VALUE;
        if(n>1)
            right = frogJumpRecursion(energy, n-2) + Math.abs(energy[n] - energy[n-2]);

        return Math.min(left,right);
    }

    public static int frogJumpDP(int[] energy, int n, int[] dp){
        if(n==0) return 0;

        if(dp[n] != -1) return dp[n];

        int left = frogJumpDP(energy, n-1,dp) + Math.abs(energy[n] - energy[n-1]);
        int right = Integer.MAX_VALUE;
        if(n>1)
            right = frogJumpDP(energy, n-2,dp) + Math.abs(energy[n] - energy[n-2]);

        return dp[n] = Math.min(left,right);
    }

    public static int frogJumpTabulation(int[] energy,int n, int[] dp) {
        dp[0] = 0;

        // Tabulation -> bottom up
        for (int i = 1; i <= n; i++) {
            int firstStep = dp[i - 1] + Math.abs(energy[n] - energy[n-1]);
            int secondStep = Integer.MAX_VALUE;
            if(i> 1)
                secondStep = dp[i-2] + Math.abs(energy[n] - energy[n-2]);

            dp[i] = Math.min(firstStep,secondStep);
        }

        return dp[n];
    }

    public static int frogJumpOptimal(int[] energy, int n) {
        int prev = 0;
        int prev2 = 0;
        int curi = 0;

        for (int i = 1; i <= n; i++) {
            int firstStep = prev + Math.abs(energy[n] - energy[n-1]);
            int secondStep = Integer.MAX_VALUE;
            if(i> 1)
                secondStep = prev2 + Math.abs(energy[n] - energy[n-2]);

            curi = Math.min(firstStep,secondStep);
            prev2 = prev;
            prev = curi;
        }

        return prev;
    }
    public static void main(String[] args) {
        int[] energy = {10,40,60,30}; // cost of jumping from one rock to another 
        System.out.println(frogJumpRecursion(energy, energy.length-1));
        int[] dp = new int[5];
        // for( int i = 0; i < dp.length; i ++){
        //     dp[i] = -1;
        // }
        Arrays.fill(dp, -1);
        System.out.println(frogJumpDP(energy, energy.length-1, dp));
        System.out.println(frogJumpTabulation(energy, energy.length -1 , dp));
        System.out.println(frogJumpOptimal(energy, energy.length-1));
    }
}

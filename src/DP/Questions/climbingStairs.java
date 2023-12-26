package DP.Questions;

import java.util.Scanner;

public class climbingStairs {
    public static int climbStairs(int n, int[] dp) {
        if (n == 0)
            return 1;
        if (n == 1)
            return 1;

        if (dp[n] != -1)
            return dp[n];

        int left = climbStairs(n - 1, dp);
        int right = climbStairs(n - 2, dp);

        return dp[n] = left + right;
    }

    public static int climbStairsTabulation(int n, int[] dp) {
        // If you notice, these two are just the base conditions of Memoization
        dp[0] = 1;
        dp[1] = 1;

        // Tabulation -> bottom up
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    public static int climbStairsOptimal(int n) {
        int prev2 = 1;
        int prev1 = 1;
        int curi = prev1 + prev2;

        for (int i = 2; i <= n; i++) {
            curi = prev1 + prev2;
            prev2 = prev1;
            prev1 = curi;
        }

        return prev1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dp = new int[n + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = -1;
        }
        System.out.println(climbStairs(n,dp));
        System.out.println(climbStairsTabulation(n, dp));
        System.out.println(climbStairsOptimal(n));
        sc.close();
    }
}

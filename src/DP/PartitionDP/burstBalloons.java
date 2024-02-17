package DP.PartitionDP;

import java.util.Arrays;

public class burstBalloons {
    public static void main(String[] args) {
        int[] balloons = {3,1,5,8};
        int n = balloons.length;

        //modify the array
        int[] balloons2 = {1,3,1,5,8,1};

        System.out.println(burstBalloonsRecursion(balloons2, 1,balloons.length));

        int[][] dp = new int[n+1][n+1];
        for(int i = 0; i< dp.length; i++){
            Arrays.fill(dp[i], -1);
        }
        System.out.println(burstBalloonsRecursionDP(balloons2, 1, balloons.length, dp));
        
        int[][] dp2 = new int[n+2][n+2];

        for(int i = 0; i< dp2.length; i++){
            Arrays.fill(dp2[i], 0);
        }
        System.out.println(burstBalloonsTabulation(balloons2, dp2));
    }

    private static int burstBalloonsRecursion(int[] balloons, int i, int j){
        if(i>j) return 0;

        int max = Integer.MIN_VALUE;
        for(int k = i; k<= j; k++){
            int coins = balloons[i-1] * balloons[j+1] * balloons[k] + burstBalloonsRecursion(balloons, i, k-1) + burstBalloonsRecursion(balloons, k+1, j);
            max = Math.max(coins,max);
        }
        return max;
    }

    private static int burstBalloonsRecursionDP(int[] balloons, int i, int j, int[][] dp){
        if(i>j) return 0;

        if(dp[i][j] != -1) return dp[i][j];

        int max = Integer.MIN_VALUE;
        for(int k = i; k<= j; k++){
            int coins = balloons[i-1] * balloons[j+1] * balloons[k] + burstBalloonsRecursion(balloons, i, k-1) + burstBalloonsRecursion(balloons, k+1, j);
            max = Math.max(coins,max);
        }
        return dp[i][j] = max;
    }

    private static int burstBalloonsTabulation(int[] balloons, int[][] dp ){
        for(int i= balloons.length-2; i > 0; i--){
            for(int j = 1; j<=balloons.length-2; j++){
                if(i>j) continue;
                int max = Integer.MIN_VALUE;
                for(int k = i; k<= j; k++){
                    int coins = balloons[i-1] * balloons[j+1] * balloons[k] + dp[i][k-1] + dp[k+1][j];
                    max = Math.max(coins,max);
                }
                dp[i][j] = max;
            }

        }
        return dp[1][balloons.length-2];
    }
}

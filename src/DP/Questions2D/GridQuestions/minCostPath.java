package DP.Questions2D.GridQuestions;

import java.util.Arrays;

public class minCostPath {
    public static void main(String[] args) {
        int row = 3;
        int col = 3;
        int[][] grid = {
            {4,6,8},
            {1,2,4},
            {8,6,3}
        };
        
        System.out.println("Using Recursion: " + minCostPathRecursion(grid, row-1, col-1));

        int[][] dp = new int[row][col];
        for(int i = 0; i< row; i ++){
            Arrays.fill(dp[i], -1);
        }
        
        System.out.println("Using Recursion with DP: " + minCostPathRecursionDP(grid, row-1, col-1, dp));

        for(int i = 0; i< row; i ++){
            Arrays.fill(dp[i], 0);
        }

        System.out.println("Using Tabulation: " + minCostPathTabulation(grid, row-1, col-1, dp));

        // for(int i = 0; i< row; i ++){
        //     System.out.println(Arrays.toString(dp[i]));
        // }
    }

    private static int minCostPathRecursion(int[][] grid, int row, int col) {
        if(row ==0 && col == 0) return grid[row][col];
        if(row < 0 || col < 0) return Integer.MAX_VALUE - 1000; // when we are out of bounds of the grid, we will send the maximum value so that in the return statement , this will never be considered when the minimum of the two are being picked // Integer.MAX_VALUE - 1000 ( because when the max value is getting returned , in the up, left variable it is being added to the grid[row][col] which is making it go beyond the Integer range, i guess that's the problem ( hopefully ))

        int up = grid[row][col] + minCostPathRecursion(grid, row-1, col);
        int left = grid[row][col] + minCostPathRecursion(grid, row, col-1);

        return Math.min(up,left);
    }

    private static int minCostPathRecursionDP(int[][] grid,int row, int col,int[][] dp){
        if(row ==0 && col == 0) return grid[row][col];
        if(row < 0 || col < 0) return Integer.MAX_VALUE - 1000;

        if(dp[row][col] != -1) return dp[row][col];

        int up = grid[row][col] + minCostPathRecursionDP(grid, row-1, col,dp);
        int left = grid[row][col] + minCostPathRecursionDP(grid, row, col-1,dp);

        return dp[row][col] = Math.min(up,left);
    }

    private static int minCostPathTabulation(int[][] grid, int row, int col, int[][] dp){
        dp[0][0] = grid[0][0];

        for(int i = 0; i <= row; i ++){
            for(int j=0; j<= col; j++){
                if(i==0 && j==0) continue;

                int up =grid[i][j];
                int left =grid[i][j];
                if(i> 0) up += dp[i-1][j];
                else up += Integer.MAX_VALUE - 1000;
                if(j> 0) left += dp[i][j-1];
                else left += Integer.MAX_VALUE - 1000;

                dp[i][j] = Math.min(up,left);
            }
        }
        return dp[row][col];
    }
}

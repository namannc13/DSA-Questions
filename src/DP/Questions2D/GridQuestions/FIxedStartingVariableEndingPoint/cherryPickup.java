package DP.Questions2D.GridQuestions.FIxedStartingVariableEndingPoint;

import java.util.Arrays;

public class cherryPickup {
    public static void main(String[] args) {
        int[][] grid = {
                { 2, 3, 1, 2 },
                { 3, 4, 2, 2 },
                { 5, 6, 3, 5 },
        };

        System.out.println("Using Recursion: " + cherryPickupRecursion(grid, 0, 0, grid[0].length - 1)); // in case of// variable// ending// points, we// will start// from the// index (0,0)// // there are// two people ,// one will// start from// 0,0 and the// second will// start from// 0,grid.length-1

        int[][][] dp = new int[grid.length][grid[0].length][grid[0].length];
        // int[][] dp = {
        // {1},
        // {2,3},
        // {3,1,1},
        // {4,3,1,1}
        // };
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        System.out.println("Using Recursion with DP: " + cherryPickupRecursionDP(grid, 0, 0, grid[0].length - 1, dp));

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        System.out.println("Using Tabulation: " + cherryPickupTabulation(grid, dp));

        
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.println(Arrays.toString(dp[i][j]));
            }
            System.out.println();
        }
    }

    private static int cherryPickupRecursion(int[][] grid, int currentRow, int currentCol1, int currentCol2) { // in// case// of// variable// ending// points,// we// will// start// from// the// index// (0,0)// //// both// person's// row// will // be the// same// every// time// so we // will// only// take// one// row// pointer
        if (currentCol1 < 0 || currentCol1 > grid[0].length - 1 || currentCol2 < 0 || currentCol2 > grid[0].length - 1)
            return Integer.MIN_VALUE + 1000;
        if (currentRow == grid.length - 1) {
            if (currentCol1 == currentCol2)
                return grid[currentRow][currentCol1]; // or we can also write currentCol2 here , doesn't matter
            return grid[currentRow][currentCol1] + grid[currentRow][currentCol2];
        }

        int maxi = Integer.MIN_VALUE;
        for (int col1 = -1; col1 <= 1; col1++) {
            for (int col2 = -1; col2 <= 1; col2++) {
                if (currentCol1 == currentCol2) maxi = Math.max(maxi, grid[currentRow][currentCol1]+ cherryPickupRecursion(grid, currentRow + 1, currentCol1 + col1, currentCol2 + col2));
                else maxi = Math.max(maxi, grid[currentRow][currentCol1] + grid[currentRow][currentCol2] + cherryPickupRecursion(grid, currentRow + 1, currentCol1 + col1, currentCol2 + col2));
            }
        }

        return maxi;
    }

    private static int cherryPickupRecursionDP(int[][] grid, int currentRow, int currentCol1, int currentCol2,
            int[][][] dp) {
        if (currentCol1 < 0 || currentCol1 > grid[0].length - 1 || currentCol2 < 0 || currentCol2 > grid[0].length - 1)
            return Integer.MIN_VALUE + 1000;
        if (currentRow == grid.length - 1) {
            if (currentCol1 == currentCol2)
                return grid[currentRow][currentCol1]; // or we can also write currentCol2 here , doesn't matter
            return grid[currentRow][currentCol1] + grid[currentRow][currentCol2];
        }

        if (dp[currentRow][currentCol1][currentCol2] != -1)
            return dp[currentRow][currentCol1][currentCol2];

        int maxi = Integer.MIN_VALUE;
        for (int col1 = -1; col1 <= 1; col1++) {
            for (int col2 = -1; col2 <= 1; col2++) {
                if (currentCol1 == currentCol2) maxi = Math.max(maxi, grid[currentRow][currentCol1]+ cherryPickupRecursion(grid, currentRow + 1, currentCol1 + col1, currentCol2 + col2));
                else  maxi = Math.max(maxi, grid[currentRow][currentCol1] + grid[currentRow][currentCol2] + cherryPickupRecursion(grid, currentRow + 1, currentCol1 + col1, currentCol2 + col2));
            }
        }

        return dp[currentRow][currentCol1][currentCol2] = maxi;
    }

    private static int cherryPickupTabulation(int[][] grid, int[][][] dp) {
        for (int j1 = 0; j1 < grid[0].length; j1++) {
            for (int j2 = 0; j2 < grid[0].length; j2++) {
                if (j1 == j2) dp[grid.length - 1][j1][j2] = grid[grid.length - 1][j1]; // or grid[i][j2];
                else dp[grid.length - 1][j1][j2] = grid[grid.length - 1][j1] + grid[grid.length - 1][j2];
            }
        }

        for (int i = grid.length - 2; i >= 0; i--) {
            for (int j = 0; j < grid[0].length; j++) {
                for (int x = 0; x < grid[0].length; x++) {
                    int maxi = Integer.MIN_VALUE;
                    for (int col1 = -1; col1 <= 1; col1++) {
                        for (int col2 = -1; col2 <= 1; col2++) {
                            if (j + col1 >= 0 && j + col1 <= grid[0].length - 1 && x + col2 >= 0  && x + col2 <= grid[0].length - 1) {
                                if (j == x) maxi = Math.max(maxi, grid[i][j] + dp[i + 1][j + col1][x + col2]);
                                else  maxi = Math.max(maxi, grid[i][j] + grid[i][x] + dp[i + 1][j + col1][x + col2]);
                            }
                        }
                    }
                    dp[i][j][x] = maxi;
                }
            }
        }
        return dp[0][0][grid[0].length-1];
    }
}

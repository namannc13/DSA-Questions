package DP.Questions2D.GridQuestions.FIxedStartingVariableEndingPoint;

import java.util.Arrays;

public class Triangle {
    public static void main(String[] args) {
        int[][] grid = {
            {1},
            {2,3},
            {3,1,1},
            {4,3,1,1}
        };
        
        System.out.println("Using Recursion: " + TriangleRecursion(grid, 0,0)); // in case of variable ending points, we will start from the index (0,0)

        int[][] dp = new int[grid.length][grid.length];
        // int[][] dp = {
        //     {1},
        //     {2,3},
        //     {3,1,1},
        //     {4,3,1,1}
        // };
        for(int i = 0; i< dp.length; i ++){
            Arrays.fill(dp[i], -1);
        }
        
        System.out.println("Using Recursion with DP: " + TriangleRecursionDP(grid, 0, 0, dp));

        for(int i = 0; i< dp.length; i ++){
            Arrays.fill(dp[i], 0);
        }

        System.out.println("Using Tabulation: " + TriangleTabulation(grid, dp));

        for(int i = 0; i< dp.length; i ++){
            System.out.println(Arrays.toString(dp[i]));
        }
    }

    private static int TriangleRecursion(int[][] grid, int currentRow, int currentCol) { // in case of variable ending points, we will start from the index (0,0)
        if(currentRow == grid.length-1) return grid[currentRow][currentCol];
        
        int down = grid[currentRow][currentCol] + TriangleRecursion(grid, currentRow+1, currentCol);
        int diagonal = grid[currentRow][currentCol] + TriangleRecursion(grid, currentRow+1, currentCol+1);

        return Math.min(down,diagonal);
    }

    private static int TriangleRecursionDP(int[][] grid,int currentRow, int currentCol,int[][] dp){
        if(currentRow == grid.length-1) return grid[currentRow][currentCol];

        if(dp[currentRow][currentCol] != -1) return dp[currentRow][currentCol];

        int down = grid[currentRow][currentCol] + TriangleRecursionDP(grid, currentRow+1, currentCol,dp);
        int diagonal = grid[currentRow][currentCol] + TriangleRecursionDP(grid, currentRow+1, currentCol+1,dp);

        return dp[currentRow][currentCol] = Math.min(down,diagonal);
    }

    private static int TriangleTabulation(int[][] grid, int[][] dp){
        int noOfColumnsInGridLastRow = grid[grid.length-1].length;
        for(int i =0; i< noOfColumnsInGridLastRow; i++){ // initializing the dp array // base case
            dp[grid.length-1][i] = grid[grid.length-1][i];
        }
        
        for(int i = grid.length-2; i >=0; i--){
            for(int j = grid[i].length-1; j>=0; j--){ // we can also write int j = i 

                int down = grid[i][j] + dp[i+1][j];
                int diagonal = grid[i][j] + dp[i+1][j+1];

                dp[i][j] = Math.min(down,diagonal);
            }
        }
        return dp[0][0];
    }
}

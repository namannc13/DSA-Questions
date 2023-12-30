package DP.Questions2D.GridQuestions.VariableStartingVariableEndingPoint;

import java.util.Arrays;

public class maxFallingPathSum {
    public static void main(String[] args) {
        int[][] grid = {
            {1,2,3,4},
            {2,3,4,3},
            {3,1,1,4},
            {4,3,1,1}
        };
        
        System.out.println("Using Recursion: " + maxFallingPathSumRecursionHelper(grid, grid.length-1,grid.length-1)); // in case of variable ending points, we will start from the index (0,0)

        int[][] dp = new int[grid.length][grid.length];
        for(int i = 0; i< dp.length; i ++){
            Arrays.fill(dp[i], -1);
        }
        
        System.out.println("Using Recursion with DP: " + maxFallingPathSumRecursionDPHelper(grid, grid.length-1,grid.length-1, dp));

        for(int i = 0; i< dp.length; i ++){
            Arrays.fill(dp[i], 0);
        }

        System.out.println("Using Tabulation: " + maxFallingPathSumTabulationHelper(grid, grid.length-1,grid.length-1, dp));

        for(int i = 0; i< dp.length; i ++){
            System.out.println(Arrays.toString(dp[i]));
        }
    }

    private static int maxFallingPathSumRecursionHelper(int[][] grid, int row, int col) { // in case of variable starting point and variable ending point, we need to start making recursion calls from every cell of the bottom of the tree
        int max = Integer.MIN_VALUE;
        for(int i = 0; i <= col; i++){
            max = Math.max(max,maxFallingPathSumRecursion(grid, row, i));
        }
        return max;
    }
    private static int maxFallingPathSumRecursion(int[][] grid, int row, int col){
        if(col < 0 || col > grid.length-1) return Integer.MIN_VALUE + 1000; // make sure to put this if condition first !!!! 
        if(row == 0) return grid[row][col];
        
        int up = grid[row][col] + maxFallingPathSumRecursion(grid, row-1, col);
        int diagonalRight = grid[row][col] + maxFallingPathSumRecursion(grid, row-1, col+1);
        int diagonalLeft = grid[row][col] + maxFallingPathSumRecursion(grid, row-1, col-1);

        return Math.max(up,Math.max(diagonalLeft,diagonalRight));
    }

    private static int maxFallingPathSumRecursionDPHelper(int[][] grid, int row, int col,int[][] dp) { // in case of variable starting point and variable ending point, we need to start making recursion calls from every cell of the bottom of the tree
        int max = Integer.MIN_VALUE;
        for(int i = 0; i <= col; i++){
            max = Math.max(max,maxFallingPathSumRecursionDP(grid, row, i,dp));
        }
        return max;
    }
    private static int maxFallingPathSumRecursionDP(int[][] grid, int row, int col,int[][] dp){
        if(col < 0 || col > grid.length-1) return Integer.MIN_VALUE + 1000; // make sure to put this if condition first !!!!
        if(row == 0) return grid[row][col];

        if(dp[row][col] != -1) return dp[row][col];
        
        int up = grid[row][col] + maxFallingPathSumRecursionDP(grid, row-1, col,dp);
        int diagonalRight = grid[row][col] + maxFallingPathSumRecursionDP(grid, row-1, col+1,dp);
        int diagonalLeft = grid[row][col] + maxFallingPathSumRecursionDP(grid, row-1, col-1,dp);

        return dp[row][col] = Math.max(up,Math.max(diagonalLeft,diagonalRight));
    }

    private static int maxFallingPathSumTabulationHelper(int[][] grid, int row, int col,int[][] dp) { // in case of variable starting point and variable ending point, we need to start making recursion calls from every cell of the bottom of the tree
        int max = Integer.MIN_VALUE;
        maxFallingPathSumTabulation(grid,dp); // the last index of dp array will have all the answers
        for(int i = 0; i <= col; i++){
            max = Math.max(max,dp[grid.length-1][i]);
        }
        return max;
    }
    private static void maxFallingPathSumTabulation(int[][] grid, int[][] dp){
        int noOfColumnsInGridFirstRow = grid[0].length;
        for(int i =0; i< noOfColumnsInGridFirstRow; i++){ // initializing the dp array // base case
            dp[0][i] = grid[0][i];
        }
        
        for(int i = 1; i <=grid.length-1; i++){
            for(int j = 0; j<=grid[i].length-1; j++){ 
                int up = grid[i][j];
                int diagonalRight = grid[i][j];
                int diagonalLeft = grid[i][j];

                if(i>0) up+= dp[i-1][j];
                else up += Integer.MIN_VALUE + 1000;
                if(i>0 && j<grid.length-1) diagonalRight += dp[i-1][j+1];
                else diagonalRight += Integer.MIN_VALUE + 1000;
                if(i>0 && j>0) diagonalLeft += dp[i-1][j-1];
                else diagonalLeft += Integer.MIN_VALUE + 1000;

                dp[i][j] = Math.max(up,Math.max(diagonalLeft,diagonalRight));
            }
        }
    }
}

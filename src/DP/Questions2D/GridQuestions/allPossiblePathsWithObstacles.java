package DP.Questions2D.GridQuestions;

import java.util.Arrays;

public class allPossiblePathsWithObstacles {
    public static void main(String[] args) {
        int row = 3;
        int col = 3;
        int[][] grid = new int[row][col];
        grid[1][1] = -1; // the obstacle
        System.out.println("Using Recursion: " + allPossiblePathsWithObstaclesRecursion(grid, row-1, col-1));

        int[][] dp = new int[row][col];
        for(int i = 0; i< row; i ++){
            Arrays.fill(dp[i], -1);
        }
        
        System.out.println("Using Recursion with DP: " + allPossiblePathsWithObstaclesDP(grid, row-1, col-1, dp));

        for(int i = 0; i< row; i ++){
            Arrays.fill(dp[i], 0);
        }

        System.out.println("Using Tabulation: " + allPossiblePathsWithObstaclesTabulation(grid, row-1, col-1, dp));
    }

    private static int allPossiblePathsWithObstaclesRecursion(int[][] grid, int row, int col) {
        if(row>=0 && col >=0 && grid[row][col] == -1) return 0; // an additional case for the obstacles  // if the row and col is valid and it has an obstacle then return 
        if(row ==0 && col == 0) return 1;
        if(row < 0 || col < 0) return 0;

        int up = allPossiblePathsWithObstaclesRecursion(grid, row-1, col);
        int left = allPossiblePathsWithObstaclesRecursion(grid, row, col-1);

        return up + left;
    }

    private static int allPossiblePathsWithObstaclesDP(int[][] grid,int row, int col,int[][] dp){
        if(row>=0 && col >=0 && grid[row][col] == -1) return 0; // an additional case for the obstacles  // if the row and col is valid and it has an obstacle then return 
        if(row ==0 && col == 0) return 1;
        if(row < 0 || col < 0) return 0;

        if(dp[row][col] != -1) return dp[row][col];

        int up = allPossiblePathsWithObstaclesDP(grid, row-1, col,dp);
        int left = allPossiblePathsWithObstaclesDP(grid, row, col-1,dp);

        return dp[row][col] = up + left;
    }

    private static int allPossiblePathsWithObstaclesTabulation(int[][] grid, int row, int col, int[][] dp){
        // conditions for leetcode 
            // case for 1 length grid
            if(grid.length == 1){
                for(int i =0; i<= col; i++){
                    if(grid[0][i] == 1) return 0;
                }
                return 1;
            }
            // case for when the starting index has the obstacle
            if(grid[0][0] == 1) return 0;
        //base case
        dp[0][0] = 1;

        for(int i = 0; i <= row; i ++){ // <= row because row here is not the rowlength but it indicates the index
            for(int j = 0; j<= col; j++){ // <= col because col here is not the collength but it indicates the index
                
                if(i==0 && j==0){
                    continue; // because this is already computed in the base condition
                } 
                else if(grid[i][j] == -1){ // an additional case for the obstacles
                    dp[i][j] = 0;
                } 
                else{
                    int up = 0;
                    int left = 0;
                    if(i > 0) up = dp[i-1][j];
                    if(j > 0) left = dp[i][j-1];
                
                    dp[i][j] = up + left;
                }
            }
        }
        return dp[row][col];
    }
}

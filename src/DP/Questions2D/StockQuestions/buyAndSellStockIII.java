package DP.Questions2D.StockQuestions;

import java.util.Arrays;

public class buyAndSellStockIII {
    public static void main(String[] args) {
        int[] stock = {3,3,5,0,0,3,1,4};

        System.out.println(buyAndSellStockIIIRecursion(stock,0, 1,2));

        int[][][] dp = new int[stock.length][2][3];
        for(int i =0; i< dp.length; i++){
            for(int j = 0; j< dp[i].length; j++){
                Arrays.fill(dp[i][j], -1);
            }
        }
        System.out.println(buyAndSellStockIIIMemoization(stock, 0, 1,2, dp));

        int[][][] dp1 = new int[stock.length+1][2][3];
        for(int i =0; i< dp1.length; i++){
            for(int j = 0; j< dp1[i].length; j++){
                
                Arrays.fill(dp1[i][j], 0);
            }
        }
        System.out.println(buyAndSellStockIIITabulation(stock, dp1));

        System.out.println(buyAndSellStockIIIOptimal(stock));
    }

    private static int buyAndSellStockIIIOptimal(int[] stock) {
        int[][] ahead= new int[2][3];
        int[][] curr = new int[2][3];

        for(int index = stock.length-1; index>= 0; index--){
            for(int buy = 0; buy <=1; buy++){
                for(int cap = 1; cap<=2; cap++){
                    int profit = 0;
                    if(buy == 1){
                        profit = Math.max(-stock[index] + ahead[0][cap], 0 + ahead[1][cap]);
                    }else{
                        profit = Math.max(stock[index] + ahead[1][cap-1], 0 + ahead[0][cap]);
                    }
    
                    curr[buy][cap] = profit;
                }
            }
            ahead = curr;
        }

        return ahead[1][2];
    }

    private static int buyAndSellStockIIITabulation(int[] stock, int[][][] dp1) {
        for(int index = stock.length-1; index>= 0; index--){
            for(int buy = 0; buy <=1; buy++){
                for(int cap = 1; cap<=2; cap++){
                    int profit = 0;
                    if(buy == 1){
                        profit = Math.max(-stock[index] + dp1[index+1][0][cap], 0 + dp1[index+1][1][cap]);
                    }else{
                        profit = Math.max(stock[index] + dp1[index+1][1][cap-1], 0 + dp1[index+1][0][cap]);
                    }
    
                    dp1[index][buy][cap] = profit;
                }
            }
        }

        return dp1[0][1][2];
    }

    private static int buyAndSellStockIIIRecursion(int[] stock, int index, int buy, int cap) {
        if(cap == 0) return 0;
        if(index == stock.length) return 0; // we need to careful here as maybe we had buy == 1, but return 0 covers all cases because if we return 0, the value of this call will go into negative and will never be picked 
        
        int profit = 0;
        if(buy == 1){
            profit = Math.max(-stock[index] + buyAndSellStockIIIRecursion(stock, index+1, 0,cap), 0 + buyAndSellStockIIIRecursion(stock, index+1, 1,cap) );
        }else{
            profit = Math.max(stock[index] + buyAndSellStockIIIRecursion(stock, index+1, 1,cap-1), 0 + buyAndSellStockIIIRecursion(stock, index+1, 0,cap));
        }

        return profit;
    }
    
    private static int buyAndSellStockIIIMemoization(int[] stock, int index, int buy,int cap, int[][][] dp){
        if(cap == 0) return 0;
        if(index == stock.length) return 0; // we need to be careful here as maybe we had buy == 1, but return 0 covers all cases because if we return 0, the value of this call will go into negative and will never be picked 

        if(dp[index][buy][cap] != -1) return dp[index][buy][cap];

        int profit = 0;
        if(buy == 1){
            profit = Math.max(-stock[index] + buyAndSellStockIIIMemoization(stock, index+1, 0,cap,dp), 0 + buyAndSellStockIIIMemoization(stock, index+1, 1,cap,dp) );
        }else{
            profit = Math.max(stock[index] + buyAndSellStockIIIMemoization(stock, index+1, 1,cap-1,dp), 0 + buyAndSellStockIIIMemoization(stock, index+1, 0,cap,dp));
        }

        return dp[index][buy][cap] =  profit;
    }
}

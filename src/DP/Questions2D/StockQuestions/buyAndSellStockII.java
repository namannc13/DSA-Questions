package DP.Questions2D.StockQuestions;

import java.util.Arrays;

public class buyAndSellStockII { // you can buy and sell multiple times
    public static void main(String[] args) {
        int[] stock = {1,2,3,4,5,6};

        System.out.println(buyAndSellStockIIRecursion(stock,0, 1));

        int[][] dp = new int[stock.length][2];
        for(int i =0; i< dp.length; i++){
            Arrays.fill(dp[i], -1);
        }
        System.out.println(buyAndSellStockIIMemoization(stock, 0, 1, dp));

        int[][] dp1 = new int[stock.length+1][2];
        for(int i =0; i< dp1.length; i++){
            Arrays.fill(dp1[i], 0);
        }
        System.out.println(buyAndSellStockIITabulation(stock, dp1));

        System.out.println(buyAndSellStockIIOptimal(stock));
    }

    private static int buyAndSellStockIIOptimal(int[] stock) {
        int[] ahead= new int[stock.length];
        int[] curr = new int[stock.length];

        ahead[0] = 0;
        ahead[1] = 0;

        for(int index = stock.length-1; index>= 0; index--){
            for(int buy = 0; buy <=1; buy++){
                int profit = 0;
                if(buy == 1){
                    profit = Math.max(-stock[index] + ahead[0], 0 + ahead[1]);
                }else{
                    profit = Math.max(stock[index] + ahead[1], 0 + ahead[0]);
                }

                curr[buy] = profit;
            }
            ahead = curr;
        }
        return ahead[1];
    }

    private static int buyAndSellStockIITabulation(int[] stock, int[][] dp1) {
        dp1[stock.length][0] = 0;
        dp1[stock.length][1] = 0;

        for(int index = stock.length-1; index>= 0; index--){
            for(int buy = 0; buy <=1; buy++){
                int profit = 0;
                if(buy == 1){
                    profit = Math.max(-stock[index] + dp1[index+1][0], 0 + dp1[index+1][1]);
                }else{
                    profit = Math.max(stock[index] + dp1[index+1][1], 0 + dp1[index+1][0]);
                }

                dp1[index][buy] = profit;
            }
        }

        return dp1[0][1];
    }

    private static int buyAndSellStockIIRecursion(int[] stock, int index, int buy) {
        if(index == stock.length) return 0; // we need to careful here as maybe we had buy == 1, but return 0 covers all cases because if we return 0, the value of this call will go into negative and will never be picked 

        int profit = 0;
        if(buy == 1){
            profit = Math.max(-stock[index] + buyAndSellStockIIRecursion(stock, index+1, 0), 0 + buyAndSellStockIIRecursion(stock, index+1, 1) );
        }else{
            profit = Math.max(stock[index] + buyAndSellStockIIRecursion(stock, index+1, 1), 0 + buyAndSellStockIIRecursion(stock, index+1, 0));
        }

        return profit;
    }
    
    private static int buyAndSellStockIIMemoization(int[] stock, int index, int buy, int[][] dp){
        if(index == stock.length) return 0; // we need to be careful here as maybe we had buy == 1, but return 0 covers all cases because if we return 0, the value of this call will go into negative and will never be picked 

        if(dp[index][buy] != -1) return dp[index][buy];

        int profit = 0;
        if(buy == 1){
            profit = Math.max(-stock[index] + buyAndSellStockIIMemoization(stock, index+1, 0,dp), 0 + buyAndSellStockIIMemoization(stock, index+1, 1,dp) );
        }else{
            profit = Math.max(stock[index] + buyAndSellStockIIMemoization(stock, index+1, 1,dp), 0 + buyAndSellStockIIMemoization(stock, index+1, 0,dp));
        }

        return dp[index][buy] =  profit;
    }
}
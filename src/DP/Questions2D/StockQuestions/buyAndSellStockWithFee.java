package DP.Questions2D.StockQuestions;

import java.util.Arrays;

// EXACTLY THE SAME QUES AS BUYANDSELLSTOCKII ( just a oneline difference)
public class buyAndSellStockWithFee {
    public static void main(String[] args) {
        int[] stock = {1,3,2,8,4,9};

        System.out.println(buyAndSellStockWithFeeRecursion(stock,0, 1, 2));

        int[][] dp = new int[stock.length][2];
        for(int i =0; i< dp.length; i++){
            Arrays.fill(dp[i], -1);
        }
        System.out.println(buyAndSellStockWithFeeMemoization(stock, 0, 1, dp, 2));

        int[][] dp1 = new int[stock.length+1][2];
        for(int i =0; i< dp1.length; i++){
            Arrays.fill(dp1[i], 0);
        }
        System.out.println(buyAndSellStockWithFeeTabulation(stock, dp1, 2));

        System.out.println(buyAndSellStockWithFeeOptimal(stock, 2));
    }

    private static int buyAndSellStockWithFeeOptimal(int[] stock, int fee) {
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
                    profit = Math.max(stock[index]-fee + ahead[1], 0 + ahead[0]);
                }

                curr[buy] = profit;
            }
            ahead = curr;
        }
        return ahead[1];
    }

    private static int buyAndSellStockWithFeeTabulation(int[] stock, int[][] dp1, int fee) {
        dp1[stock.length][0] = 0;
        dp1[stock.length][1] = 0;

        for(int index = stock.length-1; index>= 0; index--){
            for(int buy = 0; buy <=1; buy++){
                int profit = 0;
                if(buy == 1){
                    profit = Math.max(-stock[index] + dp1[index+1][0], 0 + dp1[index+1][1]);
                }else{
                    profit = Math.max(stock[index]-fee + dp1[index+1][1], 0 + dp1[index+1][0]);
                }

                dp1[index][buy] = profit;
            }
        }

        return dp1[0][1];
    }

    private static int buyAndSellStockWithFeeRecursion(int[] stock, int index, int buy, int fee) {
        if(index == stock.length) return 0; // we need to careful here as maybe we had buy == 1, but return 0 covers all cases because if we return 0, the value of this call will go into negative and will never be picked 

        int profit = 0;
        if(buy == 1){
            profit = Math.max(-stock[index] + buyAndSellStockWithFeeRecursion(stock, index+1, 0, fee), 0 + buyAndSellStockWithFeeRecursion(stock, index+1, 1,fee) );
        }else{
            profit = Math.max(stock[index]-fee + buyAndSellStockWithFeeRecursion(stock, index+1, 1, fee), 0 + buyAndSellStockWithFeeRecursion(stock, index+1, 0, fee));
        }

        return profit;
    }
    
    private static int buyAndSellStockWithFeeMemoization(int[] stock, int index, int buy, int[][] dp, int fee){
        if(index == stock.length) return 0; // we need to be careful here as maybe we had buy == 1, but return 0 covers all cases because if we return 0, the value of this call will go into negative and will never be picked 

        if(dp[index][buy] != -1) return dp[index][buy];

        int profit = 0;
        if(buy == 1){
            profit = Math.max(-stock[index] + buyAndSellStockWithFeeMemoization(stock, index+1, 0,dp, fee), 0 + buyAndSellStockWithFeeMemoization(stock, index+1, 1,dp, fee) );
        }else{
            profit = Math.max(stock[index]-fee + buyAndSellStockWithFeeMemoization(stock, index+1, 1,dp, fee), 0 + buyAndSellStockWithFeeMemoization(stock, index+1, 0,dp, fee));
        }

        return dp[index][buy] =  profit;
    }
}

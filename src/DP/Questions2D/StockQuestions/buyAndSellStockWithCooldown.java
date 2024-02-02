package DP.Questions2D.StockQuestions;

import java.util.Arrays;

// EXACTLY THE SAME QUES AS BUYANDSELLSTOCKII ( just a oneline difference)
public class buyAndSellStockWithCooldown {
    public static void main(String[] args) {
        int[] stock = {1,2,3,0,2};

        System.out.println(buyAndSellStockWithCooldownRecursion(stock,0, 1));

        int[][] dp = new int[stock.length][2];
        for(int i =0; i< dp.length; i++){
            Arrays.fill(dp[i], -1);
        }
        System.out.println(buyAndSellStockWithCooldownMemoization(stock, 0, 1, dp));

        int[][] dp1 = new int[stock.length+2][2];
        for(int i =0; i< dp1.length; i++){
            Arrays.fill(dp1[i], 0);
        }
        System.out.println(buyAndSellStockWithCooldownTabulation(stock, dp1));

        System.out.println(buyAndSellStockWithCooldownOptimal(stock));
    }

    private static int buyAndSellStockWithCooldownOptimal(int[] stock) {
        int[] front2= new int[2];
        Arrays.fill(front2, 0);
        int[] front1 = new int[2];
        Arrays.fill(front1, 0);
        int[] curr = new int[2];
        Arrays.fill(curr, 0);

        for(int index = stock.length-1; index>= 0; index--){
            curr[1] = Math.max(-stock[index] + front1[0], 0 + front1[1]);
            curr[0] = Math.max(stock[index] + front2[1], 0 + front1[0]); // as we are trying toget the value of index+2 . we need to initialise the array stock.length + 2 so it doesn't throw index out of bounds error
                
            front2 = Arrays.copyOf(front1, front1.length); // IMPORTANT -> make sure to use these
            front1 = Arrays.copyOf(curr, curr.length); // IMPORTANT -> make sure to use these
        }
        return curr[1];
    }

    private static int buyAndSellStockWithCooldownTabulation(int[] stock, int[][] dp1) {
        for(int index = stock.length-1; index>= 0; index--){
            for(int buy = 0; buy <=1; buy++){
                int profit = 0;
                if(buy == 1){
                    profit = Math.max(-stock[index] + dp1[index+1][0], 0 + dp1[index+1][1]);
                }else{
                    profit = Math.max(stock[index] + dp1[index+2][1], 0 + dp1[index+1][0]); // as we are trying toget the value of index+2 . we need to initialise the array stock.length + 2 so it doesn't throw index out of bounds error
                }

                dp1[index][buy] = profit;
            }
        }

        return dp1[0][1];
    }

    private static int buyAndSellStockWithCooldownRecursion(int[] stock, int index, int buy) {
        /* change 1 */ if(index >= stock.length) return 0; // we need to careful here as maybe we had buy == 1, but return 0 covers all cases because if we return 0, the value of this call will go into negative and will never be picked 

        int profit = 0;
        if(buy == 1){
            profit = Math.max(-stock[index] + buyAndSellStockWithCooldownRecursion(stock, index+1, 0), 0 + buyAndSellStockWithCooldownRecursion(stock, index+1, 1) );
        }else{
            /* change 2 */ profit = Math.max(stock[index] + buyAndSellStockWithCooldownRecursion(stock, index+2, 1), 0 + buyAndSellStockWithCooldownRecursion(stock, index+1, 0));
        }

        return profit;
    }
    
    private static int buyAndSellStockWithCooldownMemoization(int[] stock, int index, int buy, int[][] dp){
        if(index >= stock.length) return 0; // we need to be careful here as maybe we had buy == 1, but return 0 covers all cases because if we return 0, the value of this call will go into negative and will never be picked 

        if(dp[index][buy] != -1) return dp[index][buy];

        int profit = 0;
        if(buy == 1){
            profit = Math.max(-stock[index] + buyAndSellStockWithCooldownMemoization(stock, index+1, 0,dp), 0 + buyAndSellStockWithCooldownMemoization(stock, index+1, 1,dp) );
        }else{
            profit = Math.max(stock[index] + buyAndSellStockWithCooldownMemoization(stock, index+2, 1,dp), 0 + buyAndSellStockWithCooldownMemoization(stock, index+1, 0,dp));
        }

        return dp[index][buy] =  profit;
    }
}

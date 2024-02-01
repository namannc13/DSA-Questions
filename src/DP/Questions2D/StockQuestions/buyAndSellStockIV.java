package DP.Questions2D.StockQuestions;

public class buyAndSellStockIV {
    public static void main(String[] args) {
        int[] stock = {3,2,6,5,0,3};

        System.out.println(buyAndSellStockIVOptimal(stock, 3));
    }

    private static int buyAndSellStockIVOptimal(int[] stock, int k) {
        int[][] ahead= new int[2][k+1];
        int[][] curr = new int[2][k+1];

        for(int index = stock.length-1; index>= 0; index--){
            for(int buy = 0; buy <=1; buy++){
                for(int cap = 1; cap<=k; cap++){
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

        return ahead[1][k];
    }
}

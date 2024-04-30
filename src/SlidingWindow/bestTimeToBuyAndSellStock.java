package SlidingWindow;

public class bestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        int l = 0, r = 0, ans = 0;
        while (r < prices.length) {
            if (prices[r] < prices[l])
                l = r;
            ans = Math.max(ans, prices[r] - prices[l]);
            r++;
        }
        return ans;
    }
}

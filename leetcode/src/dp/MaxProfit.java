package dp;

// best time to buy and sell stock
// https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/


public class MaxProfit {

    // 允许买入卖出一次
    public static int maxProfitI(int [] prices) {

        int [] dp = new int[prices.length];
        dp[0] = 0;
        int min = prices[0];

        int max = 0;

        for(int i = 1; i < prices.length; i++) {
            if(prices[i] < min) {
                min = prices[i];
            }
            dp[i] = prices[i] - min;
            if(dp[i] > max) {
                max = dp[i];
            }
        }
        return max;
    }

    // 允许买入卖出任何次
    public static int maxProfitII(int [] prices) {
        int sum = 0;
        if(prices.length <= 1) {
            return 0;
        }

        for(int i = 1; i < prices.length; i++) {
            if(prices[i] > prices[i - 1]) {
                sum += prices[i] - prices[i - 1];
            }
        }

        return sum;
    }

    // 允许买入卖出两次
    public static int maxProfitIII(int [] prices) {
        int sum = 0;
        if(prices.length <= 1) {
            return 0;
        }

        int dp[][] = new int[prices.length][];




        return sum;
    }


}

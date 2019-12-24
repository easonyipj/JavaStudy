package dp;

// best time to buy and sell stock
// https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/

public class MaxProfit {


    /**
     * Say you have an array for which the ith element is the price of a given stock on day i.
     *
     * If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.
     *
     * Note that you cannot sell a stock before you buy one.
     *
     * Example 1:
     *
     * Input: [7,1,5,3,6,4]
     * Output: 5
     * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
     *              Not 7-1 = 6, as selling price needs to be larger than buying price.
     * Example 2:
     *
     * Input: [7,6,4,3,1]
     * Output: 0
     * Explanation: In this case, no transaction is done, i.e. max profit = 0.
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
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
        int n = prices.length;
        if(prices.length <= 1) {
            return 0;
        }

        // 分为五个阶段
        // 阶段1：第一次买之前
        // 阶段2：第一次持有
        // 阶段3：第一次卖出和第二次持有之间
        // 阶段4：第二次持有
        // 阶段5：第二次卖出
        // dp[i][j]表示前i天（第i - 1天，从0算起）状态为j时的获利(获利！=手中的钱)
        int dp[][] = new int[n + 1][5 + 1];
        for(int j = 1; j <= 5; j++) {
            if(j == 1) {
                dp[0][j] = 0;
            }else {
                dp[0][j] = Integer.MIN_VALUE;
            }
        }


        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= 5; j++) {
                // 阶段1、3、5
                // dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - 1] + p[i - 1] - p[i - 2])
                // 前i天不持有股票时手中的最大收益为 max(前i- 1天不持有股票, 前i-1天持有股票且第i - 1天卖掉）
                if(j == 1 || j == 3 || j == 5) {
                    dp[i][j] = dp[i - 1][j];
                    if(j > 1 && i > 1 && dp[i - 1][j - 1] != Integer.MIN_VALUE) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + prices[i - 1] - prices[i - 2]);
                    }
                }
                // 阶段2、4
                // dp[i][j] = max(前i - 1天持有并且继续持有并获利，昨天没有持有股票并且今天买入，
                // 昨天持有上一次的股票今天卖出并立即买入）
                else{
                    dp[i][j] = dp[i -1][j - 1];
                    if(i > 1 && dp[i - 1][j] != Integer.MIN_VALUE) {
                        dp[i][j] = Math.max(dp[i - 1][j] + prices[i - 1] - prices[i - 2], dp[i][j]);

                    }
                    if(i > 1 && j > 2 && dp[i -1][j - 2] != Integer.MIN_VALUE) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 2] + prices[i - 1] - prices[i - 2]);
                    }
                }
            }
        }

        return Math.max(Math.max(dp[n][1], dp[n][3]), dp[n][5]);
    }

    // 允许买入卖出K次
    public static int maxProfitIV(int [] prices, int k) {
        int n = prices.length;
        if(n <= 1) {
            return 0;
        }

        if(k > n / 2) {
            return maxProfitII(prices);
        }
        // 分为k个阶段
        // 阶段1：第一次买之前
        // 阶段2：第一次持有
        // 阶段3：第一次卖出和第二次持有之间
        // 阶段4：第二次持有
        // 阶段5：第二次卖出
        // dp[i][j]表示前i天（第i - 1天，从0算起）状态为j时的获利(获利！=手中的钱)
        int dp[][] = new int[n + 1][2 * k + 2];
        for(int j = 1; j <= 2 * k + 1; j++) {
            if(j == 1) {
                dp[0][j] = 0;
            }else {
                dp[0][j] = Integer.MIN_VALUE;
            }
        }

        int j;
        for(int i = 1; i <= n; i++) {
            for(j = 1; j <= 2 * k + 1; j += 2) {
                // 阶段1、3、5...2 * k + 1
                // dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - 1] + p[i - 1] - p[i - 2])
                // 前i天不持有股票时手中的最大收益为 max(前i- 1天不持有股票, 前i-1天持有股票且第i - 1天卖掉）
                dp[i][j] = dp[i - 1][j];
                if(j > 1 && i > 1 && dp[i - 1][j - 1] != Integer.MIN_VALUE) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + prices[i - 1] - prices[i - 2]);
                }
            }

            for(j = 2; j <= 2 * k; j += 2) {
                // 阶段2、4...2 * k + 1
                // dp[i][j] = max(前i - 1天持有并且继续持有并获利，昨天没有持有股票并且今天买入，
                // 昨天持有上一次的股票今天卖出并立即买入）
                dp[i][j] = dp[i -1][j - 1];
                if(i > 1 && dp[i - 1][j] != Integer.MIN_VALUE) {
                    dp[i][j] = Math.max(dp[i - 1][j] + prices[i - 1] - prices[i - 2], dp[i][j]);

                }
                if(i > 1 && j > 2 && dp[i -1][j - 2] != Integer.MIN_VALUE) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 2] + prices[i - 1] - prices[i - 2]);
                }
            }

        }

        int res = Integer.MIN_VALUE;
        for(j = 1; j <= 2 * k + 1; j += 2) {
            res = Math.max(res, dp[n][j]);
        }

        return res;
    }



}

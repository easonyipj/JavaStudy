package meituan;

import java.util.Scanner;

/**
 * 外卖满减
 * 输入N个菜的价格，达到K满减
 * 一个菜只能点一次
 * 最少需要花多少元达到满减金额
 */
public class ToGo {
    public static void main() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        int[] dp = new int[K + 1];
        for (int i = 0; i <= K; i++) {
            dp[i] = 10001;
        }

        // 因为一种菜只能做一次，所以需要先遍历价格，后遍历金额，且金额从大向小遍历
        for(int i = 0; i < N; i++) {
            int price = sc.nextInt();
            for(int j = K; j >= 0; j--) {
                if(j > price) {
                    dp[j] = Math.min(dp[j], dp[j - price] + price);
                }else{
                    dp[j] = Math.min(dp[j], price);
                }
            }
        }

        System.out.println(dp[K]);

    }
}

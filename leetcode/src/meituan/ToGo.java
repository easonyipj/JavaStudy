package meituan;

import java.util.Scanner;

/**
 * 链接：https://www.nowcoder.com/questionTerminal/95329d9a55b94e3fb2da475d3d052164
 * 来源：牛客网
 *
 * 你打开了美了么外卖，选择了一家店，你手里有一张满X元减10元的券，店里总共有n种菜，第i种菜一份需要A_i元，因为你不想吃太多份同一种菜，所以每种菜你最多只能点一份，现在问你最少需要选择多少元的商品才能使用这张券。
 *
 *
 * 输入描述:
 * 第一行两个正整数n和X，分别表示菜品数量和券的最低使用价格。（1≤n≤100, 1≤X≤10000） 接下来一行n个整数，第i个整数表示第i种菜品的价格。（1≤A_i≤100）
 *
 *
 * 输出描述:
 * 一个数，表示最少需要选择多少元的菜才能使用这张满X元减10元的券，保证有解。
 * 示例1
 * 输入
 * 5 20
 * 18 19 17 6 7
 * 输出
 * 23
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

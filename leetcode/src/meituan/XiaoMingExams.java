package meituan;

import java.util.Scanner;

public class XiaoMingExams {
    public static void main() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[][] p = new int[N][4];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < 4; j++) {
                p[i][j] = sc.nextInt();
            }
        }

        int[] dp = new int[N + 1];
        dp[0] = 0;
        for(int i = 1; i < N; i++) {
            // not solve
            int r1 = 0;
            //  
        }
    }
}

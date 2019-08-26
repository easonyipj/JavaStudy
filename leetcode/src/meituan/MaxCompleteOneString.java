package meituan;
// https://www.nowcoder.com/question/next?pid=16516326&qid=362169&tid=26620274

import java.util.Scanner;

public class MaxCompleteOneString {


    public static void main() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        int[] n = new int[N];

        for(int i = 0; i < N; i++) {
            n[i] = sc.nextInt();
        }

        // count the quantity of 1
        int count = 0;
        // the max 1
        int res = 0;

        for(int l = 0, r = 0; r < N; r++) {
            if(n[r] == 1) {
                count++;
            }
            while(r - l + 1 - count > K) {
                if(n[l++] == 1) {
                    count--;
                }
            }
            res = Math.max(res, r - l + 1);
        }

        System.out.println(res);
    }
}

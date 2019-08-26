package meituan;

import java.util.Scanner;

public class Router {
    public static void main() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        int[] r = new int[N];

        int n = 0;
        while(sc.hasNext()) {
            r[n++] = sc.nextInt();
        }

        int[] cap = new int[N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(j < i) {
                    if(i - j <= r[i]) {
                        cap[i]++;
                    }
                }else {
                    if(j - i <= r[i]) {
                        cap[i]++;
                    }
                }
            }
        }

        int count = 0;
        for(int k : cap) {
            if(k >= K) {
                count++;
            }
        }

        System.out.println(count);
    }
}

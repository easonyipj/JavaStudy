package chinatelecom;

import java.util.ArrayList;
import java.util.Scanner;

public class maxSubSequence {
    public static void main() {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> nums = new ArrayList<Integer>();
        int n;
        while(sc.hasNext()) {
            n = sc.nextInt();
            nums.add(n);
        }

        int sum = 0;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < nums.size(); i++) {
            if(sum < 0) {
                sum = nums.get(i);
            }else{
                sum += nums.get(i);
            }

            max = max > sum ? max : sum;
        }
        System.out.println(max);
    }
}

import dp.LongestPalindromicSubstring;
import meituan.GraphTraverse;
import meituan.MaxCompleteOneString;
import multithread.PrintInOrder;
import sort.*;
import string.SpliteString;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {

    public static void main(String[] args) throws Exception {
        FileReader reader = new FileReader("C:\\Users\\Administrator\\Desktop\\LeetCode\\leetcode-Java\\leetcode\\src\\score.txt");
        BufferedReader br = new BufferedReader(reader);

        double score;
        double weight;
        double scoreSum = 0;
        double weightSum = 0;

        String s;
        while((s = br.readLine()) != null) {
            score = Double.valueOf(s.split(" ")[0]);
            weight = Double.valueOf(s.split(" ")[1]);
            scoreSum += score * weight;
            weightSum += weight;
        }

        System.out.println(scoreSum / weightSum);

    }


    private static void printArray(int [] nums) {
        for(int i : nums) {
            System.out.println(i);
        }
    }
}

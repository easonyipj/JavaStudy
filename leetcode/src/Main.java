import dp.LongestPalindromicSubstring;
import greedy.MinimumNumberOfRefuelingStops;
import meituan.GraphTraverse;
import meituan.MaxCompleteOneString;
import multithread.PrintFooBarAlternately;
import multithread.PrintInOrder;
import multithread.Sychronized;
import multithread.WaitNotify;
import recursion.Subsets;
import sort.*;
import string.SpliteString;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws Exception {

        int[] nums = {4, 5, 3, 6, 2, 5, 1};
        HeapSort.heapSort(nums);
        printArray(nums);

    }

    private static void printArray(int [] nums) {
        for(int i : nums) {
            System.out.println(i);
        }
    }

    private static void scoreCalculate() throws IOException {
        FileReader reader = new FileReader("C:\\Users\\Administrator\\Desktop\\LeetCode\\leetcode-Java\\leetcode\\src\\score.txt");
        BufferedReader br = new BufferedReader(reader);

        double score;
        double weight;
        double scoreSum = 0;
        double weightSum = 0;

        String s;
        while((s = br.readLine()) != null) {
            score = Double.parseDouble(s.split(" ")[0]);
            weight = Double.parseDouble(s.split(" ")[1]);
            scoreSum += score * weight;
            weightSum += weight;
        }

        System.out.println(scoreSum / weightSum);
    }
}

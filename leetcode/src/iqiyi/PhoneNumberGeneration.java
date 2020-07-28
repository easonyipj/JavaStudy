package iqiyi;

import java.util.ArrayList;
import java.util.List;

public class PhoneNumberGeneration {

    /**
     * print 100 lines
     */
    private static final int COUNT = 100;
    private static int count;

    /**
     * recursion method, use dfs
     * @param list store numbers
     * @param deep represent the digit of a phone number
     */
    private static void dfs(List<Integer> list, int deep) {
        if(count == COUNT) {
            return;
        }

        if(list.size() == 10) {
            printNumbers(list);
            count++;
            return;
        }

        if(deep != 1) {
            list.add(0);
            dfs(list, deep + 1);
            list.remove(list.size() - 1);
        }

        list.add(1);
        dfs(list, deep + 1);
        list.remove(list.size() - 1);

        list.add(2);
        dfs(list, deep + 1);
        list.remove(list.size() - 1);

        list.add(3);
        dfs(list, deep + 1);
        list.remove(list.size() - 1);

        list.add(4);
        dfs(list, deep + 1);
        list.remove(list.size() - 1);

        list.add(5);
        dfs(list, deep + 1);
        list.remove(list.size() - 1);

        list.add(6);
        dfs(list, deep + 1);
        list.remove(list.size() - 1);

        list.add(7);
        dfs(list, deep + 1);
        list.remove(list.size() - 1);

        list.add(8);
        dfs(list, deep + 1);
        list.remove(list.size() - 1);

        list.add(9);
        dfs(list, deep + 1);
        list.remove(list.size() - 1);
    }

    /**
     * Loop method, from 1000000000 - 9999999999, every loop add 1
     */
    private void loop() {
        int count = 0;
        for(long i = 1000000000; i < 9999999999L; i++) {
            System.out.println(i);
            count++;
            // print 100 lines
            if(count == 100) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        // 1 use dfs
        List<Integer> list = new ArrayList<>();
        dfs(list, 1);

        // use loop
        // loop();
    }

    /**
     * print the numbers
     */
    private static void printNumbers(List<Integer> list) {
        for(int i : list) {
            System.out.print(i);
        }
        System.out.println("");
    }
}

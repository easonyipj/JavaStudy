package amazon;

import java.util.ArrayList;
import java.util.List;

public class PhoneNumberGeneration {

    private static final int COUNT = 100;
    private static int count;

    public static void dfs(List<Integer> list, int deep) {
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

    public static void main(String[] args) {
//        List<Integer> list = new ArrayList<>();
//        dfs(list, 1);
        int c = 0;
        for(long i = 1000000000; i < 9999999999l; i++) {
            System.out.println(i);
            c++;
            if(c == 50) {
                break;
            }
        }

    }

    private static void printNumbers(List<Integer> list) {
        for(int i : list) {
            System.out.print(i);
        }
        System.out.println("");
    }
}

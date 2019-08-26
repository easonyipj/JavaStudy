package meituan;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 输入一个N个节点、N-1条边的图
 * 每个节点直接距离为1
 * 求最短遍历路径长度
 */
public class GraphTraverse {

    private static int depth;

    public static void main() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        List<List<Integer>> g = new ArrayList<>(N);

        int n = N;
        while(n-- > 0) {
            g.add(new ArrayList<>());
        }

        // 邻接表表示法
        while(!sc.hasNext("eof")) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            g.get(a - 1).add(b - 1);
            g.get(b - 1).add(a - 1);
        }

        dfs(-1, 0, 0, g);

        System.out.println(2 * (N - 1) - depth);

    }

    private static void dfs(int parent, int son, int currDepth, List<List<Integer>> g) {
        List<Integer> child = g.get(son);
        if(child.size() == 1) {
            depth = Math.max(currDepth, depth);
        }

        for(Integer i : child) {
            if(i == parent) {
                continue;
            }
            dfs(son, i, currDepth + 1, g);
        }
    }
}

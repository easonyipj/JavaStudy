package meituan;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**链接：https://www.nowcoder.com/questionTerminal/5427af99168b45f4a14aec195b28a839
 来源：牛客网

 给定一张包含N个点、N-1条边的无向连通图，节点从1到N编号，每条边的长度均为1。假设你从1号节点出发并打算遍历所有节点，那么总路程至少是多少？


 输入描述:
 第一行包含一个整数N，1≤N≤10^5。

 接下来N-1行，每行包含两个整数X和Y，表示X号节点和Y号节点之间有一条边，1≤X，Y≤N。


 输出描述:
 输出总路程的最小值。
 示例1
 输入
 4
 1 2
 1 3
 3 4
 输出
 4
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

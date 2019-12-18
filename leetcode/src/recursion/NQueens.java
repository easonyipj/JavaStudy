package recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Eason
 * 2019/10/22
 *
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 *
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 *
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.
 *
 * Example:
 *
 * Input: 4
 * Output: [
 *  [".Q..",  // Solution 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // Solution 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 * Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-queens
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 **/
public class NQueens {
    /**
     * 所有摆放结果的集合
     */
    public List<List<String>> result = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        // 当前摆放结果
        char[][] answer = new char[n][n];
        // 棋盘摆放状态
        int[][] mark = new int[n][n];

        // 初始化
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                mark[i][j] = 0;
                answer[i][j] = '.';
            }
        }

        recrusion(0, n, mark, answer);

        return result;
    }

    private void recrusion(int k, int n, int[][] mark, char[][] answer) {
        if(k == n) {
            result.add(arrayToList(answer));
            return;
        }

        // 尝试在第k行的第i列放置皇后
        for(int i = 0; i < n; i++) {
            if(mark[k][i] == 0) {
                int[][] tempMark = arrayCopy(mark);
                // 放置皇后的相关操作
                answer[k][i] = 'Q';
                putdownTheQueen(k, i, mark);
                recrusion(k + 1, n, mark, answer);
                // 回溯操作
                mark = tempMark;
                answer[k][i] = '.';
            }
        }
    }

    private void putdownTheQueen(int x, int y, int[][] mark) {
        int[] xVector = new int[]{0, 0, -1, -1, -1, 1, 1, 1};
        int[] yVector = new int[]{1, -1, 0, 1, -1, 0, 1, -1};

        mark[x][y] = 1;

        for(int i = 1; i < mark.length; i++) {
            for(int j = 0; j < 8; j++) {
                int newX = x + i * xVector[j];
                int newY = y + i * yVector[j];
                if(newX >= 0 && newX < mark.length && newY >= 0 && newY < mark.length) {
                    mark[newX][newY] = 1;
                }
            }
        }
    }

    private List<String> arrayToList(char[][] answer) {
        List<String> stringList = new ArrayList<>();
        for(int i = 0; i < answer.length; i++) {
            String s = String.copyValueOf(answer[i]);
            stringList.add(s);
        }
        return stringList;
    }

    private int[][] arrayCopy(int[][] answer) {
        int[][] array = new int[answer.length][answer.length];
        for(int i = 0; i < answer.length; i++) {
            array[i] = answer[i].clone();
        }
        return array;
    }

    public static void main(String[] args) {
        List<List<String>> res = new NQueens().solveNQueens(4);
        for(List<String> list : res) {
            for(String s : list) {
                System.out.println(s);
            }
            System.out.println("");
        }
    }
}

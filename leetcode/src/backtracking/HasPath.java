package backtracking;

/**
 * Eason
 * 2019/12/22
 * Given a 2D board and a word, find if the word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
 *
 * Example:
 *
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 *
 * Given word = "ABCCED", return true.
 * Given word = "SEE", return true.
 * Given word = "ABCB", return false.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-search
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class HasPath {

    private int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private String word;
    boolean[][] marked;
    private char[][] board;
    private int m;
    private int n;

    public boolean exist(char[][] board, String word) {

        this.board = board;
        this.word = word;

        m = board.length;
        n = board[0].length;

        marked = new boolean[m][n];

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(dfs(i, j, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean dfs(int i, int j, int start) {
        if(start == word.length() - 1) {
            return board[i][j] == word.charAt(start);
        }

        if(board[i][j] == word.charAt(start)) {
            marked[i][j] = true;
            for(int k = 0; k < 4; k++) {
                int newX = i + directions[k][0];
                int newY = j + directions[k][1];
                if(inArea(newX, newY) && !marked[newX][newY]) {
                    if(dfs(newX, newY, start + 1)) {
                        return true;
                    }
                }
            }
            marked[i][j] = false;
        }

        return false;
    }

    private boolean inArea(int i, int j) {
        return i >= 0 && i < m && j >= 0 && j < n;
    }
}

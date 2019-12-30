package backtracking;

/**
 * Eason
 * 2019/12/22
 *
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 *
 * Example 1:
 *
 * Input:
 * 11110
 * 11010
 * 11000
 * 00000
 *
 * Output: 1
 * Example 2:
 *
 * Input:
 * 11000
 * 11000
 * 00100
 * 00011
 *
 * Output: 3
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-islands
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class NumberOfIslands {
    public int numIslands(char[][] grid) {
        int count = 0;
        int m = grid.length;
        if(m == 0) {
            return 0;
        }
        int n = grid[0].length;

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }

    /**
     * dfs 如果某一节点为1，则以该节点为根结点，深搜其不为0的子节点，遍历到的节点修改为0
     */
    private void dfs(char[][] grid, int x, int y) {
        int m = grid.length;
        int n = grid[0].length;

        if(x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == '0') {
            return;
        }

        grid[x][y] = '0';
        dfs(grid, x - 1, y);
        dfs(grid, x + 1, y);
        dfs(grid, x, y - 1);
        dfs(grid, x, y + 1);
    }
}

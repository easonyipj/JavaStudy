package graph;

import java.util.LinkedList;
import java.util.List;

/**
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 *
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 *
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 *
 * Example 1:
 *
 * Input: 2, [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 *              To take course 1 you should have finished course 0. So it is possible.
 * Example 2:
 *
 * Input: 2, [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 *              To take course 1 you should have finished course 0, and to take course 0 you should
 *              also have finished course 1. So it is impossible.
 * Note:
 *
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 * You may assume that there are no duplicate edges in the input prerequisites.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/course-schedule
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        // 生成入度表
        int[] indgree = new int[numCourses];
        for(int i = 0; i < prerequisites.length; i++) {
            indgree[prerequisites[i][0]]++;
        }

        LinkedList<Integer> list = new LinkedList<>();
        for(int i = 0; i < indgree.length; i++) {
            if(indgree[i] == 0) {
                list.addFirst(i);
            }
        }

        while (!list.isEmpty()) {
            int node = list.pollFirst();
            numCourses--;
            for(int i = 0; i < prerequisites.length; i++) {
                if(prerequisites[i][1] == node) {
                    indgree[prerequisites[i][0]]--;
                    if(indgree[prerequisites[i][0]] == 0) {
                        list.addFirst(prerequisites[i][0]);
                    }
                }
            }
        }

        return numCourses == 0;

    }

    /**
     * dfs TODO:https://leetcode-cn.com/problems/course-schedule/solution/course-schedule-tuo-bu-pai-xu-bfsdfsliang-chong-fa/
     */

}

package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 *
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1,
 * which is expressed as a pair: [0,1]
 *
 * Given the total number of courses and a list of prerequisite pairs,
 * return the ordering of courses you should take to finish all courses.
 *
 * There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.
 *
 * Example 1:
 *
 * Input: 2, [[1,0]]
 * Output: [0,1]
 * Explanation: There are a total of 2 courses to take. To take course 1 you should have finished
 *              course 0. So the correct course order is [0,1] .
 * Example 2:
 *
 * Input: 4, [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,1,2,3] or [0,2,1,3]
 * Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both
 *              courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
 *              So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
 * Note:
 *
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 * You may assume that there are no duplicate edges in the input prerequisites.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/course-schedule-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CourseScheduleII {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
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

        List<Integer> result = new ArrayList<>();

        while (!list.isEmpty()) {
            int node = list.pollFirst();
            result.add(node);
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

        if(numCourses == 0) {
            return result.stream().mapToInt(Integer::valueOf).toArray();
        }else {
            return new int[]{};
        }

    }
}

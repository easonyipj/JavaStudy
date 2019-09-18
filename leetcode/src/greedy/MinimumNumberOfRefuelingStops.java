package greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * A car travels from a starting position to a destination which is target miles east of the starting position.
 * Along the way, there are gas stations.  Each station[i] represents a gas station
 * that is station[i][0] miles east of the starting position, and has station[i][1] liters of gas.
 * The car starts with an infinite tank of gas, which initially has startFuel liters of fuel in it. 
 * It uses 1 liter of gas per 1 mile that it drives.
 * When the car reaches a gas station, it may stop and refuel, transferring all the gas from the station into the car.
 * What is the least number of refueling stops the car must make in order to reach its destination? 
 * If it cannot reach the destination, return -1.
 *
 * Note that if the car reaches a gas station with 0 fuel left, the car can still refuel there. 
 * If the car reaches the destination with 0 fuel left, it is still considered to have arrived.
 *
 *  
 *
 * Example 1:
 * Input: target = 1, startFuel = 1, stations = []
 * Output: 0
 * Explanation: We can reach the target without refueling.
 *
 * Example 2:
 * Input: target = 100, startFuel = 1, stations = [[10,100]]
 * Output: -1
 * Explanation: We can't reach the target (or even the first gas station).
 *
 * Example 3:
 * Input: target = 100, startFuel = 10, stations = [[10,60],[20,30],[30,30],[60,40]]
 * Output: 2
 * Explanation:
 * We start with 10 liters of fuel.
 * We drive to position 10, expending 10 liters of fuel.  We refuel from 0 liters to 60 liters of gas.
 * Then, we drive from position 10 to position 60 (expending 50 liters of fuel),
 * and refuel from 10 liters to 50 liters of gas.  We then drive to and reach the target.
 * We made 2 refueling stops along the way, so we return 2.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-number-of-refueling-stops
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MinimumNumberOfRefuelingStops {
    public static int minRefuelStops(int target, int startFuel, int[][] stations) {
        int count = 0;
        if(stations.length == 0) {
            return count;
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(stations.length, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        int remainFuel = startFuel;
        int distance = stations[0][0];

        if(remainFuel < distance) {
            return -1;
        }else {
            remainFuel -= distance;
        }

        for(int i = 0; i < stations.length; i++) {
            // 将当前加油站油量放入堆中
            maxHeap.add(stations[i][1]);
            if(i == stations.length - 1) {
                distance = target - stations[i][0];
            }else{
                distance = stations[i + 1][0] - stations[i][0];
            }

            // 加油
            while(!maxHeap.isEmpty() && remainFuel < distance) {
                remainFuel += maxHeap.poll();
                count++;
            }

            // 如果加完油还不够，则返回-1
            if(remainFuel < distance) {
                return  -1;
            }

            remainFuel -= distance;
        }

        return count;
    }
}

package website.lintcode;

import java.util.List;

/**
 * 
 * @author yanpf
 * @date 2017年11月8日 下午5:22:37
 * @description
 * 		给一个整数数组，调整每个数的大小，使得相邻的两个数的差不大于一个给定的整数target，调整每个数的代价为调整前后的差的绝对值，求调整代价之和最小是多少。
		注意事项
		你可以假设数组中每个整数都是正整数，且小于等于100
		
 * @example
 * 		对于数组[1, 4, 2, 3]和target=1，最小的调整方案是调整为[2, 3, 2, 3]，调整代价之和是2。返回2。
 *
 * @Solution 没思路。最终看的网上答案。枚举。因为所有数字都小于100.所以怎么调整。最多也就100中可能。
 *           递推公式 cost[i][j]代表将A[i]调整为j后所能得到的最小值。
 *           cost[i][j] = Math.min(cost[i-1][k] + abs(A[i]-j]),k=1,2,3.....
 */
public class 最小调整代价 {
	
	public int MinAdjustmentCost(List<Integer> A, int target) {
        // write your code here
		int[][] cost = new int[A.size()][101];
		for(int i=1; i<=100; i++) {
			cost[0][i] = Math.abs(A.get(0) - i);
		}
		for(int i=1; i<A.size(); i++) {
			for(int j=1; j<=100; j++) {
				int min = Integer.MAX_VALUE;
				for(int k=1; k<=100; k++) {
					if(Math.abs(j - k) <= target) {
						min = Math.min(cost[i-1][k] + Math.abs(A.get(i) - j), min);
					}
					cost[i][j] = min;
				}
			}
		}
		
		int min = Integer.MAX_VALUE;
		for(int i=1; i<=100; i++) {
			min = Math.min(min, cost[A.size()-1][i]);
		}
		return min;
    }

}

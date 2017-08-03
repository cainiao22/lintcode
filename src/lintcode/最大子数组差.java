package lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年8月2日 上午11:34:15
 * @description 给定一个整数数组，找出两个不重叠的子数组A和B，使两个子数组和的差的绝对值|SUM(A) -
 *              SUM(B)|最大。返回这个最大的差值。 子数组最少包含一个数
 * 
 * @example 给出数组[1, 2, -3, 1]，返回 6
 * 
 * @solution 预处理每个位置往左/右的最大/最小子数组，然后再枚举划分位置，
 * 			   求得所有MaxLeft[i] - MinRight[i+1]和MaxRight[i+1] - MinLeft[i]中的最大值，即为答案。预处理O(n)，
 * 			   枚举划分位置O(n)，整体O(n)
 */

public class 最大子数组差 {
	public int maxDiffSubArrays(int[] nums) {
		// write your code here
		int[] negativeNums = new int[nums.length];
		for(int i=0; i<nums.length; i++) {
			negativeNums[i] = -nums[i];
		}
		int[] left_max = new int[nums.length];
		int[] left_min = new int[nums.length];
		int[] right_max = new int[nums.length];
		int[] right_min = new int[nums.length];
		
		int sum = 0;
		int minSum = 0;
		//从左到右取i内所有连续区间的最大值
		for(int i=0; i<nums.length; i++) {
			sum = sum + nums[i];
			int max = Math.max(sum, sum - minSum);
			minSum = Math.min(minSum, sum);
			left_max[i] = max;
		}
		
		sum = minSum = 0;
		//从右到左取i内所有连续区间的最大值
		for(int i=nums.length - 1; i>= 0; i--) {
			sum = sum + nums[i];
			int max = Math.max(sum, sum - minSum);
			minSum = Math.min(minSum, sum);
			right_max[i] = max;
		}
		
		sum = minSum = 0;
		//从左到右取i内所有连续区间的最小值
		for(int i=0; i < negativeNums.length; i++) {
			sum = sum + negativeNums[i];
			int max = Math.max(sum, sum - minSum);
			minSum = Math.min(minSum, sum);
			left_min[i] = -max;
		}
		
		sum = minSum = 0;
		//从右到左取i内所有连续区间的最小值
		for(int i=negativeNums.length - 1; i>= 0; i--) {
			sum = sum + negativeNums[i];
			int max = Math.max(sum, sum - minSum);
			minSum = Math.min(minSum, sum);
			right_min[i] = -max;
		}
		
		int diff = Integer.MIN_VALUE;
		for(int i=0; i<nums.length-1; i++) {
			diff = Math.max(diff, left_max[i] - right_min[i + 1]);
			diff = Math.max(diff, right_max[i + 1] - left_min[i]);
		}
		
		return diff;
	}

	public int getMaxSubArraysSum(int[] nums) {
		int sum = 0;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			if (max < sum) {
				max = sum;
			}
			if (sum < 0)
				sum = 0;
		}

		return max;
	}
	
	public static void main(String[] args) {
		int[] nums = new int[] {1, 2, -3, 1, 5};
		int max = new 最大子数组差().maxDiffSubArrays(nums);
		System.out.println(max);
	}

}

package lintcode;

import java.util.ArrayList;

/**
 * 
 * @author yanpf
 * @date 2017年8月3日 上午11:51:17
 * @description 给定一个整数数组，找出两个 不重叠 子数组使得它们的和最大。
				每个子数组的数字在数组中的位置应该是连续的。
				返回最大的和,子数组最少包含一个数
				
 * @example 给出数组 [1, 3, -1, 2, -1, 2]
			这两个子数组分别为 [1, 3] 和 [2, -1, 2] 或者 [1, 3, -1, 2] 和 [2]，它们的最大和都是 7
 *
 * @Solution 和最大差值差不多，难点在复杂度上面需要达到o(N),使用原来的那个方案，可以解决，但是复杂度在o(n^2)上面
 * 			   这里需要对left_max 和 right_max做一个调整，也就是left_max[i]可以不包含第i个数
 */
public class 最大子数组II {
	
	public int maxTwoSubArrays(ArrayList<Integer> nums) {
        // write your code
		int length = nums.size();
		int[] left_max = new int[length];
		int[] right_max = new int[length];
		
		int sum = 0;
		int minSum = 0;
		for(int i=0; i<length; i++) {
			sum += nums.get(i);
			//这里可以优化为  max = Math.max(max, sum - minSum);这样max要放到外面
			int max = Math.max(sum, sum - minSum);
			if(i > 0) {
				left_max[i] = Math.max(left_max[i-1], max);
			}else {
				left_max[i] = max;
			}
			// ------待优化--------
			minSum = Math.min(sum, minSum);
		}
		
		sum = minSum = 0;
		for(int i=length - 1; i>= 0; i--) {
			sum += nums.get(i);
			int max = Math.max(sum, sum - minSum);
			minSum = Math.min(sum, minSum);
			if(i < length - 1) {
				right_max[i] = Math.max(right_max[i+1], max);
			}else {
				right_max[i] = max;
			}
		}
		
		int diff = Integer.MIN_VALUE;
		for(int i=0; i<length - 1; i ++) {
			diff = Math.max(diff, left_max[i] + right_max[i + 1]);
		}
		
		return diff;
    }
	
	public static void main(String[] args) {
		int[] nums = new int[] {-1,-2,-3,-100,-1,-50};
		ArrayList<Integer> list = new ArrayList<>();
		for(int i=0; i<nums.length; i++) {
			list.add(nums[i]);
		}
		int max = new 最大子数组II().maxTwoSubArrays(list);
		System.out.println(max);
	}

}

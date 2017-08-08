package lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年8月3日 下午1:38:23
 * @description 给定一个整数数组和一个整数 k，找出 k 个不重叠子数组使得它们的和最大。
 *              每个子数组的数字在数组中的位置应该是连续的。返回最大的和。子数组最少包含一个数
 * 
 * @example 给出数组 [-1,4,-2,3,-2,3] 以及 k = 2，返回 8
 *
 * @Solution 令localMax[i][j]代表前j个数中选择i个子数组的最大值,其中第j个为必选，必选保证j>=i,这样划分才能合法
 *           globalMax表示前j个数中选择i个子数组的全局最大值，j不一定必选 localMax[i][j] =
 *           Math.max(globalMax[i-1][j-1], localMax[i][j-1]) + nums[j]，
 *           两种情况，要不然就是和前面的最后一个数组那个连成一个，要不然就是单独做为一个数组加入(这样把它前面的那个数排除掉就可以)，
 */
public class 最大子数组III {

	public int maxSubArray(int[] nums, int k) {
		// write your code here
		int length = nums.length;
		int[][] localMax = new int[length + 1][length + 1];
		int[][] globalMax = new int[length + 1][length + 1];
		for (int i = 0; i < length; i++) {
			localMax[0][i] = 0;
			if (i == 0) {
				localMax[1][0] = nums[0];
				globalMax[1][0] = nums[0];
			} else {
				localMax[1][i] = Math.max(nums[i], localMax[1][i - 1] + nums[i]);
				globalMax[1][i] = Math.max(globalMax[1][i - 1], localMax[1][i]);
			}
		}
		for (int i = 2; i <= length; i++) {
			for (int j = i - 1; j < length; j++) {
				if (j == i - 1) {
					localMax[i][j] = localMax[i - 1][j - 1] + nums[j];
					globalMax[i][j] = localMax[i][j];
				} else {
					// 为什么直接和globalMax相加不行？这样缺少了一种情况，就是nums[j]不和前面最后那个数组连接的话还好，因为这样是意味着从前面取i-1个数组，
					// 而第j项单独作为一个数组存在，但是如果j与前面最后一个相关联的话，那就意味着前面取的i-1个数组，而j强制被单独分开了。缺少了一种情况，就是
					// 第j项与最后一个数组相连接并且直接从前面j-1项中取出i个数组
					// 所以localMax[i][j-1]+ nums[j]代表第j项与最后一个数组相连接并且直接从前面j-1项中取出i个数组的情况
					localMax[i][j] = Math.max(globalMax[i - 1][j - 1], localMax[i][j - 1]) + nums[j];
					globalMax[i][j] = Math.max(localMax[i][j], globalMax[i][j - 1]);
				}
			}
		}

		return globalMax[k][length - 1];
	}

	/**
	 * 优化方案 
	 * 1、既然找的是选择k个数组，那么没必要将选择length个数组的情况计算出来 
	 * 2、想办法将前面那个特殊的情况归并到一般情况去
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public int maxSubArrayBetter(int[] nums, int k) {
		int length = nums.length;
		int[][] localMax = new int[length + 1][length + 1];
		int[][] globalMax = new int[length + 1][length + 1];
		for (int i = 1; i <= k; i++) {
			localMax[i][i-1] = Integer.MIN_VALUE;
			for (int j = i; j <= length; j++) {
				localMax[i][j] = Math.max(globalMax[i-1][j-1], localMax[i][j-1]) + nums[j-1];
				if(i == j) {
					//globalMax[i][j] = localMax[i][j];也可以
					globalMax[i][j] = globalMax[i-1][j-1] + nums[j-1];
				}else {
					globalMax[i][j] = Math.max(globalMax[i][j-1], localMax[i][j]);
				}
			}
		}
		
		return globalMax[k][length];
	}
	
	//todo 另一个解决方案在这里  http://www.jiuzhang.com/solution/maximum-subarray-iii
	public int maxSubArrayBetterSecond(int[] nums, int k) {
		return 0;
	}
	public static void main(String[] args) {
		int[] nums = new int[] { -1, -2, -3, -100, -1, -50 };
		int result = new 最大子数组III().maxSubArray(nums, 2);
		System.out.println(result);
	}

}

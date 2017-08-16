package lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年8月15日 上午11:29:38
 * @description 给定一个由 n 个整数组成的数组和一个正整数 s ，请找出该数组中满足其和 ≥ s 的最小长度子数组。如果无解，则返回 -1
 *              如果你已经完成了O(n)时间复杂度的编程，请再试试 O(n log n)时间复杂度。
 * 
 * @example 给定数组 [2,3,1,2,4,3] 和 s = 7, 子数组 [4,3] 是该条件下的最小长度子数组
 *
 * @Solution 第一种，两个指针从前往后做扫描，j做加的操作，i做减的操作。min = j - i,类似于一个对列操作 
 * 			  第二种，利用数组的前缀和，做查找，在sum[i]-sum[length-1]种查找第一个大于等于sum[i] + s的元素，
 * 			  这里有个优化，就是如果sum[i]-sum[length-1]没有大于等于sum[i] + s的元素，那么代码可以直接跳出了。
 */
public class 和大于S的最小子数组 {

	public int minimumSize(int[] nums, int s) {
		// write your code here
		if(nums.length == 0) return -1;
		int i = 0, j = 0;
		int sum = 0;
		int min = nums.length;
		while (j <= nums.length) {
			while (sum - nums[i] >= s) {
				sum = sum - nums[i];
				i++;
			}
			if (sum >= s && min > j - i) {
				min = j - i;
			}
			if (j == nums.length)
				break;
			sum += nums[j];
			j++;
		}
		if (sum >= s) {
			return min;
		} else {
			return -1;
		}
	}
	
	/**
	 * 更加简洁的写法，思路和第一个类似
	 * @param nums
	 * @param s
	 * @return
	 */
	public int minimumSizeAnother(int[] nums, int s) {
		if(nums.length == 0) return -1;
		int i = 0, j = 0;
		int sum = 0, min = Integer.MAX_VALUE;
		while(j < nums.length) {
			sum += nums[j++];
			while(sum >= s) {
				min = Math.min(j-i, min);
				sum -= nums[i++];
			}
		}
		
		return min == Integer.MAX_VALUE ? -1 : min;
	}

	public int minimunSizeWithBinarySearch(int[] nums, int s) {
		int[] sum = new int[nums.length + 1];
		for (int i = 1; i < sum.length; i++)
			sum[i] = sum[i - 1] + nums[i - 1];
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < sum.length; i++) {
			int target = sum[i] + s;
			int index = binarySearch(i, sum.length - 1, target, sum);
			//操作技巧见下面的binarySearch说明
			if(index == sum.length) break;
			min = Math.min(min, index - i);
		}

		return min == Integer.MAX_VALUE ? -1 : min;

	}

	public int binarySearch(int low, int high, int target, int[] sum) {
		//这里加入一个等于号，目的是为了使结果出现sum中没有target时候，返回sum.length.
		//这时候就代表sum中没有需要查找的目标，代码可以从上面函数的主循环中跳出来了。
		while (low <= high) {
			int mid = (low + high) / 2;
			if (target > sum[mid]) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}

		return low;
	}

	public static void main(String[] args) {
		int[] nums = new int[] { 2, 3, 1, 2, 4, 3 };
		System.out.println(new 和大于S的最小子数组().minimunSizeWithBinarySearch(nums, 100));
	}

}

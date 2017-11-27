package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年11月20日 下午12:06:48
 * @description 给定一个未经排序的数组，请找出其排序表中连续两个要素的最大间距。 如果数组中的要素少于 2 个，请返回 0. 
 * 				能否使用线性的时间和空间复杂度的方法解决这个问题。
 * @example 给定数组 [1, 9, 2, 5]，其排序表为 [1, 2, 5, 9]，其最大的间距是在 5 和 9 之间，= 4.
 *
 * @Solution 分桶排序
 */
public class 最大间距 extends HH {

	public int maximumGap(int[] nums) {
		// write your code here
		if(nums.length <= 1) {
			return 0;
		}
		int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
		for(int i=0; i<nums.length; i++) {
			min = Math.min(min, nums[i]);
			max = Math.max(max, nums[i]);
		}
		if(max == min) {
			return 0;
		}
		double average = (max - min)*1.0 / (nums.length - 1);
		int[] localMins = new int[nums.length];
		int[] localMaxs = new int[nums.length];
		for(int i=0; i<localMaxs.length; i++) {
			localMaxs[i] = Integer.MIN_VALUE;
			localMins[i] = -1;
		}
		for(int i=0; i<nums.length; i++) {
			int index = (int)((nums[i] - min)/average);
			localMins[index] = Math.min(localMins[index], nums[i]);
			if(localMins[index] == -1) {
				localMins[index] = nums[i];
			}
			localMaxs[index] = Math.max(localMaxs[index], nums[i]);
		}
		int right = 1, left = 0;
		int ans = Integer.MIN_VALUE;
		while(right < localMaxs.length) {
			while(right < localMaxs.length && localMins[right] == -1) right ++;
			if(right == localMaxs.length) break;
			ans = Math.max(ans, localMins[right] - localMaxs[left]);
			left = right;
			right ++;
		}
		
		return ans;
	}
	
	public static void main(String[] args) {
		int[] nums = new int[] {0, Integer.MAX_VALUE};
		print(new 最大间距().maximumGap(nums));
	}

}

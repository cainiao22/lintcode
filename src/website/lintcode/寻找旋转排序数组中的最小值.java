package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年8月30日 下午12:07:42
 * @description 
 * 			假设一个旋转排序的数组其起始位置是未知的（比如0 1 2 4 5 6 7 可能变成是4 5 6 7 0 1 2）。

			你需要找到其中最小的元素。

			你可以假设数组中不存在重复的元素。
			
 * @example 给出[4,5,6,7,0,1,2]  返回 0
 *
 * @Solution 二分法查找。假设最小位置为x，那么x到最后一个位置的元素是递增的，而0~x-1的元素是递减的。
 * 			 且最后一个位置的元素小于0~x-1的任何一个元素。算法是以最后一个元素target为基准，查找第一个小于他的元素位置，
 * 			 这个位置就是最小元素所在的位置。0~length-1取中间mid,如果mid元素小于target,那么最小元素在左边，
 * 			mid元素大于target，那么最小元素在右边。以此规则迭代下去。
 */
public class 寻找旋转排序数组中的最小值 {
	
	public int findMin(int[] nums) {
        // write your code here
		int start = 0,end = nums.length - 1;
		int target = nums[nums.length - 1];
		while(start < end - 1) {
			int mid = start + (end - start) /2;
			if(nums[mid] > target) {
				start = mid;
			}else {
				end = mid;
			}
		}
		
		if(nums[start] < target) {
			return nums[start];
		}else {
			return nums[end];
		}
    }
	
	public static void main(String[] args) {
		int[] nums = new int[] {4,5,6,7,0,1,2};
		nums = new int[] {4,3,2,1,0,7,6,5};
		int res = new 寻找旋转排序数组中的最小值().findMin(nums);
		System.out.println(res);
	}

}

package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年9月26日 上午11:19:40
 * @description 
 * 		给定一个排序数组，在原数组中删除重复出现的数字，使得每个元素只出现一次，并且返回新的数组的长度。

		不要使用额外的数组空间，必须在原地没有额外空间的条件下完成
 * @example
 * 		给出数组A =[1,1,2]，你的函数应该返回长度2，此时A=[1,2]
 *
 * @Solution
 */
public class 删除排序数组中的重复数字 extends HH {
	
	public int removeDuplicates(int[] nums) {
        // write your code here
		if(nums.length <= 1) {
			return nums.length;
		}
		int i=0, j=0;
		for(; i<nums.length; i++) {
			if(nums[j] != nums[i]) {
				j ++;
				nums[j] = nums[i];
			}
		}
		
		return j + 1;
    }

	public static void main(String[] args) {
		int[] nums = new int[] {1,2,3,4,5};
		int j = new 删除排序数组中的重复数字().removeDuplicates(nums);
		System.out.println(j);
		print(nums);
	}
}

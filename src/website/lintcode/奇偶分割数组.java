package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年12月29日 上午11:38:12
 * @description
 * 		分割一个整数数组，使得奇数在前偶数在后。在原数组中完成，不使用额外空间。
 * 
 * @example
 * 		给定 [1, 2, 3, 4]，返回 [1, 3, 2, 4]。
 *
 * @Solution
 */
public class 奇偶分割数组 extends HH {
	
	public void partitionArray(int[] nums) {
        // write your code here
		int i = 0, j = nums.length - 1;
		while(i < j) {
			while(nums[i] % 2 == 1 && i < j) {
				i ++;
			}
			while(nums[j] % 2 == 0 && j > i) {
				j --;
			}
			
			if(i < j) {
				nums[i] = nums[i]^nums[j];
				nums[j] = nums[i]^nums[j];
				nums[i] = nums[i]^nums[j];
			}
		}
    }
	
	public static void main(String[] args) {
		int[] nums = {1};
		new 奇偶分割数组().partitionArray(nums);
	}

}

package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年8月11日 上午10:53:49
 * @description 给出一个包含 0 .. N 中 N 个数的序列，找出0 .. N 中没有出现在序列中的那个数。
 * 				在数组上原地完成，使用O(1)的额外空间和O(N)的时间。
 * 
 * @example N = 4 且序列为 [0, 1, 3] 时，缺失的数为2
 *
 * @Solution 1、分别将数组求和、0-N求和，然后相减
 * 			 2、类似于hash或者排序,将0-N想象成一个排好序的数组，
 * 				如果数组nums也属于0-N的话，可以将nums[i]的值对应到第i个位置上面
 */
public class 寻找缺失的数 {

	public int findMissing(int[] nums) {
        // write your code here
		int sum1 = 0, sum2 = 0;
		for(int i=0; i<nums.length; i++) {
			sum1 += i;
			sum2 += nums[i];
		}
		return sum1 + nums.length - sum2;
    }
	
	public int findMissingBetter(int[] nums) {
        // write your code here
		int res = nums.length;
		for(int i=0; i<nums.length; i++) {
			res = res + i - nums[i];
		}
		return res;
    }
	
	public static int findMissingSecond(int[] nums) {
        // write your code here
		int n = nums.length;
		for(int i = 0; i<n; ) {
			//发现位置不对应，将他放到自己对应的位置上面去
			//在这里，如果，缺失的数是小于N的，那么对于第N个数是无法找到他的位置的，
			//最终错位的那个i的位置存放的其实就是N
			if(nums[i] != i && nums[i] < n) { 
				int t = nums[i];
				nums[i] = nums[t];
				nums[t] = t;
			}else { //保证第i个位置存放的是i或者最大值的时候才放弃
				i++;
			}
		}
		for(int i=0; i<n; i++) {
			//遍历一遍数组，如果位置和数字不对应，那么输出这个位置，其实如果真的不对应的话，这个位置存放的数字应该就是N
			if(nums[i] != i) {
				return i;
			}
		}
		//如果全部对应，很显然缺少的就是N了
		return nums.length;
    }
	
	public static void main(String[] args) {
		int[] nums = new int[] {9,8,7,6,2,0,1,5,4};
		System.out.println(findMissingSecond(nums));
	}
}

package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年10月25日 下午5:17:17
 * @description
 * 		给定一个已经按升序排列的数组，找到两个数使他们加起来的和等于特定数。
		函数应该返回这两个数的下标，index1必须小于index2。注意返回的值不是 0-based。
 * @example
 * 		给定数组为 [2,7,11,15] ，target = 9
		返回 [1,2]
 *
 * @Solution  1、两层循环，判断条件更加苛刻一些 i < nums.length && nums[i] <= target - nums[i]
 * 			  2、双指针，一个从前扫。一个从后扫。
 * 				当两个数加起来大于给定的数是尾指针应该向前移动，当小于时头指针向后移动。等于就可以输出？这样可以吗
 */
public class 两数和输入已排序的数组 extends HH {
	
	public int[] twoSum(int[] nums, int target) {
        // write your code here
		int[] result = new int[2];
		for(int i=0; i < nums.length && nums[i] <= target - nums[i]; i++) {
			for(int j=i+1; j<nums.length; j++) {
				if(nums[i] + nums[j] == target) {
					result[0] = i+1;
					result[1] = j+1;
					return result;
				}else if(nums[i] + nums[j] < target) {
					continue;
				}else {
					break;
				}
			}
		}
		
		return result;
    }
	
	public int[] twoSum2(int[] nums, int target) {
		int[] result = new int[2];
		int i=0, j=nums.length-1;
		while(i < j) {
			if(nums[i] + nums[j] > target) {
				j --;
			}else if(nums[i] + nums[j] < target) {
				i++;
			}else {
				result[0] = i+1;
				result[1] = j + 1;
				break;
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		int[] nums = new int[] {2,7,11,15};
		print(new 两数和输入已排序的数组().twoSum(nums, 9));
	}

}

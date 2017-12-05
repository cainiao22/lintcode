package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年12月5日 下午5:13:53
 * @description
 * 		给定一个未排序的整数数组，找到其中位数。
		中位数是排序后数组的中间值，如果数组的个数是偶数个，则返回排序后数组的第N/2个数
 * @example
 * 		给出数组[4, 5, 1, 2, 3]， 返回 3
		给出数组[7, 9, 4, 5]，返回 5
 *
 * @Solution
 */
public class 中位数 extends HH {
	
	public int median(int[] nums) {
        // write your code here
		int mid = (nums.length-1)/2;
		return getKth(nums, 0, nums.length - 1, mid);
    }
	
	int getKth(int[] nums, int start, int end, int k) {
		int flag = nums[start];
		int i=start, j=end;
		while(i < j) {
			while(i < j && nums[i] <= flag) {
				i ++;
			}
			while(i < j && nums[j] > flag) {
				j --;
			}
			if(i < j) {
				int temp = nums[i];
				nums[i] = nums[j];
				nums[j] = temp;
				i ++;
				j --;
			}
		}
		if(flag > nums[j]) {
			nums[start] = nums[j];
			nums[j] = flag;
		}
		if(i == k) {
			return nums[j];
		}else if(i < k) {
			return getKth(nums, i+1, end, k);
		}else {
			return getKth(nums, start, j, k);
		}
	}
	
	public static void main(String[] args) {
		int[] nums = new int[] {7, 9, 4, 5};
		print(new 中位数().median(nums));
	}

}

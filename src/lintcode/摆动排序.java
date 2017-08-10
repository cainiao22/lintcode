package lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年8月9日 下午6:04:18
 * @description 给你一个没有排序的数组，请将原数组就地重新排列满足如下性质 nums[0] <= nums[1] >= nums[2] <=
 *              nums[3].... 请就地排序数组，也就是不需要额外数组
 * 
 * @example 给出数组为 nums = [3, 5, 2, 1, 6, 4] 一种输出方案为 [1, 6, 2, 5, 3, 4]
 *
 * @Solution 1、整体排序一遍，然后将数组从中间分开，将后半段逆序或顺序依次插入到前半段数组的两两直间。就像上面的那个例子一样
 * 
 * 			 2、整体排序一遍，将数组每隔一个数让它与后面的交换一次。[1,2,3,4,5,6] [1,3,2,5,4,6]
 * 
 * 			 3、不用排序，直接换，从第一个位置开始，
 * 				下标为奇数情况：正常它应该大于两边的数，如果他小于前面的数，那么就和前面的数交换；
 * 				下标为偶数情况，正常他应该小于两边的数，如果他大于前面的数，那么就和前面的数交换；
 *             经过这样一套操作之后，中间的那个奇数位置一定是大于两边的偶数位置的，原理类似于三个数找最大。
 *             剩下的就是，两个操作衔接的地方，经过一奇一偶，操作又回到了奇数上面，这里他应该大于两边的数，
 *             如果他小于前面的数，做一次交换，因为前面的那个偶数一定是小于这个偶数对应的前面那个奇数的，而他又小于前面那个偶数，
 *             所以交换之后，他必然小于前面偶数对应的奇数，不影响前面那三个位置的彼此顺序，这样操作就衔接上了。
 *             
 */
public class 摆动排序 {

	public void wiggleSort(int[] nums) {
		// Write your code here
		if(nums.length <= 1) return;
		heapSort(nums);
		for(int i=1; i<nums.length-1; i+=2) {
			swap(nums, i, i+1);
		}
	}

	public void heapSort(int[] nums) {
		int length = nums.length;
		for (int i = (length - 1) / 2; i >= 0; i--) {
			int j = i;
			do {
				if ((j*2 + 1 < length) && (nums[j] < nums[j * 2 + 1]) || (j * 2 + 2 < length && nums[j] < nums[j * 2 + 2])) {
					int max = j * 2 + 1;
					if (max + 1 < length && nums[max] < nums[max + 1]) {
						max = max + 1;
					}
					swap(nums, j, max);
					j = max;
				} else {
					break;
				}
			} while (j <= (length - 1) / 2);
		}

		for (int i = length - 1; i > 0; i--) {
			swap(nums, 0, i);
			int j = 0;
			do {
				if ((nums[j] < nums[j * 2 + 1] && (j*2 + 1 < i)) || (j * 2 + 2 < i && nums[j] < nums[j * 2 + 2])) {
					int max = j * 2 + 1;
					if (max + 1 < i && nums[max] < nums[max + 1]) {
						max = max + 1;
					}
					swap(nums, j, max);
					j = max;
				} else {
					break;
				}
			} while (j < (i-1)/ 2);
		}
	}

	private void swap(int[] nums, int i, int j) {
		if (nums[i] != nums[j]) {
			nums[i] = nums[i] ^ nums[j];
			nums[j] = nums[i] ^ nums[j];
			nums[i] = nums[i] ^ nums[j];
		}
	}
	
	public void wiggleSortBetter(int[] nums) {
		for(int i=1; i<nums.length; i++) {
			if((i %2 == 1 && nums[i] < nums[i-1]) 
					|| (i % 2 == 0 && nums[i] > nums[i-1])){
				swap(nums, i, i-1);
			}
		}
	}

	public static void main(String[] args) {
		int[] nums = new int[] { 2,1};
		new 摆动排序().wiggleSort(nums);
	}

}

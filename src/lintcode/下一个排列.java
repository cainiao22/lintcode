package lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年8月14日 上午11:18:35
 * @description 给定一个若干整数的排列，给出按正数大小进行字典序从小到大排序后的下一个排列。
 * 				如果没有下一个排列，则输出字典序最小的序列。不允许使用额外的空间。
 * 
 * @example	左边是原始排列，右边是对应的下一个排列
				1,2,3 → 1,3,2
				
				3,2,1 → 1,2,3
				
				1,1,5 → 1,5,1
 *
 * @Solution  从后往前找到第一个逆序对。然后把i-1与前面的第一比它大的数交换位置，最后把i~length的数组逆序输出
 */
public class 下一个排列 extends HH{
	public void nextPermutation(int[] nums) {
		int i = 0;
        for(i=nums.length - 1; i>0; i--) {
        	if(nums[i] > nums[i-1]) {
        		int j = nums.length - 1;
        		while(nums[j] <= nums[i-1]) {
        			j -- ;
        		}
        		nums[i-1] ^= nums[j];
        		nums[j] ^= nums[i-1];
        		nums[i-1] ^= nums[j];
        		break;
        	}
        }
        print(nums);
        for(int j= nums.length - 1; j>i; j--) {
        	if(nums[i] != nums[j]) {
        		nums[i] ^= nums[j];
        		nums[j] ^= nums[i];
        		nums[i] ^= nums[j];
        	}
        	i ++; 
        }
    }
	
	public void mergeSort(int nums[]) {
		devide(nums, 0, nums.length - 1);
		print(nums);
	}
	
	void devide(int[] nums, int start, int end) {
		if(start != end) {
			devide(nums, start, start + (end - start)/2);
			devide(nums, start + (end - start)/2 + 1, end);
			merge(nums,start, start + (end - start)/2, start + (end - start)/2 + 1, end);
		}
	}
	
	void merge(int[] nums, int startA, int endA, int startB, int endB) {
		int[] temp = new int[nums.length];
		int k = 0;
		int i = startA, j = startB;
		while(i <= endA && j <= endB) {
			if(nums[i] < nums[j]){
				temp[k] = nums[i];
				i ++;
			}else {
				temp[k] = nums[j];
				j ++;
			}
			k ++;
		}
		while(i <= endA) {
			temp[k] = nums[i];
			k ++;
			i ++;
		}
		/**
		while(j <= endB) {
			temp[k] = nums[j];
			k ++;
			j ++;
		}
		
		for(i=0; i<k; i++) {
			nums[startA + i] = temp[i];
		}
		这里可以优化为如下形式
		**/
		while(k > 0) {
			nums[--j] = temp[--k];
		}
		
	}
	
	/**
	 * 这里i如果作为rightMax的话最后会少一次merge操作。
	 * @param nums
	 */
	public void mergeSort2(int[] nums) {
		for(int i =1; i<=nums.length; i*=2) {
			for(int j=0; j<=nums.length - i; j += i*2) {
				int rightMax = j + 2*i - 1;
				int leftMax = j + i - 1;
				int rightMin = j + i;
				if(rightMax >= nums.length) {
					rightMax = nums.length - 1;
				}
				merge(nums, j, leftMax, rightMin, rightMax);
			}
			print(nums);
		}
		
		print(nums);
	}
	
	public static void main(String[] args) {
		int[] nums = new int[] {5,4,7,5,3,2};
		new 下一个排列().nextPermutation(nums);
		print(nums);
	}
}

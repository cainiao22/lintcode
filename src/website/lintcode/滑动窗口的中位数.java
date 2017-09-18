package website.lintcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author yanpf
 * @date 2017年9月12日
 * @description 给定一个包含 n 个整数的数组，和一个大小为 k
 *              的滑动窗口,从左到右在数组中滑动这个窗口，找到数组中每个窗口内的中位数。(如果数组个数是偶数，则在该窗口排序数字后，返回第
 *              N/2 个数字。) 时间复杂度为 O(nlog(n))
 * @example 对于数组 [1,2,7,8,5], 滑动大小 k = 3 的窗口时，返回 [2,7,7]
 * 
 *          最初，窗口的数组是这样的：
 * 
 *          [ | 1,2,7 | ,8,5] , 返回中位数 2;
 * 
 *          接着，窗口继续向前滑动一次。
 * 
 *          [1, | 2,7,8 | ,5], 返回中位数 7;
 * 
 *          接着，窗口继续向前滑动一次。
 * 
 *          [1,2, | 7,8,5 | ], 返回中位数 7;
 * 
 * @Solution 1、对每个窗口进行中位数查找。时间复杂度:N^2*logN
 * 			 2、平衡二叉树。root其实就是mid。因为它可以保证根节点的中序性。而平衡性保证了它是一个完全二叉树
 */
public class 滑动窗口的中位数 extends HH {

	/**
	 * 对每个窗口进行中位数查找。时间复杂度:N^2*logN
	 * @param nums
	 * @param k
	 * @return
	 */
	public List<Integer> medianSlidingWindow(int[] nums, int k) {
		// write your code here
		List<Integer> result = new ArrayList<Integer>();
		if (nums.length < k) {
			return result;
		}
		for (int i = k - 1; i < nums.length; i++) {
			int mid = findMedian(nums, i - k + 1, i);
			result.add(mid);
		}

		return result;
	}

	private int findMedian(int[] nums, int begin, int end) {
		int flag = nums[begin];
		int i = begin;
		int j = end;
		int k = (begin + end) / 2;
		while (true) {
			while (i < j) {
				while (i < j && nums[i] <= flag) {
					i++;
				}
				for (; j > i && nums[j] > flag; j--)
					;
				if (i < j) {
					int temp = nums[i];
					nums[i] = nums[j];
					nums[j] =temp;
					i++;
					j--;
				}
			}
			if (i-1 == k) {
				return flag;
			}
			if (i-1 > k) {
				end = i-1;
				i = begin;
				j = i-1;
			}else {
				k = k + begin - i + 1;
				j = end;
				flag = nums[i];
			}
		}
	}
	
	/**
	 * 平衡二叉树。root其实就是mid。因为它可以保证根节点的中序性。而平衡性保证了它是一个完全二叉树
	 * @param nums
	 * @param k
	 * @return  
	 */
	//todo 使用平衡二叉树实现
	public List<Integer> medianSlidingWindowSecond(int[] nums, int k) {
		List<Integer> result = new ArrayList<Integer>();
		if (nums.length < k) {
			return result;
		}
		//用数组模拟二叉树
		int[] stack = new int[k];
		for(int i=0; i<nums.length; i++) {
			add(stack, nums[i], i);
			if(i >= k) {
				remove(stack, nums[i-k]);
			}
			result.add(nums[k/2]);
		}
		
		return result;
	}
	
	private void add(int[] stack, int num, int index) {
		if(index == 0) {
			stack[0] = num;
		}
	}
	
	private void remove(int[] stack, int num) {
		
	}

	public static void main(String[] args) {
		int[] nums = new int[] {1,2,7,8,5};
		List<Integer> result = new 滑动窗口的中位数().medianSlidingWindow(nums, 3);
		print(result);
	}
}

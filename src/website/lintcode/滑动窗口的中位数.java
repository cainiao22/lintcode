package website.lintcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author yanpf
 * @date 2017��9��12��
 * @description ����һ������ n �����������飬��һ����СΪ k
 *              �Ļ�������,�������������л���������ڣ��ҵ�������ÿ�������ڵ���λ����(������������ż�������ڸô����������ֺ󣬷��ص�
 *              N/2 �����֡�) ʱ�临�Ӷ�Ϊ O(nlog(n))
 * @example �������� [1,2,7,8,5], ������С k = 3 �Ĵ���ʱ������ [2,7,7]
 * 
 *          ��������ڵ������������ģ�
 * 
 *          [ | 1,2,7 | ,8,5] , ������λ�� 2;
 * 
 *          ���ţ����ڼ�����ǰ����һ�Ρ�
 * 
 *          [1, | 2,7,8 | ,5], ������λ�� 7;
 * 
 *          ���ţ����ڼ�����ǰ����һ�Ρ�
 * 
 *          [1,2, | 7,8,5 | ], ������λ�� 7;
 * 
 * @Solution 1����ÿ�����ڽ�����λ�����ҡ�ʱ�临�Ӷ�:N^2*logN
 * 			 2��ƽ���������root��ʵ����mid����Ϊ�����Ա�֤���ڵ�������ԡ���ƽ���Ա�֤������һ����ȫ������
 */
public class �������ڵ���λ�� extends HH {

	/**
	 * ��ÿ�����ڽ�����λ�����ҡ�ʱ�临�Ӷ�:N^2*logN
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
	 * ƽ���������root��ʵ����mid����Ϊ�����Ա�֤���ڵ�������ԡ���ƽ���Ա�֤������һ����ȫ������
	 * @param nums
	 * @param k
	 * @return  
	 */
	//todo ʹ��ƽ�������ʵ��
	public List<Integer> medianSlidingWindowSecond(int[] nums, int k) {
		List<Integer> result = new ArrayList<Integer>();
		if (nums.length < k) {
			return result;
		}
		//������ģ�������
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
		List<Integer> result = new �������ڵ���λ��().medianSlidingWindow(nums, 3);
		print(result);
	}
}

package lintcode;

import java.util.ArrayList;

/**
 * 
 * @author yanpf
 * @date 2017��8��3�� ����11:51:17
 * @description ����һ���������飬�ҳ����� ���ص� ������ʹ�����ǵĺ����
				ÿ��������������������е�λ��Ӧ���������ġ�
				�������ĺ�,���������ٰ���һ����
				
 * @example �������� [1, 3, -1, 2, -1, 2]
			������������ֱ�Ϊ [1, 3] �� [2, -1, 2] ���� [1, 3, -1, 2] �� [2]�����ǵ����Ͷ��� 7
 *
 * @Solution ������ֵ��࣬�ѵ��ڸ��Ӷ�������Ҫ�ﵽo(N),ʹ��ԭ�����Ǹ����������Խ�������Ǹ��Ӷ���o(n^2)����
 * 			   ������Ҫ��left_max �� right_max��һ��������Ҳ����left_max[i]���Բ�������i����
 */
public class ���������II {
	
	public int maxTwoSubArrays(ArrayList<Integer> nums) {
        // write your code
		int length = nums.size();
		int[] left_max = new int[length];
		int[] right_max = new int[length];
		
		int sum = 0;
		int minSum = 0;
		for(int i=0; i<length; i++) {
			sum += nums.get(i);
			//��������Ż�Ϊ  max = Math.max(max, sum - minSum);����maxҪ�ŵ�����
			int max = Math.max(sum, sum - minSum);
			if(i > 0) {
				left_max[i] = Math.max(left_max[i-1], max);
			}else {
				left_max[i] = max;
			}
			// ------���Ż�--------
			minSum = Math.min(sum, minSum);
		}
		
		sum = minSum = 0;
		for(int i=length - 1; i>= 0; i--) {
			sum += nums.get(i);
			int max = Math.max(sum, sum - minSum);
			minSum = Math.min(sum, minSum);
			if(i < length - 1) {
				right_max[i] = Math.max(right_max[i+1], max);
			}else {
				right_max[i] = max;
			}
		}
		
		int diff = Integer.MIN_VALUE;
		for(int i=0; i<length - 1; i ++) {
			diff = Math.max(diff, left_max[i] + right_max[i + 1]);
		}
		
		return diff;
    }
	
	public static void main(String[] args) {
		int[] nums = new int[] {-1,-2,-3,-100,-1,-50};
		ArrayList<Integer> list = new ArrayList<>();
		for(int i=0; i<nums.length; i++) {
			list.add(nums[i]);
		}
		int max = new ���������II().maxTwoSubArrays(list);
		System.out.println(max);
	}

}

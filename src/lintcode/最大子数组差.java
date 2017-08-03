package lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��8��2�� ����11:34:15
 * @description ����һ���������飬�ҳ��������ص���������A��B��ʹ����������͵Ĳ�ľ���ֵ|SUM(A) -
 *              SUM(B)|��󡣷���������Ĳ�ֵ�� ���������ٰ���һ����
 * 
 * @example ��������[1, 2, -3, 1]������ 6
 * 
 * @solution Ԥ����ÿ��λ������/�ҵ����/��С�����飬Ȼ����ö�ٻ���λ�ã�
 * 			   �������MaxLeft[i] - MinRight[i+1]��MaxRight[i+1] - MinLeft[i]�е����ֵ����Ϊ�𰸡�Ԥ����O(n)��
 * 			   ö�ٻ���λ��O(n)������O(n)
 */

public class ���������� {
	public int maxDiffSubArrays(int[] nums) {
		// write your code here
		int[] negativeNums = new int[nums.length];
		for(int i=0; i<nums.length; i++) {
			negativeNums[i] = -nums[i];
		}
		int[] left_max = new int[nums.length];
		int[] left_min = new int[nums.length];
		int[] right_max = new int[nums.length];
		int[] right_min = new int[nums.length];
		
		int sum = 0;
		int minSum = 0;
		//������ȡi������������������ֵ
		for(int i=0; i<nums.length; i++) {
			sum = sum + nums[i];
			int max = Math.max(sum, sum - minSum);
			minSum = Math.min(minSum, sum);
			left_max[i] = max;
		}
		
		sum = minSum = 0;
		//���ҵ���ȡi������������������ֵ
		for(int i=nums.length - 1; i>= 0; i--) {
			sum = sum + nums[i];
			int max = Math.max(sum, sum - minSum);
			minSum = Math.min(minSum, sum);
			right_max[i] = max;
		}
		
		sum = minSum = 0;
		//������ȡi�����������������Сֵ
		for(int i=0; i < negativeNums.length; i++) {
			sum = sum + negativeNums[i];
			int max = Math.max(sum, sum - minSum);
			minSum = Math.min(minSum, sum);
			left_min[i] = -max;
		}
		
		sum = minSum = 0;
		//���ҵ���ȡi�����������������Сֵ
		for(int i=negativeNums.length - 1; i>= 0; i--) {
			sum = sum + negativeNums[i];
			int max = Math.max(sum, sum - minSum);
			minSum = Math.min(minSum, sum);
			right_min[i] = -max;
		}
		
		int diff = Integer.MIN_VALUE;
		for(int i=0; i<nums.length-1; i++) {
			diff = Math.max(diff, left_max[i] - right_min[i + 1]);
			diff = Math.max(diff, right_max[i + 1] - left_min[i]);
		}
		
		return diff;
	}

	public int getMaxSubArraysSum(int[] nums) {
		int sum = 0;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			if (max < sum) {
				max = sum;
			}
			if (sum < 0)
				sum = 0;
		}

		return max;
	}
	
	public static void main(String[] args) {
		int[] nums = new int[] {1, 2, -3, 1, 5};
		int max = new ����������().maxDiffSubArrays(nums);
		System.out.println(max);
	}

}

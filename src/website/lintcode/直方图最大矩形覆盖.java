package website.lintcode;

import java.util.Queue;
import java.util.Stack;

/**
 * 
 * @author yanpf
 * @date 2017��9��8�� ����4:00:03
 * @description	
 * 		
	Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.
	
	histogram
	
	Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
	
	histogram
	
	The largest rectangle is shown in the shaded area, which has area = 10 unit.

 * @example ���� height = [2,1,5,6,6,6]������ 10
 *
 * @Solution 1��������
 * 			 2������ջ
 */
public class ֱ��ͼ�����θ��� {

	public int largestRectangleArea(int[] height) {
        // write your code here
		int max = 0;
		for(int i=0; i<height.length; i++) {
			int j = 0, k =0;
			for(j=i; j>=0 && height[j] >= height[i]; j--);
			for(k = i; k<height.length && height[k] >= height[i]; k++);
			max = Math.max(max, height[i] * (k - j - 1));
		}
		
		return max;
    }
	//ʹ�ö�ջ�Ż�����ʵ�ǵ���ջ��˼��
	public int largestRectangleAreaBetter(int[] height) {
		int max = 0;
		Stack<Integer> stack = new Stack<>();
		for(int i=0; i<=height.length; i++) {
			int cur = i == height.length ? -1 : height[i];
			//Ŀ���Ǳ�֤��curǰ������ݲ��ܴ��������Դ����ơ��Ϳ��Ա�֤���ջ�Ǵ�С�������е�
			while(!stack.isEmpty() && height[stack.peek()] >= cur) {
				int h = stack.pop();
				//��Ϊ�ȵ�ǰԪ�أ�height[h])��Ķ�����ȥ�ˡ�����ǰһ��Ԫ���Ǳ���С
				//�ĵ�һ�������������ȵ�ʱ����i - stack.peek() - 1
				int w = stack.isEmpty() ? i : i - stack.peek() - 1;
				max = Math.max(max, height[h] * w);
			}
			stack.push(i);
		}
		
		return max;
	}
	
	public static void main(String[] args) {
		int[] height = {2,1,5,6,6,3,6,7,1,4};
		int result = new ֱ��ͼ�����θ���().largestRectangleArea(height);
		int result2 = new ֱ��ͼ�����θ���().largestRectangleAreaBetter(height);
		System.out.println(result);
		System.out.println(result2);
	}
}

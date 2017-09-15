package website.lintcode;

import java.util.Queue;
import java.util.Stack;

/**
 * 
 * @author yanpf
 * @date 2017年9月8日 下午4:00:03
 * @description	
 * 		
	Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.
	
	histogram
	
	Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
	
	histogram
	
	The largest rectangle is shown in the shaded area, which has area = 10 unit.

 * @example 给出 height = [2,1,5,6,6,6]，返回 10
 *
 * @Solution 1、遍历。
 * 			 2、单调栈
 */
public class 直方图最大矩形覆盖 {

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
	//使用堆栈优化，其实是单调栈的思想
	public int largestRectangleAreaBetter(int[] height) {
		int max = 0;
		Stack<Integer> stack = new Stack<>();
		for(int i=0; i<=height.length; i++) {
			int cur = i == height.length ? -1 : height[i];
			//目的是保证。cur前面的数据不能大于它，以此类推。就可以保证这个栈是从小到大排列的
			while(!stack.isEmpty() && height[stack.peek()] >= cur) {
				int h = stack.pop();
				//因为比当前元素（height[h])大的都弹出去了。它的前一个元素是比它小
				//的第一个数。所以求宽度的时候是i - stack.peek() - 1
				int w = stack.isEmpty() ? i : i - stack.peek() - 1;
				max = Math.max(max, height[h] * w);
			}
			stack.push(i);
		}
		
		return max;
	}
	
	public static void main(String[] args) {
		int[] height = {2,1,5,6,6,3,6,7,1,4};
		int result = new 直方图最大矩形覆盖().largestRectangleArea(height);
		int result2 = new 直方图最大矩形覆盖().largestRectangleAreaBetter(height);
		System.out.println(result);
		System.out.println(result2);
	}
}

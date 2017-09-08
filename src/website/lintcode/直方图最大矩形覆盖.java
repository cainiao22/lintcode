package website.lintcode;

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
 * @Solution
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
	//TODO 使用堆栈优化
	public int largestRectangleAreaBetter(int[] height) {
		return 0;
	}
	
	public static void main(String[] args) {
		int[] height = {2,1,5,6,6,6};
		int result = new 直方图最大矩形覆盖().largestRectangleArea(height);
		System.out.println(result);
	}
}

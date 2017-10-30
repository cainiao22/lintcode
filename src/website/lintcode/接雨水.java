package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年10月20日 下午6:33:52
 * @description
 * 		Given n non-negative integers representing an elevation map where the width of each bar is 1, 
 * 		compute how much water it is able to trap after raining.
 * 		O(n) 时间, O(1) 空间
		O(n) 时间, O(n) 空间也可以接受
 * @example
 * 		如上图所示，海拔分别为 [0,1,0,2,1,0,1,3,2,1,2,1], 返回 6
 *
 * @Solution 前三种方案 见简书  http://www.jianshu.com/p/49b5cf98b216
 */
public class 接雨水 extends HH {
	
	//tudo 部分数据验证失败，暂时找不到原因
	public int trapRainWater(int[] heights) {
        // write your code here
		int sum = 0;
		int i=0;
		while(i < heights.length - 1) {
			//如果下一个比我高那么没必要再计算了
			if(heights[i] <= heights[i+1]) {
				i += 1;
				continue;
			}
			int sumI = 0;
			int j=i+1;
			//这里如果出现等于条件的话。会越过它走到后面，一旦后面都没有比它大的。那么这个就作废了
			for(;j<heights.length && heights[j] < heights[i]; j++) {
				sumI += heights[i] - heights[j];
			}
			if(j == heights.length) {
				i += 1;
				continue;
			}
			print(i + "---->" + j);
			sum += sumI;
			i = j;
		}
		
		return sum;
    }
	
	
	/**
	 * 以下四个算法均来自网上
	 * 
	 */
	
	/**
	 * 
	 * @param heights
	 *  首先从左遍历一遍，找到已左边为基准的高度
		再从右边遍历一遍找到以右边为基准的高度，
		要能够接住水，就要求左右两边，最小的一边的高度应该比中间的都要大，能接住的水的面积就是这些差值
	 * @return
	 */
	public int trapRainWater1(int[] heights) {
		if(heights.length <= 2) {
			return 0;
		}
		int[] left = new int[heights.length];
		int[] right = new int[heights.length];
		
		int maxLeft = heights[0];
		for(int i=0; i<left.length; i++) {
			if(maxLeft < heights[i]) {
				maxLeft = heights[i];
			}
			
			left[i] = maxLeft;
		}
		
		int maxRight = heights[heights.length-1];
		for(int i=heights.length-1; i>=0; i--) {
			if(maxRight < heights[i]) {
				maxRight = heights[i];
			}
			
			right[i] = maxRight;
		}
		
		int area = 0;
		for(int i=0; i<heights.length; i++) {
			area += Math.min(left[i], right[i]) - heights[i];
		}
		
		return area;
	}
	
	/**
	 * 首先遍历一遍找到最高点，然后从左右两边分别向最高点进发
	 * @param args
	 */
	public int trapRainWater2(int[] heights) {
		if(heights.length <= 2) {
			return 0;
		}
		int max = 0;
		int maxIndex = 0;
		for(int i=0; i<heights.length; i++) {
			if(heights[i] > max) {
				max = heights[i];
				maxIndex = i;
			}
		}
		int area = 0;
		int root = heights[0];
		for(int i=0; i<maxIndex; i++) {
			if(root > heights[i]) {
				area += root - heights[i];
			}else {
				root = heights[i];
			}
		}
		
		root = heights[heights.length-1];
		for(int i=heights.length; i>maxIndex; i--) {
			if(root > heights[i]) {
				area += root - heights[i];
			}else {
				root = heights[i];
			}
		}
		
		return area;
		
	}
	
	/**
	 * 左右两个指针，一头一尾开始遍历，首先找到自己这一边的局部最高点，只要比最高点小就可以装水
	 * 相当于版本2的改进版
	 * @param args
	 */
	public int trapRainWater3(int[] heights) {
		int left = 0;
		int right = heights.length-1;
		int leftMax = heights[left];
		int rightMax = heights[right];
		int area = 0;
		while(left < right) {
			//左边可以存水
			leftMax = Math.max(leftMax, heights[left]);
			rightMax = Math.max(rightMax, heights[right]);
			if(leftMax < rightMax) {
				area += leftMax - heights[left];
				left ++;
			}else {
				area += rightMax - heights[right];
				right --;
			}
		}
		
		return area;
	}
	
	
	public static void main(String[] args) {
		int[] heights = new int[] {0,1,0,2,1,0,1,3,2,1,2,1};
		print(new 接雨水().trapRainWater(heights));
	}

}

package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��10��20�� ����6:33:52
 * @description
 * 		Given n non-negative integers representing an elevation map where the width of each bar is 1, 
 * 		compute how much water it is able to trap after raining.
 * 		O(n) ʱ��, O(1) �ռ�
		O(n) ʱ��, O(n) �ռ�Ҳ���Խ���
 * @example
 * 		����ͼ��ʾ�����ηֱ�Ϊ [0,1,0,2,1,0,1,3,2,1,2,1], ���� 6
 *
 * @Solution ǰ���ַ��� ������  http://www.jianshu.com/p/49b5cf98b216
 */
public class ����ˮ extends HH {
	
	//tudo ����������֤ʧ�ܣ���ʱ�Ҳ���ԭ��
	public int trapRainWater(int[] heights) {
        // write your code here
		int sum = 0;
		int i=0;
		while(i < heights.length - 1) {
			//�����һ�����Ҹ���ôû��Ҫ�ټ�����
			if(heights[i] <= heights[i+1]) {
				i += 1;
				continue;
			}
			int sumI = 0;
			int j=i+1;
			//����������ֵ��������Ļ�����Խ�����ߵ����棬һ�����涼û�б�����ġ���ô�����������
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
	 * �����ĸ��㷨����������
	 * 
	 */
	
	/**
	 * 
	 * @param heights
	 *  ���ȴ������һ�飬�ҵ������Ϊ��׼�ĸ߶�
		�ٴ��ұ߱���һ���ҵ����ұ�Ϊ��׼�ĸ߶ȣ�
		Ҫ�ܹ���סˮ����Ҫ���������ߣ���С��һ�ߵĸ߶�Ӧ�ñ��м�Ķ�Ҫ���ܽ�ס��ˮ�����������Щ��ֵ
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
	 * ���ȱ���һ���ҵ���ߵ㣬Ȼ����������߷ֱ�����ߵ����
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
	 * ��������ָ�룬һͷһβ��ʼ�����������ҵ��Լ���һ�ߵľֲ���ߵ㣬ֻҪ����ߵ�С�Ϳ���װˮ
	 * �൱�ڰ汾2�ĸĽ���
	 * @param args
	 */
	public int trapRainWater3(int[] heights) {
		int left = 0;
		int right = heights.length-1;
		int leftMax = heights[left];
		int rightMax = heights[right];
		int area = 0;
		while(left < right) {
			//��߿��Դ�ˮ
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
		print(new ����ˮ().trapRainWater(heights));
	}

}

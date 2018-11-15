package website.lintcode;

import java.util.Stack;

/**
 * 
 * @author yanpf
 * @date 2018��1��3�� ����11:45:43
 * @description
 * 		����һ����ά����ȨֵΪFalse��True���ҵ�һ�����ľ��Σ�ʹ�������ֵȫ��ΪTrue������������
 * 
 * @example
 * 		����һ����������

		[
		  [1, 1, 0, 0, 1],
		  [0, 1, 0, 0, 1],
		  [0, 0, 1, 1, 1],
		  [0, 0, 1, 1, 1],
		  [0, 0, 0, 0, 1]
		]

 *
 * @Solution  ֱ��ͼ�������ǵ���ı���
 */
public class ������ extends HH {
	
	public int maximalRectangle(boolean[][] matrix) {
        // write your code here
		if(matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}
		int[][] m = new int[matrix.length][matrix[0].length];
		for(int i =0; i<matrix.length; i++) {
			for(int j=0; j<matrix[0].length; j++) {
				if(matrix[i][j]) {
					m[i][j] = 1;
				}
			}
		}
		
		for(int i=1; i<m.length; i++) {
			for(int j=0; j<m[0].length; j++) {
				if(m[i][j] == 1) {
					m[i][j] += m[i-1][j];
				}
			}
		}
		int max = 0;
		for(int i=0; i<m.length; i++) {
			max = Math.max(max, getMaxArea(m[i]));
		}
		
		return max;
    }
	
	private int getMaxArea(int[] height) {
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
	
	public static int maximalRectangleTest(int[][] m) {
        // write your code here
		
		for(int i=1; i<m.length; i++) {
			for(int j=0; j<m[0].length; j++) {
				if(m[i][j] == 1) {
					m[i][j] += m[i-1][j];
				}
			}
		}
		print(m);
		int max = 0;
		for(int i=0; i<m.length; i++) {
			for(int j = 0; j<m[0].length; j++) {
				if(m[i][j] == 0) {
					continue;
				}
				int k = j + 1;
				while(k < m[0].length && m[i][k] >= m[i][j]) {
					k ++;
				}
				int cur = m[i][j] * (k - j);
				max = Math.max(max, cur);
			}
		}
		
		return max;
    }
	
	public static void main(String[] args) {
		int[][] m = {
				  {1, 1, 0, 0, 1},
				  {0, 1, 0, 0, 1},
				  {0, 0, 1, 1, 1},
				  {0, 0, 1, 1, 1},
				  {0, 0, 0, 0, 1}
				};
		int result = maximalRectangleTest(m);
		print(result);
	}

}

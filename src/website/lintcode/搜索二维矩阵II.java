package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2019��2��22�� ����11:46:56
 * @description
 * 		д��һ����Ч���㷨������m��n�����е�ֵ���������ֵ���ֵĴ�����
		�����������������ԣ�
		
		ÿ���е�����������������ġ�
		ÿһ�е��������ϵ���������ġ�
		��ÿһ�л�ÿһ����û���ظ���������
 * @example
 * 
 * 		��1:
		����:
		[[3,4]]
		target=3
		���:1
		��2:
		
		����:
		    [
		      [1, 3, 5, 7],
		      [2, 4, 7, 8],
		      [3, 5, 9, 10]
		    ]
		    target = 3
		���:2
		
		Ҫ��O(m+n) ʱ�临�ӶȺ�O(1) ����ռ�
 *
 * @Solution  ����������Լ����ص㡣������ǹ۲���Ŀ�и����Ǹ����ӣ����ǿ��Է���������λ�õ����ֺ����ص㣬���½Ǻ����Ͻǵ��������½ǵ�18���������е�����С���������������ӣ�
 * 			     ��ô���ǾͿ��Ժ�Ŀ������Ƚϣ����Ŀ������
 * 			     �������ѣ����Ŀ����С���������ѡ������Ϳ����ж�Ŀ�����Ƿ���ڡ���Ȼ����Ҳ���԰���ʼ���������Ͻǣ���������ѣ�ֹͣ����������ȷ����
 */
public class ������ά����II {
	
	 public int searchMatrix(int[][] matrix, int target) {
	        // write your code here
		 	int row = matrix.length - 1;
		 	if(row < 0) {
		 		return 0;
		 	}
		 	int col = 0;
		 	int result = 0;
		 	while(row >= 0 && col < matrix[0].length) {
		 		if(matrix[row][col] == target) {
		 			result ++;
		 			row --;
		 		}else if(matrix[row][col] > target) {
		 			row --;
		 		}else {
		 			col ++;
		 		}
		 	}
		 	
		 	return result;
		 	
	    }
	 	
	 	public static void main(String[] args) {
	 		int[][] matrix = new int[][] {{3,4}};
	 		int target = 3;
			new ������ά����II().searchMatrix(matrix, target);
		}
}

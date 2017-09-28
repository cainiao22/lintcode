package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��9��22�� ����7:58:14
 * @description ��һ�������� n, �ҵ����ɸ���ȫƽ����(����1, 4, 9, ... )ʹ�����ǵĺ͵��� n������Ҫ��ƽ�����ĸ������١�
 * @example	���� n = 12, ���� 3 ��Ϊ 12 = 4 + 4 + 4��
			���� n = 13, ���� 2 ��Ϊ 13 = 4 + 9��
 *
 * @Solution
 */

//todo �����㷨�ⷨ
public class ����ƽ�� {
	
	   public int numSquares(int n) {
	        // write your code here
		   int[] matrix = new int[n+1];
		   matrix[1] = 1;
		   for(int i=2; i<= n; i++) {
			   if(isSquare(i)) {
				   matrix[i] = 1;
			   }else {
				   int min = Integer.MAX_VALUE;
				   for(int j=1; j<=i/2; j++) {
					   min = Math.min(min, matrix[j] + matrix[i-j]);
				   }
				   matrix[i] = min;
			   }
		   }
		   
		   return matrix[n];
	    }
	   
	   boolean isSquare(int num) {
		   for(int i=num/2; i>=1; i--) {
			   if(i*i == num) {
				   return true;
			   }
		   }
		   
		   return false;
	   }
	   
	   public static void main(String[] args) {
		int result = new ����ƽ��().numSquares(13);
		System.out.println(result);
	}

}

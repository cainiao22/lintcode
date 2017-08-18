package lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��8��16�� ����4:48:58
 * @description ��һ����ά01�������ҵ�ȫΪ1�����������
 * 
 * @example ����
			1 0 1 0 0
			1 0 1 1 1
			1 1 1 1 1
			1 0 0 1 0
			���� 4
 *
 * @Solution dp ����a[i][j]������matrix[i][j]Ϊ���½ǵľ������󳤶ȣ�
 * 				��ô��a[i][j] = matrix == 0 ? 0 : min(a[i-1][j], a[i][j-1], a[i-1][j-1]) + 1
 * 			  ����ջ
 * 			  ���� �򵥸�һ�£�����k�ĳɶ���k���Ա��жϸĳɼ����������жϣ����Ӷȴ�n^5����n^2logn�������ƺ�����)
 */
public class ��������� {
	
	public int maxSquareDP(int[][] matrix) {
        // write your code here
		int[][] a = new int[matrix.length][matrix[0].length];
		int max = 0;
		for(int i=0; i<matrix.length; i++) {
			for(int j=0; j<matrix[0].length; j++) {
				if(i == 0 || j == 0 || matrix[i][j] == 0) {
					a[i][j] = matrix[i][j];
				}else {
					a[i][j] = Math.min(a[i][j-1], a[i-1][j]) + 1;
					a[i][j] = Math.min(a[i][j], a[i-1][j-1] + 1);
				}
				max = Math.max(max, a[i][j]);
			}
		}
		
		return max * max;
    }
	
	/**
	 * 
	 * �����ⷨ
	 * @param matrix
	 * @return
	 */
	//todo ̫�鷳�ˣ���ʱ����
	public int maxSquare(int[][] matrix) {
        // write your code here
		int[][] a = new int[matrix.length][matrix[0].length];
		int[][] sumi = new int[matrix.length+1][matrix[0].length+1];
		int[][] sumj = new int[matrix.length + 1][matrix[0].length + 1];
		for(int i=1; i<matrix.length; i++) {
			for(int j=1; j<matrix[0].length; j++) {
				sumi[i][j] = sumi[i][j-1] + matrix[i-1][j-1];
				sumj[j][i] = sumj[j][i-1] + matrix[i-1][j-1];
			}
		}
		
		int max = 0;
		for(int i=0; i<matrix.length; i++) {
			for(int j=0; j<matrix[0].length; j++) {
				if(i == 0 || j == 0 || matrix[i][j] == 0) {
					a[i][j] = matrix[i][j];
				}else {
					a[i][j] = Math.min(a[i][j-1], a[i-1][j]) + 1;
					a[i][j] = Math.min(a[i][j], a[i-1][j-1] + 1);
				}
				max = Math.max(max, a[i][j]);
			}
		}
		
		return max * max;
    }
	
	public static void main(String[] args) {
		//new ���������().maxSquare(matrix);
	}

}

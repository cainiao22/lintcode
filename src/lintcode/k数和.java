package lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��8��8�� ����11:46:57
 * @description ����n����ͬ��������������k��k < = n���Լ�һ��Ŀ�����֡�
 *              ����n���������ҳ�K������ʹ����K�����ĺ͵���Ŀ�����֣������ж����ַ�����
 * 
 * @example ����[1,2,3,4]��k=2�� target=5��[1,4] and [2,3]��2������Ҫ��ķ���
 *
 * @Solution sum[target+1][k+1][length+1] �����A����ѡȡk����ɺ�Ϊtarget�Ľ����������
 *           sum[i][j][m] = sum[i - A[m - 1]][j - 1][m - 1] + sum[i][j][m - 1],
 *           һ�������sum[i][j][m]�����ϵ�m�����Ͳ����m��������������ĺ� ���������A[m - 1] ==
 *           i�������ʱֻѡ��һ�����Ļ���ѡ��������һ������������������ѡ�����Ļ���
 *           ��ôѡ���m-1�����޷���Ϊһ�������������ôֻ��ѡ��ǰ�����Щ
 */
public class k���� {

	public int kSum(int A[], int k, int target) {
		// write your code here
		int length = A.length;
		int[][][] sum = new int[target + 1][k + 1][length + 1];
		for (int i = 0; i <= target; i++) {
			for (int j = 1; j <= k; j++) {
				for (int m = j; m <= length; m++) {
					if (A[m - 1] > i) {
						sum[i][j][m] = sum[i][j][m - 1];
					} else if (A[m - 1] == i) {
						if (j == 1) {
							sum[i][j][m] = 1 + sum[i][j][m - 1];
						} else {
							sum[i][j][m] = sum[i][j][m - 1];
						}
					} else {
						sum[i][j][m] = sum[i - A[m - 1]][j - 1][m - 1] + sum[i][j][m - 1];
					}
				}
			}
		}
		return sum[target][k][length];
	}
	
	/**
	 * �������2 
	 * sum[length+1][k+1][target+1] �����A����ѡȡk����ɺ�Ϊtarget�Ľ����������
	 * ��������飬ֻ�ǽ�length��target������һ��λ��
	 * 
	 * @param A
	 * @param k
	 * @param target
	 * @return
	 */
	public int kSumSecond(int A[], int k, int target) {
		int length = A.length;
		int[][][] sum = new int[length + 1][k + 1][target + 1];
		//��ǰi��������ѡ��0����Ϊ0��������������϶�����1
		for(int i=0; i<=length; i++) {
			sum[i][0][0] = 1;
		}
		for(int i=1; i<= length; i++) {
			//ǰi������ѡj����j�϶�С�ڵ���i
			for(int j=1; j<=k && j <= i; j++) {
				for(int t = 0; t<= target; t++) {
					if(A[i-1] > target) {
						sum[i][j][t] = sum[i-1][j][t];
					}else {
						sum[i][j][t] = sum[i-1][j][t] + sum[i-1][j-1][t - A[i-1]];
					}
					
					/**
					 *  ��һ��ʵ�֣����������ֵ�ͬ
						sum[i][j][t] = 0;
						if(A[i-1] <= target) {
							sum[i][j][t] = sum[i-1][j-1][t - A[i-1]];
						}
						sum[i][j][t] += sum[i-1][j][t];
					**/
				}
			}
		}
		
		return sum[length][k][target];
	}
}

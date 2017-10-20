package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��10��20�� ����3:15:24
 * @description
 * 		����n����Ʒ�����A[i]�����ֵV[i]��������װ��һ����СΪm�ı����������װ����ܼ�ֵ�ж��
 * 		O(n x m) memory is acceptable, can you do it in O(m) memory?
 * @example
 * 		������Ʒ���[2, 3, 5, 7]�Ͷ�Ӧ�ļ�ֵ[1, 5, 2, 4], ���豳����СΪ10�Ļ�������ܹ�װ��ļ�ֵΪ9��
 *
 * @Solution 
 * 		���ʹ�ö�ά���� max[i][j] = max(max[i][j-1],max[i-A[j]][j-1] + v[j])
 * 		ĳ����������˵��max[i][j-1],max[i-A[j]][j-1]��һ�е����ݡ������ǵ�ǰһ��û�й�ϵ��
 * 
 * 		Ҳ���Ե������� max[i][j] = max(max[i-1][j],max[i-1][j-weight[i]] + v[j])
 * 		�������Ҫ��������һ�е�������
 */
public class ��������II {
	
	public int backPackI(int m, int[] A, int[] V) {
        // write your code here
		int[][] max = new int[A.length][m + 1];
		for(int i=0; i<max.length; i++) {
			for(int j=0; j<A.length; j++) {
				max[i][j] = Math.max(max[i-1][j], max[i-i][j-A[i]] + V[i]);
			}
		}
		
		return max[A.length-1][m];
    }
	
	public int backPackII(int m, int[] A, int[] V) {
        // write your code here
		int[] max = new int[m + 1];
		for(int i=0; i<A.length; i++) {
			for(int j=max.length-1; j>=A[i]; j--) {
				//���⸲�ǣ�������
				max[j] = Math.max(max[j], max[j-A[i]] + V[i]);
			}
		}
		
		return max[m];
    }

}

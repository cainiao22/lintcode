package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2018��1��2�� ����5:17:42
 * @description
 * 		����2*n + 1 �������֣�������һ������֮������ÿ�����־��������Σ��ҵ�������֡�һ�α������������Ķ���ռ临�Ӷ�
 * 
 * @example
 *		���� [1,2,2,1,3,4,3]������ 4
 * @Solution
 */
public class �䵥���� {
	
	public int singleNumber(int[] A) {
        // write your code here
		int ans = A[0];
		for(int i=1; i<A.length; i++) {
			ans = ans ^ A[i];
		}
		
		return ans;
    }

}

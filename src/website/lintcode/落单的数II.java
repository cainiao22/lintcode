package website.lintcode;


/**
 * 
 * @author yanpf
 * @date 2018��1��2�� ����5:12:19
 * @description
 * 		����3*n + 1 �������֣�������һ������֮������ÿ�����־��������Σ��ҵ�������֡�
 * 		һ�α������������Ķ���ռ临�Ӷ�
 * 
 * @example
 *		���� [1,1,2,3,3,3,2,2,4,1] ������ 4
 * @Solution int��32���ֽ���ɡ�ͳ��ÿ��λ�õ��ֽڳ��ִ�����������λ�ÿ��Ա�3�����������һ�ε��Ǹ�������һλ�϶���Ϊ1�� 
 */
public class �䵥����II {
	
	public int singleNumberII(int[] A) {
        // write your code here
		int[] bits = new int[32];
		int ans = 0;
		
		//��ʵ������ѭ��������������һ�α����ġ�
		for(int i=0; i<bits.length; i++) {
			for(int j=0;j<A.length; j++) {
				bits[i] += (A[j] >> i) & 1;
			}
			bits[i] = bits[i] % 3;
			ans |= bits[i] << i;
		}
		
		return ans;
    }

}

package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2019��1��24�� ����6:33:41
 * @description ���Ҫ������Aת��ΪB����Ҫ�ı���ٸ�bitλ��
 * @example		���31ת��Ϊ14����Ҫ�ı�2��bitλ��
				(31)10=(11111)2
				(14)10=(01110)2
 *
 * @Solution
 */
public class ������Aת��ΪB {
	
	public int bitSwapRequired(int a, int b) {
        // write your code here
		int r = a ^ b;
		int ans = 0;
		while(r != 0) {
			r = r & (r - 1);
			ans ++;
		}
		
		return ans;
    }
	
	public static void main(String[] args) {
		System.out.println(new ������Aת��ΪB().bitSwapRequired(31, 14));
	}

}

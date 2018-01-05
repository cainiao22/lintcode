package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2018��1��2�� ����6:01:13
 * @description
 * 		���üӼ���ʵ�� a+b
 * @example
 *
 * @Solution
 */
public class A��B���� {
	
	public int aplusb(int a, int b) {
        // write your code here
		int[] bits = new int[33];
		for(int i=0; i<bits.length - 1; i++) {
			bits[i] += (a >> i) & 1;
			bits[i] += (b >> i) & 1;
			int temp = bits[i] % 2;
			bits[i+1] = bits[i] / 2;
			bits[i] = temp;
		}
		
		int ans = 0;
		for(int i=0; i<32; i++) {
			ans |= (bits[i] << i);
		}
		
		return ans;
    }
	
	/**
	 * ���nb����
	 * @param a
	 * @param b
	 * @return
	 */
	public int aplusb2(int a, int b) {
        // ��Ҫ���������������� 
        // ���������һ����������������λ�ӷ�
        // ��ôa ^ b����a��b���֮�󣬸ý�λ�ĵط�����λ�Ľ��
        // Ȼ�����濼����Щ�ط�Ҫ��λ����Ȼ��a��b�ﶼ��1�ĵط�
        // a & b����a��b�ﶼ��1����Щλ�ã�a & b << 1 ���ǽ�λ
        // ֮��Ľ�������ԣ�a + b = (a ^ b) + (a & b << 1)
        // ��a' = a ^ b, b' = (a & b) << 1
        // ����֪���������������ģ��ӷ���������̣���λ������
        // һֱ����������b���ջ��Ϊ0������ظ������������Ϳ���
        // ���a + b��ֵ��
        while (b != 0) {
            int _a = a ^ b;
            int _b = (a & b) << 1;
            a = _a;
            b = _b;
        }
        return a;
    }

}

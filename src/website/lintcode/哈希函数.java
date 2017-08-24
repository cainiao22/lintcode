package website.lintcode;

/**
 * 
 * @author yanpf
 * @description 

	�����ݽṹ�У���ϣ������������һ���ַ��������κ��������ͣ�ת��ΪС�ڹ�ϣ���С�Ҵ��ڵ������������һ���õĹ�ϣ�������Ծ������ٵز�����ͻ��һ�ֹ㷺ʹ�õĹ�ϣ�����㷨��ʹ����ֵ33�������κ��ַ������ǻ���33��һ�������������磺
	
	hashcode("abcd") = (ascii(a) * 333 + ascii(b) * 332 + ascii(c) *33 + ascii(d)) % HASH_SIZE 
	
	                              = (97* 333 + 98 * 332 + 99 * 33 +100) % HASH_SIZE
	
	                              = 3595978 % HASH_SIZE
	
	����HASH_SIZE��ʾ��ϣ��Ĵ�С(���Լ���һ����ϣ�����һ������0 ~ HASH_SIZE-1������)��
	
	����һ���ַ�����Ϊkey��һ����ϣ��Ĵ�С����������ַ����Ĺ�ϣֵ��

 * @example ����key="abcd" ���� size=100�� ���� 78
 * 
 * @Solution ˮ�� ע���ֹԽ��
 */
public class ��ϣ���� {
	
	public static void main(String[] args) {
		int result = new ��ϣ����().hashCode("abcd".toCharArray(), 100);
		System.err.println(result);
	}
	
	public int hashCode(char[] key,int HASH_SIZE) {
        // write your code here
		//������Ҫ��long,��ֹԽ��
		long sum = 0;
		for(int i=0; i<key.length; i++) {
			sum *= 33;
			sum += key[i];
			sum %= HASH_SIZE;
		}
		
		return (int)sum;
    }

}

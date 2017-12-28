package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��12��8�� ����6:29:24
 * @description
 * 		λ��ת -���� ��ת����ѭ����λ������������λ�Ĳ���, ��ͬ����һ���������һλ�ᱻ�Żص���һ��
		������ת��, ��˵���������һλ������Ҷ�
		���� n �� 8 λ����������. �� n = 11100101 ����ת 3 λ, �õ� n = 00101111 (����3λ, ԭ�ȵ�ǰ3λ����ĩβ).
		��� n �� 16 λ�� 32 λ����������, ��ô�� n (000��11100101)����ת��֮����� (00..0011100101000).
		�ڱ�������, ����Լ��� n ���� 32 λ�����������.
 * @example
 * 		���� n = 123, d = 4 ���� 183
 *
 * @Solution
 */
public class ����ת������λ extends HH {
	
	public int leftRotate(int n, int d) {
        // write code here
		return (n >>> (32-d)) + (n << d);
		
    }
	
	public static void main(String[] args) {
		System.out.println(Integer.toBinaryString(123));
		System.out.println(Integer.toBinaryString(112));
		System.out.println(Integer.toBinaryString(123 << 4));
		System.out.println(123 << 4);
		print(new ����ת������λ().leftRotate(123, 4));
	}

}

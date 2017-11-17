package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��11��14�� ����12:23:00
 * @description
 * 		����쳲����������е� N ������

		��ν��쳲�����������ָ��
		
		    ǰ2������ 0 �� 1 ��
		    �� i �����ǵ� i-1 �����͵�i-2 �����ĺ͡�
		
		쳲��������е�ǰ10�������ǣ�
		
		0, 1, 1, 2, 3, 5, 8, 13, 21, 34 ...
 * @example
 * 		���� 1������ 0

		���� 2������ 1
		
		���� 10������ 34
 *
 * @Solution
 */
public class 쳲��������� extends HH {
	
	public static int fibonacci(int n) {
        // write your code here
		if(n == 1 || n == 2) {
			return n - 1;
		}
		int last = 0;
		int current = 1;
		for(int i=3; i<=n; i++) {
			int temp = current + last;
			last = current;
			current = temp;
		}
		return current;
    }
	
	public int fibonacciFrmJiuZhang(int n) {
        int a = 0;
        int b = 1;
        //����Ӽ���
        for (int i = 0; i < n - 1; i++) {
            int c = a + b;
            a = b;
            b = c;
        }
        return a;
    }
	
	public static void main(String[] args) {
		print(fibonacci(10));
	}

}

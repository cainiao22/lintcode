package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2018��1��3�� ����10:51:47
 * @description
 * 		���һ���㷨�������n�׳���β����ĸ���
 * @example
 *
 * @Solution
 */
public class β������ {
	
	public long trailingZeros(long n) {
        // write your code here, try to do it without arithmetic operators.
		/**
		 * ��ѡ�ж��ж��ٸ�5�����ж��ٸ�0�����ǵ�5*5��5*5*5�������
		 * ÿ��һ��5��������1,5*5�����ж�n���ж���25��5*5*5�����ж�n���ж���125.�Դ�����
		 */
		long count = 0;
		for(long i = 5; i<= n; i*= 5) {
			count += n / i;
		}

		/**
		 * Ҳ����������������Ч�ʻ�ͺö�
		 
		for(long i = 5; i<= n; i+= 5) {
			long temp = i;
			while(temp % 5 == 0) {
				count ++;
				temp = temp / 5;
			}
		}
		*/
		return count;
    }
	
	/**
	 * ˼����һ����
	 * @param n
	 * @return
	 */
	public long trailingZeros2(long n) {
        long sum = 0;
        while (n != 0) {
            sum += n / 5;
            n /= 5;
        }
        return sum;
    }
	
	public static void main(String[] args) {
		System.out.println(new β������().trailingZeros(11));
	}

}

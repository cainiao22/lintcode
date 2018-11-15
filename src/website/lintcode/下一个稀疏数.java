package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2018��1��5�� ����3:23:52
 * @description
 * 		һ������ϡ�������������� �����Ʊ�ʾ ��û�����ڵ� 1������һ�� n ���ҳ����ڻ���� n ����С��ϡ������
		eg. 5 (�����Ʊ�ʾΪ 101)��ϡ���������� 6 (�����Ʊ�ʾΪ 110 ������ϡ����
 * @example
 * 		���� n = 6,���� 8��һ��ϡ������8
		���� n = 4,���� 4��һ��ϡ������4
		���� n = 38,���� 40��һ��ϡ������40
		���� n = 44,���� 64��һ��ϡ������64
 *
 * @Solution
 */
public class ��һ��ϡ���� extends HH {
	
	/**
	 * ��������ڵ�1�������������ư�λ���Ȼ�᲻Ϊ0��ͬ����������������Ҳ��
	 * @param x
	 * @return
	 */
	public static int nextSparseNum(int x) {
        // write your code here
		for(int i=x; i<Integer.MAX_VALUE; i++) {
			if((i & (i >> 1)) == 0 && (i & (i << 1)) == 0) {
				return i;
			}
		}
		return -1;
    }
	
	/**
	 * ��һ����߼�
	 * @param x
	 * @return
	 */
	public static int nextSparseNum2(int x) {
        // write your code here
		while(true) {
			if((x & (x >> 1)) == 0 && (x & (x << 1)) == 0) {
				break;
			}
			//��ΪҪ�ô���x����Сֵ������x���ϳ�ͻ�����һλ֮�󣬺����1��Ҫȫ����������� ������  11001 ->  100001�������Ǹ�1����Ҫ����ģ����100000
			int x2 = x & (x >> 1);
			x2 = (x2^(x2-1)) + 1;
			x2 = x2 >> 1;
			x += x2;
			int x3 = ~(x2^(x2-1));
			x = x & x3;
			
		}
		
		return x;
		
    }
	
	public static void main(String[] args) {
		int[] nums = {/*6,4,38,44, */341381939};
		for(int num : nums) {
			print(nextSparseNum2(num));//343932928
		}
		print(Integer.toBinaryString(341381939));
		print(Integer.toBinaryString(343932928));
		print(Integer.toBinaryString(344003652));
		
		print("-------------------");
		
		print(Integer.toBinaryString(524561));
		
		
	}
}

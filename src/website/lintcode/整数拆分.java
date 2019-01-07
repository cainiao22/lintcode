package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2019��1��7�� ����4:07:12
 * @description  ����һ�������� n �������ֳ���������������֮�ͣ�����ʹ��Щ����֮����󡣷���������˻�
 * @example ���� n = 2������ 1 (2 = 1 + 1)������ n = 10������ 36 (10 = 3 + 3 + 4)��
 *
 * @Solution
 */
public class ������� {
	
	 /**
	  * dp�㷨
	  * @param n
	  * @return
	  */
	 public static int integerBreak(int n) {
	        int table[] = new int[n+1];
	        table[1] = 1;
	        for(int i=2; i<=n; i++) {
	        	int max = 0;
	        	for(int j=i/2; j<i; j++) {
	        		max = Math.max(max, table[j] * table[i - j]);
	        		//�����������Ļ�����Ҫ������һ��
	        		max = Math.max(max, table[j] * (i - j));
	        		max = Math.max(max, j * (i - j));
	        	}
	        	table[i] = max;
	        }
	        
	        return table[n];
	    }
	 
	 /**
	  * ����ѧ�㷨 ���������3
	  * @param n
	  * @return
	  */
	 public static int integerBreak2(int n) {
		 if (n == 2 || n == 3) return n - 1;
	        int res = 1;
	        while (n > 4) {
	            res *= 3;
	            n -= 3;
	        }
	        return res * n;
	    }
	 
	 public static void main(String[] args) {
		System.out.println(integerBreak(15));
	}

}

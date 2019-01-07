package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2019年1月7日 下午4:07:12
 * @description  给定一个正整数 n ，将其拆分成至少两个正整数之和，并且使这些整数之积最大。返回这个最大乘积
 * @example 给定 n = 2，返回 1 (2 = 1 + 1)；给定 n = 10，返回 36 (10 = 3 + 3 + 4)。
 *
 * @Solution
 */
public class 整数拆分 {
	
	 /**
	  * dp算法
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
	        		//如果是它本身的话，需要重新来一下
	        		max = Math.max(max, table[j] * (i - j));
	        		max = Math.max(max, j * (i - j));
	        	}
	        	table[i] = max;
	        }
	        
	        return table[n];
	    }
	 
	 /**
	  * 纯数学算法 神奇的数字3
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

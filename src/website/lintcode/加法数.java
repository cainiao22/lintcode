package website.lintcode;


import java.util.Arrays;

/**
 * 
 * @author yanpf
 * @date 2019年2月28日 上午11:53:42
 * @description
 * 		加法数是一个字符串，它包含的数字可以形成一个加法序列。

		一个合法的加法序列至少需要包含三个数字。除了前两个数字，序列中后面的每个数字必须是前两个数字的和。
		
		加法序列中的数字不能有前导0， 因此序列1, 2, 03 或者 1, 02, 3 是不合法的。
		
		给出的字符串只包含数字 '0'-'9', 写一个函数来判断它是否是一个加法数。
		
 * @example
 * 		样例
		"112358" 是一个加法数， 因为这些数字可以形成一个加法序列：1, 1, 2, 3, 5, 8。
		
		1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
		"199100199" 也是一个加法数, 加法序列为： 1, 99, 100, 199。
		
		1 + 99 = 100, 99 + 100 = 199
		
		如何处理 非常大的输入整数 导致的溢出？
 *
 * @Solution
 */
public class 加法数 {

	public boolean isAdditiveNumber(String num) {
		String[][] dp = new String[num.length()][num.length()];
		String EMPTY = "-1";
		for(int i=0; i<dp.length; i++) {
			Arrays.fill(dp[i], EMPTY);
		}
		for(int i=0; i<dp.length; i++) {
			if(num.charAt(i) - '0' == 0) {
				dp[i][i] = "0";
				continue;
			}
			for(int j=i; j<dp.length; j++) {
				dp[i][j] = num.substring(i, j + 1);
			}
		}
		for(int i=2; i<dp.length; i++) {
			for(int j=i; j<dp.length; j++) {
				if(dp[i][j] == EMPTY) {
					continue;
				}
				for3:
				for(int k=0; k<i; k++) {
					String num1 = dp[0][k];
					String num2 = dp[k+1][i-1];
					if(num1 == EMPTY || num2 == EMPTY) {
						continue;
					}
					if(dp[i][j].compareTo(add(num1, num2)) == 0) {
						if(j == dp.length - 1) {
							return true;
						}
						int m = i, n = j;
						while(n < dp.length) {
							num1 = num2;
							num2 = dp[m][n];
							m = n + 1;
							n = m;
							while(n < dp.length) {
								if(dp[m][n].compareTo(add(num1, num2)) == 0) {
									if(n == dp.length - 1) {
										return true;
									}else {
										break;
									}
								}
								n ++;
							}
							if(n == dp.length) {
								continue for3;
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	private String add(String num1, String num2) {
		StringBuffer sb = new StringBuffer();
		int i = num1.length() - 1;
		int j = num2.length() - 1;
		int c = 0;
		while(i >= 0 && j >= 0) {
			char c1 = num1.charAt(i);
			char c2 = num2.charAt(j);
			if(c1 + c2 + c - '0' > '9') {
				char item = (char) (c1 + c2 + c - '0' - 10);
				c = 1;
				sb.insert(0, item);
			}else {
				char item = (char) (c1 + c2 - '0' + c);
				c = 0;
				sb.insert(0, item);
			}
			i --;
			j --;
		}
		
		while(i >= 0) {
			char c1 = num1.charAt(i);
			if(c1 + c > '9') {
				char item = (char) (c1 + c - 10);
				c = 1;
				sb.insert(0, item);
			}else {
				char item = (char) (c1 + c);
				c = 0;
				sb.insert(0, item);
			}
			i--;
		}
		
		while(j >= 0) {
			char c1 = num2.charAt(j);
			if(c1 + c > '9') {
				char item = (char) (c1 + c - 10);
				c = 1;
				sb.insert(0, item);
			}else {
				char item = (char) (c1 + c);
				c = 0;
				sb.insert(0, item);
			}
			j--;
		}
		//最后竟然忘了这个进位
		if(c != 0) {
			sb.insert(0, c);
		}
		return sb.toString();
	}
	
	public boolean isAdditiveNumberBetter(String num) {
		for(int i=1; i<num.length() / 2; i++) {
			//最后至少预留出i个字符串来匹配s3
			for(int j=i+1; j<num.length() - i; j++) {
				String s1 = num.substring(0, i);
				String s2 = num.substring(i, j);
				if((s1.length() > 1 && s1.charAt(0) == '0') 
						|| (s2.length() > 1 && s2.charAt(0) == '0')){
					continue;
				}
				String now = s1 + s2;
				String s3 = "";
				while(now.length() < num.length()) {
					s3 = add(s1, s2);
					now += s3;
					s1 = s2;
					s2 = s3;
					s3 = add(s1, s2);
				}
				if(now.equals(num)) {
					return true;
				}
			}
		}
		
		return false;
	}

	public static void main(String[] args) { //121474836472147483648
		boolean result = new 加法数().isAdditiveNumberBetter("111122335588143");
		System.out.println(result);
	}
}

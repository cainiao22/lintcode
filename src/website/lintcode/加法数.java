package website.lintcode;


import java.util.Arrays;

/**
 * 
 * @author yanpf
 * @date 2019��2��28�� ����11:53:42
 * @description
 * 		�ӷ�����һ���ַ����������������ֿ����γ�һ���ӷ����С�

		һ���Ϸ��ļӷ�����������Ҫ�����������֡�����ǰ�������֣������к����ÿ�����ֱ�����ǰ�������ֵĺ͡�
		
		�ӷ������е����ֲ�����ǰ��0�� �������1, 2, 03 ���� 1, 02, 3 �ǲ��Ϸ��ġ�
		
		�������ַ���ֻ�������� '0'-'9', дһ���������ж����Ƿ���һ���ӷ�����
		
 * @example
 * 		����
		"112358" ��һ���ӷ����� ��Ϊ��Щ���ֿ����γ�һ���ӷ����У�1, 1, 2, 3, 5, 8��
		
		1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
		"199100199" Ҳ��һ���ӷ���, �ӷ�����Ϊ�� 1, 99, 100, 199��
		
		1 + 99 = 100, 99 + 100 = 199
		
		��δ��� �ǳ������������ ���µ������
 *
 * @Solution
 */
public class �ӷ��� {

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
		//���Ȼ���������λ
		if(c != 0) {
			sb.insert(0, c);
		}
		return sb.toString();
	}
	
	public boolean isAdditiveNumberBetter(String num) {
		for(int i=1; i<num.length() / 2; i++) {
			//�������Ԥ����i���ַ�����ƥ��s3
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
		boolean result = new �ӷ���().isAdditiveNumberBetter("111122335588143");
		System.out.println(result);
	}
}

package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2019��1��22�� ����6:28:54
 * @description
 * 
 * 				ʹ������ӳ�䷽ʽ�� A-Z ����Ϣ����Ϊ����:
 * 
 *              'A' -> 1 
 *              'B' -> 2 ... 
 *              'Z' -> 26 
 *              ����֮��, ������ַ���Ҳ���԰����ַ� *, 
 *              �������� 1��9 �������е�����һ��.�����������ֺ��ַ� * �ı�����Ϣ, �������н��뷽ʽ������. 
 *              ��Ϊ������ܴܺ�, ���Խ����Ҫ��10^9 + 7 ȡģ
 * 
 *              ������ַ����ڷ�Χ [1, 10^5] ��. ������ַ���ֻ�ܰ����ַ� * ������ 0 - 9.
 *              
 * @example		���� s = "*"
				���� 9 // ������Ϣ���Ա�����Ϊ�ַ���: "A", "B", "C", "D", "E", "F", "G", "H", "I"
				
				���� s = "1*"
				���� 18
 *
 * @Solution
 */
public class ���뷽ʽII extends HH {
	
	 public static int numDecodings(String s) {
	        // write your code here
		 if(s == null || s.length() == 0) {
			 return 0;
		 }
		 char[] c = s.toCharArray();
		 long[] dp = new long[c.length + 1];
		 dp[0] = 1;
		 if(c[0] == '*') {
			 dp[1] = 9;
		 }else {
			 dp[1] = 1;
		 }
		 
		 for(int i=1; i<c.length; i++) {
			 //�����
			 if(c[i] == '*') {
				 dp[i+1] = dp[i] * 9;
			 }else if(c[i] > '0'){
				 dp[i+1] = dp[i];
			 }
			 //���
			 if(c[i] == '*') {
				 if(c[i-1] == '1') {
					 dp[i+1] += dp[i-1] * 9;
				 }else if(c[i-1] == '2') {
					 dp[i+1] += dp[i-1] * 6;
				 }else if(c[i-1] == '*') {
					 dp[i+1] += dp[i-1] * 9;
					 dp[i+1] += dp[i-1] * 6;
				 }
			 }else if(c[i] <= '6'){
				 if(c[i-1] <= '2' && c[i-1] > '0') {
					 dp[i+1] += dp[i-1];
				 }else if(c[i-1] == '*') {
					 dp[i+1] += dp[i-1] * 2;
				 }
			 }else {
				 if(c[i-1] == '1' || c[i-1] == '*') {
					 dp[i+1] += dp[i-1];
				 }
			 }
			 
			 //dp[i+1] = (int) (dp[i+1] % (Math.pow(10, 9) + 7));
		 }
		 
		 return (int) (dp[c.length] % Double.valueOf((Math.pow(10, 9) + 7)).longValue());
	 }
	 
	 public static void main(String[] args) {
		long result = numDecodings("**********");
		int a = 598555023;
		System.out.println(result);
		System.out.println((a % (Math.pow(10, 9) + 7)));
	}

}

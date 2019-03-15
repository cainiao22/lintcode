package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2019年1月22日 下午6:28:54
 * @description
 * 
 * 				使用以下映射方式将 A-Z 的消息编码为数字:
 * 
 *              'A' -> 1 
 *              'B' -> 2 ... 
 *              'Z' -> 26 
 *              除此之外, 编码的字符串也可以包含字符 *, 
 *              它代表了 1到9 的数字中的其中一个.给出包含数字和字符 * 的编码消息, 返回所有解码方式的数量. 
 *              因为结果可能很大, 所以结果需要对10^9 + 7 取模
 * 
 *              输入的字符串在范围 [1, 10^5] 内. 输入的字符串只能包含字符 * 和数字 0 - 9.
 *              
 * @example		给出 s = "*"
				返回 9 // 编码信息可以被解码为字符串: "A", "B", "C", "D", "E", "F", "G", "H", "I"
				
				给出 s = "1*"
				返回 18
 *
 * @Solution
 */
public class 解码方式II extends HH {
	
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
			 //不结合
			 if(c[i] == '*') {
				 dp[i+1] = dp[i] * 9;
			 }else if(c[i] > '0'){
				 dp[i+1] = dp[i];
			 }
			 //结合
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

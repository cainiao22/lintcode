package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2019年3月13日 下午6:09:10
 * @description
 * 		现在有1个01串str，你的任务是寻找到最长的01连续子串，0和1交替出现，例如010，10101，01。
 * 		然而，这对优秀的lintcode的学员来说，有些过于简单了。那么现在，你可以进行一些操作，使得01连续子串尽可能的长。
 * 		操作是指，你可以选择一个位置，将字符串断开，变成两个字符串，然后每个字符串翻转，最后按照原来的顺序拼接在一起。
 * 		你可以进行0次或多次这样的操作，返回你最终能够获得的最大01连续子串的长度。
 * 
 * 
 * @example
 * 		给出str="100010010"，返回5。

		你可以进行如下分割10|0010010，两边翻转后，变成了01|0100100，
		即010100100，选择位置1到位置5，01010，长度为5。
		给出str="1001"，返回2。
		
		不管如何分割翻转，都不会使得答案变大。所以10即为最大01连续子串，返回2。
 *
 * @Solution
 */
public class 寻找最长01子串 {

	/**
	 * 暴力解法   超时了
	 * @param str
	 * @return
	 */
	public int askingForTheLongest01Substring(String str) {
		int max = 0;
       for(int i=0; i<str.length(); i++) {
    	   int next = str.charAt(i) - '0';
    	   int count = 0;
    	   for(int j=0; j<str.length(); j++) {
    		   int a = str.charAt((i+j) % str.length()) - '0';
    		   if(a == next) {
    			   next = 1 ^ next;
    			   count ++;
    		   }else {
    			   break;
    		   }
    	   }
    	   
    	   max = Integer.max(count, max);
       }
       
       return max;
    }
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public int askingForTheLongest01SubstringDP(String str) {
		//dp[i]表示以i为结尾的符合要求的字符串长度
		int[] dp = new int[str.length()];
		dp[0] = 1;
		for(int i=1; i<str.length(); i++) {
			if(str.charAt(i-1) != str.charAt(i)) {
				dp[i] = dp[i-1] + 1;
			}else{
				dp[i] = 1;
			}
		}
		
		int max = 0;
		for(int i=0; i<dp.length; i++) {
			int item = dp[i];
			if(item == i+1 && str.charAt(0) != str.charAt(dp.length - 1)) {
				item += dp[dp.length - 1];
			}
			
			max = Integer.max(max, item);
		}
		
		if(max > dp.length) {
			max = dp.length;
		}
		
		return max;
		
	}
	
	/**
	 * 另一种思路的dp解法
	 * @param str
	 * @return
	 */
	public int askingForTheLongest01SubstringDP2(String str) {
		int[] dp = new int[str.length() * 2];
		String str2 = str + str;
		dp[0] = 1;
		int max = 0;
		for(int i=1; i<str2.length(); i++) {
			if(str2.charAt(i-1) != str2.charAt(i)) {
				dp[i] = dp[i-1] + 1;
			}else{
				dp[i] = 1;
			}
			if(dp[i] >= dp.length / 2) {
				return dp[i];
			}
			if(dp[i] > max) {
				max = dp[i];
			}
		}
		
		return max;
	}
	
	public static void main(String[] args) {
		String str = "010101";
		System.out.println(new 寻找最长01子串().askingForTheLongest01SubstringDP(str));
	}
}

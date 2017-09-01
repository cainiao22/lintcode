package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年8月29日 下午4:28:25
 * @description 给出一个包含大小写字母的字符串。求出由这些字母构成的最长的回文串的长度是多少。
 * 				数据是大小写敏感的，也就是说，"Aa" 并不会被认为是一个回文串。
 * 
 * @example 给出 s = "abccccdd" 返回 7.一种可以构建出来的最长回文串方案是 "dccaccd"。
 *
 * @Solution	1、暴力解法。最直观的。忽略
 * 				2、统计每个字符出现了几次，然后每个字符的次数如果是偶数就保留，奇数就-1，并标记存在奇数，所有次数相加，
 * 				      如果有奇数再+1，就是答案吧。他又没让输出方案数
 */
public class 最长回文串 {
	
	public int longestPalindrome(String s) {
        int[] hash = new int[128];
		for(int i=0; i<s.length(); i++) {
			char a = s.charAt(i);
			hash[a] += 1;
		}
		int result = 0;
		int flag = 0;
		for(int i=0; i<hash.length; i++) {
			result += hash[i] / 2;
			if(hash[i] % 2 == 1) {
				flag = 1;
			}
		}
		
		return result*2 + flag;
    }

}

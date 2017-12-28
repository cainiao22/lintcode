package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年10月25日 下午5:35:01
 * @description
 * 		
		给定一个字符串s，将s分割成一些子串，使每个子串都是回文。	
		返回s符合要求的的最少分割次数。

 * @example
 * 		比如，给出字符串s = "aab"，
		返回 1， 因为进行一次分割可以将字符串s分割成["aa","b"]这样两个回文子串
 *
 * @Solution
 */
public class 分割回文串II extends HH {
	
	//todo 待优化 超时了
	public int minCut(String s) {
        // write your code here
		if(s == null || s.length() <= 1) {
			return 0;
		}
		int length = s.length();
		int[][] min = new int[length][length];
		for(int i=0; i<length-1; i++) {
			if(s.charAt(i) == s.charAt(i+1)) {
				min[i][i+1] = 0;
			}else {
				min[i][i+1] = 1;
			}
		}
		for(int i=2; i<length; i++) {//间隔大小
			for(int j=0; j<length-i; j++) { //最左端
				if(isPalindromic(s.substring(j, j+i+1))) {
					min[i][j] = 0;
					continue;
				}
				int m = Integer.MAX_VALUE;
				for(int k=j; k<j+i; k++) { //最右端
					m = Math.min(m, min[j][k] + min[k+1][j+i] + 1);
				}
				min[j][j+i] = m;
			}
		}
		print(min);
		return min[0][length-1];
    }

	private boolean isPalindromic(String substring) {
		for(int i=0, j=substring.length()-1; i<j; i++, j--) {
			if(substring.charAt(i) != substring.charAt(j)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean[][] getIsPalindromic(String s){
		boolean[][] isPalindromic = new boolean[s.length()][s.length()];
		for(int i=0; i<s.length(); i++) {
			isPalindromic[i][i] = true;
		}
		for(int i=0; i<s.length()-1; i++) {
			isPalindromic[i][i+1] = (s.charAt(i) == s.charAt(i+1));
		}
		
		for(int len = 2; len < s.length(); len ++) {
			for(int i=0; i+len<s.length(); i++) { //这里很聪明
				isPalindromic[i][i+len] = isPalindromic[i+1][i+len-1] && s.charAt(i) == s.charAt(i+len);
			}
		}
		
		return isPalindromic;
		
	}
	
	public int minCutFromJiuZhang(String s) {
		boolean[][] isPalindromic = getIsPalindromic(s);
		//前i个字符串切割。最少切多少刀
		int[] f = new int[s.length()+1];
		f[0] = 0;
		for(int i=1; i<=s.length(); i++) {
			f[i] = i;
			for(int j=0; j<i; j++) {
				if(isPalindromic[j][i-1]){
					f[i] = Math.min(f[i], f[j] + 1);
				}
			}
		}
		
		return f[s.length()] - 1;
	}
	
	public static void main(String[] args) {
		print(new 分割回文串II().minCutFromJiuZhang("abbab"));
		System.out.println("30d4bf869d4311e791311866daf6aff9".length());
	}

}

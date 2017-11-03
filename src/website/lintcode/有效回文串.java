package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年11月1日 上午11:51:40
 * @description
 * 		
		给定一个字符串，判断其是否为一个回文串。只包含字母和数字，忽略大小写。
		注意事项
		
		你是否考虑过，字符串有可能是空字符串？这是面试过程中，面试官常常会问的问题。
		
		在这个题目中，我们将空字符串判定为有效回文。
		您在真实的面试中是否遇到过这个题？ 
		
 * @example
 * 
 * 		"A man, a plan, a canal: Panama" 是一个回文。
		"race a car" 不是一个回文。
 *
 * @Solution
 */
public class 有效回文串 {
	
	public boolean isPalindrome(String s) {
        // write your code here
		int i =0, j = s.length()-1;
		while(i < j) {
			while(i < j && !isInRange(s.charAt(i))) {
				i ++;
			}
			while(i < j && !isInRange(s.charAt(j))) {
				j --;
			}
			if(i >= j) {
				break;
			}
			if(s.charAt(i) != s.charAt(j) && Math.abs(s.charAt(i) - s.charAt(j)) != ('a' - 'A')) {
				break;
			}
			i ++;
			j --;
		}
		//这个有意思
		return j <= i;
    }
	
	private boolean isInRange(char a) {
		return (a >= '0' && a <= '9') || (a>= 'a' && a<= 'z') || (a >= 'A' && a<= 'Z');
	}
	
	public static void main(String[] args) {
		new 有效回文串().isPalindrome("aA");
	}

}

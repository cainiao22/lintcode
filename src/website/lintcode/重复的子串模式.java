package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2019年1月7日
 * @description 给你一个非空字符串，判断它能否通过重复它的某一个子串若干次（两次及以上）得到。字符串由小写字母组成，并且它的长度不会超过10000
 * 
 * @example 样例1：
 * 
 *          输入："abab"
 * 
 *          输出：True
 * 
 *          说明：可以由它的子串"ab"重复两次得到。 样例2：
 * 
 *          输入："aba"
 * 
 *          输出：False 样例3：
 * 
 *          输入："abcabcabcabc"
 * 
 *          输出：True
 * 
 *          说明：可以由它的子串"abc"重复四次得到（同时也可以是"abcabc"重复两次）。
 * 
 * 
 * @Solution 问其是否能拆成n个重复的子串。那么既然能拆分成多个子串，那么每个子串的长度肯定不能大于原字符串长度的一半，那么我们可以从原字符串长度的一半遍历到1，
 *           如果当前长度能被总长度整除，说明可以分成若干个子字符串，我们将这些子字符串拼接起来看跟原字符串是否相等。
 *           如果拆完了都不相等，返回false。
 */
public class 重复的子串模式 {

	public boolean repeatedSubstringPattern(String s) {
		// write your code here
		for (int n = s.length() / 2; n > 0; n--) {
			if (s.length() % n == 0) {
				int repeat = s.length() / n;
				StringBuffer item = new StringBuffer();
				for (int i = 0; i < n; i++) {
					char a = s.charAt(i);
					item.append(a);
				}
				String a = item.toString();
				for (int i = 1; i < repeat; i++) {
					item.append(a);
				}

				if (s.equals(item.toString())) {
					return true;
				}
			}
		}

		return false;
	}
	
	public boolean repeatedSubstringPatternKMP(String s) {
		// write your code here
		for (int n = s.length() / 2; n > 0; n--) {
			if (s.length() % n == 0) {
				int repeat = s.length() / n;
				StringBuffer item = new StringBuffer();
				for (int i = 0; i < n; i++) {
					char a = s.charAt(i);
					item.append(a);
				}
				String a = item.toString();
				for (int i = 1; i < repeat; i++) {
					item.append(a);
				}

				if (s.equals(item.toString())) {
					return true;
				}
			}
		}

		return false;
	}

}

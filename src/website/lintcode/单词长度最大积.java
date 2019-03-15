package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2019年1月23日 下午12:02:11
 * @description 给定一个字符串数组words，找length(word[i]) * length(word[j])的最大值，
 * 				而且要保证这两个单词没有共同字符。假定每一个单词都仅有小写字符组成。如果没有这样的两个单词，则返回0。
 * 
 * 
 * @example		样例 1:
				给定 ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
				返回 16
				两个单词分别是 "abcw", "xtfn".
				
				Example 2:
				给定 ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
				返回 4
				两个单词可以是 "ab", "cd".
				
				样例 3:
				给定 ["a", "aa", "aaa", "aaaa"]
返回 0
没有这样的两组单词。
 *
 * @Solution
 */
public class 单词长度最大积 {
	
	public int maxProduct(String[] words) {
        // Write your code here
		int max = 0;
		for(int i=0; i<words.length - 1; i++) {
			for(int j=i+1; j<words.length; j ++) {
				if(valid(words[i], words[j])) {
					max = Math.max(max, words[i].length() * words[j].length());
				}
			}
		}
		
		return max;
    }
	
	/**
	 * 运用bitmap思想
	 * @param s1
	 * @param s2
	 * @return
	 */
	private static boolean valid(String s1, String s2) {
		int bitmap = 0;
		for(int i=0; i<s1.length(); i++) {
			bitmap |= (1 << (s1.charAt(i) - 'a'));
		}
		for(int i=0; i<s2.length(); i++) {
			if((bitmap & (1 << (s2.charAt(i) - 'a'))) != 0) {
				return false;
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		boolean result = valid("aa", "aaaa");
		System.out.println(result);
	}

}

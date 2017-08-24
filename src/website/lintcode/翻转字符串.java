package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年8月17日 下午4:54:48
 * @description 给定一个字符串，逐个翻转字符串中的每个单词 单词的构成：无空格字母构成一个单词
 *              输入字符串是否包括前导或者尾随空格？可以包括，但是反转后的字符不能包括
 *              如何处理两个单词间的多个空格？在反转字符串中间空格减少到只含一个
 * @example
 *
 * @Solution 先将字符串按单词翻转一遍，然后在整体翻转回来
 */
public class 翻转字符串 {

	public String reverseWords(String s) {
		// write your code
		String[] words = s.split(" ");
		String revertedWord = "";
		for (int i = words.length - 1; i >= 0; i--) {
			if (!"".equals(words[i])) {
				revertedWord += words[i];
				revertedWord += " ";
			}
		}
		String result = "";
		for (int j = revertedWord.length() - 2; j >= 0; j--) {
			result += revertedWord.charAt(j);
		}

		return result;
	}

	public String reverseWords2(String s) {

		if (s == null || s.length() == 0) {
			return "";
		}
		String[] array = s.split(" ");
		StringBuilder sb = new StringBuilder();
		for (int i = array.length - 1; i >= 0; --i) {
			if (!array[i].equals("")) {
				sb.append(array[i]).append(" ");
			}
		}
		// remove the last " "
		return sb.length() == 0 ? "" : sb.substring(0, sb.length() - 1);

	}

	public static void main(String[] args) {
		System.out.println(new 翻转字符串().reverseWords2(" hello world!  thank  you func "));
	}

}

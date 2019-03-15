package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2019��1��23�� ����12:02:11
 * @description ����һ���ַ�������words����length(word[i]) * length(word[j])�����ֵ��
 * 				����Ҫ��֤����������û�й�ͬ�ַ����ٶ�ÿһ�����ʶ�����Сд�ַ���ɡ����û���������������ʣ��򷵻�0��
 * 
 * 
 * @example		���� 1:
				���� ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
				���� 16
				�������ʷֱ��� "abcw", "xtfn".
				
				Example 2:
				���� ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
				���� 4
				�������ʿ����� "ab", "cd".
				
				���� 3:
				���� ["a", "aa", "aaa", "aaaa"]
���� 0
û�����������鵥�ʡ�
 *
 * @Solution
 */
public class ���ʳ������� {
	
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
	 * ����bitmap˼��
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

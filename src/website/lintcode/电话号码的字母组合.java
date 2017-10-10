package website.lintcode;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author yanpf
 * @date 2017��10��10�� ����9:55:09
 * @description
 * 		Given a digit string excluded 01, return all possible letter combinations that the number could represent.

		A mapping of digit to letters (just like on the telephone buttons) is given below.
		
 * @example
 * 		���� "23"
		���� ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
 *
 * @Solution �ݹ� && ����
 */
public class �绰�������ĸ��� extends HH {
	
	private List<String> result = new ArrayList<>();
	
	public List<String> letterCombinations(String digits) {
        // write your code here
		StringBuffer sb = new StringBuffer();
		letterCombinations(digits, 0, sb);
		return result;
    }
	
	
	private void letterCombinations(String digits, int index, StringBuffer item) {
		if(index == digits.length()) {
			if(item.length() > 0) {
			result.add(item.toString());
			}
			return;
		}
		int N = 3;
		int num = digits.charAt(index) - '0';
		if(num == 7 || num == 9) {
			N = 4;
		}
		
		for(int i=0; i<N; i++) {
			char a = (char) ('a' + (num-2)*3 + i + num/8);
			item.append(a);
			letterCombinations(digits, index + 1, item);
			item.setLength(item.length() - 1);
		}
	}
	
	/**
	 * �����㷨
	 * @param digits
	 * @return
	 */
	public List<String> letterCombinationsWithBacktrack(String digits) {
        // write your code here
		StringBuffer sb = new StringBuffer();
		letterCombinations(digits, 0, sb);
		return result;
    }
	
	
	
	public static void main(String[] args) {
		for(int num = 2; num<=9; num ++) {
			int N = 3;
			if(num == 7 || num == 9) {
				N = 4;
			}
			
			for(int i=0; i<N; i++) {
				System.out.print((char)('a' + (num-2)*3 + i + num/8));
			}
			System.out.println();
		}
		
		List<String> result = new �绰�������ĸ���().letterCombinations("234");
		print(result);
	}
	

}

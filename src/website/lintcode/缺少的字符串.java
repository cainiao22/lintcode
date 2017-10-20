package website.lintcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author yanpf
 * @date 2017��10��18�� ����12:17:39
 * @description
 * 		���������ַ���������Ҫ�ҵ�ȱ�ٵ��ַ���
 * @example
 * 		��һ���ַ��� str1 = This is an example, ������һ���ַ��� str2 = is example
		���� ["This", "an"]
 *
 * @Solution hash��             
 */
public class ȱ�ٵ��ַ��� extends HH {

	public List<String> missingString(String str1, String str2) {
        // Write your code here
		int[] hash = new int[Integer.MAX_VALUE/3];
		List<String> result = new ArrayList<>();
		for(String word : str2.split(" ")) {
			hash[word.hashCode()] ++;
		}
		
		for(String word : str1.split(" ")) {
			if(hash[word.hashCode() % hash.length] > 0) {
				result.add(word);
			}
			hash[word.hashCode() % hash.length] --;
		}
		
		return result;
		
    }
	
	public List<String> missingString2(String str1, String str2) {
        // Write your code here
		List<String> result = new ArrayList<>();
label:		for(String word1 : str1.split(" ")) {
			for(String word2 : str2.split(" ")) {
				if(word1.equals(word2)) {
					continue label;
				}
			}
			result.add(word1);
		}
		
		return result;
		
    }
}

package website.lintcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author yanpf
 * @date 2019��1��23�� ����11:54:48
 * @description
 * 
 * 				����һ���ַ���target���ַ�������s������s�е�ԭ�������s�����а���target���ַ���(������targetΪs[i]��һ��������)
 * 
 *              s.length<=1000 1<=s�������ַ�������֮��,target<=100000
 * 
 *              ������ʵ���������Ƿ�����������⣿
 * 
 * @example ����
 *          ����target="google",s=["goooogle","abc","google","higoogle","googlg","gowogwle","gogle"],����["goooogle","google","higoogle","gowogwle"]
 * 
 *          ����: goooogle google higoogle gowogwle
 *
 * @Solution
 */
public class �����������ַ��� {
	
	public String[] getAns(String target, String[] s) {
        // Write your code here
		List<String> result = new ArrayList<>();
		for (int i = 0; i < s.length; i++) {
			if(contains(s[i], target)) {
				result.add(s[i]);
			}
		}
		
		String[] reStrings = new String[result.size()];
		for(int i=0; i<result.size(); i++) {
			reStrings[i] = result.get(i);
		}
		
		return reStrings;
		
    }
	
	boolean contains(String s, String t) {
		for(int i=0, j=0; i<s.length() & j < s.length(); i++) {
			if(s.charAt(i) == t.charAt(j)) {
				j ++;
			}
			if(j == t.length()) {
				return true;
			}
		}
		
		return false;
	}

}

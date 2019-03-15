package website.lintcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author yanpf
 * @date 2019年1月23日 上午11:54:48
 * @description
 * 
 * 				给定一个字符串target和字符串数组s，按在s中的原次序输出s中所有包含target的字符串(即满足target为s[i]的一个子序列)
 * 
 *              s.length<=1000 1<=s中所有字符串长度之和,target<=100000
 * 
 *              您在真实的面试中是否遇到过这个题？
 * 
 * @example 样例
 *          给定target="google",s=["goooogle","abc","google","higoogle","googlg","gowogwle","gogle"],返回["goooogle","google","higoogle","gowogwle"]
 * 
 *          解释: goooogle google higoogle gowogwle
 *
 * @Solution
 */
public class 满足条件的字符串 {
	
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

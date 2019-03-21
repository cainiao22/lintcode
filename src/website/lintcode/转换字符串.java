package website.lintcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author yanpf
 * @date 2019年3月16日 上午11:04:09
 * @description
 * 			给一个startString，一个endString，是否可以通过一系列的转换从startString转变成endString。
 * 			规则是只有26个小写字母，每个操作只能更改一种字母。例如，如果将a更改为b，则起始字符串中的所有a必须为b。
 * 			对于每一类型的字符，可以选择转换或不转换，转换必须在startString中的一个字符和endString相对应的一个字符之间进行，
 * 			结果返回true或false。
 * @example
 * 
 * 			给定 : startString = "abc" ; endString = "cde"
			返回 : True
			解释 : 
			a->c, b->d, c->e
			"abc"->"abe"->"ade"->"cde"
			如果先转换成 "cbc" , 你再也转换不成 "cde" .
			给定 : startString = "abc" ; endString = "bca"
			返回 : False
			解释 : 
			a->b, b->c, c->a
			"abc"->"abe"->"ace"->"bce"->"bca" 是一个不合法的转换，因为'a'只能转换成'b'
			无论你先转换那一类字符，你都不能转换成目标字符串
			给定 : startString = "aba" ; endString = "cde"
			返回 : False
			解释 : 
			a->c, b->d,a->e
			不可能同时把 'a' 转换成 'c' 和 'e' 。
 *
 * @Solution
 */
public class 转换字符串 extends HH {
	
	public boolean canTransfer(String startString, String endString) {
        // Write your code here
		char[] map = new char[26];
		if(startString.length() != endString.length()) {
			return false;
		}
		//这个循环保证一个字符不会映射到两个字符上面
		for(int i=0; i<startString.length(); i++) {
			int index = startString.charAt(i) - 'a';
			if(map[index] != 0 && map[index] != endString.charAt(i)) {
				return false;
			}
			map[index] = endString.charAt(i);
		}
		//这个循环保证不会有环，自己对应自己的环不算
		outter:
		for(char c : startString.toCharArray()) {
			Set<Character> set = new HashSet<>();
			char a = map[(c - 'a')];
			if(a == c) {
				continue;
			}
			while(a != 0 && !set.contains(a)) {
				set.add(a);
				char b = map[(a - 'a')];
				if(b == a) {
					continue outter;
				}
				a = b;
			}
			
			if(a != 0) {
				return false;
			}
		}
		
		return true;
		
    }
	/**
	 * lintcode排行榜第一位
	 * @param startString
	 * @param endString
	 * @return
	 */
	public boolean canTransfertop1(String startString, String endString) {
        // Write your code here
        String s = startString;
        String e = endString;
        
        if(s.length() != e.length()) return false;
        int[] loc = new int[26];
        Arrays.fill(loc, -1);
        for(int i = 0; i < s.length(); i++){
            char chs = s.charAt(i);
            char che = e.charAt(i);
            if(loc[chs - 'a'] == -1) loc[chs - 'a'] = che - 'a';
            else if(loc[chs - 'a'] != che - 'a') return false; 
        }
        
        for(int i = 0; i < 26; i++) loc[i] = loc[i] == i ? -1 : loc[i];
        
        for(int i = 0; i < 26; i++){
            if(isCircle(i, loc)) return false;
        }
        return true;
    }
    
    private boolean isCircle(int i, int[] loc){
        if(loc[i] == -1) return false;
        int index = i;
        while(loc[i] != index){
            if(loc[i] == -1) return false;
            i = loc[i];
        }
        return true;
    }
	
	public static void main(String[] args) {
		
		//boolean result = new 转换字符串().canTransfer("dbadf", "decad");
		boolean result = new 转换字符串().canTransfer("abc", "cde");
		System.out.println(result);
		
	}

}

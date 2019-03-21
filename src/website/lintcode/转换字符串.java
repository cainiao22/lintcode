package website.lintcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author yanpf
 * @date 2019��3��16�� ����11:04:09
 * @description
 * 			��һ��startString��һ��endString���Ƿ����ͨ��һϵ�е�ת����startStringת���endString��
 * 			������ֻ��26��Сд��ĸ��ÿ������ֻ�ܸ���һ����ĸ�����磬�����a����Ϊb������ʼ�ַ����е�����a����Ϊb��
 * 			����ÿһ���͵��ַ�������ѡ��ת����ת����ת��������startString�е�һ���ַ���endString���Ӧ��һ���ַ�֮����У�
 * 			�������true��false��
 * @example
 * 
 * 			���� : startString = "abc" ; endString = "cde"
			���� : True
			���� : 
			a->c, b->d, c->e
			"abc"->"abe"->"ade"->"cde"
			�����ת���� "cbc" , ����Ҳת������ "cde" .
			���� : startString = "abc" ; endString = "bca"
			���� : False
			���� : 
			a->b, b->c, c->a
			"abc"->"abe"->"ace"->"bce"->"bca" ��һ�����Ϸ���ת������Ϊ'a'ֻ��ת����'b'
			��������ת����һ���ַ����㶼����ת����Ŀ���ַ���
			���� : startString = "aba" ; endString = "cde"
			���� : False
			���� : 
			a->c, b->d,a->e
			������ͬʱ�� 'a' ת���� 'c' �� 'e' ��
 *
 * @Solution
 */
public class ת���ַ��� extends HH {
	
	public boolean canTransfer(String startString, String endString) {
        // Write your code here
		char[] map = new char[26];
		if(startString.length() != endString.length()) {
			return false;
		}
		//���ѭ����֤һ���ַ�����ӳ�䵽�����ַ�����
		for(int i=0; i<startString.length(); i++) {
			int index = startString.charAt(i) - 'a';
			if(map[index] != 0 && map[index] != endString.charAt(i)) {
				return false;
			}
			map[index] = endString.charAt(i);
		}
		//���ѭ����֤�����л����Լ���Ӧ�Լ��Ļ�����
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
	 * lintcode���а��һλ
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
		
		//boolean result = new ת���ַ���().canTransfer("dbadf", "decad");
		boolean result = new ת���ַ���().canTransfer("abc", "cde");
		System.out.println(result);
		
	}

}

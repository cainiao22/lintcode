package website.lintcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author yanpf
 * @date 2019��1��8�� ����9:44:15
 * @description ��������Ҫ��һЩ������������ÿ�������Ӧ����һ����ĸ���ַ��������硰Z����
 * 
 *              ���ڷ��顰C�������ǲ������������·������ǰ�����Ϊ�󷽿顰A�����ҷ��顰B������һ�㣬�����Ԫ����(A, B,
 *              C)��ʾ��ֻ�з���Ҫ�����Ԫ��ſ�������������������
 * 
 *              ���ڣ�������ײ�bottom����һ���ַ�����ʾ���ٸ������з���Ҫ��ĵ���Ԫ��allowed��ÿ����Ԫ����һ������Ϊ3���ַ�����ʾ��
 * 
 *              ������Խ����������������ˣ��򷵻�true�����򷵻�false��
 *              
 * @example 
 * 			ʾ��1:
 * 
 *          ����: bottom = "XYZ", allowed = ["XYD", "YZE", "DEA", "FFF"] ���: true
 *          ����: ���ǿ��������·�ʽ�������
 *          
 *              A
			   / \
			  D   E
			 / \ / \
			X   Y   Z
 * 
 *          ('X', 'Y', 'D'), ('Y', 'Z', 'E'), �� ('D', 'E', 'A') ���Ƿ���Ҫ�����Ԫ��
 * 
 *          ʾ��2:
 * 
 *          ����: bottom = "XXYX", allowed = ["XXX", "XXY", "XYX", "XYY", "YXZ"]
 *          ���: false ����: �������޷����������� ���������Ԫ�� (A, B, C) �� (A, B, D)�� ����C != D.
 *
 * @Solution �е�������㷨
 */
public class ������ת������ extends HH {
	
	 public static boolean pyramidTransition(String bottom, List<String> allowed) {
	        // write your code here
		 if(bottom.length() <= 1) {
			return true;
		 }
		 int[] index = new int[bottom.length() - 1];
		 String newBottom = "";
		 for(int i=0; i >= 0 && i<index.length; ) {
			 int j = index[i];
			 while(j < allowed.size()) {
				 String s = allowed.get(j);
				 String a = bottom.charAt(i) + "" + bottom.charAt(i + 1);
				 if(s.startsWith(a)) {
					 newBottom += s.charAt(s.length() - 1);
					 break;
				 }
				 j ++;
			 }
			 index[i] = j + 1;
			 
			 if(j >= allowed.size()) {
				 if(i <= 0) {
					 return false;
				 }
				 newBottom = newBottom.substring(0, newBottom.length() - 1);
				 index[i] = 0;
				 i --;
			 }else {
				 i ++;
			 }
		 }
		 print("��" + (bottom.length() - newBottom.length()) + "�㣺 " + newBottom);
		 return pyramidTransition(newBottom, allowed);
		 
	 }

	/**
	 * �������������һ��������������ĸ������ÿ��ש����ɫ������һ��allowed���飬���涼�ǳ���Ϊ�����ַ��������硰ABC����ʾC���Է���A��B���Ϸ���
	 * ע��AB����Ҳ���Է������ģ����硰ABD��Ҳ����ͬʱ���֣��������ľ��ʱ��ֻ��ѡ��һ�֡���������һ��bottom�ַ������ǽ������ĵײ㣬�������ܲ��ܴ��һ�������Ľ�������
	 * ��ôʵ�������Ǿ��Ǵӵײ㿪ʼ��һ��һ����������ۼӣ�ֱ�����������������
	 * �����������ݹ�Ľⷨ�������������������֪��������ĸ�Ϸ����Էŵ���ĸ����Ҫ���������ַ������Ϸ��ַ�����֮���ӳ�䣬�����Ϸ��ַ����Բ�Ψһ�������ø�HashSet�����ַ���
	 * ���ǵĵݹ麯����������������ǰ���ַ���cur���ϲ��ַ���above���������ǵ�HashMap�����cur�Ĵ�СΪ2��above�Ĵ�СΪ1����ô˵����ǰ�Ѿ��ﵽ�������Ķ����ˣ�
	 * �Ѿ�������ˣ�ֱ�ӷ���true�����򿴣������һ��ĳ��ȱȵ�ǰ��ĳ�������Сһ����˵����һ��Ҳ����ˣ���������ȥ�����ϲ㣬
	 * ���ǵ��õݹ麯������above������ǰ�㣬���ַ���Ϊ��һ�㣬�����õĵݹ麯�����ֱ�ӷ��ء������ʾ���ǻ���Ҫ����ȥ��above�㣬���������above��ĳ���pos��
	 * Ȼ��ӵ�ǰ���posλ�ÿ�ʼȡ�����ַ�������above���������Ҫ����ַ��Ļ����ַ����ٸ��������£�
	 * 
	 *  D 
	 * / \ / \ 
	 * A B C
	 * ���ǿ�������above��ֻ��һ��D����ôposΪ1����cur��1λ�ÿ�ʼȡ�����ַ����õ�"BC"������D����һ��λ�õ��ַ��Ļ����ַ���base��
	 * ȡ����base�����HashMap����ӳ�䣬�����Ǳ�����ӳ����ַ������е������ַ�����ÿ���ַ������õݹ麯������ʱabove�ַ�����Ҫ����������������ַ���
	 * ��Ϊ�����ڳ���������λ�ã�����з���true�ģ���ô��ǰ�ݹ麯���ͷ���true�ˣ��������շ���false
	 * 
	 * @param bottom
	 * @param allowed
	 * @return
	 */
	 public static boolean pyramidTransition2(String bottom, List<String> allowed) {
		 Map<String, Set<Character>> map = new HashMap<>();
		 allowed.forEach((a) -> {
			 Set<Character> set = map.get(a.substring(0, 2));
			 if(set == null) {
				 set = new HashSet<>();
				 map.put(a.substring(0, 2), set);
			 }
			 set.add(a.charAt(a.length() - 1));
		 });
		 
		 return helper(bottom, "", map);
		 
	 }
	 
	 private static boolean helper(String bottom, String above, Map<String, Set<Character>> map) {
		 if(bottom.length() == 2 && above.length() == 1) {
			 return true;
		 }
		 if(above.length() == bottom.length() - 1) {
			 return helper(above, "", map);
		 }
		 int pos = above.length();
		 String a = bottom.substring(pos, pos + 2);
		 if(map.containsKey(a)) {
			 Set<Character> set = map.get(a);
			 for(Character character : set) {
				 if(helper(bottom, above + character, map)) {
					 return true;
				 }
			 }
		 }
		 
		 return false;
		 
	 }
	 
	 /**
	  * ʹ��dp��ʽ���
	  * @param bottom
	  * @param allowed
	  * @return
	  */
	 public static boolean pyramidTransitionDP(String bottom, List<String> allowed) {
		 //dp[i][j][k] ��i��ĵ�j��λ���Ƿ���Է�k k������ĸA~G���±�
		 final int CONSTANT = 7;
		 boolean dp[][][] = new boolean[bottom.length()][bottom.length()][CONSTANT];
		 int length = bottom.length();
		 
		 Map<String, Set<Character>> map = new HashMap<>();
		 allowed.forEach((a) -> {
			 Set<Character> set = map.get(a.substring(0, 2));
			 if(set == null) {
				 set = new HashSet<>();
				 map.put(a.substring(0, 2), set);
			 }
			 set.add(a.charAt(a.length() - 1));
		 });
		 
		 for(int i=0; i<bottom.length(); i++) {
			 dp[length - 1][i][bottom.charAt(i) - 'A'] = true;
		 }
		 
		 for(int i=length - 1; i>0; i--) {
			 //j�����ֵ���ᳬ��i
			 for(int j=0; j <=i; j++) {
				 for(int k=0; k<CONSTANT; k++) {
					 if(dp[i][j][k]) {
						 for(int l=0; l < CONSTANT; l++) {
							 if(dp[i][j+1][l]) {
								 String key = (char)('A' + k) + "" + (char)('A' + l);
								 if(map.containsKey(key)) {
									 int index = i - 1;
									 int indexJ = j;
									 map.get(key).forEach((v) -> {
										 dp[index][indexJ][v - 'A'] = true;
									 });
								 }
							 }
						 }
					 }
				 }
			 }
		 }
		 
		 for(int i=0; i<CONSTANT; i++) {
			 if(dp[0][0][i]) {
				 return true;
			 }
		 }
		 
		 return false;
	 }
	 
	 public static boolean pyramidTransitionDP2(String bottom, List<String> allowed) {
		 //dp[i][j][k] ��i��ĵ�j��λ���Ƿ���Է�k k������ĸA~G���±�
		 final int CONSTANT = 7;
		 boolean dp[][][] = new boolean[bottom.length()][bottom.length()][CONSTANT];
		 int length = bottom.length();
		 
		 Map<Character, Set<String>> map = new HashMap<>();
		 allowed.forEach((a) -> {
			 Set<String> set = map.get(a.charAt(2));
			 if(set == null) {
				 set = new HashSet<>();
				 map.put(a.charAt(2), set);
			 }
			 set.add(a.substring(0, 2));
		 });
		 
		 for(int i=0; i<bottom.length(); i++) {
			 dp[length - 1][i][bottom.charAt(i) - 'A'] = true;
		 }
		 
		 for(int i=length - 2; i>0; i--) {
			 //j�����ֵ���ᳬ��i
			 for(int j=0; j <=i; j++) {
				for(char k = 'A'; k <= 'G'; k ++) {
					if(!map.containsKey(k)) continue;
					for (String str : map.get(k)) {
                        if (dp[i + 1][j][str.charAt(0) - 'A'] && dp[i + 1][j + 1][str.charAt(1) - 'A']) {
                            dp[i][j][k - 'A'] = true;
                        }
                    }
				}
			 }
		 }
		 
		 for(int i=0; i<CONSTANT; i++) {
			 if(dp[0][0][i]) {
				 return true;
			 }
		 }
		 
		 return false;
	 }
	 
	 public static void main(String[] args) {
		 String bottom = "ABC";
		 List<String> allowed = new ArrayList<String>() {
			 {
				 //["ABD","BCE","DEF","FFF"]
				 add("ABD");
				 add("BCE");
				 add("DEF");
				 add("FFF");
				 //add("YXZ");
			 }
		 };
		 boolean result = pyramidTransitionDP(bottom, allowed);
		 System.out.println(result);
	}

}

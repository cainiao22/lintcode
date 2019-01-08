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
 * @date 2019年1月8日 上午9:44:15
 * @description 假设我们要用一些方块搭建金字塔，每个方块对应仅含一个字母的字符串，例如“Z”。
 * 
 *              对于方块“C”，我们并不把它放在下方，而是把它作为左方块“A”和右方块“B”的上一层，这个三元组用(A, B,
 *              C)表示，只有符合要求的三元组才可以用来堆砌金字塔。
 * 
 *              现在，给定最底层bottom，用一个字符串表示，再给出所有符合要求的的三元组allowed，每个三元组用一个长度为3的字符串表示。
 * 
 *              如果可以将金字塔构建至顶端，则返回true，否则返回false。
 *              
 * @example 
 * 			示例1:
 * 
 *          输入: bottom = "XYZ", allowed = ["XYD", "YZE", "DEA", "FFF"] 输出: true
 *          解释: 我们可以用如下方式搭建金字塔
 *          
 *              A
			   / \
			  D   E
			 / \ / \
			X   Y   Z
 * 
 *          ('X', 'Y', 'D'), ('Y', 'Z', 'E'), 和 ('D', 'E', 'A') 都是符合要求的三元组
 * 
 *          示例2:
 * 
 *          输入: bottom = "XXYX", allowed = ["XXX", "XXY", "XYX", "XYY", "YXZ"]
 *          输出: false 解释: 金字塔无法构建到顶端 允许存在三元组 (A, B, C) 和 (A, B, D)， 其中C != D.
 *
 * @Solution 有点像回溯算法
 */
public class 金字塔转换矩阵 extends HH {
	
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
		 print("第" + (bottom.length() - newBottom.length()) + "层： " + newBottom);
		 return pyramidTransition(newBottom, allowed);
		 
	 }

	/**
	 * 这道题让我们累一个金字塔，用字母来代表每块砖的颜色，给了一个allowed数组，里面都是长度为三的字符串，比如“ABC”表示C可以放在A和B的上方，
	 * 注意AB上面也可以放其他的，比如“ABD”也可以同时出现，不过搭积木的时候只能选择一种。给了我们一个bottom字符串，是金字塔的底层，问我们能不能搭出一个完整的金字塔。
	 * 那么实际上我们就是从底层开始，一层一层的向上来累加，直到搭出整个金字塔。
	 * 我们先来看递归的解法，首先由于我们想快速知道两个字母上方可以放的字母，需要建立基座字符串和上方字符集合之间的映射，由于上方字符可以不唯一，所以用个HashSet来放字符。
	 * 我们的递归函数有三个参数，当前层字符串cur，上层字符串above，还有我们的HashMap。如果cur的大小为2，above的大小为1，那么说明当前已经达到金字塔的顶端了，
	 * 已经搭出来了，直接返回true。否则看，如果上一层的长度比当前层的长度正好小一个，说明上一层也搭好了，我们现在去搭上上层，
	 * 于是调用递归函数，将above当作当前层，空字符串为上一层，将调用的递归函数结果直接返回。否则表示我们还需要继续去搭above层，我们先算出above层的长度pos，
	 * 然后从当前层的pos位置开始取两个字符，就是above层接下来需要搭的字符的基座字符，举个例子如下：
	 * 
	 *  D 
	 * / \ / \ 
	 * A B C
	 * 我们看到现在above层只有一个D，那么pos为1，在cur层1位置开始取两个字符，得到"BC"，即是D的下一个位置的字符的基座字符串base。
	 * 取出了base后，如果HashMap中有映射，则我们遍历其映射的字符集合中的所有字符，对每个字符都调用递归函数，此时above字符串需要加上这个遍历到的字符，
	 * 因为我们在尝试填充这个位置，如果有返回true的，那么当前递归函数就返回true了，否则最终返回false
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
	  * 使用dp方式解决
	  * @param bottom
	  * @param allowed
	  * @return
	  */
	 public static boolean pyramidTransitionDP(String bottom, List<String> allowed) {
		 //dp[i][j][k] 第i层的第j个位置是否可以放k k代表字母A~G的下标
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
			 //j的最大值不会超过i
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
		 //dp[i][j][k] 第i层的第j个位置是否可以放k k代表字母A~G的下标
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
			 //j的最大值不会超过i
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

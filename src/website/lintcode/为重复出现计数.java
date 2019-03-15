package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2019年3月1日 下午3:48:53
 * @description		
 * 			定义S = [s,n]表示字符串 S 由字符串 s 重复 n 次构成。例如["abc", 3] = "abcabcabc"。

			同时我们定义：如果字符串 s2 去除若干个（当然也可以是 0 个）字符后变成了字符串 s1，那么我们说字符串 s1 能从字符串 s2 中得到。例如，根据我们的定义，"abc"能从"abdbec"中得到，但不能从"acbbe"中得到。
			
			给定两个非空字符串 s1 和 s2（长度均不超过100）和两个整数n1,n2（0 \leq n1 \leq 10^6, 1 \leq n2 \leq 10^60≤n1≤10
			​6
			​​ ,1≤n2≤10
			​6
			​​ ）。现在考虑两个新的字符串 S1 和 S2，其中S1=[s1,n1]，S2=[s2,n2]。找出最大的整数 M，使得[S2,M]能从S1中得到。
 * @example
 * 			输入：
			s1="acb", n1=4
			s2="ab", n2=2
			
			输出：
			2
 *
 * @Solution
 */
public class 为重复出现计数 {
	
	 public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
	       StringBuffer sb = new StringBuffer();
	       String S1, S2;
	       for(int i=0; i<n1; i++) {
	    	   sb.append(s1);
	       }
	       S1 = sb.toString();
	       sb.setLength(0);
	       for(int i=0; i<n2; i++) {
	    	   sb.append(s2);
	       }
	       S2 = sb.toString();
	      
	       int n = 0;
	       int i=0, j=0;
	       while(i < S1.length()) {
	    	   if(sb.charAt(j) == S1.charAt(i)) {
	    		   j ++;
	    	   }
	    	   i ++;
	    	   if(j == sb.length()) {
	    		   sb.append(S2);
	    		   n ++;
	    	   }
	       }
	       
	       return n;
	 }
	 
	 public int getMaxRepetitionsBetter(String s1, int n1, String s2, int n2) {
	       int i=0, j=0;
	       while(i < s1.length() * n1) {
	    	   if(s1.charAt(i % s1.length()) == s2.charAt(j % s2.length())) {
	    		   j ++;
	    	   }
	    	   i ++;
	       }
	        
	       return j / (s2.length() * n2);
	 }
	 
	/**
	 * 如果s2在S1中出现了N次，那么S2肯定在S1中出现了N/n2次，注意这里的大写表示字符串加上重复次数组成的大字符串。
	 * 
	 * 所以我们得出结论，我们只要算出s2出现的次数，然后除以n2，就可以得出S2出现的次数了。
	 * 
	 * 那么问题就是我们表示重复，我们遍历s1字符串n1次，表示每个s1字符串为一段，对于每段，我们有：
	 * 
	 * 1. 出现在该段的s2字符串的累计出现次数
	 * 
	 * 2. 一个nextIndex，其中s2[nextIndex]表示在下一段s1中你所要寻找的s2中的一个字符。(比如说s1="abc", s2="bac",
	 * 由于第一个s1中只能匹配上s2中的b，那么只有在下一段s1中才能继续匹配s2中的a，所以nextIndex=1，即a在s2中的位置为1；同理，比如s1="abca",
	 * s2="bac"，第一个s1可以匹配上s2中的ba，那么后面的c只能在下一段s1中匹配上，那么nextIndex=2，即c在s2中的位置为2)
	 * 
	 * nextIndex的范围从0到s2.size()-1，根据鸽巢原理(又称抽屉原理)，你一定会找到相同的两个nextIndex在遍历s1段s2.size()+1次之后。在上面的例子中，重复的nextIndex出现在第三段，和第一段一样都为2，那么重复的pattern就找到了，是第二段和第三段中的aabc，而且从第四段开始，每两段就有一个aabc，现在我们的目标就是统计出整个S1中有多少个s2。
	 * 
	 * 由于pattern占用了两段，所以interval为2，我们然后看整个S1中有多少个这样的两段，repeat = (n1 - start) /
	 * interval。start表示pattern的起始段数，之前的不是pattern，然后我们算在整个S1中有多少个pattern出现，patternCnt
	 * = (repeatCnt[k] - repeatCnt[start]) * repeat，注意这里的repeatCnt[k] -
	 * repeatCnt[start]表示一个pattern中有多少个字符串s2，个人感觉一般来说都是1个。然后我们算出剩下的非pattern的字符串里能包含几个s2，remainCnt
	 * = repeatCnt[start + (n1 - start) % interval]，然后我们把patternCnt +
	 * remainCnt之和算出来除以n2就是需要的结果啦。如果pattern未曾出现，那么我们直接用repeatCnt[n1] / n2也能得到正确的结果
	 * 
	 * @param s1
	 * @param n1
	 * @param s2
	 * @param n2
	 * @return
	 */
	 public int getMaxRepetitionsBest(String s1, int n1, String s2, int n2) {
		 int[] repeatCnt = new int[n1 + 1];
		 int[] nextIdx = new int[n1 + 1];
		 int j = 0;
		 int cnt = 0;
		 for(int i=1; i<=n1; i++) {
			 for(int k =0; k<s1.length(); k++) {
				 if(s1.charAt(k) == s2.charAt(j)) {
					 j ++;
				 }
				 if(j == s2.length()) {
					cnt ++;
					j = 0;
				 }
			 }
			 
			 repeatCnt[i] = cnt;
			 nextIdx[i] = j;
			 for(int start = 0; start < i; start ++) {
				 if(nextIdx[start] == nextIdx[i]) {
					 int interval = i - start;
					 int repeat = (n1 - start) / interval * (repeatCnt[i] - repeatCnt[start]);
					 repeat += repeatCnt[start + (n1 - start) % interval];
					 return repeat / n2;
				 }
			 }
			 
		 }
		 
		 return repeatCnt[n1] / n2;
	 }
	 
	 
	 
	 
	 public static void main(String[] args) {
		 String s1 = "acb", s2 = "ab";
		 int n1 = 4, n2 = 2;
		int result = new 为重复出现计数().getMaxRepetitionsBest(s1, n1, s2, n2);
		System.out.println(result);
	}

}

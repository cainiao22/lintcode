package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年10月24日 下午3:33:13
 * @description
 * 		
		给定一个只包含字母的字符串，按照先小写字母后大写字母的顺序进行排序。
		注意事项
		
		小写字母或者大写字母他们之间不一定要保持在原始字符串中的相对位置。
		在原地扫描一遍完成
 * @example
 * 		给出"abAcD"，一个可能的答案为"acbAD"
 *
 * @Solution
 * 		
 */
public class 字符大小写排序 {
	
	 public void sortLetters(char[] chars) {
	        // write your code here
		 for(int i=0, j=chars.length-1; i<j; ) {
			 while(i < j && chars[i] >= 'a' && chars[i] <= 'z') {
				 i ++;
			 }
			 
			 while(i < j && chars[j] >= 'A' && chars[j] <= 'Z') {
				 j --;
			 }
			 
			 if(i < j) {
				 char temp = chars[i];
				 chars[i] = chars[j];
				 chars[j] = temp;
				 i ++;
				 j --;
			 }
		 }
	    }
	 
	 public static void main(String[] args) {
		char[] chars = "abAcD".toCharArray();
		new 字符大小写排序().sortLetters(chars);
	}

}

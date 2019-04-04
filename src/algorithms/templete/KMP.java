package algorithms.templete;

import website.lintcode.HH;

/**
 * 字符串匹配 KMP算法
 * @author yanpf
 * @date 2019年4月2日 下午3:42:50
 * @description
 * @example
 *
 * @Solution
 */
public class KMP extends HH {
	
	
	public int match(String A, String T) {
		int[] next = next(T);
		int i = 0, j = 0;
		while(i < A.length() && j < T.length()) {
			if(j == -1 || A.charAt(i) == T.charAt(j)) {
				i ++;
				j ++;
			}else {
				j = next[j];
			}
		}
		
		if(j == T.length()) {
			return i - j + 1;
		}
		
		return -1;
	}
	
	/**
	 * 用前面的关系推出后面
	 * @param T
	 * @return
	 */
	public int[] next(String T) {
		int[] next = new int[T.length()];
		int i = 1;
		int j = 0;
		next[0] = -1;
		while(i < T.length() - 1) {
			if(j == -1 || T.charAt(i) == T.charAt(j)) {
				next[i+1] = j+1;
				i ++;
				j ++;
			}else {
				j = next[j];
			}
		}
		
		return next;
	}
	
	public int[] next2(String T) {
		int[] next = new int[T.length()];
		int i = 1;
		int j = 0;
		next[0] = -1;
		while(i < T.length() - 1) {
			if(j == -1 || T.charAt(i) == T.charAt(j)) {
				i ++;
				j ++;
				if(T.charAt(i) != T.charAt(j)) {
					next[i] = j;
				}else {//a[i] != a[next[i]]的时候才会设置next[i],下面这个是成立的（a[next[j]] != a[j]);
					next[i] = next[j];
				}
			}else {
				j = next[j];
			}
		}
		
		return next;
	}
	
	public static void main(String[] args) {
		print(new KMP().match("aabbaabaabaabc", "aabc"));
	}
}

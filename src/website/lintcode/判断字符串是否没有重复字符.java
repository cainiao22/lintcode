package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年8月14日 下午6:43:50
 * @description  实现一个算法确定字符串中的字符是否均唯一出现  如果不使用额外的存储空间，你的算法该如何改变？
 * 
 * @example 给出"abc"，返回 true  给出"aab"，返回 false
 *
 * @Solution  bitmap方式，拿一个int数组去做bitmap，或者128位的boolean数组，
 * 			    另一个完全不用空间的双重循环相互异或
 */
public class 判断字符串是否没有重复字符 {
	
	public boolean isUnique(String str) {
        // write your code here
		int[] a = new int[4];
		for(int i = 0; i<str.length(); i++) {
			int index = str.charAt(i);
			int j = index / 32;
			index %= 32;
			if((a[j] & (1 << index)) != 0) {
				return false;
			}
			a[j] |= (1 << index);
		}
		
		return true;
    }
	
	 public boolean isUnique2(String str) {
	        // write your code here
	        boolean[] char_set = new boolean[256];
	        for (int i = 0; i < str.length(); i++) {
	        int val = str.charAt(i);
	        if (char_set[val]) return false;
	            char_set[val] = true;
	        }
	        return true;
	    }
	
	public static void main(String[] args) {
		boolean res = new 判断字符串是否没有重复字符().isUnique("helloworld");
		System.out.println(Integer.toBinaryString(1 << 31));
		System.out.println(1 << 31);
		System.out.println(res);
	}

}

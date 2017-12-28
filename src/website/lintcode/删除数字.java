package website.lintcode;

import java.awt.Image;

/**
 * 
 * @author yanpf
 * @date 2017年12月19日 下午2:47:10
 * @description
 * 		
		给出一个字符串 A, 表示一个 n 位正整数, 删除其中 k 位数字, 使得剩余的数字仍然按照原来的顺序排列产生一个新的正整数。
		
		找到删除 k 个数字之后的最小正整数。
		
		N <= 240, k <= N

 * @example
 * 		给出一个字符串代表的正整数 A 和一个整数 k, 其中 A = 178542, k = 4

		返回一个字符串 "12"
 *
 * @Solution 如果这个元素前面的某些元素大于它。那么删除它前面的这个大于它的元素，肯定可以是值最小。
 * 			 本质上是需要保证序列的顺序从高位到低位的数字是从低到高的
 */
public class 删除数字 extends HH {
	
	public String DeleteDigits(String A, int l) {
        // write your code here
		char[] chars = A.toCharArray();
		int[] stack = new int[chars.length];
		stack[0] = 0;
		int top = 0, i = 1;
		while(l > 0 && i<chars.length) {
			while(top >= 0 && chars[i] < chars[stack[top]] && l > 0) {
				l --;
				chars[stack[top]] = ' ';
				top --;
			}
			top ++;
			stack[top] = i;
			i ++;
		}
		while(l > 0) {
			while(chars[i - 1] == ' ') {
				i --;
			}
			chars[i - 1] = ' ';
			l --;
		}
		
		StringBuffer sb = new StringBuffer();
		boolean flag = true;
		for(int j=0; j<chars.length; j++) {
			if(chars[j] != ' ') {
				if(chars[j] == '0' && flag) {
					continue;
				}else {
					flag = false;
				}
				sb.append(chars[j]);
			}
		}
		return sb.toString();
    }
	
	/**
	 * 上面那个方法的优化版本
	 * @param A
	 * @param k
	 * @return
	 */
	public String DeleteDigitsFromJiuZhang(String A, int k) {
        // write your code here
        StringBuffer sb = new StringBuffer(A);
        int i, j;
        for (i = 0; i < k; i++) {//保证第j个比第j+1个小
            for (j = 0; j < sb.length() - 1 && sb.charAt(j) <= sb.charAt(j + 1); j++) {}
            	//如果后一个比我大，那么大的那个就可以被删除了
                sb.delete(j, j + 1);
        }
        //去除首部的0
        while (sb.length() > 1 && sb.charAt(0) == '0') {
            sb.delete(0, 1);
        }
        return sb.toString();
    }
	
	public static void main(String[] args) {
		print(new 删除数字().DeleteDigits("90249", 2));
	}

}

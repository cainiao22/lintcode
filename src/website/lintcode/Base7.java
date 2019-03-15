package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2019年1月9日 下午5:19:40
 * @description Given an integer, return its base 7 string representation. 返回数字的7进制标识
 * 
 * @example Given num = 100, return "202".
			Given num = -7, return "-10".
 *
 * @Solution
 */
public class Base7 {

	public String convertToBase7(int num) {
        // Write your code here
		String result = "";
		String flag = "";
		if(num < 0) {
			flag = "-";
			num = - num;
		}
		
		while(num != 0) {
			result = (num % 7) + result;
			num = num / 7;
		}
		
		return flag + result;
    }
}

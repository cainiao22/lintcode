package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2019年1月24日 下午6:33:41
 * @description 如果要将整数A转换为B，需要改变多少个bit位？
 * @example		如把31转换为14，需要改变2个bit位。
				(31)10=(11111)2
				(14)10=(01110)2
 *
 * @Solution
 */
public class 将整数A转换为B {
	
	public int bitSwapRequired(int a, int b) {
        // write your code here
		int r = a ^ b;
		int ans = 0;
		while(r != 0) {
			r = r & (r - 1);
			ans ++;
		}
		
		return ans;
    }
	
	public static void main(String[] args) {
		System.out.println(new 将整数A转换为B().bitSwapRequired(31, 14));
	}

}

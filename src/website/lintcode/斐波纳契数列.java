package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年11月14日 下午12:23:00
 * @description
 * 		查找斐波纳契数列中第 N 个数。

		所谓的斐波纳契数列是指：
		
		    前2个数是 0 和 1 。
		    第 i 个数是第 i-1 个数和第i-2 个数的和。
		
		斐波纳契数列的前10个数字是：
		
		0, 1, 1, 2, 3, 5, 8, 13, 21, 34 ...
 * @example
 * 		给定 1，返回 0

		给定 2，返回 1
		
		给定 10，返回 34
 *
 * @Solution
 */
public class 斐波纳契数列 extends HH {
	
	public static int fibonacci(int n) {
        // write your code here
		if(n == 1 || n == 2) {
			return n - 1;
		}
		int last = 0;
		int current = 1;
		for(int i=3; i<=n; i++) {
			int temp = current + last;
			last = current;
			current = temp;
		}
		return current;
    }
	
	public int fibonacciFrmJiuZhang(int n) {
        int a = 0;
        int b = 1;
        //代表加几次
        for (int i = 0; i < n - 1; i++) {
            int c = a + b;
            a = b;
            b = c;
        }
        return a;
    }
	
	public static void main(String[] args) {
		print(fibonacci(10));
	}

}

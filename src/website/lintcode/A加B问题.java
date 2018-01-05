package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2018年1月2日 下午6:01:13
 * @description
 * 		不用加减法实现 a+b
 * @example
 *
 * @Solution
 */
public class A加B问题 {
	
	public int aplusb(int a, int b) {
        // write your code here
		int[] bits = new int[33];
		for(int i=0; i<bits.length - 1; i++) {
			bits[i] += (a >> i) & 1;
			bits[i] += (b >> i) & 1;
			int temp = bits[i] % 2;
			bits[i+1] = bits[i] / 2;
			bits[i] = temp;
		}
		
		int ans = 0;
		for(int i=0; i<32; i++) {
			ans |= (bits[i] << i);
		}
		
		return ans;
    }
	
	/**
	 * 这个nb大了
	 * @param a
	 * @param b
	 * @return
	 */
	public int aplusb2(int a, int b) {
        // 主要利用异或运算来完成 
        // 异或运算有一个别名叫做：不进位加法
        // 那么a ^ b就是a和b相加之后，该进位的地方不进位的结果
        // 然后下面考虑哪些地方要进位，自然是a和b里都是1的地方
        // a & b就是a和b里都是1的那些位置，a & b << 1 就是进位
        // 之后的结果。所以：a + b = (a ^ b) + (a & b << 1)
        // 令a' = a ^ b, b' = (a & b) << 1
        // 可以知道，这个过程是在模拟加法的运算过程，进位不可能
        // 一直持续，所以b最终会变为0。因此重复做上述操作就可以
        // 求得a + b的值。
        while (b != 0) {
            int _a = a ^ b;
            int _b = (a & b) << 1;
            a = _a;
            b = _b;
        }
        return a;
    }

}

package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年12月8日 下午6:29:24
 * @description
 * 		位旋转 -—— 旋转（或循环移位）是类似于移位的操作, 不同的是一端脱落的那一位会被放回到另一端
		在左旋转中, 左端掉下来的那一位会放在右端
		假设 n 用 8 位二进制来存. 对 n = 11100101 左旋转 3 位, 得到 n = 00101111 (左移3位, 原先的前3位放在末尾).
		如果 n 用 16 位或 32 位二进制来存, 那么对 n (000…11100101)左旋转了之后会变成 (00..0011100101000).
		在本问题中, 你可以假设 n 是用 32 位二进制来存的.
 * @example
 * 		给出 n = 123, d = 4 返回 183
 *
 * @Solution
 */
public class 左旋转二进制位 extends HH {
	
	public int leftRotate(int n, int d) {
        // write code here
		return (n >>> (32-d)) + (n << d);
		
    }
	
	public static void main(String[] args) {
		System.out.println(Integer.toBinaryString(123));
		System.out.println(Integer.toBinaryString(112));
		System.out.println(Integer.toBinaryString(123 << 4));
		System.out.println(123 << 4);
		print(new 左旋转二进制位().leftRotate(123, 4));
	}

}

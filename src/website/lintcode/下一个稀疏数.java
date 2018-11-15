package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2018年1月5日 下午3:23:52
 * @description
 * 		一个数是稀疏数如果这个数的 二进制表示 中没有相邻的 1，给出一个 n ，找出大于或等于 n 的最小的稀疏数。
		eg. 5 (二进制表示为 101)是稀疏数，但是 6 (二进制表示为 110 ）不是稀疏数
 * @example
 * 		给出 n = 6,返回 8下一个稀疏数是8
		给出 n = 4,返回 4下一个稀疏数是4
		给出 n = 38,返回 40下一个稀疏数是40
		给出 n = 44,返回 64下一个稀疏数是64
 *
 * @Solution
 */
public class 下一个稀疏数 extends HH {
	
	/**
	 * 如果有相邻的1，它与它的右移按位与必然会不为0，同理与它的左移向与也是
	 * @param x
	 * @return
	 */
	public static int nextSparseNum(int x) {
        // write your code here
		for(int i=x; i<Integer.MAX_VALUE; i++) {
			if((i & (i >> 1)) == 0 && (i & (i << 1)) == 0) {
				return i;
			}
		}
		return -1;
    }
	
	/**
	 * 神一般的逻辑
	 * @param x
	 * @return
	 */
	public static int nextSparseNum2(int x) {
        // write your code here
		while(true) {
			if((x & (x >> 1)) == 0 && (x & (x << 1)) == 0) {
				break;
			}
			//因为要拿大于x的最小值，所以x加上冲突后的那一位之后，后面的1需要全部清除。例如 二进制  11001 ->  100001，后面那个1是需要清掉的，变成100000
			int x2 = x & (x >> 1);
			x2 = (x2^(x2-1)) + 1;
			x2 = x2 >> 1;
			x += x2;
			int x3 = ~(x2^(x2-1));
			x = x & x3;
			
		}
		
		return x;
		
    }
	
	public static void main(String[] args) {
		int[] nums = {/*6,4,38,44, */341381939};
		for(int num : nums) {
			print(nextSparseNum2(num));//343932928
		}
		print(Integer.toBinaryString(341381939));
		print(Integer.toBinaryString(343932928));
		print(Integer.toBinaryString(344003652));
		
		print("-------------------");
		
		print(Integer.toBinaryString(524561));
		
		
	}
}

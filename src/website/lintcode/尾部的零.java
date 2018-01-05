package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2018年1月3日 上午10:51:47
 * @description
 * 		设计一个算法，计算出n阶乘中尾部零的个数
 * @example
 *
 * @Solution
 */
public class 尾部的零 {
	
	public long trailingZeros(long n) {
        // write your code here, try to do it without arithmetic operators.
		/**
		 * 首选判断有多少个5，就有多少个0，考虑到5*5、5*5*5这种情况
		 * 每增一个5，次数加1,5*5就是判断n中有多少25，5*5*5就是判断n中有多少125.以此类推
		 */
		long count = 0;
		for(long i = 5; i<= n; i*= 5) {
			count += n / i;
		}

		/**
		 * 也可以这样做，但是效率会低好多
		 
		for(long i = 5; i<= n; i+= 5) {
			long temp = i;
			while(temp % 5 == 0) {
				count ++;
				temp = temp / 5;
			}
		}
		*/
		return count;
    }
	
	/**
	 * 思想是一样的
	 * @param n
	 * @return
	 */
	public long trailingZeros2(long n) {
        long sum = 0;
        while (n != 0) {
            sum += n / 5;
            n /= 5;
        }
        return sum;
    }
	
	public static void main(String[] args) {
		System.out.println(new 尾部的零().trailingZeros(11));
	}

}

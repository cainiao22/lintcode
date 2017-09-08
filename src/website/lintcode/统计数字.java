package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年9月7日 上午11:34:24
 * @description 计算数字k在0到n中的出现的次数，k可能是0~9的一个值
 * @example 例如n=12，k=1，在 [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]，我们发现1出现了5次 (1, 10, 11, 12)
 *
 * @Solution
 */
public class 统计数字 {
	
	//TODO 有没有更高效的算法？
	public int digitCounts(int k, int n) {
        // write your code here
		int sum = 0;
		int d = 1;
		while(n > 0) {
			int r = n % 10;
			sum += n / 10 * d;
			if(r >= k) {
				sum += d;
			}
			n = n /10;
		}
		
		return sum;
    }
	
	/**
	 * 比较笨的方法，k-n每个数都分析一遍
	 * @param k
	 * @param n
	 * @return
	 */
	public int digitCountsBad(int k, int n) {
        // write your code here
		int sum = 0;
		for(int i=k; i<=n; i++) {
			int j = i;
			if(j == k) {
				sum += 1;
				continue;
			}
			while(j>0) {
				int d = j % 10;
				if(d == k) {
					sum += 1;
				}
				j = j/10;
			}
		}
		
		return sum;
    }
	
	public static void main(String[] args) {
		int sum = new 统计数字().digitCountsBad(0, 12);
		System.out.println(sum);
	}

}

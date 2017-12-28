package website.lintcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author yanpf
 * @date 2017年12月18日 上午10:23:04
 * @description
 * 		给两个数字 n 和 k. 我们需要判断 n 是否可以写成 k 个素数的和.
 * @example
 *		给出 n = 10, k = 2
		返回 true // 10 = 5 + 5
		
		给出 n = 2, k = 2
		返回 false
 * @Solution
 */
public class 判断k个素数之和 extends HH {
	
	private Map<String, Boolean> map = new HashMap<>();
	
	//TODO 个巴赫猜想 任何偶数都可以变成两个质数之和
	public boolean isSumOfKPrimes(int n, int k) {
        // write your code here
		if(k == 0 && n == 0) {
			return true;
		}
		if(map.get(n + "_" + k) != null) {
			return map.get(n + "_" + k);
		}
		for(int i = getNext(n); i>1; i = getNext(i - 1)) {
			if(isSumOfKPrimes(n - i, k - 1)) {
				map.put(n + "_" + k, true);
				return true;
			}
		}
		map.put(n + "_" + k, false);
		return false;
    }
	
	private int getNext(int n) {
		for(int i=n; i>1; i--) {
			int j = 2;
			for(; j<=Math.sqrt(n); j++) {
				if(i % j == 0) {
					break;
				}
			}
			if(j > n/2) {
				return i;
			}
		}
		return 1;
	}
	
	public static void main(String[] args) {
		print(new 判断k个素数之和().isSumOfKPrimes(2, 2));
	}
}


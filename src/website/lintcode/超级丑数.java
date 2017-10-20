package website.lintcode;

import java.util.Arrays;

/**
 * 
 * @author yanpf
 * @date 2017年10月13日 下午5:15:01
 * @description
 * 		写一个程序来找第 n 个超级丑数。

		超级丑数的定义是正整数并且所有的质数因子都在所给定的一个大小为 k 的质数集合内。

		比如给你 4 个质数的集合 [2, 7, 13, 19], 那么 [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32] 是前 12 个超级丑数。
 * @example
 * 
 * 		给出 n = 6 和质数集合 [2, 7, 13, 19]。第 6 个超级丑数为 13，所以返回 13 作为结果。
 *
 * @Solution 三重for循环
 */
public class 超级丑数 extends HH {
	
	public long nthSuperUglyNumber(int n, int[] primes) {
        // write your code here
		long max = 1;
		Arrays.sort(primes);
		long[] ugly = new long[n];
		ugly[0] = 1;
		for(int i=1;i<n; i++) {
			if(i == 368) {
				System.out.println();
			}
			long Min = Long.MAX_VALUE;
			for(int j=0; j<i; j++) {
				for(int k=0; k<primes.length; k++) {
					if(ugly[j] * primes[k] > max) {
						Min = Math.min(Min, ugly[j] * primes[k]);
						if(Min == 135754917) {
							System.out.println(ugly[j] + " " + primes[k]);
						}
						break;
					}
				}
			}
			ugly[i] = Min; 
			max = Min;
			//System.out.println(Min);
		}
		//print(ugly);
		return ugly[n-1];
    }
	
	public int nthSuperUglyNumber2(int n, int[] primes) {
		//primes中第j个数对应的最大数的位置
        int[] times = new int[primes.length];
        int[] uglys = new int[n];
        uglys[0] = 1;

        for (int i = 1; i < n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < primes.length; j++) {
                min = Math.min(min, primes[j] * uglys[times[j]]);
            }
            uglys[i] = min;
            System.out.println(min);
            for (int j = 0; j < times.length; j++) {
                if (uglys[times[j]] * primes[j] == min) {
                    times[j]++;
                }
            }
        }
        System.out.println("------------------");
        return uglys[n - 1];
    }
	
	public static void main(String[] args) {
		int[] primes = new int[] {31,151,97,67,353,271,101,37};
		System.out.println(new 超级丑数().nthSuperUglyNumber(394, primes));
		int a = 135754917;
		System.out.println(a);
		for(int i=0; i<primes.length; i++) {
			while(a % primes[i] == 0) {
				System.out.println(primes[i]);
				a = a/primes[i];
			}
			if(a == 1) {
				break;
			}
		}
		                   //135754917
		long b = 119749249 * 37;
		System.out.println(b);
		//System.out.println(183694369);
	}

}

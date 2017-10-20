package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年10月16日 下午2:35:00
 * @description  
 * 		计算an % b，其中a，b和n都是32位的整数。
 * @example
 * 		例如 2^31 % 3 = 2
		例如 100^1000 % 1000 = 0
 *
 * @Solution
 */
public class 快速幂 {
	
	/**
	 * 本质上没有错。只是数字太大了。容易溢出。而这种溢出无法控制，即便是long long 也够呛
	 * 在九章算法的答案里，是计算一半的模temp。那么总的模就是temp*temp%b
	 * 这样得出的最大数字是原来的一半。降低这种溢出概率
	 * @param a
	 * @param b
	 * @param n
	 * @return
	 */
	public long fastPower(Long a, Long b, Long n) {
        // write your code here
		if(n == 0) {
			return 1%b;
		}
		if(n == 1) {
			return a%b;
		}
		long ax = a;
		long bx = b;
		ax = (ax%bx)*(ax%bx)%b;
		if((int)ax <= 0) {
			System.out.println(a);
		}
		if(n % 2 == 0) {
			return fastPower(ax, b, n/2);
		}else {
			return a%b * fastPower(ax, b, (n-1)/2)%b;
		}
    }
	
	public int fastPowerIterator(int a, int b, int n) {
        // write your code here
		int sum = 0;
		if(n == 0) {
			return 1%b;
		}
		if(n == 1) {
			return a%b;
		}
		int count = n;
		int result = 1;
		while(count > 0) {
			System.out.println(count);
			long c = a;
			long i=0;
			for(i=1; i*2<=count; i*=2) {
				System.out.println("-----" + i + "-------");
				c = c * c % b;
				sum ++;
			}
			result *= c;
			result %= b;
			count -= i;
			if(count == 1) {
				result = result*a%b;
				break;
			}
		}
		//System.out.println(sum);
		return result;
		
    }
	
	int fastPowerBest(int a, int b, int n) {
        if (n == 0) {
            return 1 % b;
        }
        if (n == 1) {
            return a % b;
        }
        long result;
        long temp = fastPowerBest(a, b, n / 2);
        if (n % 2 == 0) {
            result = (temp * temp) % b;
        } else {
            result = ((temp * temp) % b * a) % b;
        }
        return (int) result;
    }
	
	
	public static void main(String[] args) {
		int result = new 快速幂().fastPowerIterator(109, 10000007, 1000001);
		System.out.println(result);
		//System.out.println(8%31);
	}
	

}

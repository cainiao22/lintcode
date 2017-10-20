package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��10��16�� ����2:35:00
 * @description  
 * 		����an % b������a��b��n����32λ��������
 * @example
 * 		���� 2^31 % 3 = 2
		���� 100^1000 % 1000 = 0
 *
 * @Solution
 */
public class ������ {
	
	/**
	 * ������û�д�ֻ������̫���ˡ��������������������޷����ƣ�������long long Ҳ��Ǻ
	 * �ھ����㷨�Ĵ���Ǽ���һ���ģtemp����ô�ܵ�ģ����temp*temp%b
	 * �����ó������������ԭ����һ�롣���������������
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
		int result = new ������().fastPowerIterator(109, 10000007, 1000001);
		System.out.println(result);
		//System.out.println(8%31);
	}
	

}

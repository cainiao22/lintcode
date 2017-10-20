package website.lintcode;

import java.util.Arrays;

/**
 * 
 * @author yanpf
 * @date 2017��10��13�� ����5:15:01
 * @description
 * 		дһ���������ҵ� n ������������

		���������Ķ������������������е��������Ӷ�����������һ����СΪ k �����������ڡ�

		������� 4 �������ļ��� [2, 7, 13, 19], ��ô [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32] ��ǰ 12 ������������
 * @example
 * 
 * 		���� n = 6 ���������� [2, 7, 13, 19]���� 6 ����������Ϊ 13�����Է��� 13 ��Ϊ�����
 *
 * @Solution ����forѭ��
 */
public class �������� extends HH {
	
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
		//primes�е�j������Ӧ���������λ��
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
		System.out.println(new ��������().nthSuperUglyNumber(394, primes));
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

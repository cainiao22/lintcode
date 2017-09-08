package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��9��7�� ����11:34:24
 * @description ��������k��0��n�еĳ��ֵĴ�����k������0~9��һ��ֵ
 * @example ����n=12��k=1���� [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]�����Ƿ���1������5�� (1, 10, 11, 12)
 *
 * @Solution
 */
public class ͳ������ {
	
	//TODO ��û�и���Ч���㷨��
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
	 * �Ƚϱ��ķ�����k-nÿ����������һ��
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
		int sum = new ͳ������().digitCountsBad(0, 12);
		System.out.println(sum);
	}

}

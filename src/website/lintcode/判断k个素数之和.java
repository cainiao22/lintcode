package website.lintcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author yanpf
 * @date 2017��12��18�� ����10:23:04
 * @description
 * 		���������� n �� k. ������Ҫ�ж� n �Ƿ����д�� k �������ĺ�.
 * @example
 *		���� n = 10, k = 2
		���� true // 10 = 5 + 5
		
		���� n = 2, k = 2
		���� false
 * @Solution
 */
public class �ж�k������֮�� extends HH {
	
	private Map<String, Boolean> map = new HashMap<>();
	
	//TODO ���ͺղ��� �κ�ż�������Ա����������֮��
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
		print(new �ж�k������֮��().isSumOfKPrimes(2, 2));
	}
}


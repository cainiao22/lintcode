package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��8��11�� ����12:01:53
 * @description ��һ���� 1 - n �����������ɵ�һ���ַ������У����ж�ʧ��һ�����������ҵ�����
 * @example ���� n = 20, str = 19201234567891011121314151618,��ʧ������ 17 �������������
 *
 * @Solution 1�����ʽ ����һ�������¼����ַ���������0��9���ַ���������N���ڵĸ�����λ���ֵĴ�����һ��ͳ�Ƽ���
 * 				Ȼ���������еĳ��ִ�����һ�ζԱȣ��Ѳ��Ե���������
 * 
 * 			 2��dsf ����ֻ����λ�����޷Ǿ���ǰһ�����ǲ������һ�������
 */			
public class Ѱ�Ҷ�ʧ����II {
	
	private int miss = 0;
	private boolean find = false;
	boolean[] hash;
	
	public int findMissing2(int n, String str) {
        // Write your code here
		hash = new boolean[n+1];
		dfsBetter(0, str, n);
		if(!find) {
		}
		return miss;
    }
	
	private void dfs(int index, String str, int n) {
		if(index >= str.length()) {
			for(int i=1; i<n + 1; i++) {
				if(!hash[i]) {
					System.out.println("miss=" + i);
					miss = i;
					find = true;
				}
			}
			return;
		}
		int a = str.charAt(index) - '0';
		if(a != 0 && !hash[a]) {
			hash[a] = true;
			dfs(index+1, str, n);
			hash[a] = false;
		}
		//����жϳ�a==0����ô��λ���϶�Ҳ�����ܵ�
		if(find || a == 0) return;
		//���������⣬����д��ֻ�ʺ���λ������Ҫ�Ľ�
		if(index + 1 < str.length()) {
			int b = str.charAt(index + 1) - '0';
			a = a * 10 + b;
			if(a > n) return;
			if(!hash[a]) {
				hash[a] = true;
				dfs(index + 2, str, n);
				hash[a] = false;
			}
		}
	}
	
	private void dfsBetter(int index, String str, int n) {
		if(index >= str.length()) {
			for(int i=1; i<n + 1; i++) {
				if(!hash[i]) {
					System.out.println("miss=" + i);
					miss = i;
					find = true;
				}
			}
			return;
		}
		int a = str.charAt(index) - '0';
		if(a == 0) return;
		int j = index;
		//����д��������ͨ���ԣ����پ�����N��λ��
		while(a <= n && !find) {
			if(!hash[a]) {
				hash[a] = true;
				dfs(j + 1, str, n);
				hash[a] = false;
			}
			j++;
			if(j < str.length()) {
				a = a * 10 + (str.charAt(j) - '0');
			}
		}
	}
	
	public static void main(String[] args) {
		int miss = new Ѱ�Ҷ�ʧ����II().findMissing2(11, "111098765432");
		System.out.println();
		System.out.println(miss);
	}

}

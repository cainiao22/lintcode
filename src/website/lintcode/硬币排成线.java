package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��10��11�� ����10:44:11
 * @description
 * 			
		�� n ��Ӳ���ų�һ���ߡ������������������ұ��������� 1 �� 2 ��Ӳ�ң�ֱ��û��Ӳ��Ϊֹ���õ����һöӲ�ҵ��˻�ʤ��
		
		���ж� ��һ����� ���仹��Ӯ��

 * @example
 * 		n = 1, ���� true.

		n = 2, ���� true.
		
		n = 3, ���� false.
		
		n = 4, ���� true.
		
		n = 5, ���� true
 *
 * @Solution
 */
public class Ӳ���ų��� extends HH{
	
	public boolean firstWillWin2(int n) {
        // write your code here
		
		return n%3 != 0;
    }
	
	public boolean firstWillWin(int n) {
        // write your code here
		
		if(n <= 2) {
			return true;
		}
		return !firstWillWin(n-1) || !firstWillWin(n-2);
    }
	
	public boolean firstWillWinDp(int n) {
        // write your code here
		
		if(n <= 2) {
			return true;
		}
		boolean[] memory = new boolean[n+1];
		memory[1] = memory[2] = true;
		for(int i=3; i<= n; i++) {
			memory[i] = !memory[i-1] || !memory[i-2];
		}
		return memory[n];
    }
	
	public static void main(String[] args) {
		print(new Ӳ���ų���().firstWillWinDp(5));
	}

}

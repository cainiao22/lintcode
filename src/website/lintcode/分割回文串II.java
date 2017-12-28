package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��10��25�� ����5:35:01
 * @description
 * 		
		����һ���ַ���s����s�ָ��һЩ�Ӵ���ʹÿ���Ӵ����ǻ��ġ�	
		����s����Ҫ��ĵ����ٷָ������

 * @example
 * 		���磬�����ַ���s = "aab"��
		���� 1�� ��Ϊ����һ�ηָ���Խ��ַ���s�ָ��["aa","b"]�������������Ӵ�
 *
 * @Solution
 */
public class �ָ���Ĵ�II extends HH {
	
	//todo ���Ż� ��ʱ��
	public int minCut(String s) {
        // write your code here
		if(s == null || s.length() <= 1) {
			return 0;
		}
		int length = s.length();
		int[][] min = new int[length][length];
		for(int i=0; i<length-1; i++) {
			if(s.charAt(i) == s.charAt(i+1)) {
				min[i][i+1] = 0;
			}else {
				min[i][i+1] = 1;
			}
		}
		for(int i=2; i<length; i++) {//�����С
			for(int j=0; j<length-i; j++) { //�����
				if(isPalindromic(s.substring(j, j+i+1))) {
					min[i][j] = 0;
					continue;
				}
				int m = Integer.MAX_VALUE;
				for(int k=j; k<j+i; k++) { //���Ҷ�
					m = Math.min(m, min[j][k] + min[k+1][j+i] + 1);
				}
				min[j][j+i] = m;
			}
		}
		print(min);
		return min[0][length-1];
    }

	private boolean isPalindromic(String substring) {
		for(int i=0, j=substring.length()-1; i<j; i++, j--) {
			if(substring.charAt(i) != substring.charAt(j)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean[][] getIsPalindromic(String s){
		boolean[][] isPalindromic = new boolean[s.length()][s.length()];
		for(int i=0; i<s.length(); i++) {
			isPalindromic[i][i] = true;
		}
		for(int i=0; i<s.length()-1; i++) {
			isPalindromic[i][i+1] = (s.charAt(i) == s.charAt(i+1));
		}
		
		for(int len = 2; len < s.length(); len ++) {
			for(int i=0; i+len<s.length(); i++) { //����ܴ���
				isPalindromic[i][i+len] = isPalindromic[i+1][i+len-1] && s.charAt(i) == s.charAt(i+len);
			}
		}
		
		return isPalindromic;
		
	}
	
	public int minCutFromJiuZhang(String s) {
		boolean[][] isPalindromic = getIsPalindromic(s);
		//ǰi���ַ����и�����ж��ٵ�
		int[] f = new int[s.length()+1];
		f[0] = 0;
		for(int i=1; i<=s.length(); i++) {
			f[i] = i;
			for(int j=0; j<i; j++) {
				if(isPalindromic[j][i-1]){
					f[i] = Math.min(f[i], f[j] + 1);
				}
			}
		}
		
		return f[s.length()] - 1;
	}
	
	public static void main(String[] args) {
		print(new �ָ���Ĵ�II().minCutFromJiuZhang("abbab"));
		System.out.println("30d4bf869d4311e791311866daf6aff9".length());
	}

}

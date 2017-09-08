package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��9��7�� ����3:14:33
 * @description 
 * 			������������word1��word2���������word1 ת��Ϊword2�����ٲ���������
			
			���ܹ����ֲ���������
			
			    ����һ���ַ�
			    ɾ��һ���ַ�
			    �滻һ���ַ�
			    
			    
 * @example  ���� work1="mart" �� work2="karma"  ���� 3
 *
 * @Solution  
 * 		1�����ǿ�����k�������ڽ� s[1��i] ת��Ϊ t[1��j-1]

		2�����ǿ�����k���������潫s[1..i-1]ת��Ϊt[1..j]
		
		3�����ǿ�����k���������潫 s[1��i-1] ת��Ϊ t [1��j-1]
		
		��Ե�1�����������ֻ��Ҫ����� t[j] ����s[1..i]�������ƥ�䣬�����ܹ�����Ҫk+1��������
		
		��Ե�2�����������ֻ��Ҫ�����s[i]�Ƴ���Ȼ��������k�������������ܹ���Ҫk+1��������
		
		��Ե�3�����������ֻ��Ҫ�����s[i]�滻Ϊ t[j]��ʹ������s[1..i] == t[1..j]�������ܹ�Ҳ��Ҫk+1��������������ڵ�3������£�s[i]�պõ���t[j]�������ǾͿ��Խ���ʹ��k�����������������̡�
		
		  ���Ϊ�˱�֤�õ��Ĳ��������������ٵģ����ǿ��Դ��������������ѡ���������ٵ�һ����Ϊ��s[1..i]ת��Ϊt[1..j]����Ҫ����С����������
 */
public class �༭���� {
	
	public int minDistance(String word1, String word2) {
        // write your code here
		int M = word1.length();
		int N = word2.length();
		//�д���word1���д���word2
		int[][] matrix = new int[M+1][N+1];
		//��word1��ǰi����ĸ���word2��ǰ0����ĸ��ֻ��Ҫɾ��i��
		for(int i=0; i<=M; i++) {
			matrix[i][0] = i;
		}
		//��word1��ǰ0����ĸ���word2��ǰi����ĸ��ֻ��Ҫ����i��
		for(int i=0; i<=N; i++) {
			matrix[0][i] = i;
		}
		
		for(int i=1; i<=M; i++) {
			for(int j=1; j<= N; j++) {
				//����ǰj-1�����ǰi-1���������j���͵�i����ȡ������ٲ�����������һ������j���i
				int temp = word1.charAt(i-1) != word2.charAt(j-1) ? 1:0;
				//ǰj-1�����ǰi������j����ĸҪ�Ƴ���ǰj�����ǰi-1������ô��i��Ҫ���롣���Զ����һ������
				matrix[i][j] = Math.min(matrix[i-1][j]+1, matrix[i][j-1]+1);
				matrix[i][j] = Math.min(matrix[i][j], matrix[i-1][j-1] + temp);
				
			}
		}
		
		return matrix[M][N];
    }
	
	public static void main(String[] args) {
		int a = new �༭����().minDistance("a", "a");
		System.out.println(a);
	}

}

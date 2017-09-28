package website.lintcode;

import java.util.Stack;

/**
 * 
 * @author yanpf
 * @date 2017��9��27�� ����3:16:06
 * @description
 * 
 * 				����һ����ά����ĸ���һ�����ʣ�Ѱ����ĸ���������Ƿ����������ʡ�
 *              ���ʿ����ɰ�˳������ڵ�Ԫ����ĸ��ɣ��������ڵ�Ԫָ����ˮƽ���ߴ�ֱ�������ڡ�ÿ����Ԫ�е���ĸ���ֻ��ʹ��һ�Ρ�
 * 
 * @example ����board = [
 * 
 *          "ABCE",
 * 
 *          "SFCS",
 * 
 *          "ADEE" ] word = "ABCCED"�� ->���� true, word = "SEE"��-> ���� true, word =
 *          "ABCB"�� -> ���� false.
 *
 * @Solution
 */
public class �������� extends HH {

	//private Stack<Character> stack = new Stack<>();
	private int[][] flag;
	int[] dx = new int[] { 1, 0, -1, 0 };
	int[] dy = new int[] { 0, 1, 0, -1 };
	int M, N;

	public boolean exist(char[][] board, String word) {
		// write your code here
		flag = new int[board.length][board[0].length];
		M = board.length;
		N = board[0].length;
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] == word.charAt(0)) {
					flag[i][j] = 1;
					//stack.push(word.charAt(0));
					boolean result = dfs(board, i, j, word, 1);
					if (result) {
						print(flag);
						return result;
					}
					flag[i][j] = 0;
					//stack.pop();
				}
			}
		}

		return false;
	}
	//������flag��stack��ȫ�Ƕ���ģ�ֻ�����ڵ��ԣ��жϵó���·���ͽ���Ƿ���ȷ
	private boolean dfs(char[][] board, int i, int j, String word, int index) {
		if (index == word.length()) {
			return true;
		}
		for (int k = 0; k < dx.length; k++) {
			int m = i + dx[k];
			int n = j + dy[k];
			if (m >= 0 && m < M && n >= 0 && n < N 
					&& flag[m][n] != 1
					&& board[m][n] == word.charAt(index)) {
				//stack.push(board[m][n]);
				flag[m][n] = 1;
				boolean result = dfs(board, m, n, word, index + 1);
				if (result) {
					//print(stack);
					return result;
				}
				//stack.pop();
				flag[m][n] = 0;
			}
		}

		return false;
	}
	
	//����������������
	private boolean find(char[][] board, int i, int j, String word, int start){
        if(start == word.length())
            return true;
        
        if (i < 0 || i>= board.length || 
     j < 0 || j >= board[0].length || board[i][j] != word.charAt(start)){
            return false;
	 }
        
        board[i][j] = '#'; // should remember to mark it
        boolean rst = find(board, i-1, j, word, start+1) 
|| find(board, i, j-1, word, start+1) 
|| find(board, i+1, j, word, start+1) 
|| find(board, i, j+1, word, start+1);
        board[i][j] = word.charAt(start);
        return rst;
    }

	public static void main(String[] args) {
		char[][] board = new char[][] {

				{ 'A', 'B', 'C', 'E' },

				{ 'S', 'F', 'C', 'S' },

				{ 'A', 'D', 'E', 'E' } };
		String word = "ABCB";
		new ��������().exist(board, word);
		set(board);
		System.out.println("--------");
		print(board);
		
	}
	
	public static void set(char[][] a) {
		a[0][0] = '#';
		a[0][1] = '#';
		print(a);
	}

}

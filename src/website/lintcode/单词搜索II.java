package website.lintcode;

import java.util.List;

/**
 * 
 * @author yanpf
 * @date 2018��3��9�� ����4:29:23
 * @description
 * 		����һ����Сд��ĸ��ɵľ����һ���ֵ䡣�ҳ�����ͬʱ���ֵ�;����г��ֵĵ��ʡ�һ�����ʿ��ԴӾ����е�����λ�ÿ�ʼ����������/��/��/���ĸ����ڷ����ƶ�
 * 		ʹ�õ��ʲ�������ʵ������㷨
 * @example
 * 		��������
			doaf
			agai
			dcan
			
			���ֵ䣺
			
			{"dog", "dad", "dgdg", "can", "again"}
			
			
			���� {"dog", "dad", "can", "again"}
			
			
			dog:
			
			doaf
			agai
			dcan
			
			dad:
			
			doaf
			agai
			dcan
			
			can:
			
			doaf
			agai
			dcan
			
			again:
			
			doaf
			agai
			dcan
 *
 * @Solution
 * 
 */
public class ��������II {
	
	//TODO δ��� ��������
	public List<String> wordSearchII(char[][] board, List<String> words) {
        // write your code here
		int[][] direction = new int[][] {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
		for(String word : words) {
			int[][] flag = new int[board.length][board[0].length];
			for(int i=0; i<board.length; i++) {
				for(int j=0; j<board[0].length; j++) {
					if(board[i][j] == word.charAt(0)) {
						int k=1;
						while(k < word.length()) {
							
						}
					}
				}
			}
		}
		
		return null;
    }
	

}

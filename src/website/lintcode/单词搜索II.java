package website.lintcode;

import java.util.List;

/**
 * 
 * @author yanpf
 * @date 2018年3月9日 下午4:29:23
 * @description
 * 		给出一个由小写字母组成的矩阵和一个字典。找出所有同时在字典和矩阵中出现的单词。一个单词可以从矩阵中的任意位置开始，可以向左/右/上/下四个相邻方向移动
 * 		使用单词查找树来实现你的算法
 * @example
 * 		给出矩阵：
			doaf
			agai
			dcan
			
			和字典：
			
			{"dog", "dad", "dgdg", "can", "again"}
			
			
			返回 {"dog", "dad", "can", "again"}
			
			
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
public class 单词搜索II {
	
	//TODO 未完成 单词搜索
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

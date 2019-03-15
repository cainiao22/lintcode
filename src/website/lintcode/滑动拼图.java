package website.lintcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 
 * @author yanpf
 * @date 2019年2月27日 下午3:12:07
 * @description
 * 		在一块大小为 2x3 的板上，有 5 块瓦片，分别用整数 1 到 5 表示，还有一块空地用 0 表示。
		一次移动表示 0 与其相邻的四个方向之一的数字交换位置。
		当且仅当 这块板 上的瓦片摆放状态为 [[1,2,3],[4,5,0]] 时，才能说这块板存在的问题被解决了。
		board 会以上面讲的 2 x 3 大小的数组形式输入 。
		board[i][j] 会是 [0, 1, 2, 3, 4, 5] 中的其中一个值.
 * @example
 * 		给出 board = [[1,2,3],[4,0,5]], 返回 1.

		结束: 
		交换0和5，只需要一步.
		给出 board = [[1,2,3],[5,4,0]], 返回 -1.
		
		解释: 
		不管移动多少步都无法解决问题。
		给出 board = [[4,1,2],[5,0,3]], 返回 5.
		
		解释: 
		至少需要移动5步来解决这个问题。
		比如这么做:
		移动 0 步之后: [[4,1,2],[5,0,3]]
		移动 1 步之后: [[4,1,2],[0,5,3]]
		移动 2 步之后: [[0,1,2],[4,5,3]]
		移动 3 步之后: [[1,0,2],[4,5,3]]
		移动 4 步之后: [[1,2,0],[4,5,3]]
		移动 5 步之后: [[1,2,3],[4,5,0]]
		给出 board = [[3,2,4],[1,5,0]], 返回 14.
 *
 * @Solution
 */
public class 滑动拼图 extends HH {
	
	private Map<String, Integer> map = new HashMap<>();
	int[] dx = new int[] {0, -1, 0, 1};
	int[] dy = new int[] {1, 0, -1, 0};
	int minSteps = Integer.MAX_VALUE;
	
	/**
	 * 
	 * @param board
	 * @return
	 */
	//TODO 递归方式，有问题
	public int slidingPuzzlerecursive (int[][] board) {
        // write your code here
		map.put("123450", 0);
		int[] pos = new int[2];
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board[0].length; j++) {
				if(board[i][j] == 0) {
					pos[0] = i;
					pos[1] = j;
					break;
				}
			}
		}
		helper(board, pos);
		return map.get(getKey(board)) == Integer.MAX_VALUE ? -1 : map.get(getKey(board));
    }
	
	private int helper(int[][] board, int[] pos) {
		String key = getKey(board);
		if(map.get(key) != null) {
			return map.get(key);
		}
		map.put(key, Integer.MAX_VALUE);
		for(int i=0; i<dx.length; i++) {
			if(pos[0] + dx[i] >= 0 && pos[0] + dx[i] < board.length 
					&& pos[1] + dy[i] >= 0 && pos[1] + dy[i] < board[0].length) {
				board[pos[0]][pos[1]] = board[pos[0] + dx[i]][pos[1] + dy[i]];
				pos[0] = pos[0] + dx[i];
				pos[1] = pos[1] + dy[i];
				board[pos[0]][pos[1]] = 0;
				int steps = helper(board, pos);
				if(steps != Integer.MAX_VALUE) {
					steps += 1;
				}
				map.put(key, Math.min(map.get(key), steps));

				board[pos[0]][pos[1]] = board[pos[0] - dx[i]][pos[1] - dy[i]];
				board[pos[0] - dx[i]][pos[1] - dy[i]] = 0;
				pos[0] = pos[0] - dx[i];
				pos[1] = pos[1] - dy[i];
			}
		}
		
		return map.get(key);
	}
	
	private String getKey(int[][] board) {
		StringBuffer sBuffer = new StringBuffer();
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board[0].length; j++) {
				sBuffer.append(board[i][j]);
			}
		}
		
		return sBuffer.toString();
	}
	
	
	public int slidingPuzzleBFS1(int[][] board) {
		map.put("123450", 0);
		Map<String, Integer[]> posMap = new HashMap<>();
		Queue<String> queue = new LinkedList<>();
		queue.add("123450");
		posMap.put("123450", new Integer[] {1, 2});
		while(!queue.isEmpty()) {
			String top = queue.poll();
			int[][] temp = getBoardFromKey(top);
			Integer[] pos = posMap.get(top);
			for(int i=0; i<dx.length; i++) {
				if(pos[0] + dx[i] >= 0 && pos[0] + dx[i] < temp.length 
						&& pos[1] + dy[i] >= 0 && pos[1] + dy[i] < temp[0].length) {
					int[][] temp2 = copyArray(temp);
					temp2[pos[0]][pos[1]] = temp2[pos[0] + dx[i]][pos[1] + dy[i]];
					temp2[pos[0] + dx[i]][pos[1] + dy[i]] = 0;
					if(!map.containsKey(getKey(temp2))) {
						map.put(getKey(temp2), map.get(top) + 1);
						posMap.put(getKey(temp2), new Integer[] {pos[0] + dx[i], pos[1] + dy[i]});
						queue.add(getKey(temp2));
					}
				}
			}
		}
		
		return map.get(getKey(board)) == null ? -1 : map.get(getKey(board));
		
	}
	
	private int[][] copyArray(int[][] temp) {
		int[][] result = new int[2][3];
		for(int i=0; i<temp.length; i++) {
			for(int j=0; j<temp[0].length; j++) {
				result[i][j] = temp[i][j];
			}
		}
		
		return result;
	}

	private int[][] getBoardFromKey(String poll) {
		int[][] board = new int[2][3];
		for(int i=0; i<poll.length(); i++) {
			board[i/3][i%3] = poll.charAt(i) - '0';
		}
		return board;
	}
	
	/**
	 * 比较优化的方法
	 * @param board
	 * @return
	 */
	public int slidingPuzzleBFS2(int[][] board) {
		Set<String> visitedPathes = new HashSet<>();
		Map<String, String> pre = new HashMap<>();
		String start = getKey(board);
		String target = "123450";
		visitedPathes.add(start);
		//dirs[i] 代表0在位置i的时候可以移动的位置,这个方式只适用于board比较小的情况，如果board太大还是得用上面那个dx,dy的方式
		int[][] dirs = new int[][] {{1,3}, {0,2,4}, {1,5}, {0,4}, {1,3,5}, {2,4}};
		Queue<String> queue = new LinkedList<>();
		queue.add(start);
		int res = 0;
		while(!queue.isEmpty()) {
			//这一层的都是路径数相同的节点，这个解题思路经常出现，得记住
			int len = queue.size();
			for(int i=0; i< len; i++) {
				String top = queue.poll();
				if(top.equals(target)) {
					while(top != null) {
						System.out.println(top);
						top = pre.get(top);
					}
					return res;
				}
				int index = top.indexOf('0');
				int[] dir = dirs[index];
				for(int j=0; j<dir.length; j++) {
					char[] chars = top.toCharArray();
					char a = chars[dir[j]];
					chars[dir[j]] = '0';
					chars[index] = a;
					String s = new String(chars);
					if(visitedPathes.contains(s)) {
						continue;
					}
					pre.put(s, top);
					visitedPathes.add(s);
					queue.add(s);
				}
			}
			
			res ++;
			
		}
		
		return -1;
	}
	
	
	
	
	

	public static void main(String[] args) {
		int[][] board = new int[][] {{4,1,2},{5,0,3}};
		int result = new 滑动拼图().slidingPuzzleBFS2(board);
		System.out.println(result);
	}

}

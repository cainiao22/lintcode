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
 * @date 2019��2��27�� ����3:12:07
 * @description
 * 		��һ���СΪ 2x3 �İ��ϣ��� 5 ����Ƭ���ֱ������� 1 �� 5 ��ʾ������һ��յ��� 0 ��ʾ��
		һ���ƶ���ʾ 0 �������ڵ��ĸ�����֮һ�����ֽ���λ�á�
		���ҽ��� ���� �ϵ���Ƭ�ڷ�״̬Ϊ [[1,2,3],[4,5,0]] ʱ������˵������ڵ����ⱻ����ˡ�
		board �������潲�� 2 x 3 ��С��������ʽ���� ��
		board[i][j] ���� [0, 1, 2, 3, 4, 5] �е�����һ��ֵ.
 * @example
 * 		���� board = [[1,2,3],[4,0,5]], ���� 1.

		����: 
		����0��5��ֻ��Ҫһ��.
		���� board = [[1,2,3],[5,4,0]], ���� -1.
		
		����: 
		�����ƶ����ٲ����޷�������⡣
		���� board = [[4,1,2],[5,0,3]], ���� 5.
		
		����: 
		������Ҫ�ƶ�5�������������⡣
		������ô��:
		�ƶ� 0 ��֮��: [[4,1,2],[5,0,3]]
		�ƶ� 1 ��֮��: [[4,1,2],[0,5,3]]
		�ƶ� 2 ��֮��: [[0,1,2],[4,5,3]]
		�ƶ� 3 ��֮��: [[1,0,2],[4,5,3]]
		�ƶ� 4 ��֮��: [[1,2,0],[4,5,3]]
		�ƶ� 5 ��֮��: [[1,2,3],[4,5,0]]
		���� board = [[3,2,4],[1,5,0]], ���� 14.
 *
 * @Solution
 */
public class ����ƴͼ extends HH {
	
	private Map<String, Integer> map = new HashMap<>();
	int[] dx = new int[] {0, -1, 0, 1};
	int[] dy = new int[] {1, 0, -1, 0};
	int minSteps = Integer.MAX_VALUE;
	
	/**
	 * 
	 * @param board
	 * @return
	 */
	//TODO �ݹ鷽ʽ��������
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
	 * �Ƚ��Ż��ķ���
	 * @param board
	 * @return
	 */
	public int slidingPuzzleBFS2(int[][] board) {
		Set<String> visitedPathes = new HashSet<>();
		Map<String, String> pre = new HashMap<>();
		String start = getKey(board);
		String target = "123450";
		visitedPathes.add(start);
		//dirs[i] ����0��λ��i��ʱ������ƶ���λ��,�����ʽֻ������board�Ƚ�С����������board̫���ǵ��������Ǹ�dx,dy�ķ�ʽ
		int[][] dirs = new int[][] {{1,3}, {0,2,4}, {1,5}, {0,4}, {1,3,5}, {2,4}};
		Queue<String> queue = new LinkedList<>();
		queue.add(start);
		int res = 0;
		while(!queue.isEmpty()) {
			//��һ��Ķ���·������ͬ�Ľڵ㣬�������˼·�������֣��ü�ס
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
		int result = new ����ƴͼ().slidingPuzzleBFS2(board);
		System.out.println(result);
	}

}

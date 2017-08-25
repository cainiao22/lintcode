package website.lintcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author yanpf
 * @date 2017年8月21日 下午2:24:04
 * @description 给定一个包含 m x n 个要素的矩阵，（m 行, n 列），按照螺旋顺序，返回该矩阵中的所有要素。
 * 
 * @example 给定如下矩阵：
 * 
 *          [ [ 1, 2, 3 ], [ 4, 5, 6 ], [ 7, 8, 9 ] ]
 * 
 *          应返回 [1,2,3,6,9,8,7,4,5]。
 *
 * @Solution 直接模拟，但是需要模拟的是三个等长的竹棍头尾相接
 */
public class 螺旋矩阵 extends HH {
	
	//todo 有bug需要修改
	public List<Integer> spiralOrderError(int[][] matrix) {
		List<Integer> list = new ArrayList<>();
		int m = matrix.length;
		if (m == 0) {
			return list;
		}
		int n = matrix[0].length;
		for (int i = 0; i < (m + 1) / 2 && i < (n + 1) / 2; i++) {
			System.out.println(i);
			int j;
			for (j = i; j < n - i - 1; j++) {
				System.out.print(matrix[i][j] + " ");
				list.add(matrix[i][j]);
			}
			System.out.println();
			for (j = i; j < m - i - 1; j++) {
				System.out.print(matrix[j][n - i - 1] + " ");
				list.add(matrix[j][n - i - 1]);
			}
			System.out.println();
			for (j = n - 1 - i; j > i; j--) {
				System.out.print(matrix[m - i - 1][j] + " ");
				list.add(matrix[m - i - 1][j]);
			}
			System.out.println();
			for (j = m - i - 1; j > i; j--) {
				System.out.print(matrix[j][i] + " ");
				list.add(matrix[j][i]);
			}
			System.out.println();
		}

		return list;
	}

	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> list = new ArrayList<>();
		int m = matrix.length;
		if (m == 0) {
			return list;
		}
		int n = matrix[0].length;
		int[] cursor = new int[] { 0, 0 };
		int direction = Direction.RIGHT;
		for (int i = 0; i < m * n; i++) {
			list.add(matrix[cursor[0]][cursor[1]]);
			matrix[cursor[0]][cursor[1]] = -1;
			int[] nexcur = Direction.move(cursor, direction);
			if (nexcur[0] >= m || nexcur[0] < 0 || nexcur[1] >= n || nexcur[1] < 0
					|| matrix[nexcur[0]][nexcur[1]] == -1) {
				direction = Direction.turnRight(direction);
				nexcur = Direction.move(cursor, direction);
			}
			cursor = nexcur;
		}
		return list;
	}

	public static void main(String[] args) {
		int[][] matrix = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 10, 11, 12 }, { 13, 14, 15 }
				/*
				 * {1}, {2}
				 */
		};
		List<Integer> list = new 螺旋矩阵().spiralOrder(matrix);
		print(list);
	}
}

class Direction {
	static final int LEFT = 0;
	static final int RIGHT = 1;
	static final int UP = 2;
	static final int DOWN = 3;
	
	/** 这里需要注意x和y指的是谁  **/
	static int[] dx = new int[] { 0, 0, -1, 1 };
	static int[] dy = new int[] { -1, 1, 0, 0 };

	public static int turnRight(int direction) {
		switch (direction) {
		case LEFT:
			return UP;
		case RIGHT:
			return DOWN;
		case DOWN:
			return LEFT;
		case UP:
			return RIGHT;
		}

		return 0;
	}

	public static int[] move(int[] cursor, int direction) {
		int[] nextcur = new int[2];
		nextcur[0] = cursor[0] + dx[direction];
		nextcur[1] = cursor[1] + dy[direction];

		return nextcur;
	}
}

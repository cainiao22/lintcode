package website.lintcode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 
 * @author yanpf
 * @date 2019��3��28�� ����12:04:26
 * @description
 * 		����һ������N��ʾN*N*N�Ŀռ䣬ÿһ�����������ʾ�ϰ���λ�ã����ش�(0,0,0) ��(N-1,N-1,N-1)����С�Ĳ�����������ܵ����򷵻�-1��
 * @example
 * 		���� 1:

		����: N = 3, barrier = [[1,0,0],[1,0,1],[1,0,2],[1,1,0],[1,1,1],[1,1,2],[1,2,1],[1,2,2]]
		���: 6
		���� 2:
		
		����: N = 3, barrier = [[2,2,2]]
		���: -1
 *
 * @Solution
 */
public class ��ά�ռ��е���̾��� {
	//x, y, z
	int[][] dt = new int[][] {{1, 0, 0,}, {0, 1, 0}, {0, 0, 1}, {-1, 0, 0}, {0, -1, 0}, {0, 0, -1}};
	int MAX_VALUE =Integer.MAX_VALUE - 10;
	public int shortestDistance(int N, int[][] barriers) {
        // Write your code here
		int[][][] map = new int[N][N][N];
		for(int i=0; i<barriers.length; i++) {
			map[barriers[i][0]][barriers[i][1]][barriers[i][2]] = 1;
		}
		if(map[0][0][0] == 1 || map[N-1][N-1][N-1] == 1) {
			return -1;
		}
		
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {0, 0, 0});
		int steps = 0;
		while(!queue.isEmpty()) {
			int len = queue.size();
			for(int i=0; i<len; i++) {
				int[] top = queue.poll();
				if(top[0] == N - 1 && top[1] == N - 1 && top[2] == N - 1) {
					return steps;
				}
				labelJ:
				for(int j=0; j<dt.length; j++) {
					for(int k=0; k<top.length; k++) {
						if(!isValidate(top[k] + dt[j][k], N)) {
							continue labelJ;
						}
					}
					if(map[top[0] + dt[j][0]][top[1]+dt[j][1]][top[2] + dt[j][2]] == 0) {
						queue.add(new int[] {top[0] + dt[j][0], top[1]+dt[j][1], top[2] + dt[j][2]});
						map[top[0] + dt[j][0]][top[1]+dt[j][1]][top[2] + dt[j][2]] = 1;
					}
				}
			}
			steps ++;
		}
		return -1;
    }
	
	boolean isValidate(int cur, int N) {
		return cur >= 0 && cur < N;
	}
	
	public static void main(String[] args) {
		int[][] barriers = new int[][]{{1,0,0},{1,0,1},{1,0,2},{1,1,0},{1,1,1},{1,1,2},{1,2,1},{1,2,2}};
		int N = 3;
		int result = new ��ά�ռ��е���̾���().shortestDistance(N, barriers);
		System.out.println(result);
	}

}

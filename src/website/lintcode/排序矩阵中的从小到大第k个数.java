package website.lintcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 
 * @author yanpf
 * @date 2017��8��8�� ����5:30:03
 * 
 * @description ��һ������������Ҵ�С����ĵ� k ���������������Ķ���Ϊ��ÿһ�е�����ÿһ��Ҳ������
 * 
 * @example ���� k = 7 ��һ���������
 * 
 *          [ [1 ,5 ,7, 10],
 *            [3 ,7 ,8, 11], 
 *            [6 ,8 ,9, 12], 
 *            [7 ,9 ,9, 12], ]
 * 
 *          ���� 7��
 *
 * @Solution 1����·�鲢�������ʽ�ǽ���С���ѣ�����ĳһ������������ԣ�����k������
 * 
 *           2�����þ����㷨�ķ�ʽ��ʹ�����ȶ��У�matrix[i][j]����ǵ�ǰ��С����ô�Ƚ����ԱȽϵĺ������ڵ�
 *           Ҳ����matrix[i][j+1]��matrix[i+1][j]������ֱ�ӵĴ�С���ڲ�Ƕ��һ��ѭ����ֱ�ӽ���������ջ���ɡ�
 *           
 *           3�����ַ���һ��Ķ��ֶ��Ƕ��±�ȡmid,��������Ķ��ַ�ʽ���õ��ǶԾ������ֵȡmid,����ȡ����������Сֵ
 *           �ֱ�λ�����ϽǺ����½ǣ�Ȼ�������ֵȡ���ǵ��м�����Ȼ�󵽾�����ȡѰ������м�����λ��
 *           
 */
public class ��������еĴ�С�����k���� {

	class Pair {
		int x, y, val;

		public Pair(int x, int y, int val) {
			this.x = x;
			this.y = y;
			this.val = val;
		}
	}

	public int kthSmallest(int[][] matrix, int k) {
		// write your code here
		int m = matrix.length;
		int n = matrix[0].length;
		boolean[][] hash = new boolean[m][n];
		PriorityQueue<Pair> queue = new PriorityQueue<Pair>(new Comparator<Pair>() {

			@Override
			public int compare(Pair o1, Pair o2) {
				return o1.val - o2.val;
			}
		});
		Pair root = new Pair(0, 0, matrix[0][0]);
		int[] dx = { 0, 1 };
		int[] dy = { 1, 0 };
		queue.add(root);
		for (int i = 0; i < k - 1; i++) {
			Pair cur = queue.poll();
			for (int j = 0; j < 2; j++) {
				int next_x = cur.x + dx[j];
				int next_y = cur.y + dy[j];
				if(next_x < m && next_y < n && hash[next_x][next_y] == false) {
					hash[next_x][next_y] = true;
					Pair pair = new Pair(next_x, next_y, matrix[next_x][next_y]);
					queue.add(pair);
				}
			}
		}
		return queue.poll().val;
	}
	
	class ResultType {
		boolean exists;
		int num;
		
		public ResultType(int num, boolean exists) {
			this.num = num;
			this.exists = exists;
		}
	}
	
	//todo �ƺ�����ĳ�����������ĳ�����ظ��ˣ��պÿ����һ��������ô��������������ô�죿
	ResultType check(int[][] matrix, int val) {
		int m = matrix.length;
		int n = matrix[0].length;
		int i = m - 1;
		int j = 0;
		int nums = 0;
		boolean exists = false;
		while(i >= 0 && j < n) {
			if(matrix[i][j] <= val) {
				if(matrix[i][j] == val) exists = true;
				nums += i + 1;
				j += 1;
			}else {
				i -= 1;
			}
		}
		return new ResultType(nums, exists);
		
	}
	
	public int kthSmallestErFen(int[][] matrix, int k) {
		int left = matrix[0][0];
		int m = matrix.length -1;
		int n = matrix[0].length -1;
		int right = matrix[m][n];
		//Ϊ�˱�������ĳ���������һ�����������������ƺ��޷��������
		while(left <= right) {
			int mid = left + (right - left) / 2;
			ResultType resultType = check(matrix, mid);
			if(resultType.num == k && resultType.exists) {
				return mid;
			}else if(resultType.num >= k) {
				//������Ϊ����resultType.num == k���������������Ǹ�check����ʵ���ϼ�����Ԫ�ص���Զλ�á�
				//���Բ���+1,�����������ͬһ��Ԫ��ս����һ�ε�λ�õ����
				right = mid;
			}else {
				left = mid + 1;
			}
		}
		
		return left;
	}
	
	public static void main(String[] args) {
		int[][] matrix = new int[][] {{998,1002},{998,1003},{999,1003},{1000,1003},{1000,1004}};
		int res1 = new ��������еĴ�С�����k����().kthSmallest(matrix, 7);
		int res2 = new ��������еĴ�С�����k����().kthSmallestErFen(matrix, 7);
		System.out.println(res1);
		System.out.println(res2);
	}

}

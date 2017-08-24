package website.lintcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 
 * @author yanpf
 * @date 2017年8月8日 下午5:30:03
 * 
 * @description 在一个排序矩阵中找从小到大的第 k 个整数。排序矩阵的定义为：每一行递增，每一列也递增。
 * 
 * @example 给出 k = 7 和一个排序矩阵：
 * 
 *          [ [1 ,5 ,7, 10],
 *            [3 ,7 ,8, 11], 
 *            [6 ,8 ,9, 12], 
 *            [7 ,9 ,9, 12], ]
 * 
 *          返回 7。
 *
 * @Solution 1、多路归并，这个方式是建立小顶堆，利用某一个方向的有序性，进行k次排序
 * 
 *           2、引用九章算法的方式，使用优先队列，matrix[i][j]如果是当前最小，那么比较难以比较的和他相邻的
 *           也就是matrix[i][j+1]，matrix[i+1][j]两个数直接的大小。内部嵌套一层循环，直接将两个数入栈即可。
 *           
 *           3、二分法。一般的二分都是对下标取mid,但是这里的二分方式采用的是对具体的数值取mid,首先取矩阵的最大最小值
 *           分别位于左上角和右下角，然后对两个值取他们的中间数，然后到矩阵中取寻找这个中间数的位置
 *           
 */
public class 排序矩阵中的从小到大第k个数 {

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
	
	//todo 似乎存在某种隐患，如果某个数重复了，刚好夸过了一段区域，那么在这段区域的数怎么办？
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
		//为了避免那种某个数跨过了一段区域的情况，但是似乎无法解决问题
		while(left <= right) {
			int mid = left + (right - left) / 2;
			ResultType resultType = check(matrix, mid);
			if(resultType.num == k && resultType.exists) {
				return mid;
			}else if(resultType.num >= k) {
				//这里因为有了resultType.num == k的条件，而上面那个check方法实际上检查的是元素的最远位置。
				//所以不能+1,避免出现那种同一个元素战距了一段的位置的情况
				right = mid;
			}else {
				left = mid + 1;
			}
		}
		
		return left;
	}
	
	public static void main(String[] args) {
		int[][] matrix = new int[][] {{998,1002},{998,1003},{999,1003},{1000,1003},{1000,1004}};
		int res1 = new 排序矩阵中的从小到大第k个数().kthSmallest(matrix, 7);
		int res2 = new 排序矩阵中的从小到大第k个数().kthSmallestErFen(matrix, 7);
		System.out.println(res1);
		System.out.println(res2);
	}

}

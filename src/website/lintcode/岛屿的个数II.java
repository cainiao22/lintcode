package website.lintcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sound.midi.Soundbank;

/**
 * 
 * @author yanpf
 * @date 2019年2月25日 上午10:28:56
 * @description
 * 		给定 n, m, 分别代表一个二维矩阵的行数和列数, 并给定一个大小为 k 的二元数组A. 初始二维矩阵全0. 
 * 		二元数组A内的k个元素代表k次操作, 设第i个元素为 (A[i].x, A[i].y), 表示把二维矩阵中下标为A[i].x行A[i].y列的元素由海洋变为岛屿. 
 * 		问在每次操作之后, 二维矩阵中岛屿的数量. 你需要返回一个大小为k的数组.

 * @example
 * 		样例 1:
		输入: n = 4, m = 5, A = [[1,1],[0,1],[3,3],[3,4]]
		输出: [1,1,2,2]
		解释: 
		0.  00000
		    00000
		    00000
		    00000
		1.  00000
		    01000
		    00000
		    00000
		2.  01000
		    01000
		    00000
		    00000
		3.  01000
		    01000
		    00000
		    00010
		4.  01000
		    01000
		    00000
		    00011
		样例 2:
		
		输入: n = 3, m = 3, A = [[0,0],[0,1],[2,2],[2,1]]
		输出: [1,1,2,2]
 *
 * @Solution 并查集
 */
public class 岛屿的个数II extends HH {

	// Memory Limit Exceeded
	public List<Integer> numIslands2(int n, int m, Point[] operators) {
        // write your code here
		List<Integer> result = new ArrayList<>();
		if(operators == null || operators.length == 0) {
			return result;
		}
		Map<Integer, Integer> tree = new HashMap<>();
		int[][] map = new int[n][m];
		int[] dx = new int[]{-1, 0, 1, 0};//行
		int[] dy = new int[] {0, 1, 0, -1}; //列
		int cur = 0;
		for(Point point : operators) {
			for(int i=0; i<dx.length; i++) {
				if(point.y + dy[i] < 0 || point.y + dy[i] >= m) {
					continue;
				}
				if(point.x + dx[i] < 0 || point.x + dx[i] >= n) {
					continue;
				}
				if(map[point.x + dx[i]][point.y + dy[i]] != 0) {
					if(map[point.x][point.y] == 0) {
						map[point.x][point.y] = map[point.x + dx[i]][point.y + dy[i]];
					}else {
						int parent = Math.min(map[point.x][point.y], map[point.x + dx[i]][point.y + dy[i]]);
						int child = Math.max(map[point.x][point.y], map[point.x + dx[i]][point.y + dy[i]]);
						while(tree.get(child) != null && tree.get(child) != map[point.x][point.y]) {
							child = tree.get(child);
						}
						while(tree.get(parent) != null && tree.get(parent) != child) {
							parent = tree.get(parent);
						}
						
						if(tree.get(child) == null && tree.get(parent) == null && parent != child) {
							if(child > parent) {
								tree.put(child, parent);
							}else {
								tree.put(parent, child);
							}
						}
					}
				}
			}
			
			if(map[point.x][point.y] == 0) {
				cur ++;
				map[point.x][point.y] = cur;
				tree.put(cur, null);
			}
			
			int numIslands = 0;
			for(int k : tree.keySet()) {
				if(tree.get(k) == null) {
					numIslands ++;
				}
			}
			
			result.add(numIslands);
			
		}
		
		return result;
    }
	
	/**
	 * 上一个方法的优化： 1、二维数组转为一维数组，2. 直接在数组中维护并查集
	 * @param n
	 * @param m
	 * @param operators
	 * @return
	 */
	public List<Integer> numIslands2_better(int n, int m, Point[] operators) {
		List<Integer> result = new ArrayList<>();
		if(operators == null || operators.length == 0) {
			return result;
		}
		int[] island = new int[n * m];
		int[] dx = new int[]{-1, 0, 1, 0};//行
		int[] dy = new int[] {0, 1, 0, -1}; //列
		Arrays.fill(island, -1);
		int current = 0;
		for(int i=0; i<operators.length; i++) {
			for(int j=0; j<dx.length; j++) {
				if(operators[i].x + dx[j] < 0 || operators[i].x + dx[j] >= n) {
					continue;
				}
				if(operators[i].y + dy[j] < 0 || operators[i].y + dy[j] >= m) {
					continue;
				}
				
				if(island[(operators[i].x + dx[j]) * m + operators[i].y + dy[j]] != -1) {
					if(island[operators[i].x * m + operators[i].y] == -1) {
						island[operators[i].x * m + operators[i].y] = findParent(island, (operators[i].x + dx[j]) * m + operators[i].y + dy[j]);
					}else {
						int root1 = findParent(island, operators[i].x * m + operators[i].y);
						int root2 = findParent(island, (operators[i].x + dx[j]) * m + operators[i].y + dy[j]);
						if(root1 != root2) {
							island[root1] = root2;
							//两个root不相等时候才能合并，如果是同一个岛，current不需要减
							current --;
						}
						
					}
				}
				
			}
			
			if(island[operators[i].x * m + operators[i].y] == -1) {
				island[operators[i].x * m + operators[i].y] = operators[i].x * m + operators[i].y;
				current ++;
			}
			
			result.add(current);
		}
		
		return result;
	}
	
	private int findParent(int[] island, int node) {
		int root = node;
		while(root != island[root]) {
			root = island[root];
		}
		while(island[node] != root) {
			int temp = island[node];
			island[node] = root;
			node = temp;
		}
		return root;
	}
	
	private int[] islands;
    private int root(int island) {
        while (islands[island] != island) {
            islands[island] = islands[islands[island]];
            island = islands[island];
        }
        return island;
    }
    private int[] yo = {-1, 1, 0, 0};
    private int[] xo = {0, 0, -1, 1};
    /**
     * 网上解法
     * @param m
     * @param n
     * @param positions
     * @return
     */
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        islands = new int[m*n];
        Arrays.fill(islands, -1);
        int island = 0;
        List<Integer> nums = new ArrayList<>();
        for(int i=0; i<positions.length; i++) {
            int y =positions[i][0];
            int x = positions[i][1];
            int id=y*n+x;
            islands[id] = id;
            island ++;
            for(int j=0; j<4; j++) {
                int ny = y+yo[j];
                int nx = x+xo[j];
                int nid=ny*n+nx;
                if (ny>=0 && ny<m && nx>=0 && nx<n && islands[nid] != -1) {
                    int root = root(nid);
                    if (root != id) {
                        islands[root] = id;
                        island --;
                    }
                }
            }
            nums.add(island);
        }
        return nums;
    }
	
	public static void main(String[] args) {
		int n = 4, m = 5;
		//[0,9],[5,4],[0,12],[6,9],[6,5],[0,4]
		//new Point(0, 9), new Point(5, 4), new Point(0, 12), new Point(6, 9), new Point(6, 5), new Point(0, 4)
		Point[] operators = new Point[] {new Point(1, 1), new Point(0, 1), new Point(3, 3), new Point(3, 4)};
		List<Integer> result = new 岛屿的个数II().numIslands2_better(n, m, operators);
		print(result);
	}
}

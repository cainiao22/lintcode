package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2019年1月3日 下午4:53:54
 * @description 给定一张用二维数组表示的网格地图，其中1表示陆地单元格，0表示水域单元格。网格地图中的单元格视为水平/垂直相连（斜向不相连）。
 *              这个网格地图四周完全被水域包围着，并且其中有且仅有一个岛（定义为一块或多块相连的陆地单元格）。这个岛不包含湖（定义为不和外围水域相连的水域单元格）。
 *              一个地图单元格是边长为1的一个正方形；网格地图是一个矩形，并且它的长和宽不超过100。你要做的是求出这个岛的周长。
 * @example [[0,1,0,0], 
 *           [1,1,1,0], 
 *           [0,1,0,0], 
 *           [1,1,0,0]]
 * 
 *          答案：16
 * 
 *          说明：岛的边界为下图中被标为黄色的边，其周长即为16：
 * 
 * @Solution
 */
public class 岛的周长 {

	public static int islandPerimeter(int[][] grid) {
		int height = grid.length;
		int width = grid[0].length;
		int result = 0;
		for(int i=0; i<height; i++) {
			for(int j=0; j<width; j++) {
				if(grid[i][j] == 1) {
					if(i == 0) {
						result ++;
					}else if(grid[i-1][j] == 0) {
						result ++;
					}
					if(i == height - 1) {
						result ++;
					}else if(grid[i+1][j] == 0) {
						result ++;
					}
					if(j == 0) {
						result ++;
					}else if(grid[i][j-1] == 0) {
						result ++;
					} 
					if(j == width - 1) {
						result ++;
					}else if(grid[i][j+1] == 0) {
						result ++;
					}
				}
			}
		}
		
		return result;
	}
	
	private static boolean valid(int[][] grid, int x, int y) {
		if(x >= 0 && x <= grid.length - 1 && y >= 0 && y <= grid[0].length - 1) {
			return grid[x][y] == 0;
		}
		
		return true;
	}
	
	public static int islandPerimeter2(int[][] grid) {
		int[] dx = new int[] {0, -1, 0, 1};
		int[] dy = new int[] {-1, 0, 1, 0};
		int result = 0;
		
		for(int x = 0; x < grid.length; x ++) {
			for(int y=0; y < grid[0].length; y ++) {
				if(grid[x][y] == 1) {
					for(int k = 0; k < 4; k++) {
						if(valid(grid, x + dx[k], y + dy[k])) {
							result ++;
						}
					}
				}
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		int [][] grid = new int[][] {{1, 1}};
		                             
	  int result = islandPerimeter2(grid);
	  System.out.println(result);
	}

}

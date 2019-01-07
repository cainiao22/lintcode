package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2019��1��3�� ����4:53:54
 * @description ����һ���ö�ά�����ʾ�������ͼ������1��ʾ½�ص�Ԫ��0��ʾˮ��Ԫ�������ͼ�еĵ�Ԫ����Ϊˮƽ/��ֱ������б����������
 *              ��������ͼ������ȫ��ˮ���Χ�ţ������������ҽ���һ����������Ϊһ�����������½�ص�Ԫ�񣩡��������������������Ϊ������Χˮ��������ˮ��Ԫ�񣩡�
 *              һ����ͼ��Ԫ���Ǳ߳�Ϊ1��һ�������Σ������ͼ��һ�����Σ��������ĳ��Ϳ�����100����Ҫ�����������������ܳ���
 * @example [[0,1,0,0], 
 *           [1,1,1,0], 
 *           [0,1,0,0], 
 *           [1,1,0,0]]
 * 
 *          �𰸣�16
 * 
 *          ˵�������ı߽�Ϊ��ͼ�б���Ϊ��ɫ�ıߣ����ܳ���Ϊ16��
 * 
 * @Solution
 */
public class �����ܳ� {

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

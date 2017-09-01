package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年8月29日 下午12:06:28
 * @description 
			给定一个数字三角形，找到从顶部到底部的最小路径和。每一步可以移动到下面一行的相邻数字上。
			注意事项
			
			如果你只用额外空间复杂度O(n)的条件下完成可以获得加分，其中n是数字三角形的总行数。

 * @example 
 * 			比如，给出下列数字三角形：
			[
			     [2],
			    [3,4],
			   [6,5,7],
			  [4,1,8,3]
			]
			
			从顶到底部的最小路径和为11 ( 2 + 3 + 5 + 1 = 11)。
 *
 * @Solution 1、自顶向下，triangle[i][j] = Math.min(triangle[i-1][j-j], triangle[i-1][j]) + triangle[i][j];
 * 			 	因为每次只是下面的数组长度比上面的数组长度加了1，所以不可以除以2，靠，这里坑了
 * 				(被题意的自顶向上限制住了，第一反应会选择它，但是自底向上的方式要优于它）
 * 
 * 			 2、自底向上，这种方式，相比第一种要简洁的多，至少没有j=0和j=length-1两个特殊条件。
 * 				而且没必要在最后做一次比较，直接拿a[0][0]就可以。(有点逆向思维的感觉)
 * 			 
 * 			 3、递归，思想和方法2相同
 * 			
 */
public class 数字三角形 extends HH {

	public int minimumTotal(int[][] triangle) {
        // write your code here
		for(int i=1; i<triangle.length; i++) {
			for(int j=0; j<triangle[i].length; j ++) {
				if(j == 0) {
					triangle[i][j] = triangle[i-1][0] + triangle[i][j];
				}else if (j == triangle[i].length - 1) {
					triangle[i][j] = triangle[i-1][triangle[i-1].length - 1] + triangle[i][j];
				} else {
					triangle[i][j] = Math.min(triangle[i-1][j-1], triangle[i-1][j]) + triangle[i][j];
				}
			}
		}
		int min = Integer.MAX_VALUE;
		for(int i=0; i<triangle[triangle.length - 1].length; i++) {
			min = Math.min(min, triangle[triangle.length - 1][i]);
		}
		
		return min;
    }
	
	public int minimumTotalbottomToUp(int[][] triangle) {
		if(triangle.length == 1) {
			return triangle[0][0];
		}
		int length = triangle.length;
		for(int i=length - 2; i>= 0; i -- ) {
			for(int j=0; j<triangle[i].length; j++) {
				triangle[i][j] = Math.min(triangle[i+1][j], triangle[i+1][j+1]) + triangle[i][j];
			}
		}
		
		return triangle[0][0];
	}
	
	public int minimumTotalbottomToUpDFS(int[][] triangle) {
		if(triangle.length == 0) {
			return -1;
		}
		this.triangle = triangle;
		this.length = triangle.length;
		f = new int[length][length];
		for(int i=0; i<length; i++) {
			for(int j=0; j<length; j++) {
				f[i][j] = Integer.MAX_VALUE;
			}
		}
		
		return search(0, 0);
	}
	int[][] f, triangle;
	int length;
	public int search(int x, int y) {
		if(x >= length) return 0;
		if(f[x][y] != Integer.MAX_VALUE) {
			return f[x][y];
		}
		f[x][y] = Math.min(search(x + 1, y), search(x + 1, y + 1)) + triangle[x][y];
		return f[x][y];
	}
	
	public static void main(String[] args) {
		int[][] triangle = new int[][] {
					{-7},
				   {-2,1},
			      {-5,-5,9},
			     {-4,-5,4,4},
			   {-6,-6,2,-1,-5},
			{3,7,8,-3,7,-9},
			{-9,-1,-9,6,9,0,7},
			{-7,0,-6,-8,7,1,-4,9},
			{-3,2,-6,-9,-7,-6,-9,4,0},
			{-8,-6,-3,-9,-2,-6,7,-5,0,7},
			{-9,-1,-2,4,-2,4,4,-1,2,-5,5},
			{1,1,-6,1,-2,-4,4,-2,6,-6,0,6},
			{-3,-3,-6,-2,-6,-2,7,-9,-5,-7,-5,5,1}};
		int num = new 数字三角形().minimumTotalbottomToUpDFS(triangle);
		System.out.println(num);
		print(triangle);
	}
}

package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2019年2月22日 上午11:46:56
 * @description
 * 		写出一个高效的算法来搜索m×n矩阵中的值，返回这个值出现的次数。
		这个矩阵具有以下特性：
		
		每行中的整数从左到右是排序的。
		每一列的整数从上到下是排序的。
		在每一行或每一列中没有重复的整数。
 * @example
 * 
 * 		例1:
		输入:
		[[3,4]]
		target=3
		输出:1
		例2:
		
		输入:
		    [
		      [1, 3, 5, 7],
		      [2, 4, 7, 8],
		      [3, 5, 9, 10]
		    ]
		    target = 3
		输出:2
		
		要求O(m+n) 时间复杂度和O(1) 额外空间
 *
 * @Solution  这道题有它自己的特点。如果我们观察题目中给的那个例子，我们可以发现有两个位置的数字很有特点，左下角和右上角的数。左下角的18，往上所有的数变小，往右所有数增加，
 * 			     那么我们就可以和目标数相比较，如果目标数大，
 * 			     就往右搜，如果目标数小，就往上搜。这样就可以判断目标数是否存在。当然我们也可以把起始数放在右上角，往左和下搜，停止条件设置正确就行
 */
public class 搜索二维矩阵II {
	
	 public int searchMatrix(int[][] matrix, int target) {
	        // write your code here
		 	int row = matrix.length - 1;
		 	if(row < 0) {
		 		return 0;
		 	}
		 	int col = 0;
		 	int result = 0;
		 	while(row >= 0 && col < matrix[0].length) {
		 		if(matrix[row][col] == target) {
		 			result ++;
		 			row --;
		 		}else if(matrix[row][col] > target) {
		 			row --;
		 		}else {
		 			col ++;
		 		}
		 	}
		 	
		 	return result;
		 	
	    }
	 	
	 	public static void main(String[] args) {
	 		int[][] matrix = new int[][] {{3,4}};
	 		int target = 3;
			new 搜索二维矩阵II().searchMatrix(matrix, target);
		}
}

package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年9月14日 上午10:14:19
 * @description
 * 		
		写出一个高效的算法来搜索 m × n矩阵中的值。
		这个矩阵具有以下特性：
		    每行中的整数从左到右是排序的。
		    每行的第一个数大于上一行的最后一个整数。


 * @example	考虑下列矩阵：

			[
			  [1, 3, 5, 7],
			  [10, 11, 16, 20],
			  [23, 30, 34, 50]
			]
			
			给出 target = 3，返回 true
 *
 * @Solution 1、分两步来，首先获取target可能所在的行。（这里需要进行一次二分查找。），然后在这行再次进行二叉查找，
 * 			 2、将整个二维数组抽象成一个一维数组来用。这个一维数组的长度就是m*n,而找到mid时候。mid/n,代表行，mid%n代表列
 */
public class 搜索二维矩阵 {
	
	public boolean searchMatrix(int[][] matrix, int target) {
		if(matrix.length == 0) {
			return false;
		}
        // write your code here
		int m = matrix.length;
		int n = matrix[0].length;
		int mstart = 0, nstart = 0;
		int mend = m-1, nend = n - 1;
		
		//这里判断0也可以，判断end也可以
		while(mstart < mend) {
			int mid = mstart + (mend - mstart)/2;
			if(matrix[mid][nend] > target) {
				mend = mid;
			}else if(matrix[mid][nend] < target){
				mstart = mid + 1;
			}else {
				return true;
			}
		}
		/*  这个是判断0的，需要在最后兜底
		 while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (matrix[mid][0] == target) {
                return true;
            } else if (matrix[mid][0] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        //兜底工作
        if (matrix[end][0] <= target) {
            row = end;
        } else if (matrix[start][0] <= target) {
            row = start;
        } else {
            return false;
        }
		 */
		
		while(nstart < nend) {
			int mid = nstart + (nend - nstart)/2;
			if(matrix[mstart][mid] == target) {
				return true;
			}else if(matrix[mstart][mid] < target) {
				nstart = mid + 1;
			}else {
				nend = mid - 1;
			}
		}
		
		return false;
    }
	
	public boolean searchMatrix2(int[][] matrix, int target) {
		if(matrix.length == 0) {
			return false;
		}
		int m = matrix.length;
		int n = matrix[0].length;
		int end = m*n - 1;
		int start = 0;
		while(start <= end) {
			int mid = start + (end - start)/2;
			if(matrix[mid/n][mid%n] < target) {
				end = mid - 1;
			}else if(matrix[mid/n][mid%n] == target){
				return true;
			}else {
				start = mid + 1;
			}
		}
		return false;
	}
	
	
	
	public static void main(String[] args) {
		int[][] matrix = new int[][] {{1,3,5,7},{10,11,16,20},{23,30,34,50}};
		new 搜索二维矩阵().searchMatrix(matrix, 7);
	}

}

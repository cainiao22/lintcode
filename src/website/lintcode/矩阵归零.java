package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年9月20日 上午11:54:28
 * @description	
 * 			
		给定一个m×n矩阵，如果一个元素是0，则将其所在行和列全部元素变成0。
		
		需要在原矩阵上完成操作。
		您在真实的面试中是否遇到过这个题？ 
		你是否使用了额外的空间？
		一个直接的解决方案是使用 O(MN) 的额外空间，但这并不是一个好的方案。
		一个简单的改进方案是使用 O(M + N) 的额外空间，但这仍然不是最好的解决方案。
		你能想出一个常数空间的解决方案吗？
 * @example
 *
 * @Solution 1.完全copy，时间复杂度 O(MN) 忽略
 * 			 2.定义两个一维数组M,N，分别存放为0元素的行列。遍历一遍matrix，找出为0的元素，将数组M、N对应位置设为1，
 * 			       再次遍历数组，根据M、N对应位置是否为1判断这个位置是否为0
 *           3、思想与前面相同，只不过需要将M、N两个数组替换为matrix[i][j]为0的那个行列，记下i、j的位置,只不过用完
 *           之后需要将他们设置为0；
 *           
 *           4.首先遍历一遍整个数组，找出最大值max，定义MAX=max+1;
 *             再次遍历数组找到元素为0的将它所在的行和列中的非0元素设置为MAX
 *             最后再遍历一次，将所有MAX全部设为0 
 *           5.来自九章算法。思路和第三个差不多，只不过这次充当数组M和N的变成了matrix的第0行和第0列
 */
public class 矩阵归零 {
	
	public void setZeroes(int[][] matrix) {
        // write your code here
		int M = matrix.length;
		if(M == 0) {
			return;
		}
		int N = matrix[0].length;
		if(N == 0){
		    return;
		}
		
		int[] m = new int[M];
		int[] n = new int[N];
		
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				if(matrix[i][j] == 0) {
					m[i] = 1;
					n[j] = 1;
				}
			}
		}
		
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				if(m[i] == 1 && n[j] == 1) {
					setZero(matrix, i, j);
				}
			}
		}
    }
	
	private void setZero(int[][] matrix, int m, int n) {
		int M = matrix.length;
		int N = matrix[0].length;
		for(int i=0; i<M; i++) {
			matrix[i][n] = 0;
		}
		for(int i=0; i<N; i++) {
			matrix[m][i] = 0;
		}
	}
	
	//todo 第三种方式实现，太麻烦，代写
	public void setZeroes3(int[][] matrix) {
        // write your code here
		int M = matrix.length;
		if(M == 0) {
			return;
		}
		int N = matrix[0].length;
		if(N == 0){
		    return;
		}
		int m = -1, n = -1;
label:	for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				if(matrix[i][j] == 0) {
					if(m == -1 && n == -1)
					
					matrix[i][j] = 0;
					break label;
				}
			}
		}
    }
	
	public void setZeroes4(int[][] matrix) {
		int M = matrix.length;
		if(M == 0) {
			return;
		}
		int N = matrix[0].length;
		if(N == 0){
		    return;
		}
		//有一步获取最大值的操作，这里使用的是Integer.MAX_VALUE代替的
		//正常应该是矩阵的最大值+1
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				if(matrix[i][j] == 0) {
					setMAX(matrix, i, j);
				}
			}
		}
		
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				if(matrix[i][j] == Integer.MAX_VALUE) {
					matrix[i][j] = 0;
				}
			}
		}
	}
	
	private void setMAX(int[][] matrix, int m, int n) {
		int M = matrix.length;
		int N = matrix[0].length;
		for(int i=0; i<M; i++) {
			if(matrix[i][n] != 0) {
			matrix[i][n] = Integer.MAX_VALUE;
			}
		}
		for(int i=0; i<N; i++) {
			if(matrix[m][i] != 0) {
				matrix[m][i] = Integer.MAX_VALUE;
			}
		}
	}
	
	public void setZeroes5(int[][] matrix) {
		int M = matrix.length;
		if(M == 0) {
			return;
		}
		int N = matrix[0].length;
		if(N == 0){
		    return;
		}
		boolean empty_row0 = false;
        boolean empty_col0 = false;
		for(int i=0; i<M; i++) {
			if(matrix[i][0] == 0) {
				empty_col0 = true;
				break;
			}
		}
		
		for(int i=0; i<N; i++) {
			if(matrix[0][i] == 0) {
				empty_row0 = true;
				break;
			}
		}
		
		for(int i=1; i<M; i++) {
			for(int j=1; j<N; j++) {
				if(matrix[i][j] == 0) {
					matrix[i][0] = 0;
					matrix[0][j] = 0;
				}
			}
		}
		
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				if(matrix[i][0] == 0 || matrix[0][j] == 0) {
					matrix[i][j] = 0;
				}
			}
		}
		
		if(empty_row0){
            for(int i = 0; i < M; i++){
                matrix[0][i] = 0;
            }           
        }
        
        if(empty_col0){
            for(int i = 0; i < N; i++){
                matrix[i][0] = 0;
            }           
        }
	}

}

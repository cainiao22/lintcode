package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年9月22日 下午7:58:14
 * @description 给一个正整数 n, 找到若干个完全平方数(比如1, 4, 9, ... )使得他们的和等于 n。你需要让平方数的个数最少。
 * @example	给出 n = 12, 返回 3 因为 12 = 4 + 4 + 4。
			给出 n = 13, 返回 2 因为 13 = 4 + 9。
 *
 * @Solution
 */

//todo 九章算法解法
public class 完美平方 {
	
	   public int numSquares(int n) {
	        // write your code here
		   int[] matrix = new int[n+1];
		   matrix[1] = 1;
		   for(int i=2; i<= n; i++) {
			   if(isSquare(i)) {
				   matrix[i] = 1;
			   }else {
				   int min = Integer.MAX_VALUE;
				   for(int j=1; j<=i/2; j++) {
					   min = Math.min(min, matrix[j] + matrix[i-j]);
				   }
				   matrix[i] = min;
			   }
		   }
		   
		   return matrix[n];
	    }
	   
	   boolean isSquare(int num) {
		   for(int i=num/2; i>=1; i--) {
			   if(i*i == num) {
				   return true;
			   }
		   }
		   
		   return false;
	   }
	   
	   public static void main(String[] args) {
		int result = new 完美平方().numSquares(13);
		System.out.println(result);
	}

}

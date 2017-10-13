package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年10月13日 上午10:38:26
 * @description
 * 		在n个物品中挑选若干物品装入背包，最多能装多满？假设背包的大小为m，每个物品的大小为A[i]
 * @example
 * 		如果有4个物品[2, 3, 5, 7]

		如果背包的大小为11，可以选择[2, 3, 5]装入背包，最多可以装满10的空间。
		
		如果背包的大小为12，可以选择[2, 3, 7]装入背包，最多可以装满12的空间。
		
		函数需要返回最多能装满的空间大小。
 *
 * @Solution
 */
public class 背包问题 extends HH{
	
	public int backPack(int m, int[] A) {
		//前i个商品放入容量为j的背包中的最大价值
       int[][] value = new int[A.length + 1][m + 1];
       //包的容量为0
       for(int j=0;j<m+1; j++) {
    	   value[0][j] = 0;
       }
       //一个商品都没有
       for(int i=0; i<A.length+1; i++) {
    	   value[i][0] = 0;
       }
       
       for(int i=1; i<A.length + 1; i++) {
    	   for(int j=1; j<m+1; j++) {
    		   if(j < A[i-1]) {
    			   value[i][j] = value[i-1][j];
    		   }else {
    			   //拿第i个商品，总价就是value[i-1][j-A[i-1]] + A[i-1]， 不拿就是value[i-1][j]
    			   value[i][j] = Math.max(value[i-1][j], value[i-1][j-A[i-1]] + A[i-1]);
    		   }
    	   }
       }
       
       return value[A.length][m];
    }
	
	public static void main(String[] args) {
		int m = 12;
		int[] A = new int[] {2, 3, 5, 7};
		print(new 背包问题().backPack(m, A));
	}

}

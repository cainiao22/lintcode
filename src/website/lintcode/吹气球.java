package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年12月6日 下午5:28:04
 * @description
 * 		有n个气球，编号为0到n-1，每个气球都有一个分数，存在nums数组中。每次吹气球i可以得到的分数为 nums[left] * nums[i] * nums[right]，
 * 		left和right分别表示i气球相邻的两个气球。当i气球被吹爆后，其左右两气球即为相邻。要求吹爆所有气球，得到最多的分数
 * @example
 * 		给出 [4, 1, 5, 10]
		返回 270
		
		nums = [4, 1, 5, 10] burst 1, 得分 4 * 1 * 5 = 20
		nums = [4, 5, 10]    burst 5, 得分 4 * 5 * 10 = 200 
		nums = [4, 10]       burst 4, 得分 1 * 4 * 10 = 40
		nums = [10]          burst 10, 得分 1 * 10 * 1 = 10
		
		总共的分数为 20 + 200 + 40 + 10 = 270
 *
 * @Solution
 * 		首先按照题意，我们可以先在nums数组两端各加一个1，方便计算。
		dp[i , j]表示吹爆第i个到第j个气球能获得的最多的分数。对于第i 到 第j个气球中，可以首先吹爆任意一个气球k（i<=k<=j），
		吹爆第k个气球时，能获得的分数为nums[k]* （此刻k的前一个数）* （此刻k的后一个数），但是由于并不知道之前k左边和右边的气球有没有被吹爆，
		所以不能确定此刻左右的数。换一种思路，既然可以首先吹爆任意一个气球k，那么也可以选择最后吹爆任意一个气球k。此时，k的左右数字就确定了，分别是nums[i-1]和nums[j+1]。

		那么获得的分数就是nums[i-1]* nums[k]* nums[j+1]，这是吹爆k获得的分数，再加上吹爆k之前获得的最大分数dp[i , k-1]+dp[k+1 , j]
		（即在k之前吹爆的：k左边第i个到第k-1个，k右边第k+1个到第j个）。
		综上，dp[i , j]=max(nums[i-1]* nums[k]* nums[j+1] + dp[i , k-1]+ dp[k+1 , j])，（对于所有的 k : i<=k<=j）.
 */
public class 吹气球 extends HH {
	
	public int maxCoins(int[] nums) {
        // write your code here
		int n = nums.length;
		int[] p = new int[nums.length + 2];
		n += 2;
		p[0] = p[n-1] = 1;
		int[][] f = new int[n][n];
		for(int i=0; i<nums.length; i++) {
			p[i+1] = nums[i];
			f[i+1][i+1] = nums[i];//len=0的情况
		}
		//下面的需要从1开始
		for(int len=1; len<p.length; len++) {
			for(int i=1; i<p.length - len; i++) {
				int j = i + len - 1;
				for(int k = i; k<=j; k++) {//因为k是最后吹爆的，所以i~j的气球已经没有了，剩下的只是i-1和j+1
					f[i][j] = Math.max(f[i][j], f[i][k-1] + f[k+1][j] + p[i-1]*p[k]*p[j+1]);
				}
			}
		}
		
		return f[1][n-2];
    }
	
	/**
	 * 递归方式求解
	 * @param nums
	 * @return
	 */
	public int maxCoinsDFS(int[] nums) {
		int n = nums.length;
		int[] p = new int[nums.length + 2];
		n += 2;
		p[0] = p[n-1] = 1;
		int[][] f = new int[n][n];
		boolean[][] visited = new boolean[n][n];
		for(int i=0; i<nums.length; i++) {
			p[i+1] = nums[i];
		}
		return search(p, f, visited, 1, nums.length);
	}
	
	private int search(int[] p, int[][] f, boolean[][] visited, int left, int right) {
		if(visited[left][right]) {
			return f[left][right];
		}
		for(int k = left; k<=right; k++) {
			int l = search(p, f, visited, left, k-1);
			int r = search(p, f, visited, k+1, right);
			f[left][right] = Math.max(f[left][right], l + r + p[k]*p[left-1]*p[right+1]);
		}
		visited[left][right] = true;
		return f[left][right];
	}
	
	public static void main(String[] args) {
		int[] nums = new int[] {4, 1, 5, 10};
		print(new 吹气球().maxCoinsDFS(nums));
	}

}

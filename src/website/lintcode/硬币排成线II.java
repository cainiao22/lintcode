package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年10月11日 上午11:50:13
 * @description
 * 		
		有 n 个不同价值的硬币排成一条线。两个参赛者轮流从左边依次拿走 1 或 2 个硬币，直到没有硬币为止。
		计算两个人分别拿到的硬币总价值，价值高的人获胜。
		请判定 第一个玩家 是输还是赢？

 * @example
 * 
 * 		给定数组 A = [1,2,2], 返回 true.
		给定数组 A = [1,2,4], 返回 false
 *
 * @Solution min-max博弈+记忆化，max：helper返回。min:suffix-helper
 */
public class 硬币排成线II extends HH {
	
	private int[] global;
	
	public boolean firstWillWin(int[] values) {
        // write your code here
		//后缀和
		int[] suffix = new int[values.length];
		global = new int[values.length];
		suffix[values.length-1] = values[values.length-1];
		for(int i=values.length-2; i>= 0; i--) {
			suffix[i] = values[i] + suffix[i+1];
		}
		
		int max = helper(values, 0, suffix);
		return max > suffix[0] - max;
		
    }
	
	public int helper(int[] values, int index, int[] suffix) {
		if(values.length - index <= 2) {
			return suffix[index];
		}
		if(global[index] != 0) {
			return global[index];
		}
		int max = Math.max(values[index] + suffix[index+1] - helper(values, index+1, suffix), 
				values[index] + values[index + 1] + suffix[index+2] - helper(values, index+2, suffix));
		
		global[index] = max;
		
		return max;
	}
	
	/**
	 * 管他最后一个是谁拿，不管谁拿，他必须保证前面的那位得到的最小，以此倒推，推到第0个，因为第0的是我拿
	 * @param values
	 * @return
	 */
	public boolean firstWillWinDP(int[] values) {
		if(values.length <= 2) {
			return true;
		}
		int[] suffix = new int[values.length];
		suffix[values.length-1] = values[values.length-1];
		for(int i=values.length-2; i>= 0; i--) {
			suffix[i] = values[i] + suffix[i+1];
		}
		int[] max = new int[values.length];
		max[values.length-1] = suffix[values.length-1];
		max[values.length-2] = suffix[values.length-2];
		for(int i=values.length-3; i>=0; i--) {
			max[i] = Math.max(values[i] + (suffix[i+1] - max[i+1]),
					values[i]+values[i+1] + suffix[i+2] - max[i+2]);
		}
		
		return max[0] > suffix[0]/2;
		
	}
	
	public static void main(String[] args) {
		int[] values = new int[] {16,27,25,23,25,16,12,9,1,2,7,20,19,23,16,0,6,22,16,11,8,27,9,2,20,2,13,7,25,29,12,12,18,29,27,13,16,1,22,9,3,21,29,14,7,8,14,5,0,23,16,1,20};
		System.out.println(new 硬币排成线II().firstWillWinDP(new int[] {10000}));
	}

}

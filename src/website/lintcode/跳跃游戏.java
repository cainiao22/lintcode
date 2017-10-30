package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年10月30日 下午3:38:51
 * @description
 * 		
		给出一个非负整数数组，你最初定位在数组的第一个位置。　　　
		
		数组中的每个元素代表你在那个位置可以跳跃的最大长度。　　　　
		
		判断你是否能到达数组的最后一个位置。
		注意事项
		
		这个问题有两个方法，一个是贪心和 动态规划。
		
		贪心方法时间复杂度为O（N）。
		
		动态规划方法的时间复杂度为为O（n^2）。
		
		我们手动设置小型数据集，使大家可以通过测试的两种方式。这仅仅是为了让大家学会如何使用动态规划的方式解决此问题。如果您用动态规划的方式完成它，你可以尝试贪心法，以使其再次通过一次。

 * @example
 * 		A = [2,3,1,1,4]，返回 true.
		A = [3,2,1,0,4]，返回 false.
 *
 * @Solution
 */
public class 跳跃游戏 extends HH {
	
	//这个和dp差不多
	public boolean canJump(int[] A) {
        // write your code here
		int [] jump = new int[A.length];
		jump[0] = A[0];
		for(int i=1; i<A.length-1; i++) {
			if(jump[i-1] >= i && A[i] + i > jump[i-1]) {
				jump[i] = A[i] + i;
			}else {
				jump[i] = jump[i-1];
			}
		}
		return jump[A.length-2] >= A.length-1;
    }
	
	//贪心
	public boolean canJumpGredy(int[] A) {
		//最开始的时候最远可以跳多远
		int farthest = A[0];
		for(int i=1; i<A.length; i++) {
			if(farthest >= i && i+A[i] > farthest) {
				farthest = i + A[i];
			}
		}
		return farthest >= A.length-1;
	}
	
	//动态规划
	public boolean canJumpDP(int[] A) {
		boolean [] jump = new boolean[A.length];
		jump[0] = true;
		for(int i=1; i<A.length; i++) {
			for(int j=0; j<i; j++) {
				if(jump[j] && j + A[j] >= i){
					jump[i] = true;
					break;
				}
			}
		}
		return jump[A.length-1];
	}

}

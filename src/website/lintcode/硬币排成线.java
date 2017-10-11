package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年10月11日 上午10:44:11
 * @description
 * 			
		有 n 个硬币排成一条线。两个参赛者轮流从右边依次拿走 1 或 2 个硬币，直到没有硬币为止。拿到最后一枚硬币的人获胜。
		
		请判定 第一个玩家 是输还是赢？

 * @example
 * 		n = 1, 返回 true.

		n = 2, 返回 true.
		
		n = 3, 返回 false.
		
		n = 4, 返回 true.
		
		n = 5, 返回 true
 *
 * @Solution
 */
public class 硬币排成线 extends HH{
	
	public boolean firstWillWin2(int n) {
        // write your code here
		
		return n%3 != 0;
    }
	
	public boolean firstWillWin(int n) {
        // write your code here
		
		if(n <= 2) {
			return true;
		}
		return !firstWillWin(n-1) || !firstWillWin(n-2);
    }
	
	public boolean firstWillWinDp(int n) {
        // write your code here
		
		if(n <= 2) {
			return true;
		}
		boolean[] memory = new boolean[n+1];
		memory[1] = memory[2] = true;
		for(int i=3; i<= n; i++) {
			memory[i] = !memory[i-1] || !memory[i-2];
		}
		return memory[n];
    }
	
	public static void main(String[] args) {
		print(new 硬币排成线().firstWillWinDp(5));
	}

}

package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年11月30日 下午2:38:36
 * @description
 * 		
		给出一个非负整数数组，你最初定位在数组的第一个位置。
		数组中的每个元素代表你在那个位置可以跳跃的最大长度。　　　
		你的目标是使用最少的跳跃次数到达数组的最后一个位置。

 * @example
 * 		给出数组A = [2,3,1,1,4]，最少到达数组最后一个位置的跳跃次数是2(从数组下标0跳一步到数组下标1，然后跳3步到数组的最后一个位置，一共跳跃2次)
 *
 * @Solution
 */
public class 跳跃游戏II {
	
	//非贪心
	public int jump(int[] A) {
        // write your code here
		int[] steps = new int[A.length];
		for(int i=1; i<steps.length; i++) {
			steps[i] = Integer.MAX_VALUE;
		}
		for(int i=1; i<A.length; i++) {
			for(int j=0; j<i; j++) {
				if(j + A[j] >= i) {
					steps[i] = Integer.min(steps[i], steps[j] + 1);
				}
			}
		}
		return steps[A.length - 1];
		
    }
	
	/**
	 * 贪心算法
	 * @param A
	 * @return
	 */
	public int jump2FromJiuzhang(int[] A) {
        if (A == null || A.length == 0) {
            return -1;
        }
        int start = 0, end = 0, jumps = 0;
        while (end < A.length - 1) {
            jumps++;
            int farthest = end;
            for (int i = start; i <= end; i++) {
                if (A[i] + i > farthest) {
                    farthest = A[i] + i;
                }
            }
            start = end + 1;
            end = farthest;
        }
        return jumps;
    }

}

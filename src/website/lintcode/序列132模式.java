package website.lintcode;

import java.util.Stack;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

/**
 * 
 * @author yanpf
 * @date 2017年11月7日 下午3:30:15
 * @description
 * 		给你一个 n 个整数的序列 a1,a2,...,an，一个 132 模式是对于一个子串 ai,aj,ak，满足 i < j < k 和 ai < ak < aj。
 * 		设计一个算法来检查输入的这 n 个整数的序列中是否存在132模式。
		n 会小于 20,000
 * @example
 * 		给你序列 nums = [1,2,3,4]
		返回 False//没有132模式在这个序列中。
		给你序列 nums = [3,1,4,2]
		返回 True//存在132模式：[1,4,2]。
 *
 * @Solution
 */
public class 序列132模式 extends HH {
	
	/**
	 * 	这道题给我们了一个数组，让我们找到132的模式，就是第一个数小于第二第三个数，
	 * 	且第三个数小于第二个数。那么我们就按顺序来找这三个数，首先我们来找第一个数，
	 * 	这个数需要最小，那么我们如果发现当前数字大于等于后面一个数字，我们就往下继续遍历，
	 * 	直到当前数字小于下一个数字停止。然后我们找第二个数字，这个数字需要最大，
	 * 	那么如果我们发现当前数字小于等于下一个数字就继续遍历，直到当前数字大雨下一个数字停止。
	 * 	最后就找第三个数字，我们验证这个数字是否在之前两个数字的中间，如果没有找到，
	 * 	我们就从第二个数字的后面一个位置继续开始重新找这三个数字
	 * 
	 * @param nums
	 * @return
	 */
	public boolean find132pattern1(int[] nums) {
		int i = 0;
		while(i<nums.length) {
			//首先找第一个，保证这个数小于它后面的那个数
			while(i<nums.length-1 && nums[i] >= nums[i+1]) i++;
			//走到这里代表找到了。然后找aj和ak，保证aj>ak
			int j=i+1;
			while(j < nums.length-1 && nums[j] <= nums[j+1]) j++;
			//走到这里表示找到了符合ai < aj && ak < aj的数。省下的就是保证ak>ai
			int k = j + 1;
			while(k < nums.length) {
				if(nums[k] <nums[j] && nums[k] > nums[i]) {
					return true;
				}
				k++;
			}
			//走到这里表示没有符合条件的(i一直在加，它以前的数都是 nums[i] >= nums[i+1]这样的。肯定不符合
			//如果没有符合要求的那说明两种情况：1、nums[k] <= nums[i].因为i~j中nums[i]是最小值。nums[j]是最大值。
			//既然nums[k]全部小于nums[i]所以i~j中的数任何一个也就大于nums[k]。自然这里的数都没办法符合。
			//2、nums[k] >nums[j],很明显。这成了123模式。不用玩了。直接到j+1上来吧
			i = j+1;
		}
		return false;
		
	}
	
	/**
	 * 堆栈方式。将132模式中的数字分别设置为first、Second、third.定义外部变量third.stack中存放的是Second值。
	 * 从后向前遍历。将满足second>third的值入栈。同时去除当前遍历过的最大栈顶元素赋值给third(保证在满足Second>third)的前提
	 * 下。third越大越好，因为这样更容易找到满足first<third的first。
	 * @param nums
	 * @return
	 */
	public boolean find132pattern2(int[] nums) {
		Stack<Integer> stack = new Stack<>();
		int third = Integer.MIN_VALUE; //保证初始时候没有数据可以比third小
		for(int j=nums.length-1; j>=0; j--) {
			if(nums[j] < third) return true;//在保证初始时候没有数据可以比third小的前提下。如果成立表示存在132模式
			//这里相当于找最大值
			while(!stack.isEmpty() && nums[j] > stack.peek()) {
				third = stack.pop();
			}
			stack.push(nums[j]);
		}
		return false;
	}
	
	public boolean find132pattern3(int[] nums) {
        // write your code here
		if(nums.length < 3) {
			return false;
		}
		int[] mins = new int[nums.length];
		mins[0] = nums[0];
		for(int i=1; i<nums.length; i++) {
			mins[i] = Math.min(mins[i-1], nums[i-1]);
		}
		
		Stack<Integer> stack = new Stack<>();
		int max = Integer.MIN_VALUE;
		//从后往前，找i~length之间的最大值。如果这个最大值大于前面i个数的最小值。那么相当于有这个模式
		for(int i=nums.length-1; i>=0; i--) {
			if(nums[i] > mins[i]) {
				while(!stack.isEmpty() && nums[i] > stack.peek()) {
					max = stack.pop();
				}
				if(max > mins[i]) {
					return true;
				}
				stack.push(nums[i]);
			}
		}
		return false;
		
    }

}

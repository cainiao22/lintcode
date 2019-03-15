package website.lintcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Stack;

/**
 * 
 * @author yanpf
 * @date 2017年12月14日 下午3:01:36
 * @description
 * 		
		一只青蛙正要过河，这条河分成了 x 个单位，每个单位可能存在石头，青蛙可以跳到石头上，但它不能跳进水里。
		按照顺序给出石头所在的位置，判断青蛙能否到达最后一块石头所在的位置。刚开始时青蛙在第一块石头上，假设青蛙第一次跳只能跳一个单位的长度。
		如果青蛙最后一个跳 k 个单位，那么它下一次只能跳 k - 1 ，k 或者 k + 1 个单位。注意青蛙只能向前跳。
		注意事项
		
		石头的个数 >= 2并且 <= 1100。
		每块石头的位置是一个非负数并且 < 2^31。
		第一块石头的位置总是 0.

 * @example
 * 		给出石头的位置为 [0,1,3,5,6,8,12,17]

		总共8块石头。
		第一块石头在 0 位置，第二块石头在 1 位置，第三块石头在 3 位置等等......
		最后一块石头在 17 位置。
		
		返回 true。青蛙可以通过跳 1 格到第二块石头，跳 2 格到第三块石头，跳 2 格到第四块石头，跳 3 格到第六块石头，跳 4 格到第七块石头，最后跳 5 格到第八块石头。
		
		给出石头的位置为 `[0,1,2,3,4,8,9,11]`
		返回 false。青蛙没有办法跳到最后一块石头因为第五块石头跟第六块石头的距离太大了。
 *
 * @Solution 1、dfs
 */
public class 青蛙跳 extends HH {
	
	/**
	 * 递归方式
	 * @param stones
	 * @return
	 */
	public boolean canCross(int[] stones) {
        // write your code here
		if(stones.length == 1) {
			return stones[0] == 0;
		}
		if(stones[1] != 1) {
			return false;
		}
		return cross(stones, 1, 1);
    }
	
	//也可以用boolean数组来表示。memary[stones.length][stones.length] 横坐标是stoneIndex，纵坐标是step
	private Map<String, Boolean> cache = new HashMap<>();
	
	boolean cross(int[] stones, int stoneIndex, int steps) {
		if(stoneIndex == stones.length - 1) {
			return true;
		}
		int curPos = stones[stoneIndex];
		for(int step = steps + 1; step > 0 && step > steps - 2; step --) {
			if(cache.get(curPos + "_" + step) != null) {
				return false;
			}
			for(int i=stoneIndex + 1; i < stones.length; i++) {
				int nextPos = curPos + step;
				if(nextPos < stones[i]) {
					break;
				}
				if(nextPos == stones[i]) {
					boolean flag = cross(stones, i, step);
					if(flag) {
						return true;
					}
				}
			}
			cache.put(curPos + "_" + step, false);
		}
		return false;
	}
	
	/**
	 * 用堆栈实现
	 * @param stones
	 * @return
	 */
	
	public boolean canCross2(int[] stones) {
		Stack<Position> stack = new Stack<>();
		// write your code here
		if(stones.length == 1) {
			return stones[0] == 0;
		}
		if(stones[1] != 1) {
			return false;
		}
		stack.push(new Position(1, 1));
		boolean[][] cache2 = new boolean[stones.length][stones.length];
		while(!stack.isEmpty()) {
			Position pos = stack.pop();
			if(pos.stoneIndex == stones.length - 1) {
				return true;
			}
			if(cache2[pos.stoneIndex][pos.step]) {
				continue;
			}
			int curPos = stones[pos.stoneIndex];
			for(int step = pos.step + 1; step > 0 && step > pos.step - 2; step --) {
				for(int i=pos.stoneIndex + 1; i < stones.length; i++) {
					int nextPos = curPos + step;
					if(nextPos < stones[i]) {
						break;
					}
					if(nextPos == stones[i]) {
						stack.push(new Position(i, step));
					}
				}
			}
			//走到这里不知道是否找到了最终结果，只是告诉它，这种情况我已经走过了，而且放入stack中去了，没必要再放一遍
			cache2[pos.stoneIndex][pos.step] = true;
			
		}
		return false;
	}
	
	/**
	 * 地推方式
	 * @param stones
	 * @return
	 */
	public boolean canCross3(int[] stones) {
		Map<Integer, HashSet<Integer>> map = new HashMap<>();
		for(int i=0; i<stones.length; i++) {
			map.put(stones[i], new HashSet<>());
		}
		//初始时候就在0的位置，所以第0个石头是从走0步过来的，因此到第一个石头肯定只能走一步。（0-1,0,0+1）三个去除前两个无效的
		map.get(0).add(0);
		for(int i=0; i<stones.length - 1; i++) {
			for(int k : map.get(stones[i])) {
				for(int j=k+1; j > 0 && j>=k-1; j--) {
					if(map.containsKey(stones[i] + j)) {
						map.get(stones[i] + j).add(j);
					}
				}
			}
		}
		return !map.get(stones[stones.length - 1]).isEmpty();
	}

	
	static class Position {
		public int stoneIndex;
		public int step;
		
		public Position(int stoneIndex, int step) {
			super();
			this.stoneIndex = stoneIndex;
			this.step = step;
		}
	}
	
	public static void main(String[] args) {
		int[] stones = new int[] {0,1,3,5,6,8,12,17};
		print(new 青蛙跳().canCross3(stones));
	}

}

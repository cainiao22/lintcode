package website.lintcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author yanpf
 * @date 2019年4月3日 下午5:46:17
 * @description
 * 		一个花园有N个位置。每个位置上有一朵花。这N朵花会在N天内逐一盛开。每天都一定会有并且只有一朵花盛开，从这天起，这朵花将一直处于盛开的状态。

		给定一个由数字1到N组成的数组flowers。数组中的每个数字表示那一天将会盛开的花的位置。
		
		例如，flowers[i] = x表示在位置x上的花会在第i天盛开，其中i和x都在1到N的范围内。
		
		给一个参数 m，找到同时有 m 组花开放的最后一天（每一组至少有 k 盆花）。
		
		如果不存在这样一天，那么返回-1。
 * @example
 * 		让大家少走弯路,我觉得lintcode的题目应该自己审核一下吧?
		开花是从数组0开始, 每天开一朵, 位置就是数组上的数字.
		然后找m组, 每组至少有k朵花的最后那一天.
		直接上例子
		[1,2,3,6,5,4,9,8,7]
		2
		2
		
		第一天开花后就是:
		F 0 0 0 0 0 0 0 0
		第二天开花后就是:
		F F 0 0 0 0 0 0 0
		3:
		F F F 0 0 0 0 0 0
		4:
		F F F 0 0 F 0 0 0
		现在就有两组花, 但一组是3朵, 一组是一朵, 不符合参数要求, 因为现在的例子是k=2
		5:
		F F F 0 F F 0 0 0
		到了第五天就是符合要求啦, 但是不确定是不是最后一天, 先保存下来, 接着往下走.
		6:
		F F F F F F 0 0 0
		变成一组了
		7:
		F F F F F F 0 0 F
		8:
		F F F F F F 0 F F
		好了,第八天也符合要求,可以替换掉原来的第五天
		9:
		F F F F F F F F F
		所以答案是8
 *
 * @Solution
 */
public class 开花问题 {
	
	public int flowerProblem(int[] flowers, int k, int m) {
		// Write your code here
		int[] pos = new int[flowers.length];
		int result = -1;
		for(int i=0; i<flowers.length; i++) {
			pos[flowers[i] - 1] = 1;
			int m2 = 0;
			int count = 0;
			for(int j=0; j<pos.length; j++) {
				if(pos[j] == 0) {
					if(count >= k) {
						m2 ++;
					}
					count = 0;
				}else {
					count += 1;
				}
			}
			if(count >= k) {
				m2 ++;
			}
			if(m2 >= m) {
				result = i;
			}
			
		}
		
		return result == -1 ? -1 : result + 1;

	}
	
	/**
	 * 既然是最后一天 那就倒着来
	 * @param flowers
	 * @param k
	 * @param m
	 * @return
	 */
	public int flowerProblem2(int[] flowers, int k, int m) {
		// Write your code here 0为已开花，
		if(m == 1 && flowers.length >= k) {
			return flowers.length;
		}
		int[] pos = new int[flowers.length];
		for(int i=flowers.length - 1; i >= 0; i--) {
			pos[flowers[i] - 1] = 1;
			int m2 = 0;
			int count = 0;
			for(int j=0; j<pos.length; j++) {
				if(pos[j] == 1) {
					if(count >= k) {
						m2 ++;
					}
					count = 0;
				}else {
					count += 1;
				}
			}
			if(count >= k) {
				m2 ++;
			}
			if(m2 >= m) {
				return i;
			}
			
		}
		
		return -1;

	}
	
	/**
	 * 并查集
	 * @param flowers
	 * @param k
	 * @param m
	 * @return
	 */
	public int flowerProblem3(int[] flowers, int k, int m) {
		int[] pos = new int[flowers.length];
		for(int i=0; i<pos.length; i++) {
			pos[i] = -1;
		}
		int[] count = new int[flowers.length];
		Set<Integer> roots = new HashSet<>();
		int result = -1;
		for(int i=0; i<flowers.length; i++) {
			int flowerPos = flowers[i] - 1;
			pos[flowerPos] = -1;
			count[flowerPos] = 1;
			int pre = flowerPos - 1;
			int post = flowerPos + 1;
			if(pre >= 0 && count[pre] > 0) {
				pos[flowerPos] = pre;
				while(pre != -1) {
					count[pre] += 1;
					pre = pos[pre];
				}
			}
			if(post < flowers.length && count[post] > 0) {
				if(count[post] >= k) {
					roots.remove(new Integer(post));
				}
				pos[post] = flowerPos;
				pre = flowerPos;
				while(pre != -1) {
					count[pre] += count[post];
					pre = pos[pre];
				}
			}
			while(pos[flowerPos] != -1) {
				flowerPos = pos[flowerPos];
			}
			if(count[flowerPos] >=k) {
				roots.add(flowerPos);
			}
			if(roots.size() >= m) {
				result = i + 1;
			}
		}
		
		return result;
	}
	
	/**
	 * 并查集拆分
	 * @param flowers
	 * @param k
	 * @param m
	 * @return
	 */
	//TODO 有问题 提交不通过
	public int flowerProblem4(int[] flowers, int k, int m) {
		if(m == 1 && flowers.length >= k) {
			return flowers.length;
		}
		int[] count = new int[flowers.length];
		int[] pos = new int[flowers.length];
		int m2 = 1;
		for(int i=0; i<flowers.length; i++) {
			count[i] = flowers.length - i;
			pos[i] = i - 1;
		}
		for(int i=flowers.length - 1; i>=0; i--) {
			int flowerPos = flowers[i] - 1;
			if(count[flowerPos] >= k && pos[flowerPos] == -1) {
				m2 --;
			}
			int pre = flowerPos - 1;
			int post = flowerPos + 1;
			while(pre >= 0 && count[pre] != 0) {
				count[pre] -= count[flowerPos];
				if(pos[pre] == -1 && count[pre] < k) {
					m2 --;
				}
				pre = pos[pre];
			}
			count[flowerPos] = 0;
			if(post < flowers.length) {
				pos[post] = -1;
				if(count[post] >= k) {
					m2 ++;
				}
			}
			
			if(m2 >= m) {
				return i;
			}
		}
		
		return -1;
	}
	
	public static void main(String[] args) {
		int[] flowers = new int[] {3,5,10,1,7,6,4,2,8,9};
		int k = 2;
		int m = 2;
		int result = new 开花问题().flowerProblem4(flowers, k, m);
		System.out.println(result);
	}
}

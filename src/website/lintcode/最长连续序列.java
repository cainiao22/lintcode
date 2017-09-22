package website.lintcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author yanpf
 * @date 2017年9月18日 下午6:25:50
 * @description 给定一个未排序的整数数组，找出最长连续序列的长度。要求你的算法复杂度为O(n)
 * 
 * @example 给出数组[100, 4, 200, 1, 3, 2]，这个最长的连续序列是 [1, 2, 3, 4]，返回所求长度 4
 *
 * @Solution 采用hashSet,分别从中间向两边扩展，如果hashset中包含了这个数，那么将这个数删除，这样才可以严格保证对每一个元素
 * 			   的操作是常数，之所以可以删除，是因为经过这种操作后，它的上面一个数和下面一个数再次操作其实就是重复性的了。因为这是
 * 			   一个全局性的查找
 */
public class 最长连续序列 {
	
	public static int longestConsecutive(int[] num) {
        // write your code here
		Set<Integer> hashSet = new HashSet<>();
		for(int i=0; i<num.length; i++) {
			hashSet.add(num[i]);
		}
		int max = 1;
		for(int i=0; i<num.length; i++) {
			int down = num[i] - 1;
			while(hashSet.contains(down)) {
				hashSet.remove(down);
				down --;
			}
			int up = num[i] + 1;
			while(hashSet.contains(up)) {
				hashSet.remove(up);
				up ++;
			}
			
			max = Math.max(max, up - down - 1);
		}
		
		return max;
		
    }
	
	public static void main(String[] args) {
		int[] nums = new int[] {0,0,-1};
		int result = longestConsecutive(nums);
		System.out.println(result);
	}
}

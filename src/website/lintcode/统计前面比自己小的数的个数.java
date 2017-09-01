package website.lintcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author yanpf
 * @date 2017年8月22日 下午4:45:52
 * @description 给定一个整数数组（下标由 0 到 n-1， n 表示数组的规模，取值范围由 0 到10000）。
 * 				对于数组中的每个 ai 元素，请计算 ai 前的数中比它小的元素的数量
 * 
 * @example 对于数组[1,2,7,8,5] ，返回 [0,1,2,3,2]
 *
 * @Solution 1、因为数组规模是给定的，可以构建一颗线段树，区间的取值范围就是0-10000，
 * 			        然后区间树的内部数据就是0到这个区间的数字个数，遍历整个数组，将每个素的值更新到这个线段树中，并query
 * 			        查询的时候其实就是在线段树中查找0-a[i]-1的范围的数据数量
 * 			        但是它是边走边更新的，所以全部数组更新完毕后，再从新遍历线段树的时候，结果就和原来不一样了
 * 			 
 * 			 2、利用快排思想，new一个一模一样的数组。每加入一个元素就以当前元素为基准做一次快排，假设第n个元素已经找到自己的位置k。
 * 			 	当拿到第n+1个元素的时候。首先与第n个元素做比较，如果a[n+1] > a[n] 那么就在k~n区间做快排，得出的结果就是k+x,
 * 				否则就在0-k区间做快排，得到的结果为k-x。
 * 
 */
public class 统计前面比自己小的数的个数 extends HH {
	
	//因为线段树是一个完全二叉树，所以可以直接用数组模拟,在九章算法的答案里是直接用树模拟的
	private static int[] segmentTree;
	
	private static final int N = 10000;
	
	public List<Integer> countOfSmallerNumberII(int[] A) {
        // write your code here
		segmentTree = new int[4*N];
		List<Integer> result = new ArrayList<>();
		for(int i=0; i<A.length; i++) {
			int num = query(0, 0, N - 1, 0, A[i] - 1);
			result.add(num);
			modifyNode(0, 0, N-1, A[i], 1);
		}
		return result;
    }
	/**
	 * 
	 * @param root  线段树的根节点下标
	 * @param begin 查询区间的开始位置
	 * @param end   查询区间的结束位置
	 * @param qBegin 查询目标的结束位置
	 * @param qEnd   查询目标的结束位置
	 * @return 0-num中的数字个数
	 */
	private static int query(int root, int begin, int end, int qBegin, int qEnd) {
		if(qEnd < begin || qBegin > end) {
			return 0;
		}
		if(begin > end) {
			return 0;
		}
		if(qBegin <= begin && qEnd >= end) {
			return segmentTree[root];
		}
		int mid = begin + (end - begin)/2;
		int left = query(root*2 + 1, begin, mid, qBegin, qEnd);
		int right = query(root*2 + 2, mid+1, end, qBegin, qEnd);
		
		return left + right;
	}
	
	private void modifyNode(int root, int begin, int end, int index, int value) {
		if(begin == index && end == index) {
			segmentTree[root] ++;
			return;
		}
		int mid = (begin + end) /2;
		if(index <= mid && index >= begin) {
			modifyNode(root*2 + 1, begin, mid, index, value);
		}else if(index > mid && index <= end) {
			modifyNode(root*2 + 2, mid+1, end, index, value);
		}
		
		segmentTree[root] = segmentTree[root * 2 + 1] + segmentTree[root*2 + 2];
	}
	
	public static void main(String[] args) {
		int[] nums = new int[] {1,2,7,8,5};
		List<Integer> result = new 统计前面比自己小的数的个数().countOfSmallerNumberII(nums);
		print(result);
		for(int i=0; i<nums.length; i++) {
			int num = query(0, 0, N - 1, 0, nums[i] - 1);
			System.out.println(num);
		}
	}

}

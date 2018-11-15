package website.lintcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author yanpf
 * @date 2018年1月15日 下午6:05:42
 * @description
 * 		给定一个整数数组（下标由 0 到 n-1，其中 n 表示数组的规模），以及一个查询列表。每一个查询列表有两个整数 [start, end]。 
 * 		对于每个查询，计算出数组中从下标 start 到 end 之间的数的最小值，并返回在结果列表中。
 * 
 * @example
 * 		对于数组 [1,2,7,8,5]， 查询 [(1,2),(0,4),(2,4)]，返回 [2,1,5]
 *
 * @Solution
 */
public class 区间最小数 {
	
	private static class Interval {
	   int start, end;
	   Interval(int start, int end) {
           this.start = start;
	       this.end = end;
	   }
	}
	
	private static class TreeNode {
		Interval interval;
		TreeNode left, right;
		int val;
	}
		 
		 
	public List<Integer> intervalMinNumber(int[] A, List<Interval> queries) {
       TreeNode root = buildInterval(A, 0, A.length - 1);
       List<Integer> result = new ArrayList<>();
       for(Interval query : queries) {
    	   result.add(findMin(root, query));
       }
       return result;
    }
	
	private Integer findMin(TreeNode root, Interval query) {
		Interval interval = root.interval;
		if(query.start <= interval.start && query.end >= interval.end) {
			return root.val;
		}
		int mid = (interval.start + interval.end) / 2;
		if(query.end <= mid) {
			return findMin(root.left, query);
		}else if(query.start > mid) {
			return findMin(root.right, query);
		}else {
			return Math.min(findMin(root.left, query), findMin(root.right, query));
		}
		
	}
	
	private TreeNode buildInterval(int[] A, int start, int end)
	{
		if(start > end) {
			return null;
		}
		Interval interval = new Interval(start, end);
		TreeNode treeNode = new TreeNode();
		treeNode.interval = interval;
		if(start == end) {
			treeNode.val = A[start];
		}else {
			int mid = (start + end) / 2;
			treeNode.left = buildInterval(A, start, mid);
			treeNode.right = buildInterval(A, mid + 1, end);
			treeNode.val = Math.min(treeNode.left.val, treeNode.right.val);
		}
		
		return treeNode;
	}
}

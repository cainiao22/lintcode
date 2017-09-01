package datastructure.segmentTree;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author yanpf
 * @date 2017年8月24日 下午6:12:30
 * @description 简单的线段树的实现，本质上是一个完全二叉树，所以可以使用数组模拟，
 * @example
 *
 * @Solution
 */
public class SegmentTree<T extends Comparable<T>> {
	
	public SegmentTreeNode<T> head;
	
	public static class SegmentTreeNode<T> {
		public T data;
		public int start, end;
		public SegmentTreeNode<T> left, right, parent;
		public SegmentTreeNode(T data, int start, int end) {
			this.data = data;
			this.start = start;
			this.end = end;
		}
	}
	
	public static <T extends Comparable<T>> SegmentTree<T> build(T[] arr, int begin, int end){
		SegmentTree<T> tree = new SegmentTree<>();
		tree.head = buildSegmentTreeNode(arr, begin, end);
		
		return tree;
	}
	
	private static <T extends Comparable<T>> SegmentTreeNode<T> buildSegmentTreeNode(T[] arr, int begin, int end){
		if(begin == end) {
			return new SegmentTreeNode<T>(arr[begin], begin, end);
		}
		System.out.println(begin + "-->" + end);
		int mid = begin + (end - begin)/2;
		SegmentTreeNode<T> node = new SegmentTreeNode<T>(null, begin, end);
		SegmentTreeNode<T> left = buildSegmentTreeNode(arr, begin, mid);
		SegmentTreeNode<T> right = buildSegmentTreeNode(arr, mid + 1, end);
		node.left = left;
		node.right = right;
		node.data = left.data.compareTo(right.data) <= 0 ? left.data : right.data;
		return node;
	}
	
	public T query(SegmentTreeNode<T> root, int start, int end) {
		
		if(start > root.end || end < root.start) {
			return null;
		}
		
		if(start <= root.start && end >= root.end) {
			return root.data;
		}
		
		T left = query(root.left, start, end);
		T right = query(root.right, start, end);
		if(left == null) {
			return right;
		}else if(right == null) {
			return left;
		}
		return left.compareTo(right) < 0 ? left : right;
	}
	
	public static void main(String[] args) {
		Integer[] nums = new Integer[] {2, 5, 1, 4, 9, 3};
		SegmentTree<Integer> segmentTree = build(nums, 0, nums.length-1);
		
		SegmentTreeNode<Integer> head = segmentTree.head;
		/*List<SegmentTreeNode<Integer>> list = new ArrayList<>();
		list.add(head);
		while(!list.isEmpty()) {
			SegmentTreeNode<Integer> node = list.remove(0);
			System.out.println(node.data);
			if(node.left != null)
			list.add(node.left);
			if(node.right != null)
			list.add(node.right);
		}*/
		
		for(int i=0; i<nums.length; i++) {
			for(int j = i + 1; j < nums.length * 2; j ++) {
				System.out.print("find in " + i + "-->" + j + "\t");
				System.out.println(segmentTree.query(head, i, j));
			}
		}
	}
}

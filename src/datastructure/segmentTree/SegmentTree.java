package datastructure.segmentTree;

/**
 * 
 * @author yanpf
 * @date 2017��8��24�� ����6:12:30
 * @description �򵥵��߶�����ʵ�֣���������һ����ȫ�����������Կ���ʹ������ģ�⣬
 * @example
 *
 * @Solution
 */
public class SegmentTree<T extends Comparable<T>> {
	
	public SegmentTreeNode<T> head;
	
	public static class SegmentTreeNode<T> {
		public T data;
		public SegmentTreeNode<T> left, right, parent;
		public SegmentTreeNode(T data) {
			this.data = data;
		}
	}
	
	public static <T extends Comparable<T>> SegmentTree<T> build(T[] arr, int begin, int end){
		SegmentTree<T> tree = new SegmentTree<>();
		tree.head = buildSegmentTreeNode(arr, begin, end);
		
		return tree;
	}
	
	private static <T extends Comparable<T>> SegmentTreeNode<T> buildSegmentTreeNode(T[] arr, int begin, int end){
		if(begin < end) {
			return null;
		}
		int mid = begin + (end - begin)/2;
		SegmentTreeNode<T> node = new SegmentTreeNode<T>(null);
		SegmentTreeNode<T> left = buildSegmentTreeNode(arr, begin, mid);
		SegmentTreeNode<T> right = buildSegmentTreeNode(arr, mid + 1, end);
		node.left = left;
		node.right = right;
		node.data = left.data.compareTo(right.data) <= 0 ? left.data : right.data;
		return node;
	}
}

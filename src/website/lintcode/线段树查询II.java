package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年12月29日 上午11:26:25
 * @description
 * 		对于一个数组，我们可以对其建立一棵 线段树, 每个结点存储一个额外的值 count 来代表这个结点所指代的数组区间内的元素个数. (数组中并不一定每个位置上都有元素)
		实现一个 query 的方法，该方法接受三个参数 root, start 和 end, 分别代表线段树的根节点和需要查询的区间，找到数组中在区间[start, end]内的元素个数
		
 * @example
 * 		对于数组 [0, 空，2, 3], 对应的线段树为：

                     [0, 3, count=3]
                     /             \
          [0,1,count=1]             [2,3,count=2]
          /         \               /            \
   [0,0,count=1] [1,1,count=0] [2,2,count=1], [3,3,count=1]

		query(1, 1), return 0
		
		query(1, 2), return 1
		
		query(2, 3), return 2
		
		query(0, 2), return 2
 *
 * @Solution
 */
public class 线段树查询II {
	
	private static class SegmentTreeNode {
		public int start, end, count;
		public SegmentTreeNode left, right;

		public SegmentTreeNode(int start, int end, int count) {
	          this.start = start;
	          this.end = end;
	          this.count = count;
	          this.left = this.right = null;
	      }
	}
	
	public int query(SegmentTreeNode root, int start, int end) {
        // write your code here
		if(root == null || root.start > end || root.end < start) {
			return 0;
		}
		if(root.start >= start && root.end <= end) {
			return root.count;
		}
		start = Math.max(root.start, start);
		end = Math.min(root.end, end);
		int left = query(root.left, start, end);
		int right = query(root.right, start, end);
		
		return left + right;
    }

}



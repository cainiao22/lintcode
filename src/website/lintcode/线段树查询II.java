package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��12��29�� ����11:26:25
 * @description
 * 		����һ�����飬���ǿ��Զ��佨��һ�� �߶���, ÿ�����洢һ�������ֵ count ��������������ָ�������������ڵ�Ԫ�ظ���. (�����в���һ��ÿ��λ���϶���Ԫ��)
		ʵ��һ�� query �ķ������÷��������������� root, start �� end, �ֱ�����߶����ĸ��ڵ����Ҫ��ѯ�����䣬�ҵ�������������[start, end]�ڵ�Ԫ�ظ���
		
 * @example
 * 		�������� [0, �գ�2, 3], ��Ӧ���߶���Ϊ��

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
public class �߶�����ѯII {
	
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



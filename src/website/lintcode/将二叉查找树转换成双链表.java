package website.lintcode;

import website.lintcode.二叉查找树中搜索区间.TreeNode;

/**
 * 
 * @author yanpf
 * @date 2017年10月30日 下午6:08:40
 * @description
 * 		将一个二叉查找树按照中序遍历转换成双向链表。
 * @example
 *		给定一个二叉查找树：

		    4
		   / \
		  2   5
		 / \
		1   3
		
		返回 1<->2<->3<->4<->5。
 * @Solution
 */
public class 将二叉查找树转换成双链表 {
	
	public static class DoublyListNode {
		int val;
		DoublyListNode next, prev;

		DoublyListNode(int val) {
			this.val = val;
			this.next = this.prev = null;
		}
	}

	public DoublyListNode bstToDoublyList(TreeNode root) {
		// write your code here
	}

}

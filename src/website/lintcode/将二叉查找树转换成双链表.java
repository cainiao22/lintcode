package website.lintcode;

import java.util.Stack;

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
		Stack<TreeNode> stack = new Stack<>();
		while(root != null) {
			stack.push(root);
			root = root.left;
		}
		DoublyListNode result = new DoublyListNode(0);
		DoublyListNode current = result;
		while(!stack.isEmpty()) {
			TreeNode head = stack.pop();
			DoublyListNode temp = new DoublyListNode(head.val);
			current.next = temp;
			temp.prev = current;
			current = temp;
			head = head.right;
			while(head != null) {
				stack.push(head);
				head = head.left;
			}
		}
		return result.next;
	}
	
	/**
	 * 递归方式
	 * @param root
	 * @return
	 */
	public DoublyListNode bstToDoublyListDfs(TreeNode root) {
		helper(root);
		return result.next;
	}
	private DoublyListNode result = new DoublyListNode(0);
	private DoublyListNode current = result;
	public void helper(TreeNode root) {
		if(root == null) {
			return;
		}
		helper(root.left);
		DoublyListNode temp = new DoublyListNode(root.val);
		current.next = temp;
		temp.prev = current;
		current = temp;
		helper(root.right);
		
	}
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(4);
		TreeNode left1 = new TreeNode(2);
		TreeNode right1 = new TreeNode(5);
		TreeNode left11 = new TreeNode(1);
		TreeNode right11 = new TreeNode(3);
		root.left = left1;
		root.right = right1;
		left1.left = left11;
		left1.right = right11;
		
		new 将二叉查找树转换成双链表().bstToDoublyList(root);
	}

}

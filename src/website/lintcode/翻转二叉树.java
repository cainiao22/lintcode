package website.lintcode;

import java.util.Stack;

/**
 * 
 * @author yanpf
 * @date 2019年1月9日 下午5:08:55
 * @description 翻转一棵二叉树
 * @example
 * 		  1         1
		 / \       / \
		2   3  => 3   2
		   /       \
		  4         4
 *
 * @Solution 递归固然可行，能否写个非递归的？
 */


public class 翻转二叉树 {
	
	class TreeNode {
		public int val;
		public TreeNode left, right;

		public TreeNode(int val) {
			this.val = val;
			this.left = this.right = null;
		}
	}
	
	public void invertBinaryTree(TreeNode root) {
        // write your code here
		if(root != null) {
			TreeNode tmp = root.left;
			root.left = root.right;
			root.right = tmp;
			invertBinaryTree(root.left);
			invertBinaryTree(root.right);
		}
		
    }
	
	public void invertBinaryTreeIterator(TreeNode root) {
        // write your code here
		Stack<TreeNode> stack = new Stack<>();
		if(root != null) {
			stack.push(root);
		}
		while(!stack.isEmpty()) {
			TreeNode top = stack.pop();
			TreeNode tmp = top.left;
			top.left = top.right;
			top.right = tmp;
			if(top.left != null) {
				stack.push(top.left);
			}
			if(top.right != null) {
				stack.push(top.right);
			}
		}
		
    }

}

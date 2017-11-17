package website.lintcode;

import java.util.LinkedList;
import java.util.Queue;

import website.lintcode.二叉查找树中搜索区间.TreeNode;

/**
 * 
 * @author yanpf
 * @date 2017年11月14日 下午12:00:38
 * @description
 * 		给定一个二叉树,确定它是高度平衡的。对于这个问题,一棵高度平衡的二叉树的定义是：一棵二叉树中每个节点的两个子树的深度相差不会超过1。
 * @example
 * 		给出二叉树 A={3,9,20,#,#,15,7}, B={3,#,20,15,7}

		A)  3            B)    3 
		   / \                  \
		  9  20                 20
		    /  \                / \
		   15   7              15  7

		二叉树A是高度平衡的二叉树，但是B不是
 *
 * @Solution
 */
public class 平衡二叉树 {

	public boolean isBalanced(TreeNode root) {
		// write your code here
		if (root == null) {
			return true;
		}
		helper(root);
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		TreeNode head = root;
		while (!queue.isEmpty()) {
			head = queue.poll();
			int left = head.left == null ? 0 : head.left.val;
			int right = head.right == null ? 0 : head.right.val;
			if (Math.abs(left - right) > 1) {
				return false;
			}
			if (head.left != null) {
				queue.add(head.left);
			}
			if (head.right != null) {
				queue.add(head.right);
			}
		}
		return true;
	}

	private int helper(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int left = helper(root.left);
		int right = helper(root.right);
		root.val = Math.max(left, right) + 1;
		return root.val;
	}

	public boolean isBalancedFromJiuZhang(TreeNode root) {
		return maxDepth(root) != -1;
	}
	//上面那个方式是取玩深度再重新判断是否左右平衡。但是可以直接算，而且不必对原树做任何修改
	private int maxDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}

		int left = maxDepth(root.left);
		int right = maxDepth(root.right);
		//只要左右有不平衡的，就会把这个情况穿透到上面，否则给一个真实的深度
		if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
			return -1;
		}
		return Math.max(left, right) + 1;
	}

}

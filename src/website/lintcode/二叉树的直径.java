package website.lintcode;

import website.lintcode.二叉查找树中搜索区间.TreeNode;

/**
 * 
 * @author yanpf
 * @date 2019年3月6日 下午5:00:05
 * @description	
 * 				给定一颗二叉树，您需要计算树的直径长度。 二叉树的直径是树中任意两个节点之间最长路径的长度。 此路径不一定会通过树根。
				两个节点之间的路径长度由它们之间的边数表示。
				
 * @example
 *			给定一棵二叉树 
			          1
			         / \
			        2   3
			       / \     
			      4   5    
			返回3, 这是路径[4,2,1,3] 或者 [5,2,1,3]的长度.
 * @Solution
 */
public class 二叉树的直径 extends HH {
	
	int max = 0;
	
	public int diameterOfBinaryTree(TreeNode root) {
		helper2(root);
		return max;
	}
	
	/**
	 * 这个才是最好的
	 * @param root
	 * @return
	 */
	public int helper2(TreeNode root) {
		if(root == null) {
			return 0;
		}
		int left = helper2(root.left);
		int right = helper2(root.right);
		max = Math.max(max, left + right);
		return Math.max(left, right) + 1;
	}
	
	public int helper(TreeNode root) {
        // write your code here
		if(root == null) {
			return 0;
		}
		int left = 0;
		int right = 0;
		if(root.left != null) {
			left = helper(root.left) + 1;
		}
		if(root.right != null) {
			right = helper(root.right) + 1;
		}
	
		max = Math.max(max, left + right);
		return Math.max(left, right);
    }

	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		//root.left = new TreeNode(2);
		//root.right = new TreeNode(3);
		//root.left.left = new TreeNode(4);
		//root.left.right = new TreeNode(5);
		
		print(new 二叉树的直径().diameterOfBinaryTree(root));
	}
}

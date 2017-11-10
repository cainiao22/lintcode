package website.lintcode;

import website.lintcode.二叉查找树中搜索区间.TreeNode;

/**
 * 
 * @author yanpf
 * @date 2017年11月9日 下午4:56:54
 * @description
 * 		给定二叉搜索树(BST)，将其转换为更大的树，使原始BST上每个节点的值都更改为在原始树中大于等于该节点值得节点值之和(包括该节点)。
 * @example
 * 		Given a binary search Tree `{5,2,13}｀:

              5
            /   \
           2     13

		Return the root of new tree
		
             18
            /   \
          20     13

 *
 * @Solution
 */
public class 把二叉搜索树转化成更大的树 extends HH {
	
	private int sum;
	public TreeNode convertBST(TreeNode root) {
        // write your code here
		if(root == null) {
			return root;
		}
		convertBST(root.right);
		sum += root.val;
		root.val = sum;
		convertBST(root.left);
		return root;
    }
	
	private int dfs(TreeNode root) {
		if(root == null) {
			return 0;
		}
		if(root.right != null) {
			int max = dfs(root.right);
			root.val += max;
		}
		if(root.left != null) {
			int max = dfs(root.left);
			root.left.val += root.val;
			root.left.val += max;
		}
		return root.left == null ? root.val:root.left.val;
	}
	
	//1,2,3,4,5,6,7
	public static void main(String[] args) {
		TreeNode root = new TreeNode(4);
		root.left = new TreeNode(2);
		root.right = new TreeNode(6);
		root.left.right = new TreeNode(3);
		root.left.left = new TreeNode(1);
		root.right.left = new TreeNode(5);
		root.right.right = new TreeNode(7);
		new 把二叉搜索树转化成更大的树().convertBST(root);
	}

}

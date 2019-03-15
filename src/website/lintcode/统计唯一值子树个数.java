package website.lintcode;

import website.lintcode.二叉查找树中搜索区间.TreeNode;

/**
 * 
 * @author yanpf
 * @date 2019年1月29日 上午10:27:51
 * 
 * @description		给定一棵二叉树，统计唯一值子树的数目.
					唯一值子树意味着子树的所有节点都具有相同的值.
 * @example
 * 					给定二叉树 = {5,1,5,5,5,#,5}, 返回 4.
				
				              5
				             / \
				            1   5
				           / \   \
				          5   5   5
 *
 * @Solution
 */
public class 统计唯一值子树个数 {
	
	public int countUnivalSubtrees(TreeNode root) {
        // write your code here
		if(root == null) {
			return 0;
		}
		helper2(root);
		return count;
    }
	
	private int count;
	
	private boolean helper(TreeNode root) {
		if(root == null) {
			return true;
		}
		if(helper(root.left) & helper(root.right)) {
			//正向思考
			if(root.left == null && root.right == null) {
				count ++;
				return true;
			}else if(root.left == null) {
				if(root.val == root.right.val) {
					count ++;
					return true;
				}
			}else if(root.right == null) {
				if(root.val == root.left.val) {
					count ++;
					return true;
				}
			}else if(root.val == root.left.val && root.val == root.right.val) {
				count ++;
				return true;
			}
		}
		
		return false;
	}
	
	
	private boolean helper2(TreeNode root) {
		if(root == null) {
			return true;
		}
		if(helper(root.left) & helper(root.right)) {
			//逆向思考
			if(root.right != null && root.right.val != root.val) {
				return false;
			}
			if(root.left != null && root.left.val != root.val) {
				return false;
			}
			count ++;
			return true;
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(1);
		root.right = new TreeNode(5);
		root.left.left = new TreeNode(5);
		root.left.right = new TreeNode(5);
		root.right.right = new TreeNode(5);
		
		int result = new 统计唯一值子树个数().countUnivalSubtrees(root);
		System.out.println(result);
	}
}

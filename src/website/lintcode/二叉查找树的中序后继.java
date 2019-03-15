package website.lintcode;

import java.util.Stack;
import website.lintcode.二叉查找树中搜索区间.TreeNode;

/**
 * 
 * @author yanpf
 * @date 2019年3月6日 下午6:24:19
 * @description
 * 		给定一个二叉查找树(什么是二叉查找树)，以及一个节点，求该节点在中序遍历的后继，如果没有则返回null
 * 		O(h)，其中h是BST的高度。
 * @example
 * 		样例 1:
		
		输入: tree = {1,#,2}, node value = 1
		输出: 2
		解释: 
		  1
		   \
		    2
		样例 2:
		
		输入: tree = {2,1,3}, node value = 1
		输出: 2
		解释: 
		    2
		   / \
		  1   3
 * 
 * @Solution
 */
public class 二叉查找树的中序后继 {
	
	public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        // write your code here
		if(root == null) {
			return root;
		}
		Stack<TreeNode> stack = new Stack<>();
		while(root != null) {
			stack.push(root);
			root = root.left;
		}
		boolean findIt = false;
		while(!stack.isEmpty()) {
			TreeNode top = stack.pop();
			System.out.println(top.val);
			if(findIt) {
				return top;
			}
			if(top == p) {
				findIt = true;
			}	
			top = top.right;
			while(top != null) {
				stack.push(top);
				top = top.left;
			}
		}
		
		return null;
    }
	
	public TreeNode inorderSuccessor2(TreeNode root, TreeNode p) {
		if(root == null || p == null) {
			return null;
		}
		if(root.val == p.val) {
			root = root.right;
			while(root != null && root.left != null) {
				root = root.left;
			}
			return root;
		}else if(root.val < p.val) {
			return inorderSuccessor(root.right, p);
		}else {
			TreeNode left = inorderSuccessor(root.left, p);
			if(left == null) {
				return root;
			}else {
				return left;
			}
		}
	}
	
	public TreeNode inorderSuccessor3(TreeNode root, TreeNode p) {
		TreeNode post = null;
		while(root != null && root.val != p.val) {
			if(root.val > p.val) {
				post = root;
				root = root.left;
			}else {
				root = root.right;
			}
		}
		//表示没有找到
		if(root == null) {
			return null;
		}
		if(root.right == null) {
			return post;
		}
		root = root.right;
		while(root.left != null) {
			root = root.left;
		}
		
		return root;
	}
	
	/**
	 * //精简后的递归
	 * @param root
	 * @param p
	 * @return
	 */
	public TreeNode inorderSuccessor4(TreeNode root, TreeNode p) {
		if(root == null || p == null) {
			return null;
		}
		if(root.val  <= p.val) {
			return inorderSuccessor4(root.right, p);
			
		}else {
			TreeNode left = inorderSuccessor4(root.left, p);
			return left == null ? root : left;
		}
	}
	
	public TreeNode inorderSuccessor5(TreeNode root, TreeNode p) {
		TreeNode post = null;
		while(root != null && root.val != p.val) {
			if(root.val > p.val) {
				post = root;
				root = root.left;
			}else { //如果root.val == p.val root右边节点一定大于p，所以后面的遍历也一定是一直向左偏的 66666
				root = root.right;
			}
		}
		
		return post;
	}
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(2);
		root.left = new TreeNode(1);
		root.right = new TreeNode(3);
		//root.right.left = new TreeNode(4);
		//root.right.right = new TreeNode(5);
		
		TreeNode target = new 二叉查找树的中序后继().inorderSuccessor2(root, root.left);
		System.out.println(target);
	}

}

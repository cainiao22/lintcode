package website.lintcode;

import java.util.Stack;

import website.lintcode.二叉查找树中搜索区间.TreeNode;


/**
 * 
 * @author yanpf
 * @date 2019年2月26日 下午3:46:45
 * @description 
 * 		给定一个二叉树，判断它是否是合法的二叉查找树(BST)
 * 		一棵BST定义为：
 *              节点的左子树中的值要严格小于该节点的值。 
 *              节点的右子树中的值要严格大于该节点的值。 
 *              左右子树也必须是二叉查找树。
 *              一个节点的树也是二叉查找树。
 * @example
 * 
		 * 样例 1:
			输入:  给定一棵树，只有一个节点:
			-1
			输出：true
			
		样例 2:
			输入:  给定如下的树
			
			
			  2
			 / \
			1   4
			   / \
			  3   5
				
			输出: true
 *
 * @Solution
 */
public final class 验证二叉查找树 {
	
	/**
	 * 比较笨的递归方式
	 * @param root
	 * @return
	 */
	public boolean isValidBSTOfMy(TreeNode root) {
        // write your code here
		if(root == null) {
			return true;
		}
		if(root.left != null && root.val > root.left.val) {
			TreeNode node = root.left;
			while(node.right != null) {
				node = node.right;
			}
			if(root.val <= node.val) {
				return false;
			}
		}else if(root.left != null && root.val <= root.left.val){
			return false;
		}
		
		if(root.right != null && root.val < root.right.val) {
			TreeNode node = root.right;
			while(node.left != null) {
				node = node.left;
			}
			if(root.val > node.val) {
				return false;
			}
		}else if(root.right != null && root.val >= root.right.val) {
			return false;
		}
		
		return isValidBSTOfMy(root.left) && isValidBSTOfMy(root.right);
    }
	
	
	
	
	private boolean isFirst = true;
	private int lastValue = Integer.MIN_VALUE;
	/**
	 * 一个中序遍历操作，lastValue中永远保存遍历后的最后一个节点的数据。二叉搜索树的中序遍历一定是有序的，所以只要无序了，那就有问题了
	 * 
	 * @param root
	 * @return
	 */
	public boolean isValidBSTDFS(TreeNode root) {
		if(root == null) {
			return true;
		}
		if(!isValidBSTDFS(root.left)) {
			return false;
		}
		//isFirst 应对只有一个Integer.MIN_VALUE节点的情况
		if(!isFirst && lastValue >= root.val) {
			return false;
		}
		isFirst = false;
		lastValue = root.val;
		return isValidBSTDFS(root.right);
	}
	
	
	/**
	 * 迭代方式
	 * @param root
	 * @return
	 */
	public boolean isValidBSTIterator(TreeNode root) {
		Stack<TreeNode> stack = new Stack<>();
		TreeNode pre = null;
		while(!stack.isEmpty() || root != null) {
			if(root != null) {
				stack.push(root);
				root = root.left;
			}else {
				root = stack.pop();
				if(pre != null && pre.val >= root.val) {
					return false;
				}
				pre = root;
				root = root.right;
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(1);
		//root.right = new TreeNode(4);
		//root.right.left = new TreeNode(3);
		//root.right.right = new TreeNode(5);
		boolean result = new 验证二叉查找树().isValidBSTIterator(root);
		System.out.println(result);
	}

}

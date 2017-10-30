package website.lintcode;

import java.util.Stack;

import website.lintcode.二叉查找树中搜索区间.TreeNode;

/**
 * 
 * @author yanpf
 * @date 2017年10月23日 下午4:57:09
 * @description
 * 		将一棵二叉树按照前序遍历拆解成为一个假链表。所谓的假链表是说，用二叉树的 right 指针，来表示链表中的 next 指针。
 * 		不要忘记将左儿子标记为 null，否则你可能会得到空间溢出或是时间溢出。
 * 		不使用额外的空间耗费。
 * @example
 * 		              1
		               \
		     1          2
		    / \          \
		   2   5    =>    3
		  / \   \          \
		 3   4   6          4
		                     \
		                      5
		                       \
		                        6
 *
 * @Solution
 */
public class 将二叉树拆成链表 extends HH {

	 public void flatten(TreeNode root) {
	     // write your code here
		 Stack<TreeNode> stack = new Stack<>();
		 if(root != null) {
			 stack.push(root);
		 }
		 TreeNode head = new TreeNode(0);
		 TreeNode current = head;
		 while(!stack.isEmpty()) {
			 current.right = stack.pop();
			 current = current.right;
			 if(current.right != null) {
				 stack.push(current.right);
			 }
			 if(current.left != null) {
				 stack.push(current.left);
			 }
			 current.left = current.right = null;
		 }
		 root = head.right;
	 }
	 
	 /**
	  * 递归实现
	  * @param root
	  */
	 
	 private TreeNode lastNode = null;
	 public void flatten1(TreeNode root) {
		 if(root == null) {
			 return;
		 }
		 if(lastNode != null) {
			 lastNode.right = root;
			 lastNode.left = null;
		 }
		 lastNode = root;
		 TreeNode right = root.right;
		 flatten1(root.left);
		 flatten1(right);
	 }
	 
	 /**
	  * 	分治算法
	  */
	 public void flatten2(TreeNode root) {
		 helper(root);
	 }
	 
	 private TreeNode helper(TreeNode root) {
		 if(root == null) {
			 return null;
		 }
		 TreeNode leftNode = helper(root.left);
		 TreeNode rightNode = helper(root.right);
		 if(leftNode != null) {
			 //这里leftNode的下一个应该是rightNode的第一个。而这里的rightNode是右子数的最后一个节点
			 leftNode.right = root.right;
			 //这里连接的是左子树的第一个节点。
			 root.right = root.left;
		 }
		 
		 root.left = null;
		 //这里需要返回的是最后一个顶点
		 if(rightNode != null) {
			 return rightNode;
		 }
		 
		 if(leftNode != null) {
			 return leftNode;
		 }
		 
		 return root;
	 }
}

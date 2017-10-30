package website.lintcode;

import java.util.Stack;

import website.lintcode.�������������������.TreeNode;

/**
 * 
 * @author yanpf
 * @date 2017��10��23�� ����4:57:09
 * @description
 * 		��һ�ö���������ǰ���������Ϊһ����������ν�ļ�������˵���ö������� right ָ�룬����ʾ�����е� next ָ�롣
 * 		��Ҫ���ǽ�����ӱ��Ϊ null����������ܻ�õ��ռ��������ʱ�������
 * 		��ʹ�ö���Ŀռ�ķѡ�
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
public class ��������������� extends HH {

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
	  * �ݹ�ʵ��
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
	  * 	�����㷨
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
			 //����leftNode����һ��Ӧ����rightNode�ĵ�һ�����������rightNode�������������һ���ڵ�
			 leftNode.right = root.right;
			 //�������ӵ����������ĵ�һ���ڵ㡣
			 root.right = root.left;
		 }
		 
		 root.left = null;
		 //������Ҫ���ص������һ������
		 if(rightNode != null) {
			 return rightNode;
		 }
		 
		 if(leftNode != null) {
			 return leftNode;
		 }
		 
		 return root;
	 }
}

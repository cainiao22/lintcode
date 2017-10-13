package datastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import website.lintcode.HH;

/**
 * 
 * @author yanpf
 * @date 2017年10月12日 下午5:15:36
 * @description 二叉树的非递归遍历
 * @example
 * 
 * 
			    20
			   /  \
			  8   22
			 / \
			4   12
 *
 * @Solution
 */

class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

public class BinarySearchTree extends HH {
	
	public List<Integer> preOrder(TreeNode root){
		List<Integer> list = new ArrayList<>();
		Stack<TreeNode> stack = new Stack<>();
		TreeNode head = root;
		while(head != null || !stack.isEmpty()) {
			list.add(head.val);
			if(head.right != null) {
				stack.add(head.right);
			}
			if(head.left != null) {
				stack.add(head.left);
			}
			if(!stack.isEmpty()) {
				head = stack.pop();
			}else {
				head = null;
			}
		}
		
		return list;
	}
	
	public List<Integer> midOrder(TreeNode root){
		List<Integer> list = new ArrayList<>();
		Stack<TreeNode> stack = new Stack<>();
		TreeNode head = root;
		//这里是把root访问合并到一起了
		while(head != null || !stack.isEmpty()) {
			//一直遍历到最左边，不取
			while(head != null) {
				stack.add(head);
				head = head.left;
			}
			if(!stack.isEmpty()) {
				//取出最左边的做访问，其实到这里它的左半边已经访问过了，只不过是个null，跳过了
				//这里访问的是他本身
				head = stack.pop();
				list.add(head.val);
				//访问完进入它的右半边
				head = head.right;
			}
		}
		
		return list;
	}
	
	public List<Integer> postOrder1(TreeNode root){
		List<Integer> list = new ArrayList<>();
		Stack<TreeNode> stack = new Stack<>();
		TreeNode head = root;
		if(head != null) {
			stack.push(head);
		}
		
		while(!stack.isEmpty()) {
			head = stack.peek();
			if(head.left == null && head.right == null) {
				stack.pop();
				list.add(head.val);
			}
			if(head.right != null) {
				stack.push(head.right);
				//这里改
				head.right = null;
			}
			
			if(head.left != null) {
				stack.push(head.left);
				head.left = null;
			}
		}
		
		return list;
	}
	
	public List<Integer> postOrder2(TreeNode root){
		List<Integer> list = new ArrayList<>();
		Stack<TreeNode> stack = new Stack<>();
		TreeNode head = root;
		if(head != null) {
			stack.push(head);
		}
		
		while(!stack.isEmpty()) {
			head = stack.peek();
			if(head.left == null && head.right == null) {
				stack.pop();
				list.add(head.val);
			}
			if(head.right != null) {
				stack.push(head.right);
				//这里改
				head.right = null;
			}
			
			if(head.left != null) {
				stack.push(head.left);
				head.left = null;
			}
		}
		
		return list;
	}
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(20);
		root.left = new TreeNode(8);
		root.right = new TreeNode(22);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(12);
		
		List<Integer> list = new BinarySearchTree().postOrder1(root);
		print(list);
	}
}

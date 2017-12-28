package website.lintcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * 
 * @author yanpf
 * @date 2017��10��11�� ����6:29:05
 * @description
 * 		��������ֵ k1 �� k2��k1 < k2����һ������������ĸ��ڵ㡣�ҵ���������ֵ�� k1 �� k2 ��Χ�ڵĽڵ㡣
 * 		����ӡ����x (k1 <= x <= k2) ���� x �Ƕ�����������еĽڵ�ֵ��������������Ľڵ�ֵ��
 * 		
 * @example
 * 		����� k1 = 10 �� k2 = 22, ��ĳ���Ӧ�÷��� [12, 20, 22].

			    20
			   /  \
			  8   22
			 / \
			4   12
 *
 * @Solution
 */
public class ������������������� extends HH {
	
	  public static class TreeNode {
	      public int val;
	      public TreeNode left, right;
	      public TreeNode(int val) {
	          this.val = val;
	          this.left = this.right = null;
	      }
	      @Override
	    public String toString() {
	    	return this.toString() + "\t" + left == null ? "" : left.toString() + "\t" + right == null ? "" : right.toString();
	    }
	  }
	
	
	public List<Integer> searchRange(TreeNode root, int k1, int k2) {
		if(root != null) {
			bstSearch(root, k1, k2);
		}
		return list;
    }
	
	private List<Integer> list = new ArrayList<>();
	
	private void bstSearch(TreeNode root, int k1, int k2){
		TreeNode head = root;
		if(head.val >= k1 && head.left != null) {
			bstSearch(head.left, k1, k2);
		}
		if(head.val >= k1 && head.val <= k2) {
			list.add(head.val);
		}
		if(head.val <= k2 && head.right != null) {
			bstSearch(head.right, k1, k2);
		}
	}
	
	
	private List<Integer> bstSearchWithIterator(TreeNode root, int k1, int k2){
		List<Integer> res = new ArrayList<>();
		TreeNode head = root;
		Stack<TreeNode> stack = new Stack<>();
	
		while(head != null || !stack.isEmpty()) {
			while(head!= null) {
				stack.push(head);
				head = head.left;
			}
			if(!stack.isEmpty()) {
				head = stack.pop();
				if(head.val >= k1 && head.val <= k2) {
					res.add(head.val);
				}
				if(head.val <= k2 && head.right != null) {
					head = head.right;
				}else {
					head = null;
				}
			}
		}
		
		return res;
	}
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(20);
		root.left = new TreeNode(8);
		root.right = new TreeNode(22);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(12);
		
		List<Integer> list = new �������������������().bstSearchWithIterator(root, 10, 22);
		print(list);
	}

}

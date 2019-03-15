package website.lintcode;

import website.lintcode.�������������������.TreeNode;

/**
 * 
 * @author yanpf
 * @date 2019��1��29�� ����10:27:51
 * 
 * @description		����һ�ö�������ͳ��Ψһֵ��������Ŀ.
					Ψһֵ������ζ�����������нڵ㶼������ͬ��ֵ.
 * @example
 * 					���������� = {5,1,5,5,5,#,5}, ���� 4.
				
				              5
				             / \
				            1   5
				           / \   \
				          5   5   5
 *
 * @Solution
 */
public class ͳ��Ψһֵ�������� {
	
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
			//����˼��
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
			//����˼��
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
		
		int result = new ͳ��Ψһֵ��������().countUnivalSubtrees(root);
		System.out.println(result);
	}
}

package website.lintcode;

import website.lintcode.�������������������.TreeNode;

/**
 * 
 * @author yanpf
 * @date 2019��3��6�� ����5:00:05
 * @description	
 * 				����һ�Ŷ�����������Ҫ��������ֱ�����ȡ� ��������ֱ�����������������ڵ�֮���·���ĳ��ȡ� ��·����һ����ͨ��������
				�����ڵ�֮���·������������֮��ı�����ʾ��
				
 * @example
 *			����һ�ö����� 
			          1
			         / \
			        2   3
			       / \     
			      4   5    
			����3, ����·��[4,2,1,3] ���� [5,2,1,3]�ĳ���.
 * @Solution
 */
public class ��������ֱ�� extends HH {
	
	int max = 0;
	
	public int diameterOfBinaryTree(TreeNode root) {
		helper2(root);
		return max;
	}
	
	/**
	 * ���������õ�
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
		
		print(new ��������ֱ��().diameterOfBinaryTree(root));
	}
}

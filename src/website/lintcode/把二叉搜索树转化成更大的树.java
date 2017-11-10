package website.lintcode;

import website.lintcode.�������������������.TreeNode;

/**
 * 
 * @author yanpf
 * @date 2017��11��9�� ����4:56:54
 * @description
 * 		��������������(BST)������ת��Ϊ���������ʹԭʼBST��ÿ���ڵ��ֵ������Ϊ��ԭʼ���д��ڵ��ڸýڵ�ֵ�ýڵ�ֵ֮��(�����ýڵ�)��
 * @example
 * 		Given a binary search Tree `{5,2,13}��:

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
public class �Ѷ���������ת���ɸ������ extends HH {
	
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
		new �Ѷ���������ת���ɸ������().convertBST(root);
	}

}

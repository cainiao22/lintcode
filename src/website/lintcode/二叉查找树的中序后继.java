package website.lintcode;

import java.util.Stack;
import website.lintcode.�������������������.TreeNode;

/**
 * 
 * @author yanpf
 * @date 2019��3��6�� ����6:24:19
 * @description
 * 		����һ�����������(ʲô�Ƕ��������)���Լ�һ���ڵ㣬��ýڵ�����������ĺ�̣����û���򷵻�null
 * 		O(h)������h��BST�ĸ߶ȡ�
 * @example
 * 		���� 1:
		
		����: tree = {1,#,2}, node value = 1
		���: 2
		����: 
		  1
		   \
		    2
		���� 2:
		
		����: tree = {2,1,3}, node value = 1
		���: 2
		����: 
		    2
		   / \
		  1   3
 * 
 * @Solution
 */
public class ����������������� {
	
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
		//��ʾû���ҵ�
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
	 * //�����ĵݹ�
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
			}else { //���root.val == p.val root�ұ߽ڵ�һ������p�����Ժ���ı���Ҳһ����һֱ����ƫ�� 66666
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
		
		TreeNode target = new �����������������().inorderSuccessor2(root, root.left);
		System.out.println(target);
	}

}

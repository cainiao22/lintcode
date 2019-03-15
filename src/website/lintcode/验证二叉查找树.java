package website.lintcode;

import java.util.Stack;

import website.lintcode.�������������������.TreeNode;


/**
 * 
 * @author yanpf
 * @date 2019��2��26�� ����3:46:45
 * @description 
 * 		����һ�����������ж����Ƿ��ǺϷ��Ķ��������(BST)
 * 		һ��BST����Ϊ��
 *              �ڵ���������е�ֵҪ�ϸ�С�ڸýڵ��ֵ�� 
 *              �ڵ���������е�ֵҪ�ϸ���ڸýڵ��ֵ�� 
 *              ��������Ҳ�����Ƕ����������
 *              һ���ڵ����Ҳ�Ƕ����������
 * @example
 * 
		 * ���� 1:
			����:  ����һ������ֻ��һ���ڵ�:
			-1
			�����true
			
		���� 2:
			����:  �������µ���
			
			
			  2
			 / \
			1   4
			   / \
			  3   5
				
			���: true
 *
 * @Solution
 */
public final class ��֤��������� {
	
	/**
	 * �Ƚϱ��ĵݹ鷽ʽ
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
	 * һ���������������lastValue����Զ�������������һ���ڵ�����ݡ��������������������һ��������ģ�����ֻҪ�����ˣ��Ǿ���������
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
		//isFirst Ӧ��ֻ��һ��Integer.MIN_VALUE�ڵ�����
		if(!isFirst && lastValue >= root.val) {
			return false;
		}
		isFirst = false;
		lastValue = root.val;
		return isValidBSTDFS(root.right);
	}
	
	
	/**
	 * ������ʽ
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
		boolean result = new ��֤���������().isValidBSTIterator(root);
		System.out.println(result);
	}

}

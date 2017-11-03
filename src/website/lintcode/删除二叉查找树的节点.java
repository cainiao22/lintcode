package website.lintcode;

import website.lintcode.�������������������.TreeNode;

/**
 * 
 * @author yanpf
 * @date 2017��11��2�� ����4:56:05
 * @description
 * 		����һ�þ��в�ͬ�ڵ�ֵ�Ķ����������ɾ�����������ֵ��ͬ�Ľڵ㡣
 * 		�������û����ֵͬ�Ľڵ㣬�Ͳ����κδ�����Ӧ�ñ�֤����֮��������Ƕ����������
 * 
 * @example
 * 		�������¶����������
			
			          5
			
			       /    \
			
			    3          6
			
			 /    \
			
			2       4
			
			ɾ���ڵ�3֮������Է��أ�
			
			          5
			
			       /    \
			
			    2          6
			
			      \
			
			         4
			
			���ߣ�
			
			          5
			
			       /    \
			
			    4          6
			
			 /   
			
			2
 *
 * @Solution
 */
public class ɾ������������Ľڵ� {

	public TreeNode removeNode(TreeNode root, int value) {
		TreeNode summary = new TreeNode(0);
		summary.left = root;
		TreeNode parent = find(summary, root, value);
		delete(parent, value);
		return summary.left;
	}
	
	private TreeNode find(TreeNode parent, TreeNode current, int value) {
		if(current == null) {
			return parent;
		}
		if(current.val == value) {
			return parent;
		}
		if(current.val < value) {
			return find(current, current.right, value);
		}else {
			return find(current, current.left, value);
		}
	}
	
	private void delete(TreeNode parent, int value) {
		TreeNode target = null;
		boolean isLeft = true;
		if(parent.left != null && parent.left.val == value) {
			target = parent.left;
			isLeft = true;
		}else if(parent.right != null && parent.right.val == value) {
			target = parent.right;
			isLeft = false;
		}
		if(target == null) {
			return;
		}
		if(target.right == null) {
			if(isLeft) {
				parent.left = target.left;
			}else {
				parent.right = target.left;
			}
		}else { //��������Ϊ�ա���Ҫ��target���������ŵ��������������
			TreeNode temp = target.right;
			while(temp.left != null) {
				temp = temp.left;
			}
			temp.left = target.left;
			if(isLeft) {
				parent.left = target.right;
			}else {
				parent.right = target.right;
			}
		}
	}
	
	public static void main(String[] args) {
		
	}

}

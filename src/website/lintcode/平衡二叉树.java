package website.lintcode;

import java.util.LinkedList;
import java.util.Queue;

import website.lintcode.�������������������.TreeNode;

/**
 * 
 * @author yanpf
 * @date 2017��11��14�� ����12:00:38
 * @description
 * 		����һ��������,ȷ�����Ǹ߶�ƽ��ġ������������,һ�ø߶�ƽ��Ķ������Ķ����ǣ�һ�ö�������ÿ���ڵ������������������ᳬ��1��
 * @example
 * 		���������� A={3,9,20,#,#,15,7}, B={3,#,20,15,7}

		A)  3            B)    3 
		   / \                  \
		  9  20                 20
		    /  \                / \
		   15   7              15  7

		������A�Ǹ߶�ƽ��Ķ�����������B����
 *
 * @Solution
 */
public class ƽ������� {

	public boolean isBalanced(TreeNode root) {
		// write your code here
		if (root == null) {
			return true;
		}
		helper(root);
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		TreeNode head = root;
		while (!queue.isEmpty()) {
			head = queue.poll();
			int left = head.left == null ? 0 : head.left.val;
			int right = head.right == null ? 0 : head.right.val;
			if (Math.abs(left - right) > 1) {
				return false;
			}
			if (head.left != null) {
				queue.add(head.left);
			}
			if (head.right != null) {
				queue.add(head.right);
			}
		}
		return true;
	}

	private int helper(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int left = helper(root.left);
		int right = helper(root.right);
		root.val = Math.max(left, right) + 1;
		return root.val;
	}

	public boolean isBalancedFromJiuZhang(TreeNode root) {
		return maxDepth(root) != -1;
	}
	//�����Ǹ���ʽ��ȡ������������ж��Ƿ�����ƽ�⡣���ǿ���ֱ���㣬���Ҳ��ض�ԭ�����κ��޸�
	private int maxDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}

		int left = maxDepth(root.left);
		int right = maxDepth(root.right);
		//ֻҪ�����в�ƽ��ģ��ͻ����������͸�����棬�����һ����ʵ�����
		if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
			return -1;
		}
		return Math.max(left, right) + 1;
	}

}

package website.lintcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author yanpf
 * @date 2018��1��16�� ����9:51:11
 * @description
 * 		����һ�ö��������ҵ������ڵ������������ڵ�(LCA)��
		������������������ڵ�Ĺ��������Ƚڵ��Ҿ���������
 * @example
 * 		����������ö�����

		  4
		 / \
		3   7
		   / \
		  5   6
		
		LCA(3, 5) = 4
		
		LCA(5, 6) = 7
		
		LCA(6, 7) = 7
 *
 * @Solution 1�����鼯����
 * 			 2�����C�ǽڵ�A��B������������ȡ���ôA��B��Ȼ�ֱ�λ��C���������ࡣ
 * 				��������ڵ����C��ĳһ�࣬��ô��һ��϶��Ҳ���A��B,��ʱ���Խ����ų����������ҿ����ҵ�A��B����һ��ֱ����������ֱ�����ҵ�A��BΪֹ
 */
public class ����������� {
	

	 private static class TreeNode {
	     public int val;
	     public TreeNode left, right;
	     public TreeNode(int val) {
	         this.val = val;
	         this.left = this.right = null;
	     }
	 }
	 
	
	private Map<TreeNode, TreeNode> parent = new HashMap<>();
	
	/**
	 * ���鼯 �Ƚϱ��ķ���
	 * @param root
	 * @param A
	 * @param B
	 * @return
	 */
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode A, TreeNode B) {
        // write your code here
		findParents(root);
		for(TreeNode pA = A; pA != root; pA = parent.get(pA)) {
			for(TreeNode pB = B; pB != root; pB = parent.get(pB)) {
				if(pA == pB) {
					return pA;
				}
			}
		}
		
		return root;
    }
	
	private void findParents(TreeNode root) {
		if(root == null) {
			return;
		}
		if(root.left != null) {
			parent.put(root.left, root);
		}
		if(root.right != null) {
			parent.put(root.right, root);
		}
		findParents(root.left);
		findParents(root.right);
	}
	
	public TreeNode lowestCommonAncestorBetter(TreeNode root, TreeNode A, TreeNode B) {
		//null����û���ҵ�������A����B��ʾ�����ҵ���һ��
		if(root == null || root == A || root == B) {
			return root;
		}
		TreeNode left = lowestCommonAncestor(root.left, A, B);
		TreeNode right = lowestCommonAncestor(root.right, A, B);
		if(left != null && right != null) {
			return root;
		}
		if(left != null) {
			return left;
		}
		if(right != null) {
			return right;
		}
		return null;
				
	}

}

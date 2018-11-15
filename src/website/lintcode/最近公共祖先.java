package website.lintcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author yanpf
 * @date 2018年1月16日 上午9:51:11
 * @description
 * 		给定一棵二叉树，找到两个节点的最近公共父节点(LCA)。
		最近公共祖先是两个节点的公共的祖先节点且具有最大深度
 * @example
 * 		对于下面这棵二叉树

		  4
		 / \
		3   7
		   / \
		  5   6
		
		LCA(3, 5) = 4
		
		LCA(5, 6) = 7
		
		LCA(6, 7) = 7
 *
 * @Solution 1、并查集搜索
 * 			 2、如果C是节点A和B的最近公共祖先。那么A和B必然分别位于C的左右两侧。
 * 				如果两个节点均在C的某一侧，那么另一侧肯定找不到A和B,此时可以将它排除掉。继续找可以找到A和B的那一侧直到左右两侧分别可以找到A和B为止
 */
public class 最近公共祖先 {
	

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
	 * 并查集 比较笨的方法
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
		//null代表没有找到。等于A或者B表示至少找到了一个
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

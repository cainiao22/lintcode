package website.lintcode;

import website.lintcode.二叉查找树中搜索区间.TreeNode;

/**
 * 
 * @author yanpf
 * @date 2017年11月14日 下午5:17:00
 * @description
 * 		
		根据前序遍历和中序遍历树构造二叉树.
		注意事项
		
		你可以假设树中不存在相同数值的节点

 * @example
 * 		给出中序遍历：[1,2,3]和前序遍历：[2,1,3]. 返回如下的树:

			  2
			 / \
			1   3

 *
 * @Solution
 * 
 * 		根据前序遍历的特点, 知前序序列(PreSequence)的首个元素(PreSequence[0])为二叉树的根(root),  
 * 		然后在中序序列(InSequence)中查找此根(root),  根据中序遍历特点, 知在查找到的根(root) 
 * 		前边的序列为根的左子树的中序遍历序列,  后边的序列为根的右子树的中序遍历序列。 
 * 		设在中序遍历序列(InSequence)根前边有left个元素. 则在前序序列(PreSequence)中, 
 * 		紧跟着根(root)的left个元素序列(即PreSequence[1...left]) 为根的左子树的前序遍历序列, 
 * 		在后边的为根的右子树的前序遍历序列.而构造左子树问题其实跟构造整个二叉树问题一样，
 * 		只是此时前序序列为PreSequence[1...left]), 中序序列为InSequence[0...left-1], 分别为原序列的子串, 
 * 		构造右子树同样, 显然可以用递归方法解决。
 */
public class 前序遍历和中序遍历树构造二叉树 extends HH {
	
	public TreeNode buildTree(int[] preorder, int[] inorder) {
        // write your code here
		if(preorder.length == 0 || inorder.length == 0) {
			return null;
		}
		TreeNode root = new TreeNode(preorder[0]);
		int i=0;
		for(i=0; i<inorder.length; i++) {
			if(inorder[i] == root.val) {
				break;
			}
		}
		int[] leftPre = new int[0] ;
		int[] leftIn = new int[0];
		if(i > 0) {
			leftIn = new int[i-1];
			leftPre = new int[i-1];
		}
		int[] rightPre = new int[preorder.length - i];
		int[] rightIn = new int[inorder.length - i];
		for(int j=0; j<i; j++) {
			leftPre[j] = preorder[j+1];
			leftIn[j] = inorder[j];
		}
		for(int j=i+1;j<inorder.length; j++) {
			rightIn[j-i-1] = inorder[j];
			rightPre[j-i-1] = preorder[j-1];
		}
		root.left = buildTree(leftPre, leftIn);
		root.right = buildTree(rightPre, rightIn);
		
		return root;
    }
	
	
	public static void main(String[] args) {
		new 前序遍历和中序遍历树构造二叉树().buildTree(new int[]{1,2}, new int[]{1,2});
	}

}

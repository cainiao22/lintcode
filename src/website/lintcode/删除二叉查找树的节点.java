package website.lintcode;

import website.lintcode.二叉查找树中搜索区间.TreeNode;

/**
 * 
 * @author yanpf
 * @date 2017年11月2日 下午4:56:05
 * @description
 * 		给定一棵具有不同节点值的二叉查找树，删除树中与给定值相同的节点。
 * 		如果树中没有相同值的节点，就不做任何处理。你应该保证处理之后的树仍是二叉查找树。
 * 
 * @example
 * 		给出如下二叉查找树：
			
			          5
			
			       /    \
			
			    3          6
			
			 /    \
			
			2       4
			
			删除节点3之后，你可以返回：
			
			          5
			
			       /    \
			
			    2          6
			
			      \
			
			         4
			
			或者：
			
			          5
			
			       /    \
			
			    4          6
			
			 /   
			
			2
 *
 * @Solution
 */
public class 删除二叉查找树的节点 {

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
		}else { //右子树不为空。需要把target的左子树放到右子树的最左侧
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

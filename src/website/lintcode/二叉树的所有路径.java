package website.lintcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * 
 * @author yanpf
 * @date 2018年1月5日 下午2:59:23
 * @description
 * 		给一棵二叉树，找出从根节点到叶子节点的所有路径。
 * @example
 * 		给出下面这棵二叉树：
			   1
			 /   \
			2     3
			 \
			  5
			
			所有根到叶子的路径为：
			
			[
			  "1->2->5",
			  "1->3"
			]

 *
 * @Solution
 */
public class 二叉树的所有路径 {
	

	 private static class TreeNode {
	      public int val;
	      public TreeNode left, right;
	      public TreeNode(int val) {
	          this.val = val;
	          this.left = this.right = null;
	      }
	  }
	 
	private List<String> result = new ArrayList<>();
	
	public List<String> binaryTreePaths(TreeNode root) {
        // write your code here
		List<Integer> list = new ArrayList<>();
		helper(list, root);
		
		return result;
    }
	
	private void helper(List<Integer> list, TreeNode node) {
		if(node == null) {
			return;
		}
		list.add(node.val);
		if(node.left == null && node.right == null) {
			StringBuilder sb = new StringBuilder();
			for(Integer integer : list) {
				sb.append(integer).append("->");
			}
			sb.setLength(sb.length() - 2);
			result.add(sb.toString());
		}
		if(node.left != null) {
			helper(list, node.left);
		}
		if(node.right != null) {
			helper(list, node.right);
		}
		list.remove(list.size() - 1);
	}
	
	/**
	 * 九章答案 有点像后序遍历
	 * @param root
	 * @return
	 */
	public List<String> binaryTreePaths2(TreeNode root) {
        List<String> paths = new ArrayList<>();
        if (root == null) {
            return paths;
        }
        
        List<String> leftPaths = binaryTreePaths2(root.left);
        List<String> rightPaths = binaryTreePaths2(root.right);
        for (String path : leftPaths) {
            paths.add(root.val + "->" + path);
        }
        for (String path : rightPaths) {
            paths.add(root.val + "->" + path);
        }
        
        // root is a leaf
        if (paths.size() == 0) {
            paths.add("" + root.val);
        }
        
        return paths;
    }

}

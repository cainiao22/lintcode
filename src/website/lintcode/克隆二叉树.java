package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年12月6日 下午3:16:41
 * @description	
		深度复制一个二叉树。给定一个二叉树，返回一个他的 克隆品 。

 * @example
 * 		给定一个二叉树：

		     1
		   /  \
		  2    3
		 / \
		4   5
		
		返回其相同结构相同数值的克隆二叉树：
		
		     1
		   /  \
		  2    3
		 / \
		4   5

 *
 * @Solution
 */
public class 克隆二叉树 extends HH {
	
	/**
	 * 为了不报错只好这样了，但是程序是对的
	 public TreeNode cloneTree(TreeNode root) {
	        // write your code here
		 if(root == null) {
			 return null;
		 }
		 TreeNode copy = new TreeNode(root.val);
		 copy.left = cloneTree(root.left);
		 copy.right = cloneTree(root.right);
		 return copy;
	 }
	 **/

}



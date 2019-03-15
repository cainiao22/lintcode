package website.lintcode;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import website.lintcode.二叉查找树中搜索区间.TreeNode;

/**
 * 
 * @author yanpf
 * @date 2019年1月10日 上午10:42:19
 * @description  给定一棵二叉树，找到这棵树中最后一行中最左边的值。
 * @example
 * 			输入:[2,1,3]
			输出:1
			输人:[1,2,3,4,5,6,#,#,7]
			输出:7
 *
 * @Solution
 */
public class 寻找树中最左下结点的值 {
	
	private TreeNode result;
	int deepth;
	
	public int findBottomLeftValue(TreeNode root) {
        // write your code here
		findBottomLeftValueDFS(root, 1);
		return result.val;
    }
	
	/**
	 * dfs 递归方式的解法
	 * @param root
	 * @param currentDeepth
	 */
	public void findBottomLeftValueDFS(TreeNode root, int currentDeepth) {
        if(root == null) {
        	return;
        }
        if(currentDeepth > deepth) {
        	result = root;
        	deepth = currentDeepth;
        }
        findBottomLeftValueDFS(root.left, currentDeepth + 1);
        findBottomLeftValueDFS(root.right, currentDeepth + 1);
    }
	
	/**
	 * 迭代方式的dfs
	 * @param root
	 */
	public void findBottomLeftValueDFSIterator(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> stackDeepth = new Stack<>();
        stack.push(root);
        stackDeepth.push(1);
        while(!stack.isEmpty()) {
        	TreeNode top = stack.pop();
        	int currentDeepth = stackDeepth.pop();
        	if(currentDeepth > deepth) {
        		result = top;
        		deepth = currentDeepth;
        	}
        	//这边得倒过来，让他先访问左节点
        	if(top.right != null) {
        		stack.push(top.right);
        		stackDeepth.push(currentDeepth + 1);
        	}
        	if(top.left != null) {
        		stack.push(top.left);
        		stackDeepth.push(currentDeepth + 1);
        	}
        	
        }
    }
	
	/**
	 * BFS
	 * @param root
	 */
	public void findBottomLeftValueBFS(TreeNode root) {
		if(root == null) {
			return;
		}
		Queue<TreeNode> queue = new LinkedList<>();
		Queue<Integer> queueDepth = new LinkedList<>();
		queue.add(root);
		queueDepth.add(1);
		while(!queue.isEmpty()) {
			TreeNode top = queue.poll();
			int currentDeepth = queueDepth.poll();
			if(currentDeepth > deepth) {
				result = top;
				deepth = currentDeepth;
			}
			if(top.left != null) {
				queue.add(top.left);
				queueDepth.add(currentDeepth + 1);
			}
			
			if(top.right != null) {
				queue.add(top.right);
				queueDepth.add(currentDeepth + 1);
			}
		}
	}
	
	/**
	 * 最巧妙的方式
	 * @param root
	 * @return
	 */
	int findBottomLeftValueBFS_BEST(TreeNode root) {
		int res = 0;
		if(root == null) {
			return res;
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
        while (!queue.isEmpty()) {
        	 //表示该层有多少元素
            int n = queue.size();
            for (int i = 0; i < n; ++i) {
                TreeNode t = queue.poll();
                //每一层的第一个元素就是我要找的
                if (i == 0) res = t.val;
                if (t.left != null) queue.add(t.left);
                if (t.right != null) queue.add(t.right);
            }
        }
        return res;
    }
	
	public static void main(String[] args) {
		
	}
}

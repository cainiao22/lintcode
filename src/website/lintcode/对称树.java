package website.lintcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import website.lintcode.�������������������.TreeNode;


/**
 * 
 * @author yanpf
 * @date 2019��1��25�� ����4:38:41
 * @description	������������������Ƿ�������ľ��񣨼���Χ�������ĶԳƣ���
 * @example		���磬��������� "{1,2,2,3,4,4,3}" �ǶԳƵ�:

				    1
				   / \
				  2   2
				 / \ / \
				3  4 4  3
				Ȼ������ {1,2,2,#,3,#,3} ����:
				
				    1
				   / \
				  2   2
				   \   \
				   3    3
 *
 * @Solution
 */
public class �Գ��� {
	
	/**
	 * �ֲ���� ��ʱ��
	 * @param root
	 * @return
	 */
	public boolean isSymmetric(TreeNode root) {
        if(root == null) {
        	return true;
        }
        List<TreeNode> queue = new ArrayList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
        	int length = queue.size();
        	for(int i=0, j=length - 1; i < j; i++, j--) {
        		if(queue.get(i) == null && queue.get(j) == null) {
        			continue;
        		}else if(queue.get(i) == null || queue.get(j) == null) {
        			return false;
        		}else if(queue.get(i).val != queue.get(j).val) {
        			return false;
        		}
        	}
        	
        	boolean allNull = true;
        	for(int i=0; i<length; i++) {
        		TreeNode top = queue.remove(0);
        		if(top == null) {
        			queue.add(null);
        			queue.add(null);
        		}else {
        			queue.add(top.left);
        			queue.add(top.right);
        			allNull = false;
        		}
        	}
        	
        	if(allNull) {
        		break;
        	}
        }
        return true; 
    }
	
	/**
	 * ����ʱ
	 * @param root
	 * @return
	 */
	public boolean isSymmetric3(TreeNode root) {
		if(root == null) {
			return true;
		}
		Queue<TreeNode> q1 = new LinkedList<>();
		Queue<TreeNode> q2 = new ArrayDeque<TreeNode>();
		q1.add(root.left);
		q2.add(root.right);
		while(!q1.isEmpty() && !q2.isEmpty()) {
			TreeNode node1 = q1.poll();
			TreeNode node2 = q2.poll();
			if(node1 == null && node2 == null) {
				continue;
			}else if(node1 == null || node2 == null) {
				return false;
			}else if(node1.val != node2.val) {
				return false;
			}
			
			q1.add(node1.left);
			q1.add(node1.right);
			q2.add(node2.right);
			q2.add(node2.left);
		}
		
		return true;
 	}
	
	/**
	 * �ݹ�
	 * @param root
	 * @return
	 */
	public boolean isSymmetric2(TreeNode root) {
		if(root == null) {
			return true;
		}
		return helper(root.left, root.right);
	}
	
	private boolean helper(TreeNode left, TreeNode right) {
		if(left == null && right == null) {
			return true;
		}else if(left == null || right == null) {
			return false;
		}else if(left.val != right.val) {
			return false;
		}
		
		return helper(left.left, right.right) && helper(left.right, right.left);
	}
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(2);
		root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(3);
		
		root.right.left = new TreeNode(4);
		root.right.right = new TreeNode(3);
		
		boolean result = new �Գ���().isSymmetric(root);
		System.out.println(result);
	}
}

package website.lintcode;

import java.util.ArrayList;
import java.util.List;

import website.lintcode.�������������������.TreeNode;

/**
 * 
 * @author yanpf
 * @date 2017��10��20�� ����4:47:58
 * @description
 * 		����һ�����������ҳ�����·���и��ڵ�����ܺ͵��ڸ��� Ŀ��ֵ ��·����

		һ����Ч��·����ָ���ǴӸ��ڵ㵽Ҷ�ڵ��·��
 * @example
 * 		����һ������������ Ŀ��ֵ = 5:
		
		     1
		    / \
		   2   4
		  / \
		 2   3
		
		���أ�
		
		[
		  [1, 2, 2],
		  [1, 4]
		]

 *
 * @Solution
 */
public class ��������·���� extends HH {
	
	private List<List<Integer>> result = new ArrayList<>();
	public List<List<Integer>> binaryTreePathSum(TreeNode root, int target) {
        // write your code here
		List<Integer> item = new ArrayList<>();
		dfs(root, target, item);
		return result;
    }
	
	public void dfs(TreeNode root, int target, List<Integer> item) {
		if(root == null) {
			return;
		}
		/*if(root != null && root.val != target) {
			item.add(root.val);
			dfs(root.left, target-root.val, item);
			dfs(root.right, target-root.val, item);
			item.remove(item.size()-1);
		}else if(root != null && root.val == target && root.left == null && root.right == null) {
			List<Integer> copy = new ArrayList<>();
			for(Integer a : item) {
				copy.add(a);
			}
			copy.add(root.val);
			result.add(copy);
		}*/
		
		if(root != null) {
			if(root.val == target && root.left == null && root.right == null) {
				List<Integer> copy = new ArrayList<>();
				for(Integer a : item) {
					copy.add(a);
				}
				copy.add(root.val);
				result.add(copy);
			}
			item.add(root.val);
			dfs(root.left, target-root.val, item);
			dfs(root.right, target-root.val, item);
			item.remove(item.size()-1);
		}
	}
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		TreeNode left1 = new TreeNode(2);
		TreeNode right1 = new TreeNode(4);
		TreeNode left11 = new TreeNode(2);
		TreeNode right11 = new TreeNode(3);
		root.left = left1;
		root.right = right1;
		left1.left = left11;
		left1.right = right11;
		
		List<List<Integer>> result = new ��������·����().binaryTreePathSum(root, 5);
		for (List<Integer> list : result) {
			print(list);
		}
	}

}

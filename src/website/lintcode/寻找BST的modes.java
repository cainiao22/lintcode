package website.lintcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import website.lintcode.�������������������.TreeNode;

/**
 * 
 * @author yanpf
 * @date 2019��1��13�� ����4:24:43
 * @description ���������ظ���Ķ�����������BST�����ҵ�����BST�е�����modes����Ƶ�����ֵ�Ԫ�أ���
 * 
 *              ���������һ��BST�������£�
 * 
 *              �ڵ����������������С�ڻ���ڸ��ڵ�Ľڵ㡣 �ڵ�������������������ڻ���ڸ��ڵ�Ľڵ㡣 ��������Ҳ�����Ƕ�����������
 * 
 * 
 * @example ���� BST [1,#,2,2], ���� [2].
 * @Solution
 */
public class Ѱ��BST��modes {

	/**
	 * �����Ľ���취
	 * 
	 * @param root
	 * @return
	 */
	public int[] findMode(TreeNode root) {
		// write your code here
		Map<Integer, Integer> map = new HashMap<>();
		LRD(root, map);
		int max = 0;
		for (int a : map.values()) {
			max = Math.max(max, a);
		}
		List<Integer> list = new ArrayList<>();
		final int temp = max;
		map.forEach((k, v) -> {
			if (temp == v) {
				list.add(k);
			}
		});
		int[] result = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			result[i] = list.get(i);
		}
		return result;
	}

	public void LRD(TreeNode treeNode, Map<Integer, Integer> map) {
		if (treeNode == null) {
			return;
		}
		if (map.get(treeNode.val) == null) {
			map.put(treeNode.val, 1);
		} else {
			map.put(treeNode.val, map.get(treeNode.val) + 1);
		}

		LRD(treeNode.left, map);
		LRD(treeNode.right, map);
	}
	
	private int currCount;
	private int currVal;
	private int maxCount;
	private int modeCount;
	private int[] mode;
	
	public int[] findMode2(TreeNode root) {
		inorder(root);
		mode = new int[modeCount];
		modeCount = 0;
		currCount = 0;
		inorder(root);
		return mode;
		
	}
	
	private void handleValue(int val) {
		if(val != currVal) {
			currCount = 0;
		}
		currCount ++;
		if(currCount > maxCount) {
			maxCount = currCount;
			modeCount = 1;
		}else if(currCount == maxCount) {
			if(mode != null) {
				mode[modeCount] = val;
			}
			modeCount ++;
		}
	}
	
	private void inorder(TreeNode root) {
		if(root == null) {
			return;
		}
		inorder(root.left);
		handleValue(root.val);
		inorder(root.right);
	}
	
	
	

	public static void main(String[] args) {
		int[] nums = new int[4];
		nums[0] = 2;
		nums[1] = 7;
		nums[2] = 11;
		nums[3] = 15;
		int target = 9;
		int[] result = new int[2];
		for (int i = 0; i < nums.length; i++) {
			for (int j = 0; j < nums.length; j++) {
				if (j == i) {
					continue;
				}
				if (nums[i] + nums[j] == target) {
					result[0] = i;
					result[1] = j;
					break;
				}
			}
		}
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}
	}
}

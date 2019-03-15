package website.lintcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import website.lintcode.�������������������.TreeNode;

/**
 * 
 * @author yanpf
 * @date 2019��1��9�� ����5:25:28
 * @description ����n������������1...nΪ�ڵ���ɵĲ�ͬ�Ķ��������
 * @example ����n = 3����������5�ֲ�ͬ��̬�Ķ����������
 * 
 *          1         3     3       2    1
			 \       /     /       / \    \
			  3     2     1       1   3    2
			 /     /       \                \
			2     1         2                3
 *
 * @Solution ��Ϊ������������㸸�ڵ��ֵ�������ӽڵ��ֵ��С�����ӽڵ��ֵ���������ǿ��ԣ�
 * 
 *           (1) �� N=1 ��ʼ����������������������������ڵ���Ϊ 0���������ڵ���Ϊ n-1��
 * 
 *           (2) N=2 ʱ���������ڵ���Ϊ 1���������ڵ���Ϊ n-2��
 * 
 *           ����
 * 
 *           (n) N=n ʱ���������ڵ���Ϊ n-1���������ڵ��� 0��
 * 
 * 
 * 
 *           ���ڵ�(1)���У�����������ִ������ѭ����������������ִ�����ѭ�������գ����ǿ��Խ������ڵ������ٵ�
 *           1����һ���ڵ�ֻ��һ�����з�ʽ�����Դ�ʱ���Ժ�����ԥ�ؽ�������ظ���һ����Ȼ������������ڵ�Ķ��������з�ʽ�ֱ����ظ���һ��������
 */
public class ��ͬ�Ķ��������II {
	
	public List<TreeNode> generateTrees(int n) {
        // write your code here
		return this.generateTrees(1, n);
    }
	
	public List<TreeNode> generateTrees(int lf, int r){
		List<TreeNode> result = new ArrayList<>();
		if(lf == r) {
			result.add(new TreeNode(lf));
			return result;
		}
		if(lf > r) {
			result.add(null);
			return result;
		}
		
		for(int i=lf; i<=r; i++) {
			List<TreeNode> leftList = generateTrees(lf, i - 1);
			List<TreeNode> rightList = generateTrees(i+1, r);
			for(TreeNode left : leftList) {
				for(TreeNode right : rightList) {
					TreeNode root = new TreeNode(i);
					root.left = left;
					root.right = right;
					result.add(root);
				}
			}
			
			/*if(leftList.isEmpty()) {
				for(TreeNode right : rightList) {
					TreeNode root = new TreeNode(i);
					root.right = right;
					result.add(root);
				}
			}
			
			if(rightList.isEmpty()) {
				for(TreeNode left : leftList) {
					TreeNode root = new TreeNode(i);
					root.left = left;
					result.add(root);
				}
			}*/
		}
		
		return result;
	}
	
	/**
	 * ʹ�þ������˵�˼��
	 * @param n
	 * @return
	 */
	public List<TreeNode> generateTreesDP(int n) {
		if(n == 0) {
			List<TreeNode> res = new ArrayList<>();
			res.add(null);
			return res;
		}
		Map<Integer, Map<Integer, List<TreeNode>>> dp = new HashMap<>();
		for(int i=1; i<=n; i++) {
			Map<Integer, List<TreeNode>> item = new HashMap<>();
			item.put(i, new ArrayList<>());
			item.get(i).add(new TreeNode(i));
			dp.put(i, item);
		}
		
		dp.put(0, new HashMap<>());
		dp.put(n+1, new HashMap<>());
		
		for (int k = 1; k < n; k++) {
			for (int i = 1; i <= n - k; i++) {
				int j = i + k;
				for (int m = i; m <= j; m++) {
					List<TreeNode> leftList = dp.get(i).get(m - 1);
					List<TreeNode> rightList = dp.get(m + 1).get(j);
					List<TreeNode> result = dp.get(i).get(j);
					if (result == null) {
						result = new ArrayList<>();
						dp.get(i).put(j, result);
					}
					if (leftList != null && rightList != null) {
						for (TreeNode left : leftList) {
							for (TreeNode right : rightList) {
								TreeNode root = new TreeNode(m);
								root.left = left;
								root.right = right;
								result.add(root);
							}
						}

						continue;
					}

					if (leftList == null) {
						for (TreeNode right : rightList) {
							TreeNode root = new TreeNode(m);
							root.right = right;
							result.add(root);
						}
					}

					if (rightList == null) {
						for (TreeNode left : leftList) {
							TreeNode root = new TreeNode(m);
							root.left = left;
							result.add(root);
						}
					}

				}
			}
		}
		
		return dp.get(1).get(n);
	}
	
	public static void main(String[] args) {
		List<TreeNode> result = new ��ͬ�Ķ��������II().generateTreesDP(0);
		System.out.println(result.size());
	}
}

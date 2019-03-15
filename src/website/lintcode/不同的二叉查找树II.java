package website.lintcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import website.lintcode.二叉查找树中搜索区间.TreeNode;

/**
 * 
 * @author yanpf
 * @date 2019年1月9日 下午5:25:28
 * @description 给出n，生成所有由1...n为节点组成的不同的二叉查找树
 * @example 给出n = 3，生成所有5种不同形态的二叉查找树：
 * 
 *          1         3     3       2    1
			 \       /     /       / \    \
			  3     2     1       1   3    2
			 /     /       \                \
			2     1         2                3
 *
 * @Solution 因为二叉查找树满足父节点的值大于左子节点的值，小于右子节点的值，所以我们可以：
 * 
 *           (1) 从 N=1 开始构建二叉查找树，则它的左子树节点数为 0，右子树节点数为 n-1；
 * 
 *           (2) N=2 时，左子树节点数为 1，右子树节点数为 n-2；
 * 
 *           ……
 * 
 *           (n) N=n 时，左子树节点数为 n-1，右子树节点数 0。
 * 
 * 
 * 
 *           而在第(1)步中，右子树继续执行上述循环，子树的子树又执行这个循环，最终，我们可以将子树节点数减少到
 *           1，而一个节点只有一种排列方式，所以此时可以毫不犹豫地将结果返回给上一级。然后包含有两个节点的二叉树排列方式又被返回给上一级。……
 */
public class 不同的二叉查找树II {
	
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
	 * 使用矩阵连乘的思想
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
		List<TreeNode> result = new 不同的二叉查找树II().generateTreesDP(0);
		System.out.println(result.size());
	}
}

package website.lintcode;

import java.util.HashMap;
import java.util.Map;

import website.lintcode.二叉查找树中搜索区间.TreeNode;

/**
 * 
 * @author yanpf
 * @date 2017年10月30日 下午3:05:21
 * @description
 * 		
	在上次打劫完一条街道之后和一圈房屋之后，窃贼又发现了一个新的可以打劫的地方，
	但这次所有的房子组成的区域比较奇怪，聪明的窃贼考察地形之后，发现这次的地形是一颗二叉树。
	与前两次偷窃相似的是每个房子都存放着特定金额的钱。你面临的唯一约束条件是：相邻的房子装着相互联系的防盗系统，
	且当相邻的两个房子同一天被打劫时，该系统会自动报警。
	
	算一算，如果今晚去打劫，你最多可以得到多少钱，当然在不触动报警装置的情况下。

 * @example
 * 
 * 			  3
			 / \
			2   3
			 \   \ 
			  3   1
			
			窃贼最多能偷窃的金钱数是 3 + 3 + 1 = 7.
			
			    3
			   / \
			  4   5
			 / \   \ 
			1   3   1
			
			窃贼最多能偷窃的金钱数是 4 + 5 = 9.
 *
 * @Solution
 */
public class 打劫房屋III extends HH {

	private Map<TreeNode, Integer> map1 = new HashMap<>();
	private Map<TreeNode, Integer> map2 = new HashMap<>();
	public int houseRobber3(TreeNode root) {
        // write your code here
		return helper(root, false);
    }
	
	/**
	 * 
	 * @param root 当前打劫节点
	 * @param parent 父节点是否被打劫了
	 * @return
	 */
	public int helper(TreeNode root, boolean parent) {
		if(root == null) {
			return 0;
		}
		if(parent) { //父节点被打劫了。这个节点就不能被打劫了
			if(map1.get(root) != null) {
				return map1.get(root);
			}
			int left = helper(root.left, false);
			int right = helper(root.right, false);
			map1.put(root, left + right);
			return left + right;
		}else {
			if(map2.get(root) != null) {
				return map2.get(root);
			}
			int max1 = root.val;//打劫当前节点
			int left = helper(root.left, true);
			int right = helper(root.right, true);
			max1 += left;
			max1 += right;
			
			//不打劫当前节点
			left = helper(root.left, false);
			right = helper(root.right, false);
			
			//取最大值
			int result = Math.max(max1, left + right);
			map2.put(root, result);
			return result;
		}
	}
	
	public int houseRobber3FromJiuZhang1(TreeNode root) {
		int[] ans = dp(root);
        return Math.max(ans[0], ans[1]);
	}
	//dp[i][0]表示以i为根的子树不偷根节点能获得的最高价值，dp[i][1]表示以i为根的子树偷根节点能获得的最高价值
	private int[] dp(TreeNode root){
		if(root == null) {
			return new int[] {0,0};
		}
		int[] left = dp(root.left);
		int[] right = dp(root.right);
		int[] ans = new int[2];
		//不偷根节点。子节点可偷可不偷
		ans[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
		
		//偷根节点。子节点不能偷
		ans[1] = left[0] + right[0] + root.val;
		
		return ans;
	}
}

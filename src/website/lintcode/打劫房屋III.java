package website.lintcode;

import java.util.HashMap;
import java.util.Map;

import website.lintcode.�������������������.TreeNode;

/**
 * 
 * @author yanpf
 * @date 2017��10��30�� ����3:05:21
 * @description
 * 		
	���ϴδ����һ���ֵ�֮���һȦ����֮�������ַ�����һ���µĿ��Դ�ٵĵط���
	��������еķ�����ɵ�����Ƚ���֣������������������֮�󣬷�����εĵ�����һ�Ŷ�������
	��ǰ����͵�����Ƶ���ÿ�����Ӷ�������ض�����Ǯ�������ٵ�ΨһԼ�������ǣ����ڵķ���װ���໥��ϵ�ķ���ϵͳ��
	�ҵ����ڵ���������ͬһ�챻���ʱ����ϵͳ���Զ�������
	
	��һ�㣬�������ȥ��٣��������Եõ�����Ǯ����Ȼ�ڲ���������װ�õ�����¡�

 * @example
 * 
 * 			  3
			 / \
			2   3
			 \   \ 
			  3   1
			
			���������͵�ԵĽ�Ǯ���� 3 + 3 + 1 = 7.
			
			    3
			   / \
			  4   5
			 / \   \ 
			1   3   1
			
			���������͵�ԵĽ�Ǯ���� 4 + 5 = 9.
 *
 * @Solution
 */
public class ��ٷ���III extends HH {

	private Map<TreeNode, Integer> map1 = new HashMap<>();
	private Map<TreeNode, Integer> map2 = new HashMap<>();
	public int houseRobber3(TreeNode root) {
        // write your code here
		return helper(root, false);
    }
	
	/**
	 * 
	 * @param root ��ǰ��ٽڵ�
	 * @param parent ���ڵ��Ƿ񱻴����
	 * @return
	 */
	public int helper(TreeNode root, boolean parent) {
		if(root == null) {
			return 0;
		}
		if(parent) { //���ڵ㱻����ˡ�����ڵ�Ͳ��ܱ������
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
			int max1 = root.val;//��ٵ�ǰ�ڵ�
			int left = helper(root.left, true);
			int right = helper(root.right, true);
			max1 += left;
			max1 += right;
			
			//����ٵ�ǰ�ڵ�
			left = helper(root.left, false);
			right = helper(root.right, false);
			
			//ȡ���ֵ
			int result = Math.max(max1, left + right);
			map2.put(root, result);
			return result;
		}
	}
	
	public int houseRobber3FromJiuZhang1(TreeNode root) {
		int[] ans = dp(root);
        return Math.max(ans[0], ans[1]);
	}
	//dp[i][0]��ʾ��iΪ����������͵���ڵ��ܻ�õ���߼�ֵ��dp[i][1]��ʾ��iΪ��������͵���ڵ��ܻ�õ���߼�ֵ
	private int[] dp(TreeNode root){
		if(root == null) {
			return new int[] {0,0};
		}
		int[] left = dp(root.left);
		int[] right = dp(root.right);
		int[] ans = new int[2];
		//��͵���ڵ㡣�ӽڵ��͵�ɲ�͵
		ans[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
		
		//͵���ڵ㡣�ӽڵ㲻��͵
		ans[1] = left[0] + right[0] + root.val;
		
		return ans;
	}
}

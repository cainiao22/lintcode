package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��12��6�� ����3:16:41
 * @description	
		��ȸ���һ��������������һ��������������һ������ ��¡Ʒ ��

 * @example
 * 		����һ����������

		     1
		   /  \
		  2    3
		 / \
		4   5
		
		��������ͬ�ṹ��ͬ��ֵ�Ŀ�¡��������
		
		     1
		   /  \
		  2    3
		 / \
		4   5

 *
 * @Solution
 */
public class ��¡������ extends HH {
	
	/**
	 * Ϊ�˲�����ֻ�������ˣ����ǳ����ǶԵ�
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



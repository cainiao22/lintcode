package website.lintcode;

import website.lintcode.�������������������.TreeNode;

/**
 * 
 * @author yanpf
 * @date 2017��11��14�� ����5:17:00
 * @description
 * 		
		����ǰ�������������������������.
		ע������
		
		����Լ������в�������ͬ��ֵ�Ľڵ�

 * @example
 * 		�������������[1,2,3]��ǰ�������[2,1,3]. �������µ���:

			  2
			 / \
			1   3

 *
 * @Solution
 * 
 * 		����ǰ��������ص�, ֪ǰ������(PreSequence)���׸�Ԫ��(PreSequence[0])Ϊ�������ĸ�(root),  
 * 		Ȼ������������(InSequence)�в��Ҵ˸�(root),  ������������ص�, ֪�ڲ��ҵ��ĸ�(root) 
 * 		ǰ�ߵ�����Ϊ�����������������������,  ��ߵ�����Ϊ����������������������С� 
 * 		���������������(InSequence)��ǰ����left��Ԫ��. ����ǰ������(PreSequence)��, 
 * 		�����Ÿ�(root)��left��Ԫ������(��PreSequence[1...left]) Ϊ������������ǰ���������, 
 * 		�ں�ߵ�Ϊ������������ǰ���������.������������������ʵ��������������������һ����
 * 		ֻ�Ǵ�ʱǰ������ΪPreSequence[1...left]), ��������ΪInSequence[0...left-1], �ֱ�Ϊԭ���е��Ӵ�, 
 * 		����������ͬ��, ��Ȼ�����õݹ鷽�������
 */
public class ǰ������������������������� extends HH {

	public TreeNode buildTree(int[] preorder, int[] inorder) {
        // write your code here
		if(preorder.length == 0 || inorder.length == 0) {
			return null;
		}
		TreeNode root = new TreeNode(preorder[0]);
		int mid = preorder[0];
		int i = 0;
		while(inorder[i] != mid) {
			i++;
		}
		int[] leftInorder = new int[i];
		int[] leftPreorder = new int[i];
		for(int j=0; j<i; j++) {
			leftInorder[j] = inorder[j];
			leftPreorder[j] = preorder[1 + j];
		}
		int[] rightInorder = new int[inorder.length - 1 - i];
		int[] rightPreorder = new int[inorder.length - 1 - i];
		for(int j=inorder.length - 1; j>i; j--) {
			rightInorder[j - i - 1] = inorder[j];
			rightPreorder[j - i - 1] = preorder[j];
		}
		
		root.left = buildTree(leftPreorder, leftInorder);
		root.right = buildTree(rightPreorder, rightInorder);
		return root;
    }
	
	
	public static void main(String[] args) {
		TreeNode root = new ǰ�������������������������().buildTree(new int[]{1,2,4,6,5,3}, new int[]{6,4,2,5,1,3});
		System.out.println(root);
	}

}

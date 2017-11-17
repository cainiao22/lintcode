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
		int i=0;
		for(i=0; i<inorder.length; i++) {
			if(inorder[i] == root.val) {
				break;
			}
		}
		int[] leftPre = new int[0] ;
		int[] leftIn = new int[0];
		if(i > 0) {
			leftIn = new int[i-1];
			leftPre = new int[i-1];
		}
		int[] rightPre = new int[preorder.length - i];
		int[] rightIn = new int[inorder.length - i];
		for(int j=0; j<i; j++) {
			leftPre[j] = preorder[j+1];
			leftIn[j] = inorder[j];
		}
		for(int j=i+1;j<inorder.length; j++) {
			rightIn[j-i-1] = inorder[j];
			rightPre[j-i-1] = preorder[j-1];
		}
		root.left = buildTree(leftPre, leftIn);
		root.right = buildTree(rightPre, rightIn);
		
		return root;
    }
	
	
	public static void main(String[] args) {
		new ǰ�������������������������().buildTree(new int[]{1,2}, new int[]{1,2});
	}

}

package website.lintcode;

import website.lintcode.�������������������.TreeNode;

/**
 * 
 * @author yanpf
 * @date 2017��10��30�� ����6:08:40
 * @description
 * 		��һ����������������������ת����˫������
 * @example
 *		����һ�������������

		    4
		   / \
		  2   5
		 / \
		1   3
		
		���� 1<->2<->3<->4<->5��
 * @Solution
 */
public class �����������ת����˫���� {
	
	public static class DoublyListNode {
		int val;
		DoublyListNode next, prev;

		DoublyListNode(int val) {
			this.val = val;
			this.next = this.prev = null;
		}
	}

	public DoublyListNode bstToDoublyList(TreeNode root) {
		// write your code here
	}

}

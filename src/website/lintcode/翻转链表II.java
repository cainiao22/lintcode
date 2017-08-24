package website.lintcode;

/**
 * 
 * @author yanpf
 * @description ��ת�����е�m���ڵ㵽��n���ڵ�Ĳ��� ( m��n����1 �� m �� n �� ������ )
 * @example ��������1->2->3->4->5->null�� m = 2 ��n = 4������1->4->3->2->5->null
 * 
 * @Soultion �͵�һ��������ƣ�����Ҫ����m��nλ�õĽڵ㣬���Ҫ�ֱ��������ڵ���Ե�ǰ���ͺ���Ҫ���ν�
 */
public class ��ת����II {

	public ListNode reverseBetween(ListNode head, int m, int n) {
		// write your code
		ListNode pre = null, result = head;
		for (int i = 0; i < m - 1; i++) {
			pre = head;
			head = head.next;
		}
		ListNode temp = null, tempM = head, tempPre = pre;
		for (int i = m; i <= n; i++) {
			temp = head.next;
			head.next = pre;
			pre = head;
			head = temp;
		}
		if(m == 1) {
			result = pre;
		}else {
			tempPre.next = pre;
		}
		tempM.next = temp;
		return result;
	}
	
	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		ListNode tail = head;
		for(int i=2; i<6; i++) {
			ListNode t = new ListNode(i);
			tail.next = t;
			tail = t;
		}
		ListNode revert = new ��ת����II().reverseBetween(head, 1, 4);
		System.out.println(revert);
	}

}

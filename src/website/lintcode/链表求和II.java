package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��10��9�� ����11:30:08
 * @description �ٶ���һ�������ʾ������������ÿ���ڵ������һ�����֡�������������������˳�����У�
 *              �����һ�ַ�������������ӣ�������������Ϊ�������ʽ
 * 
 * @example ���� 6->1->7 + 2->9->5������617 + 295��
 * 
 *          ���� 9->1->2������912 ��
 *
 * @Solution �Ƚ���������ת�����������ռӷ�����ִ��
 */
public class �������II {

	public ListNode addLists2(ListNode l1, ListNode l2) {
		// write your code here
		l1 = revert(l1);
		l2 = revert(l2);
		int add = 0;//��λ��־
		ListNode head = new ListNode(0);
		ListNode cur = head;
		while(l1 != null && l2 != null) {
			ListNode next = new ListNode(l1.val + l2.val + add);
			add = next.val/10;
			next.val = next.val%10;
			cur.next = next;
			cur = next;
			l1 = l1.next;
			l2 = l2.next;
		}
		while(l1 != null) {
			ListNode next = new ListNode(l1.val + add);
			add = next.val/10;
			next.val = next.val%10;
			cur.next = next;
			cur = next;
			l1 = l1.next;
		}
		while(l2 != null) {
			ListNode next = new ListNode(l2.val + add);
			add = next.val/10;
			next.val = next.val%10;
			cur.next = next;
			cur = next;
			l2 = l2.next;
		}
		while(add != 0) {
			ListNode next = new ListNode(add);
			add = next.val/10;
			next.val = next.val%10;
			cur.next = next;
			cur = next;
		}
		return revert(head.next);
	}

	private ListNode revert(ListNode head) {
		ListNode pre = null;
		ListNode temp = null;
		while (head != null) {
			temp = head.next;
			head.next = pre;
			pre = head;
			head = temp;
		}
		return pre;
	}
}

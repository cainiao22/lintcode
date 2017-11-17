package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��11��13�� ����4:21:24
 * @description
 * 		����������������������������ÿ���ڵ����һ�����֡����ִ洢������ԭ���������෴��˳��
 * 		ʹ�õ�һ������λ������Ŀ�ͷ��д��һ������������������ӣ���������ʽ���غ�
 * @example
 * 		������������ 3->1->5->null �� 5->9->2->null������ 8->0->8->null
 *
 * @Solution ���ǧ��Ҫ���˽�λ��־�Ƿ񻹴���
 */
public class ������� extends HH {
	
	public ListNode addLists(ListNode l1, ListNode l2) {
        // write your code here
		ListNode head = new ListNode(0);
		int inc = 0;
		while(l1 != null && l2 != null) {
			int sum = l1.val + l2.val + inc;
			inc = sum / 10;
			sum = sum % 10;

			ListNode item =new ListNode(sum);
			item.next = head.next;
			head.next = item;
			l1 = l1.next;
			l2 = l2.next;
		}
		while(l1 != null) {
			int sum = l1.val + inc;
			inc = sum / 10;
			sum = sum % 10;
			ListNode item =new ListNode(sum);
			item.next = head.next;
			head.next = item;
			l1 = l1.next;
		}
		
		while(l2 != null) {
			int sum = l2.val + inc;
			inc = sum / 10;
			sum = sum % 10;
			ListNode item =new ListNode(sum);
			item.next = head.next;
			head.next = item;
			l2 = l2.next;
		}
		while(inc != 0) {
			int sum = inc;
			inc = sum / 10;
			sum = sum % 10;
			ListNode item =new ListNode(sum);
			item.next = head.next;
			head.next = item;
			l2 = l2.next;
		}
		return head.next;
    }
	
	/**
	 * �����ʽ���Ӽ��
	 * @param l1
	 * @param l2
	 * @return
	 */
	 public ListNode addListsFromJiuZhang(ListNode l1, ListNode l2) {
	        // write your code here
	        ListNode dummy = new ListNode(0);
	        ListNode tail = dummy;

	        int carry = 0;
	        //for������������
	        for (ListNode i = l1, j = l2; i != null || j != null; ) {
	            int sum = carry;
	            sum += (i != null) ? i.val : 0;
	            sum += (j != null) ? j.val : 0;

	            tail.next = new ListNode(sum % 10);
	            tail = tail.next;

	            carry = sum / 10;
	            i = (i == null) ? i : i.next;
	            j = (j == null) ? j : j.next;
	        }

	        if (carry != 0) {
	            tail.next = new ListNode(carry);
	        }
	        return dummy.next;
	    }
	
	public static void main(String[] args) {
		ListNode l1 = new ListNode(3);
		l1.next = new ListNode(1);
		l1.next.next = new ListNode(2);
		
		ListNode l2 = new ListNode(5);
		l2.next = new ListNode(9);
		l2.next.next = new ListNode(5);
		l2.next.next.next = new ListNode(1);
		new �������().addLists(l1, l2);
	}

}

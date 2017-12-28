package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��12��26�� ����3:05:33
 * @description
 * 			ɾ�������е��ڸ���ֵval�����нڵ㡣
 * @example
 *			�������� 1->2->3->3->4->5->3, �� val = 3, ����Ҫ����ɾ��3֮�������1->2->4->5��
 * @Solution
 */
public class ɾ�������е�Ԫ�� {

	public ListNode removeElements(ListNode head, int val) {
        ListNode result = new ListNode(0);
        ListNode current = head, tail = result;
        while(current != null) {
        	if(current.val != val) {
        		tail.next = current;
        		tail = current;
        	}
        	current = current.next;
        }
        //������һ��Сϸ�ڣ�����tail������β���Ǹ�������ķǷ����ͻᱻ��������
        tail.next = null;
        return result.next;
    }
	
	/**
	 * ֱ���ж�next��ֵ�����Ϸ���ɾ��
	 * @param head
	 * @param val
	 * @return
	 */
	public ListNode removeElementsBetter(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        
        while (head.next != null) {
            if (head.next.val == val) { //������������һ��Ԫ�صĻ�������next����null,û��Ҫ���ֶ�����
                head.next = head.next.next;
            } else {
                head = head.next;
            }
        }
        
        return dummy.next;
    }
}

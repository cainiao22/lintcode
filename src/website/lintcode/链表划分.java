package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2018��3��9�� ����4:18:57
 * @description
 * 		����һ�����������ֵx����������ʹ������С��x�Ľڵ����ڴ��ڵ���x�Ľڵ�֮ǰ��
		��Ӧ�ñ���������������ڵ�ԭ�е����˳��
 * @example
 * 		�������� 1->4->3->2->5->2->null������ x=3
		���� 1->2->2->4->3->5->null
 *
 * @Solution
 */
public class ������ {
	
	public ListNode partition(ListNode head, int x) {
        ListNode h = new ListNode(0);
        ListNode cur = h;
        ListNode t = new ListNode(0);
        ListNode tail = t;
        for(ListNode ptr = head; ptr != null; ptr = ptr.next){
        	if(ptr.val < x) {
        		cur.next = ptr;
        		cur = cur.next;
        	}else {
        		tail.next = ptr;
        		tail = tail.next;
        	}
        }
        cur.next = t.next;
        //�����һ��Ԫ�ص���һλ�ÿգ���ֹ��ѭ��
        tail.next = null;
        return h.next;
    }

}

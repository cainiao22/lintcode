package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��9��8�� ����10:35:40
 * @description ����һ����������ɾ�������ظ���Ԫ��ֻ����ԭ������û���ظ���Ԫ�ء�
 * @example ���� 1->2->3->3->4->4->5->null������ 1->2->5->null
 * 
 *          ���� 1->1->1->2->3->null������ 2->3->null
 *
 * @Solution 1������������ͬ�Ļ���num+1������ж�num�Ƿ�Ϊ0,���Ϊ0�����
 * 			 2��ֱ���жϣ����
 */
public class ɾ�����������е��ظ�����II {

	public static ListNode deleteDuplicates(ListNode head) {
		// write your code here
		if (head == null || head.next == null) {
			return head;
		}
		ListNode result = new ListNode(0);
		ListNode tail = result;
		ListNode temp = head;
		head = head.next;
		int num = 0;
		while (head != null) {
			while (head != null && temp.val == head.val) {
				head = head.next;
				num++;
			}
			if (num == 0) {
				tail.next = temp;
				tail = temp;
			}
			num = 0;
			temp = head;
			//������Ҫע����
			if (head != null) {
				head = head.next;
			}
		}
		if (num == 0) {
			tail.next = temp;
		}
		return result.next;
	}
	
	public static ListNode deleteDuplicates2(ListNode head) {
		if(head == null || head.next == null) {
			return head;
		}
		
		ListNode result = new ListNode(0);
		head = result;
		//һ��ѭ������head����û���ƶ���ֻ������next��������һ��У�飬�����ʱhead.next.next=null
		//��ʾhead����պ�ֻ��һ��Ԫ�أ����Ԫ��Ҳ�պ���������
		while(head.next != null && head.next.next != null) {
			if(head.next.val == head.next.next.val) {
				//�������ֵ��һֱ���±�����ֱ��head.next.val���ڵ������ֵ
				//����head����û�б仯���仯��ֻ��head.next
				int val = head.next.val;
				while(head.next.val == val) {
					head.next = head.next.next;
				}
			//�����ʾ��һ���պúϸ�
			}else {
				head = head.next;
			}
		}
		return result.next;
	}
	
	public ListNode soulutionFromJiuZhang(ListNode head) {
		if(head == null || head.next == null)
            return head;
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;

        while (head.next != null && head.next.next != null) {
            if (head.next.val == head.next.next.val) {
                int val = head.next.val;
                while (head.next != null && head.next.val == val) {
                    head.next = head.next.next;
                }            
            } else {
                head = head.next;
            }
        }
        
        return dummy.next;
	}

	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(1);
		head.next.next = new ListNode(2);
		head.next.next.next = new ListNode(3);
		head.next.next.next.next = new ListNode(3);
		head.next.next.next.next.next = new ListNode(4);
		ListNode result = deleteDuplicates(head);
		while (result != null) {
			System.out.println(result.val);
			result = result.next;
		}
	}

}

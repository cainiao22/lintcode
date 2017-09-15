package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��9��11�� ����2:59:29
 * @description ���һ�ַ�ʽ���һ�������Ƿ�Ϊ��������O(n)��ʱ���O(1)�Ķ���ռ�
 * @example 1->2->1 ����һ����������
 *
 * @Solution 1��ջ������������������ջ������ջ֮ǰ���ж�ջ���Ƿ��뽫Ҫ��ջ��Ԫ����ȡ������ȣ��͵�ջ�������ַ�ʽֻ�ʺϸ���Ԫ�ؾ�����ȵ����)
 * 			 2��ջ�����Ľ����ҵ��м�λ�ã��м�λ��֮ǰ��ȫ����ջ��Ȼ����м�λ��֮����ж���ȣ���ʼ��ջ��ֻҪ�в���ȵľʹ����ǣ����ջΪ�գ��������
 * 	         3����ȻҪ�ҵ��м�λ�ã����м���ǰ��Ԫ��ȫ������Ȼ�������ж��Ƿ����
 */
public class �������� {
	
	public static boolean isPalindrome(ListNode head) {
        // write your code here
		if(head == null || head.next == null) {
			return true;
		}
		int num=0;
		ListNode cur = head;
		while(cur != null) {
			cur = cur.next;
			num ++;
		}
		//todo �������̫���ˡ���ô�������ϵ��ձ������ȥ
		if(num == 2) {
			return head.val == head.next.val;
		}
		if(num == 3) {
			return head.val == head.next.next.val;
		}
		int mid = num/2;
		cur = head;
		ListNode temp = cur.next;
		ListNode midHead = temp.next;
		for(int i=1; i< mid; i++) {
			midHead = temp.next;
			temp.next = cur;
			cur = temp;
			temp = midHead;
		}
		if(num%2  ==1) {
			midHead = midHead.next;
		}
		while(midHead != null) {
			if(cur.val != midHead.val) {
				return false;
			}
			cur = cur.next;
			midHead = midHead.next;
		}
		
		return true;
    }
	
	public boolean isPalindromeFromJiuZhang(ListNode head) {
        if (head == null) {
            return true;
        }
        
        ListNode middle = findMiddle(head);
        middle.next = reverse(middle.next);
        
        ListNode p1 = head, p2 = middle.next;
        while (p1 != null && p2 != null && p1.val == p2.val) {
            p1 = p1.next;
            p2 = p2.next;
        }
        
        return p2 == null;
    }
    
	/**
	 * �������666��������������һ����2λ�ٶ�ǰ����һ����1λ�ٶ�ǰ����
	 * ������Ǹ������յ�ʱ�������Ǹ��͵������м�
	 * @param head
	 * @return
	 */
    private ListNode findMiddle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return slow;
    }
    
    //��ת����
    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        
        while (head != null) {
            ListNode temp = head.next;
            head.next = prev;
            prev = head;
            head = temp;
        }
        
        return prev;
    }
	
	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		//head.next.next.next = new ListNode(2);
		//head.next.next.next.next = new ListNode(1);
		System.out.println(isPalindrome(head));
	}

}

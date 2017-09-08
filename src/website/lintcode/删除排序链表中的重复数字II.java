package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年9月8日 上午10:35:40
 * @description 给定一个排序链表，删除所有重复的元素只留下原链表中没有重复的元素。
 * @example 给出 1->2->3->3->4->4->5->null，返回 1->2->5->null
 * 
 *          给出 1->1->1->2->3->null，返回 2->3->null
 *
 * @Solution 1、计数法。相同的话。num+1，最后判断num是否为0,如果为0则添加
 * 			 2、直接判断，添加
 */
public class 删除排序链表中的重复数字II {

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
			//这里需要注意下
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
		//一次循环过后，head本身并没有移动。只是他的next进入了下一个校验，如果此时head.next.next=null
		//表示head后面刚好只有一个元素，这个元素也刚好满足需求。
		while(head.next != null && head.next.next != null) {
			if(head.next.val == head.next.next.val) {
				//保留这个值，一直往下遍历，直到head.next.val不在等于这个值
				//但是head本身并没有变化，变化的只是head.next
				int val = head.next.val;
				while(head.next.val == val) {
					head.next = head.next.next;
				}
			//这里表示下一个刚好合格
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

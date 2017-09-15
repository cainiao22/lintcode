package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年9月11日 下午2:59:29
 * @description 设计一种方式检查一个链表是否为回文链表。O(n)的时间和O(1)的额外空间
 * @example 1->2->1 就是一个回文链表
 *
 * @Solution 1、栈操作，将链表依次入栈。在入栈之前先判断栈顶是否与将要入栈的元素相等。如果相等，就弹栈。（这种方式只适合各个元素均不相等的情况)
 * 			 2、栈操作改进，找到中间位置，中间位置之前的全部入栈。然后从中间位置之后的判断相等，开始弹栈。只要有不相等的就代表不是，最后栈为空，代表就是
 * 	         3、依然要找到中间位置，将中间以前的元素全部逆序，然后依次判断是否相等
 */
public class 回文链表 {
	
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
		//todo 特殊情况太多了。怎么把这俩合到普遍情况下去
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
	 * 这个方法666啊。两个步长。一个以2位速度前进，一个以1位速度前进。
	 * 当快的那个到达终点时候，慢的那个就到达了中间
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
    
    //翻转函数
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

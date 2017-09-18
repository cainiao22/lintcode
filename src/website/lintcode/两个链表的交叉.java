package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年9月14日 下午3:37:42
 * @description
 * 			
		请写一个程序，找到两个单链表最开始的交叉节点。
		注意事项
		
		    如果两个链表没有交叉，返回null。
		    在返回结果后，两个链表仍须保持原有的结构。
		    可假定整个链表结构中没有循环。


 * @example
			列两个链表：
			A:          a1 → a2
			                   	↘
			                     c1 → c2 → c3
			                   	↗            
			B:     b1 → b2 → b3
			
			在节点 c1 开始交叉。
 * 		
 *
 * @Solution 1、先将两个链表分别逆序。从尾部开始匹配。直到匹配的不一致为止
 * 			 2、 先求出两个链表长度。将长链表的前面多出来忽略。从两个链表长度相等的地方开始匹配。666666666666666
 * 			 3、九章答案。将两个链表连接在一起，
 */
public class 两个链表的交叉 {
	
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // Write your code here
		int lengthA = 0, lengthB = 0;
		ListNode a = headA;
		while(a != null) {
			lengthA ++;
			a = a.next;
		}
		ListNode b = headB;
		while(b != null) {
			lengthB ++;
			b = b.next;
		}
		if(lengthA >= lengthB) {
			a = headA;
			b = headB;
		}else {
			a = headB;
			b = headA;
		}
		int cha = Math.abs(lengthA - lengthB);
		while(cha > 0) {
			a = a.next;
			cha --;
		}
		while(a != null && b != null) {
			if(a == b) {
				return a;
			}
			a = a.next;
			b = b.next;
		}
		
		return null;
    }  
	
	public ListNode getIntersectionNodeFromJiuZhang(ListNode headA, ListNode headB) {
		return null;
	}
	
	public static void main(String[] args) {
		new 两个链表的交叉().getIntersectionNode(new ListNode(1), null);
	}

}

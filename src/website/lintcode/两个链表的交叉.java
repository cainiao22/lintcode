package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��9��14�� ����3:37:42
 * @description
 * 			
		��дһ�������ҵ������������ʼ�Ľ���ڵ㡣
		ע������
		
		    �����������û�н��棬����null��
		    �ڷ��ؽ���������������뱣��ԭ�еĽṹ��
		    �ɼٶ���������ṹ��û��ѭ����


 * @example
			����������
			A:          a1 �� a2
			                   	�K
			                     c1 �� c2 �� c3
			                   	�J            
			B:     b1 �� b2 �� b3
			
			�ڽڵ� c1 ��ʼ���档
 * 		
 *
 * @Solution 1���Ƚ���������ֱ����򡣴�β����ʼƥ�䡣ֱ��ƥ��Ĳ�һ��Ϊֹ
 * 			 2�� ��������������ȡ����������ǰ���������ԡ���������������ȵĵط���ʼƥ�䡣666666666666666
 * 			 3�����´𰸡�����������������һ��
 */
public class ��������Ľ��� {
	
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
		new ��������Ľ���().getIntersectionNode(new ListNode(1), null);
	}

}

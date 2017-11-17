package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��11��14�� ����12:35:19
 * @description
 * 		��ϣ�������Ĵ�С��һ��ʼ�ǲ�ȷ���ġ������ϣ��洢��Ԫ��̫�ࣨ�糬��������ʮ��֮һ����
 * 		����Ӧ�ý���ϣ����������һ�����������еĹ�ϣֵ���°��š�������������һ��ϣ��
		size=3, capacity=4
		
		 [null, 21, 14, null]
	       ��    ��
	       9   null
	       ��
	      null

		��ϣ����Ϊ��
		
		int hashcode(int key, int capacity) {
		    return key % capacity;
		}
		
		��������������9��14��21������21��9����ͬһ��λ����Ϊ��������ͬ�Ĺ�ϣֵ1(21 % 4 = 9 % 4 = 1)�����ǽ����Ǵ洢��ͬһ�������С�
		
		�ؽ���ϣ������������һ�������ǽ���õ���
		
		size=3, capacity=8
		
		index:   0    1    2    3     4    5    6   7
		hash : [null, 9, null, null, null, 21, 14, null]
		
		����һ����ϣ�������ع�ϣ��Ĺ�ϣ��
		
 * @example
 *		���� [null, 21->9->null, 14->null, null]

		���� [null, 9->null, null, null, null, 21->null, 14->null, null
 * @Solution
 */
public class �ع�ϣ extends HH {
	public static ListNode[] rehashing(ListNode[] hashTable) {
        // write your code here
		ListNode[] result = new ListNode[hashTable.length * 2];
		for(int i=0; i<hashTable.length; i++) {
			ListNode temp = hashTable[i];
			while(temp != null) {
				int hash = hashcode(temp.val, result.length);
				if(result[hash] == null) {
					result[hash] = new ListNode(temp.val);
				}else {
					ListNode tail = result[hash];
					while(tail.next != null) {
						tail = tail.next;
					}
					tail.next = new ListNode(temp.val);
				}
				temp = temp.next;
			}
		}
		return result;
    }
	
	static int hashcode(int key, int capacity) {
	    return (key % capacity + capacity) % capacity;
	}
}

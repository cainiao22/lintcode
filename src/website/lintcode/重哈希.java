package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年11月14日 下午12:35:19
 * @description
 * 		哈希表容量的大小在一开始是不确定的。如果哈希表存储的元素太多（如超过容量的十分之一），
 * 		我们应该将哈希表容量扩大一倍，并将所有的哈希值重新安排。假设你有如下一哈希表：
		size=3, capacity=4
		
		 [null, 21, 14, null]
	       ↓    ↓
	       9   null
	       ↓
	      null

		哈希函数为：
		
		int hashcode(int key, int capacity) {
		    return key % capacity;
		}
		
		这里有三个数字9，14，21，其中21和9共享同一个位置因为它们有相同的哈希值1(21 % 4 = 9 % 4 = 1)。我们将它们存储在同一个链表中。
		
		重建哈希表，将容量扩大一倍，我们将会得到：
		
		size=3, capacity=8
		
		index:   0    1    2    3     4    5    6   7
		hash : [null, 9, null, null, null, 21, 14, null]
		
		给定一个哈希表，返回重哈希后的哈希表。
		
 * @example
 *		给出 [null, 21->9->null, 14->null, null]

		返回 [null, 9->null, null, null, null, 21->null, 14->null, null
 * @Solution
 */
public class 重哈希 extends HH {
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

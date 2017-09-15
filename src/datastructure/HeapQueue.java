package datastructure;

import java.util.Comparator;

/**
 * 
 * @author yanpf
 * @date 2017年9月14日 下午12:47:45
 * @description
 * @example
 *
 * @Solution
 * @param <E>
 */
public class HeapQueue<E> {
	
	private Comparator<E> comparator;
	
	private E[] queue;
	
	private int lastIndex;
	
	public void add(E e) {
		if(lastIndex == 0) {
			queue[lastIndex] = e;
			lastIndex ++;
			return;
		}
		queue[lastIndex] = e;
		int parent = (lastIndex-1) >>> 1;
		int k = lastIndex;
		while(k>0 && comparator.compare(e, queue[parent]) < 0) {
			queue[k] = queue[parent];
			k = parent;
			parent = (k-1) >>> 1;
		}
		queue[parent] = e;
		lastIndex ++;
	}
	
	public E peek() {
		if(lastIndex == 0) {
			return null;
		}
		return queue[0];
	}
	
	public E poll() {
		if(lastIndex == 0) {
			return null;
		}
		E top = peek();
		lastIndex --;
		queue[0] = queue[lastIndex];
		int k = 0;
		while(k  <= lastIndex/2) {
			int min = k;
			if( min*2 + 1 <= lastIndex && comparator.compare(queue[min*2 + 1],queue[min]) < 0) {
				min = min*2 + 1;
			}
			
			if(min*2 + 2 <= lastIndex && comparator.compare(queue[min*2 + 2],queue[min]) < 0) {
				min = min*2 + 2;
			}
			if(min != k) {
				swap(queue[k], queue[min]);
				k = min;
			}else {
				break;
			}
		}
		
		return top;
	}
	
	private void swap(E e1, E e2) {
		E temp = e1;
		e1 = e2;
		e2 = temp;
	}

}

package datastructure.humdrumQueue;

import java.util.ArrayList;
import java.util.List;

/**
 * �����ݼ�����
 * @author yanpf
 * @date 2017��8��24�� ����3:55:35
 * @description ��ʵ���ֱ��������Ϳ���ģ�����
 * @example
 *
 * @Solution
 */
public class HumdrumQueue<T extends Comparable> {
	
	private int size;
	
	public List<HumdrumQueueNode<T>> queue;
	
	public static class HumdrumQueueNode<T> {
		private int index;
		private T data;
		
		public HumdrumQueueNode(int index, T data){
			this.index = index;
			this.data = data;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}

		public T getData() {
			return data;
		}

		public void setData(T data) {
			this.data = data;
		}
	}

	public HumdrumQueue(int size) {
		this.size = size;
		queue = new ArrayList<>(size);
	}
	
	public void add(int index, T data) {
		if(queue.size() == size) {
			queue.remove(0);
		}
		for(int i=queue.size(); i> 0; i--) {
			HumdrumQueueNode<T> tail = queue.get(queue.size()-1);
			if(tail.data.compareTo(data) <= 0) {
				queue.remove(queue.size()-1);
			}else {
				break;
			}
		}
		queue.add(new HumdrumQueueNode<T>(index, data));
	}
	
	public HumdrumQueueNode<T> get(){
		if(queue != null && !queue.isEmpty()) {
			return queue.get(0);
		}
		
		return null;
	}
	
	//todo �߶������������
	public void insertBetween(int begin, int end, int value) {
		
	}
	
}

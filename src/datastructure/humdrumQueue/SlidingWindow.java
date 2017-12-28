package datastructure.humdrumQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import datastructure.humdrumQueue.HumdrumQueue.HumdrumQueueNode;

/**
 * 
 * @author yanpf
 * @date 2017��8��24�� ����3:47:41
 * @description ����һ������ΪN����������a(i),i=0,1,...,N-1�ʹ�����k.

				Ҫ��
				
				      f(i) = max{a(i-k+1),a(i-k+2),..., a(i)},i = 0,1,...,N-1
				
				�������һ������������һ������Ϊk�Ĵ��������������ƶ������������������������ֵ��
				
 * @example
 *
 * @Solution ��򵥵ĵ������нⷨ
 */
public class SlidingWindow {
	
	/**
	 * 
	 * @param k ���ڴ�С
	 * @param num
	 * @return
	 */
	public List<Integer> getMaxNumForK(int k, int[] num){
		List<Integer> result = new ArrayList<>();
		HumdrumQueue<Integer> queue = new HumdrumQueue<>(k);
		for(int i=0; i<num.length; i++) {
			queue.add(i, num[i]);
			result.add(queue.get().getData());
			queue.queue.forEach(new Consumer<HumdrumQueueNode<Integer>>() {

				@Override
				public void accept(HumdrumQueueNode<Integer> t) {
					System.out.print(t.getData() + "\t");
				}
			});
			System.out.println();
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		int[] nums = new int[] {1, 3, -1, -3, 5, 3, 6, 7};
		List<Integer> result = new SlidingWindow().getMaxNumForK(3, nums);
		System.out.println("*************************");
		result.forEach(new Consumer<Integer>() {

			@Override
			public void accept(Integer t) {
				System.out.print(t + "\t");
			}
		});
		System.out.println();
		
	}
	
}


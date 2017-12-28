package datastructure.humdrumQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import datastructure.humdrumQueue.HumdrumQueue.HumdrumQueueNode;

/**
 * 
 * @author yanpf
 * @date 2017年8月24日 下午3:47:41
 * @description 给定一个长度为N的整数数列a(i),i=0,1,...,N-1和窗长度k.

				要求：
				
				      f(i) = max{a(i-k+1),a(i-k+2),..., a(i)},i = 0,1,...,N-1
				
				问题的另一种描述就是用一个长度为k的窗在整数数列上移动，求窗里面所包含的数的最大值。
				
 * @example
 *
 * @Solution 最简单的单调对列解法
 */
public class SlidingWindow {
	
	/**
	 * 
	 * @param k 窗口大小
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


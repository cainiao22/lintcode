package website.lintcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 
 * @author yanpf
 * @date 2017年9月14日 上午11:23:01
 * @description 数字是不断进入数组的，在每次添加一个新的数进入数组的同时返回当前新数组的中位数。
 * 
 * @example	持续进入数组的数的列表为：[1, 2, 3, 4, 5]，则返回[1, 1, 2, 2, 3]

			持续进入数组的数的列表为：[4, 5, 1, 3, 2, 6, 0]，则返回 [4, 4, 4, 3, 3, 3, 3]
			
			持续进入数组的数的列表为：[2, 20, 100]，则返回[2, 2, 20]
 *
 * @Solution 使用两个堆。一个大顶堆，一个小顶堆。使用哪种你一个我一个的分配方式，重要的是要保证大顶堆的堆顶小于小顶堆的堆顶
 * 			 这样大顶堆的堆顶就是中位数
 */
public class 数据流中位数 extends HH {
	
	public int[] medianII(int[] nums) {
        // write your code here
		if(nums.length < 2) {
			return nums;
		}
		int[] result = new int[nums.length];
		//顶上放最小的
		PriorityQueue<Integer> minHeap = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o1 - o2;
			}
		});
		//顶上放最大的
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o2 - o1;
			}
		});
		maxHeap.add(nums[0]);
		minHeap.add(nums[1]);
		if(maxHeap.peek() > minHeap.peek()) {
			int a1 = maxHeap.poll();
			int b1 = minHeap.poll();
			maxHeap.add(b1);
			minHeap.add(a1);
		}
		result[0] = nums[0];
		result[1] = maxHeap.peek();
		for(int i=2;i<nums.length; i++) {
			if(i%2 == 1) {
				minHeap.add(nums[i]);
			}else {
				maxHeap.add(nums[i]);
			}
			if(maxHeap.peek() > minHeap.peek()) {
				int a1 = maxHeap.poll();
				int b1 = minHeap.poll();
				maxHeap.add(b1);
				minHeap.add(a1);
			}
			result[i] = maxHeap.peek();
		}
		
		return result;
    }
	
	 	private PriorityQueue<Integer> maxHeap, minHeap;
	    private int numOfElements = 0;

	    public int[] medianIIFromJiuZhang(int[] nums) {
	        // write your code here
	        Comparator<Integer> revCmp = new Comparator<Integer>() {
	            @Override
	            public int compare(Integer left, Integer right) {
	                return right.compareTo(left);
	            }
	        };
	        int cnt = nums.length;
	        maxHeap = new PriorityQueue<Integer>(cnt, revCmp);
	        minHeap = new PriorityQueue<Integer>(cnt);
	        int[] ans = new int[cnt];
	        for (int i = 0; i < cnt; ++i) {
	            addNumber(nums[i]);
	            ans[i] = getMedian();
	        }
	        return ans;
	    }
	    void addNumber(int value) {
	    	//这个方法的原理和上面一样，只是添加方式不同，首先直接将value添加到大顶堆上面。
	    	//根据添加个数的奇偶性不同，如果是偶数需要将大顶堆和小顶堆的堆顶做个比较，如果大顶堆的堆顶大于小顶堆。两者交换
	    	//实质是将数据加入了大顶堆里面。如果是奇数，将大顶堆的堆顶弹出，加入到小顶堆中。实质是将数据加入了小顶堆里面，
	        maxHeap.add(value);
	        if (numOfElements%2 == 0) {
	            if (minHeap.isEmpty()) {
	                numOfElements++;
	                return;
	            }
	            else if (maxHeap.peek() > minHeap.peek()) {
	                Integer maxHeapRoot = maxHeap.poll();
	                Integer minHeapRoot = minHeap.poll();
	                maxHeap.add(minHeapRoot);
	                minHeap.add(maxHeapRoot);
	            }
	        }
	        else { //这里和上面那个方法比起来省略了比较操作
	            minHeap.add(maxHeap.poll());
	        }
	        numOfElements++;
	    }
	    int getMedian() {
	        return maxHeap.peek();
	    }
	
	public static void main(String[] args) {
		int[] nums = new int[] {4, 5, 1, 3, 2, 6, 0};
		int[] result = new 数据流中位数().medianII(nums);
		print(result);
	}
}

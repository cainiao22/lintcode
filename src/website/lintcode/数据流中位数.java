package website.lintcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 
 * @author yanpf
 * @date 2017��9��14�� ����11:23:01
 * @description �����ǲ��Ͻ�������ģ���ÿ�����һ���µ������������ͬʱ���ص�ǰ���������λ����
 * 
 * @example	������������������б�Ϊ��[1, 2, 3, 4, 5]���򷵻�[1, 1, 2, 2, 3]

			������������������б�Ϊ��[4, 5, 1, 3, 2, 6, 0]���򷵻� [4, 4, 4, 3, 3, 3, 3]
			
			������������������б�Ϊ��[2, 20, 100]���򷵻�[2, 2, 20]
 *
 * @Solution ʹ�������ѡ�һ���󶥶ѣ�һ��С���ѡ�ʹ��������һ����һ���ķ��䷽ʽ����Ҫ����Ҫ��֤�󶥶ѵĶѶ�С��С���ѵĶѶ�
 * 			 �����󶥶ѵĶѶ�������λ��
 */
public class ��������λ�� extends HH {
	
	public int[] medianII(int[] nums) {
        // write your code here
		if(nums.length < 2) {
			return nums;
		}
		int[] result = new int[nums.length];
		//���Ϸ���С��
		PriorityQueue<Integer> minHeap = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o1 - o2;
			}
		});
		//���Ϸ�����
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
	    	//���������ԭ�������һ����ֻ����ӷ�ʽ��ͬ������ֱ�ӽ�value��ӵ��󶥶����档
	    	//������Ӹ�������ż�Բ�ͬ�������ż����Ҫ���󶥶Ѻ�С���ѵĶѶ������Ƚϣ�����󶥶ѵĶѶ�����С���ѡ����߽���
	    	//ʵ���ǽ����ݼ����˴󶥶����档��������������󶥶ѵĶѶ����������뵽С�����С�ʵ���ǽ����ݼ�����С�������棬
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
	        else { //����������Ǹ�����������ʡ���˱Ƚϲ���
	            minHeap.add(maxHeap.poll());
	        }
	        numOfElements++;
	    }
	    int getMedian() {
	        return maxHeap.peek();
	    }
	
	public static void main(String[] args) {
		int[] nums = new int[] {4, 5, 1, 3, 2, 6, 0};
		int[] result = new ��������λ��().medianII(nums);
		print(result);
	}
}

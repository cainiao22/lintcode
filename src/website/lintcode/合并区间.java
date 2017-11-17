package website.lintcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 
 * @author yanpf
 * @date 2017��11��13�� ����6:21:28
 * @description
 * 		�������ɱպ����䣬�ϲ������ص��Ĳ��֡�
 * 		O(n log n) ��ʱ��� O(1) �Ķ���ռ䡣
 * @example
 * 		�����������б� => �ϲ���������б�

		[                     [
		  [1, 3],               [1, 6],
		  [2, 6],      =>       [8, 10],
		  [8, 10],              [15, 18]
		  [15, 18]            ]
		]

 *
 * @Solution
 */
public class �ϲ����� {
	
	public static class Interval {
	      int start, end;
	      Interval(int start, int end) {
	         this.start = start;
	          this.end = end;
	      }
	      
	      @Override
	    public String toString() {
	    	return start + " " + end;
	    }
	}
	 
	
	public List<Interval> merge(List<Interval> intervals) {
        // write your code here
		Interval[] arr = new Interval[intervals.size()];
		Arrays.sort(intervals.toArray(arr), new Comparator<Interval>() {

			@Override
			public int compare(Interval o1, Interval o2) {
				return o1.start - o2.start;
			}
		});
		
		List<Interval> result = new ArrayList<>();
		Interval current = arr[0];
		for(int i=1; i<arr.length; i++) {
			if(arr[i].start <= current.end) {//��ô�򵥵Ĳ������Ҳ�
				current.end = Math.max(arr[i].end, current.end);
			}else {
				result.add(current);
				current = arr[i];
			}
		}
		result.add(current);
		return result;
    }
	
	public static void main(String[] args) {
		List<Interval> intervals = new ArrayList<>();
		intervals.add(new Interval(1, 6));
		intervals.add(new Interval(8, 10));
		intervals.add(new Interval(2, 3));
		intervals.add(new Interval(15, 18));
		//intervals.add(new Interval(1, 10));
		new �ϲ�����().merge(intervals);
	}

}

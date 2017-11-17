package website.lintcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 
 * @author yanpf
 * @date 2017年11月13日 下午6:21:28
 * @description
 * 		给出若干闭合区间，合并所有重叠的部分。
 * 		O(n log n) 的时间和 O(1) 的额外空间。
 * @example
 * 		给出的区间列表 => 合并后的区间列表：

		[                     [
		  [1, 3],               [1, 6],
		  [2, 6],      =>       [8, 10],
		  [8, 10],              [15, 18]
		  [15, 18]            ]
		]

 *
 * @Solution
 */
public class 合并区间 {
	
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
			if(arr[i].start <= current.end) {//那么简单的操作。我擦
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
		new 合并区间().merge(intervals);
	}

}

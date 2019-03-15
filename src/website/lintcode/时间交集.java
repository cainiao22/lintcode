package website.lintcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import website.lintcode.合并区间.Interval;

/**
 * 
 * @author yanpf
 * @date 2019年3月13日 下午4:39:13
 * @description
 * 		目前有两个用户的有序在线时间序列，每一个区间记录了该用户的登录时间点x和离线时间点y，
 * 		请找出这两个用户同时在线的时间段，输出的时间段请从小到大排序。
 * @example
 *
 * @Solution
 */
public class 时间交集 {
	
	public List<Interval> timeIntersection(List<Interval> seqA, List<Interval> seqB) {
        int i = 0, j =0;
        List<Interval> result = new ArrayList<>();
        while(i < seqA.size() && j < seqB.size()) {
        	Interval i1 = seqA.get(i), i2 = seqB.get(j);
        	int flag = compare(i1, i2);
        	if(flag == 0) {
        		Interval interval = new Interval(Math.max(i1.start, i2.start), Math.min(i1.end, i2.end));
        		result.add(interval);
        		if(i1.start < i2.start && i1.end < i2.end) {
        			i ++;
        		}
        	}
        	if(i1.end <= i2.end) {
        		i ++;
        	}else {
        		j ++;
        	}
        }
        
        return result;
    }
	
	int compare(Interval i1, Interval i2) {
		if(i1.end <= i2.start) {
			return -1;
		}else if(i1.start >= i2.end) {
			return 1;
		}else {
			return 0;
		}
	}
	
	/**
	 * 一个比较蠢的算法，但是思路很有趣
	 * @param seqA
	 * @param seqB
	 * @return
	 */
	 public List<Interval> timeIntersection2(List<Interval> seqA, List<Interval> seqB) {
	        // Write your code here
	        int [] visit = new int[1000001];
	        for(int i = 0; i < 1000001; i++) {
	            visit[i] = 0;
	        }
	        for(int i = 0; i < seqA.size(); i++) {
	            for(int j = seqA.get(i).start; j <= seqA.get(i).end; j++) {
	                visit[j] ++;
	            }
	        }
	        for(int i = 0; i < seqB.size(); i++) {
	            for(int j = seqB.get(i).start; j <= seqB.get(i).end; j++) {
	                visit[j] ++;
	            }
	        }
	        List<Interval> ans = new ArrayList<>();
	        for(int i = 0; i < 1000001; i++) {
	            if(visit[i] >= 2) {
	                int x = i;
	                int y = i;
	                while(y + 1 < 1000001 && visit[y + 1] >= 2) {
	                    y++;
	                }
	                ans.add(new Interval(x, y));
	                i = y + 1;
	            }
	        }
	        return ans;
	    }
	 
	 /**
	  * 一个比较有意思的线性扫描算法
	  * @param seqA
	  * @param seqB
	  * @return
	  */
	 
	 public List<Interval> timeIntersection3(List<Interval> seqA, List<Interval> seqB) {
		 List<Event> events = new ArrayList<>();
		 List<Interval> result = new ArrayList<>();
		 for(Interval interval : seqA) {
			 events.add(new Event(interval.start, Event.START));
			 events.add(new Event(interval.end, Event.END));
		 }
		 
		 for(Interval interval : seqB) {
			 events.add(new Event(interval.start, Event.START));
			 events.add(new Event(interval.end, Event.END));
		 }
		 
		 Collections.sort(events);
		 int count = 0;
		 Integer start = null, end = null;
		 for(Event event : events) {
			 if(event.type == Event.START) {
				 count ++;
			 }else {
				 if(count == 2) {
					 end = event.time;
				 }
				 count --;
			 }
			 if(count == 2) {
				 start = event.time;
			 }
			 
			 if(start != null && end != null) {
				 result.add(new Interval(start, end));
				 start = null;
				 end = null;
			 }
		 }
		 
		 
		 return result;
	 }

}

class Event implements Comparable<Event> {

	static final int START = 0;
	static final int END = 1;
	int time, type;
	
	public Event(int time, int type){
        this.time = time;
        this.type = type;
    }
	@Override
	public int compareTo(Event o) {
		if(this.time != o.time) {
			return this.time - o.time;
		}
		return this.type - o.type;
	}

}

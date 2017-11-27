package website.lintcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import website.lintcode.合并区间.Interval;

/**
 * 
 * @author yanpf
 * @date 2017年11月17日 下午6:05:02
 * @description
 * 		给出飞机的起飞和降落时间的列表，用 interval 序列表示. 请计算出天上同时最多有多少架飞机？
 * 		如果多架飞机降落和起飞在同一时刻，我们认为降落有优先权
 * @example
 *		对于每架飞机的起降时间列表：[[1,10],[2,3],[5,8],[4,7]], 返回3。
 * @Solution
 */
public class 数飞机 extends HH {
	
	/**
	 * 超时了
	 * @param airplanes
	 * @return
	 */
	public int countOfAirplanes(List<Interval> airplanes) {
        // write your code here
		if(airplanes == null) {
			return 0;
		}
		if(airplanes.size() < 2) {
			return airplanes.size();
		}
		int[] nums = new int[airplanes.size()+1];
		Collections.sort(airplanes, new Comparator<Interval>() {

			@Override
			public int compare(Interval o1, Interval o2) {
				return o1.start - o2.start;
			}
		});
		println(airplanes);
		nums[1] = 1;
		for(int i=1; i<airplanes.size(); i++) {
			Interval temp = airplanes.get(i);
			int j = i-1;
			int max = 1;
			while(j >= 0) {
				if(airplanes.get(j).end > temp.start) {
					max ++;
				}
				j--;
			}
			nums[i+1] = max;
		}
		int max = 0;
		for(int i=0; i<nums.length; i++) {
			max = Math.max(nums[i], max);
		}
		print(nums);
		return max;
    }
	
	static class Point implements Comparable<Point> {
		int flag;
		int time;
		
		@Override
		public int compareTo(Point o) {
			if(this.time == o.time) {
				return this.flag - o.flag;
			}else {
				return this.time - o.time;
			}
		}

		public Point(int flag, int time) {
			super();
			this.flag = flag;
			this.time = time;
		}
		
		
	}
	
	public int countOfAirplanesFromJiuZhang(List<Interval> airplanes) {
		int max = 0;
		if(airplanes == null) {
			return 0;
		}
		if(airplanes.size() < 2) {
			return airplanes.size();
		}
		List<Point> list = new ArrayList<>();
		for(Interval interval : airplanes) {
			list.add(new Point(1, interval.start));
			list.add(new Point(-1, interval.end));
		}
		Collections.sort(list);
		
		int count =0;
		for(Point point : list) {
			if(point.flag == 1) {
				count ++;
			}else {
				count --;
			}
			max = Math.max(max, count);
		}
		return max;
	}
	
	public static void main(String[] args) {
		String datas = "[52,61],[54,59],[34,35],[28,37],[94,97],[96,100],[76,85],[77,87],[77,87],[21,24],[9,15],[45,55],[99,103],[58,66],[35,43],[23,27],[40,49],[45,49],[13,19],[37,42],[31,32],[5,11],[83,89],[90,97],[36,41],[90,97],[91,98],[98,101],[93,102],[80,85],[60,62],[72,79],[85,87],[77,86],[93,103],[64,74],[62,69],[77,78],[71,74],[47,53],[21,29],[43,50],[30,34],[68,75],[53,61],[3,10],[8,9],[14,21],[32,40],[10,15],[91,96],[6,11],[16,23],[27,31],[51,58],[73,74],[98,106],[51,58],[10,17],[24,34]";
		List<Interval> airplanes = new ArrayList<>();
		String[] splited = datas.split("],");
		for(String item : splited) {
			item = item.replace("[", "").replace("]", "");
			String[] result = item.split(",");
			airplanes.add(new Interval(Integer.valueOf(result[0]), Integer.valueOf(result[1])));
		}
		
		print(new 数飞机().countOfAirplanes(airplanes));
	}

}

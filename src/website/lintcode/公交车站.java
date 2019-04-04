package website.lintcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 
 * @author yanpf
 * @date 2019年3月29日 下午3:01:57
 * @description
 * 		现在有一个城市的 N 个公车信息，route[i] 储存着第 i 辆公交车经过的公交车站，请你求车站 A 到车站 B 的最少换乘，
 * 		如果无法从车站 A 到达车站 B 返回 -1。

		1 <= N <= 100, 2 <= |route[i]| <= 100, 0 <= route[i][j] <= 2^31 - 1
		A 和 B 两个车站一定存在，且不相等
 * @example
 *		给出 N = 2, route = [[1, 2, 3, 4], [3, 5, 6, 7]], A = 1, B = 4, 返回 1。

		解释：
		我们只需要坐公车 0 号，就可以从车站 0 到达车站 3。
		给出 N = 2, route = [[1, 2, 3, 4], [3, 5, 6, 7]], A = 1, B = 7, 返回 2。
		
		解释：
		我们需要从车站 0 坐公车 0 号，然后在车站 2 换乘公车 1 号到达车站 6。
 * @Solution
 */
public class 公交车站 {
	
	int MIN_VALUE = Integer.MAX_VALUE - 100;
	
	 public int getMinTransferNumber(int N, int[][] route, int A, int B) {
	        // Write your code here
		 	Map<Integer, HashSet<Integer>> map = new HashMap<>();
		 	for(int i=0; i<route.length; i++) {
		 		for(int j=0; j<route[i].length; j++) {
		 			if(map.get(route[i][j]) == null){
		 				map.put(route[i][j], new HashSet<>());
		 			}
		 			map.get(route[i][j]).add(i);
		 		}
		 	}
		 	boolean[] cars = new boolean[N];
		 	HashSet<Integer> stations = new HashSet<>();
		 	int min = selectCars(A, route, map, cars, stations, B);
		 	return min >= MIN_VALUE ? -1 : min;
		 	
	}
	 
	 private int selectCars(int station, int[][] route, Map<Integer, HashSet<Integer>> map, boolean[] cars, HashSet<Integer> stations, int B) {
		 
		 if(station == B) {
			 return 0;
		 }
		 
		 HashSet<Integer> carsSet = map.get(station);
		 int min = MIN_VALUE;
		 for(int car : carsSet) {
			 if(!cars[car]) {
				 cars[car] = true;
				 int item = selectStation(car, route, map, cars, stations, B);
				 min = Integer.min(min, item + 1);
				 cars[car] = false;
			 }
		 }
		 
		 return min;
	 }
	 
	 private int selectStation(int car, int[][] route,Map<Integer, HashSet<Integer>> map, boolean[] cars, HashSet<Integer> stations, int B) {
		 int[] stationList = route[car];
		 int min = MIN_VALUE;
		 for(int station : stationList) {
			 if(!stations.contains(station)) {
				 stations.add(station);
				 int item = selectCars(station, route, map, cars, stations, B);
				 min = Integer.min(min, item);
				 stations.remove(station);
			 }
		 }
		 
		 return min;
	 }
	 
	 public int getMinTransferNumberBFS(int N, int[][] route, int A, int B) {
		 //Set<Integer> set = new HashSet<>();
		 //set.add(A);
		 Queue<Integer> queue = new LinkedList<>();
		 Map<Integer, HashSet<Integer>> map = new HashMap<>();
	 	for(int i=0; i<route.length; i++) {
	 		for(int j=0; j<route[i].length; j++) {
	 			if(map.get(route[i][j]) == null){
	 				map.put(route[i][j], new HashSet<>());
	 			}
	 			map.get(route[i][j]).add(i);
	 		}
	 	}
		 queue.add(A);
		 int steps = 0;
		 boolean[] cars = new boolean[N];
		 while(!queue.isEmpty()) {
			 int len = queue.size();
			 for(int i=0; i<len; i++) {
				 int top = queue.poll();
				 if(top == B) {
					 return steps;
				 }
				 Set<Integer> carsSet = map.get(top);
				 for(int car : carsSet) {
					 if(!cars[car]) {
						 cars[car] = true;
						 int[] stations = route[car];
						 for(int station: stations) {
							 //这里可以优化掉  为什么会比放着效率高？？？
							 //if(!set.contains(station)) {
								 queue.add(station);
								 //set.add(station);
							 //}
						 }
					 }
				 }
				 
			 }
			 
			 steps ++;
		 }
		 
		 return -1;
	 }
	 
	 public static void main(String[] args) {
		 int N = 96;
		 int[][] route = new int[][] {{11121,4967,9834},{4529,11003,922,4610,8805},{9918,6228,8066,5574},{5550,4967},{5472,7245},{1262,3098,8805,1403},{6893,2174,1600},{11103,2742},{5471,5490},{3745,3455,135,11121,5574},{7177,5471,7116},{3098,3130,2174,5550},{1403,10399,749},{1753,1403},{12115,7212},{266,6848},{11410,5511,9629},{11826,4442,5511},{4538,1521,5471},{11003,8219,922},{5550,8805,8219},{3098,8805,1334,1908,1089},{10429,2174,1600,280},{5574,922,4967,1393,2742},{6848,7212,5471,6141,5550},{7177,6848},{6141,1521,8219,1908,9918},{5472,8652,8805},{1141,749,5723},{7177,6141,1896,11003},{5723,10033,8411,6228},{10969,3441},{8805,3130,7212},{3523,7245},{1089,3969},{1403,7238,10429,11410,3098},{1896,1908},{691,6990,11121,1896},{3128,2742,5511,12115,8411},{922,11103,5490,691},{266,11410,8411},{10429,1262,7116,11121},{7238,8411},{1262,4839,8219},{6990,135,4610,1089},{4538,922},{9834,6141},{10969,749},{6893,922,11103},{6141,11826,8066,4967},{3745,1262,10429,6848},{9820,5077,1089,6228},{7238,3455},{3128,922,1521,7212},{1393,4538,8411,5471,266},{7238,6141,1908,8938},{1403,8652},{1908,1403},{922,135,11081,266},{280,5550,266,541,1141},{1403,6228,6990,5511,11003},{7212,3128,9820},{1334,10033,9629,4610},{5678,4967,5550,749,6062},{9834,4538,12071,691,6062},{11003,1896,8652,1753,1089},{3098,11081,1393},{1753,266,1600,10429,11103},{8805,4538},{5472,1089,691},{135,5511,5550},{4610,4442,4529,6848},{8411,5574,7212},{5472,3098,6848},{2174,8938},{3523,8411,8978,12071,1334},{1403,1141,6228},{7238,10969},{6990,5511},{8805,4839,3098},{12071,7245,10429},{7710,4839,8978,5471,8805},{5490,1403},{7710,4529,10809,266,7116},{6228,3523,5472,691,4610},{4538,8652,8066},{8066,11410,541},{3969,749,10399,1141},{749,5550,11081},{5723,1896,10809,12115},{3969,12115,1908,9834},{5077,11003,5490,1393},{11103,135,1896,749,6848},{8211,10809},{3969,6062,280},{8066,1896}};
		 int result = new 公交车站().getMinTransferNumberBFS(N, route, 1403, 10809);
		 System.out.println(result);
	}

}

package website.lintcode;

import java.util.Arrays;

/**
 * 
 * @author yanpf
 * @date 2019年1月24日 下午7:35:59
 * @description
 * 		冬天来啦！你的任务是设计出一个具有固定加热半径的加热器，使得所有房屋在这个冬天不至于太冷。

		现在你能够获知所有房屋和加热器所处的位置，它们均分布在一条水平线中。你需要找出最小的加热半径使得所有房屋都处在至少一个加热器的加热范围内。
		
		所以，你的输入将会是所有房屋和加热器所处的位置，期望输出为加热器最小的加热半径。
		
		房屋和加热器的数目均为非负整数，并且它们不会超过25000。
		房屋和加热器的位置均为非负整数，并且它们不会超过10^9。
		只要一间房屋位于加热器的加热半径内（包括边界），它就会被加热。
		所有加热器的加热半径相同。
		
		
 * @example
 * 		样例1：

		输入：[1,2,3],[2]
		输出：1
		说明：唯一的一个加热器被放在2的位置，那么只要加热半径为1，就能覆盖到所有房屋了。
		样例2：
		
		输入：[1,2,3,4],[1,4]
		输出：1
		说明：两个加热器分别位于1和4，只需要加热半径为1，就能加热所有房屋了。
 *
 * @Solution
 */
public class 加热器 extends HH {
	//TODO 错的
	public int findRadius(int[] houses, int[] heaters) {
        // Write your code here
		sort(houses);
		sort(heaters);
		int max = 0;
		int min = heaters[0] - houses[0];
		min = Math.max( min, houses[houses.length - 1] - heaters[heaters.length - 1]);
		for(int i=0; i<heaters.length - 1; i++) {
			for(int j=i+1; j<heaters.length; j++) {
				max = Math.max((heaters[j] - heaters[i])/2, max);
			}
		}
		out:
		for(int t=min; t<= max || t == min; t++) {
			int j=0;
			inner:
			for(int i=0; i<houses.length; i++) {
				if(houses[i] <= heaters[j] + t && houses[i] >= heaters[j] - t) {
					continue inner;
				}
				j ++;
				if(j == heaters.length) {
					continue out;
				}
				if(houses[i] <= heaters[j] + t && houses[i] >= heaters[j] - t) {
					continue inner;
				}
				
				continue out;
			}
			
			return t;
		}
		
		return -1;
    }
	
	private void sort(int[] arr) {
		for(int i=1; i<arr.length; i++) {
			int tmp = arr[i];
			int j=i-1;
			for( ; j>=0; j--) {
				if(arr[j] > tmp) {
					arr[j+1] = arr[j];
				}else {
					break;
				}
			}
			
			arr[j+1] = tmp;
		}
	}
	
	/**
	 * 对于每一个houses， 在heaters中找距离它最近的两个点。用差值找距离，最后去除最大的 min-max
	 * @param houses
	 * @param heaters
	 * @return
	 */
	public int findRadius2(int[] houses, int[] heaters) {
		Arrays.sort(heaters);
		int max = 0;
		outer:
		for(int i=0; i<houses.length; i++) {
			int target = houses[i];
			int left = 0, right = heaters.length - 1;
			//这个方式记一下
			while(left + 1 < right) {
				int mid = (left + right) / 2;
				if(target == heaters[mid]) {
					continue outer;
				}else if(target > heaters[mid]) {
					left = mid;
				}else {
					right = mid;
				}
			}
			max = Math.max(Math.min(Math.abs(target - heaters[left]), Math.abs(heaters[right] - target)), max);
		}
		
		return max;
	}
	
	public static void main(String[] args) {
		int[] houses = new int[] {1,2,3};
		int[] heaters = new int[] {2};
		int result = new 加热器().findRadius2(houses, heaters);
		//new 加热器().sort(houses);
		print(result);
	}
}

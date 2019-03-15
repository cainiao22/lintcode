package website.lintcode;

import java.util.Arrays;

/**
 * 
 * @author yanpf
 * @date 2019��1��24�� ����7:35:59
 * @description
 * 		���������������������Ƴ�һ�����й̶����Ȱ뾶�ļ�������ʹ�����з�����������첻����̫�䡣

		�������ܹ���֪���з��ݺͼ�����������λ�ã����Ǿ��ֲ���һ��ˮƽ���С�����Ҫ�ҳ���С�ļ��Ȱ뾶ʹ�����з��ݶ���������һ���������ļ��ȷ�Χ�ڡ�
		
		���ԣ�������뽫�������з��ݺͼ�����������λ�ã��������Ϊ��������С�ļ��Ȱ뾶��
		
		���ݺͼ���������Ŀ��Ϊ�Ǹ��������������ǲ��ᳬ��25000��
		���ݺͼ�������λ�þ�Ϊ�Ǹ��������������ǲ��ᳬ��10^9��
		ֻҪһ�䷿��λ�ڼ������ļ��Ȱ뾶�ڣ������߽磩�����ͻᱻ���ȡ�
		���м������ļ��Ȱ뾶��ͬ��
		
		
 * @example
 * 		����1��

		���룺[1,2,3],[2]
		�����1
		˵����Ψһ��һ��������������2��λ�ã���ôֻҪ���Ȱ뾶Ϊ1�����ܸ��ǵ����з����ˡ�
		����2��
		
		���룺[1,2,3,4],[1,4]
		�����1
		˵���������������ֱ�λ��1��4��ֻ��Ҫ���Ȱ뾶Ϊ1�����ܼ������з����ˡ�
 *
 * @Solution
 */
public class ������ extends HH {
	//TODO ���
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
	 * ����ÿһ��houses�� ��heaters���Ҿ���������������㡣�ò�ֵ�Ҿ��룬���ȥ������ min-max
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
			//�����ʽ��һ��
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
		int result = new ������().findRadius2(houses, heaters);
		//new ������().sort(houses);
		print(result);
	}
}

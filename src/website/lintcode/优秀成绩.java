package website.lintcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author yanpf
 * @date 2019年1月3日 下午5:38:00
 * @description 每个学生有两个属性 id 和 scores。找到每个学生最高的5个分数的平均值。
 * @example 给出 results =
 *          [[1,91],[1,92],[2,93],[2,99],[2,98],[2,97],[1,60],[1,58],[2,100],[1,61]]
 * 
 *          返回： 1: 72.40 2: 97.40
 *
 * @Solution
 */
public class 优秀成绩 {

	public Map<Integer, Double> highFive(Record[] results) {
		// Write your code here
		Map<Integer, MinStack> map = new HashMap<>();
		for (int i = 0; i < results.length; i++) {
			if(map.get(results[i].id) == null) {
				map.put(results[i].id, new MinStack(5));
			}
			MinStack stack = map.get(results[i].id);
			stack.push(results[i].score);
		}
		
		Map<Integer, Double> res = new HashMap<>();
		map.forEach((k, v) -> {
			res.put(k, v.getSum() / 5.0);
		});
		
		return res;
	}
	
	private static class MinStack {
		private int length;
		private int count = 0;
		private int[] data;
		
		private int sum;
		
		
		
		public int getSum() {
			return sum;
		}

		public void setSum(int sum) {
			this.sum = sum;
		}

		public MinStack(int length){
			this.length = length;
			data = new int[length];
		}
		
		public void push(int item) {
			count ++;
			if(count <= length) {
				data[count - 1] = item;
				System.out.println("push:" + item);
				sum += item;
				int i = count - 1;
				int j = (i - 1) / 2;
				while(j >= 0) {
					if(data[j] > data[i]) {
						int tmp = data[j];
						data[j] = data[i];
						data[i] = tmp;
						i = j;
						j = (i - 1) / 2;
					}else {
						break;
					}
				}
			}else if(item > data[0]) {
				System.out.println("pop:" + data[0]);
				sum -= data[0];
				data[0] = item;
				sum += item;
				System.out.println("push:" + item);
				int i = 0;
				int j = i*2 + 1;
				while( j < length) {
					if(j + 1 < length && data[j] > data[j+1]) {
						j = j + 1;
					}
					if(data[i] > data[j]) {
						int tmp = data[j];
						data[j] = data[i];
						data[i] = tmp;
						i = j;
						j = i*2 + 1;
					}else {
						break;
					}
				}
			}
			
		}
		
		
	}

}





class Record {
	public int id, score;

	public Record(int id, int score) {
		this.id = id;
		this.score = score;
	}
}

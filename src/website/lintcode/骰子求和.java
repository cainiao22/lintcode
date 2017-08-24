package website.lintcode;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * @author yanpf
 * @date 2017年8月16日 上午11:27:21
 * @description 扔 n 个骰子，向上面的数字之和为 S。给定 Given n，请列出所有可能的 S 值及其相应的概率
 * 
 * @example 给定 n = 1，返回 [ [1, 0.17], [2, 0.17], [3, 0.17], [4, 0.17], [5, 0.17],
 *          [6, 0.17]]。
 *
 * @Solution 1、直接相乘，假设第i个结果已经出来了，现在求第i+1个结果，直接遍历第i个结果与骰子的投掷结果相乘，
 * 			         然后放入另一个map中，以此类推，这种方式有一个缺陷就是，最后得出的结果不一定按照从小到大的顺序给出
 * 			 2、真的dp,设f[n+1][6*n+1]代表第n次投掷，出现j的概率 f[i][j] = sum(f[i-1][j-k] * sum[1][k]) ( 1<=k<=6)
 * 				实际k应该小于等于min(j-1, 6) 
 */
public class 骰子求和 {

	public List<Map.Entry<Integer, Double>> dicesSum(int n) {
		// Write your code here
		// Ps. new AbstractMap.SimpleEntry<Integer, Double>(sum, pro)
		// to create the pair
		double[] item = new double[7];
		item[0] = 0;
		for (int i = 1; i < item.length; i++) {
			item[i] = 1.0 / 6;
		}
		Map<Integer, Double> map = new HashMap<Integer, Double>();
		for (int i = 1; i <= 6; i++) {
			map.put(i, (1.0 / 6));
		}
		for (int i = 2; i <= n; i++) {
			Map<Integer, Double> teMap = new HashMap<Integer, Double>();
			for (Map.Entry<Integer, Double> entry : map.entrySet()) {
				for(int j=0; j<=6; j++) {
					int key = entry.getKey() + j;
					if(teMap.get(key) == null) {
						teMap.put(key, entry.getValue() * item[j]);
					}else {
						teMap.put(key, entry.getValue() *item[j] + teMap.get(key));
					}
				}
			}
			map = teMap;
		}
		List<Map.Entry<Integer, Double>> result = new ArrayList<>();
		for (Map.Entry<Integer, Double> entry : map.entrySet()) {
			if(entry.getValue() != 0) {
				result.add(entry.getKey(), entry);
			}
		}
		return result;
	}
	
	public List<Map.Entry<Integer, Double>> dicesSumDP(int n) {
		double[][] f = new double[n+1][6*n + 1];
		for(int i=1; i<= 6; i++) {
			f[1][i] = 1.0/6;
		}
		for(int i=2; i<=n; i++) {
			for(int j=i; j<=6*i; j++) {
				for(int k = 1; k <= 6; k++) {
					if(k>= j) break;
					f[i][j] += f[i-1][j-k] * f[1][k];
					//这块可以写成f[i][j] += f[i-1][j-k]，最后再循环外面统一除以6
				}
				//f[i][j] /= 6;
			}
		}
		
		List<Map.Entry<Integer, Double>> result = new ArrayList<>();
		for(int i=n; i<=6*n; i++) {
			result.add(new AbstractMap.SimpleEntry<Integer, Double>(i, f[n][i]));
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		for(int i=1; i<=15; i++) {
			List<Map.Entry<Integer, Double>> list = new 骰子求和().dicesSum(i);
			double sum = 0;
			for (Entry<Integer, Double> entry : list) {
				System.out.print("{" + entry.getKey() +  "," + entry.getValue() + "}" + ",");
				sum += entry.getValue();
			}
			System.out.println();
			System.out.println(sum);
		}
	}

}

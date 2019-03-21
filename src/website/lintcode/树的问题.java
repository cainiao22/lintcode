package website.lintcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author yanpf
 * @date 2019年3月21日 上午9:53:50
 * @description
 * 		给定一棵n个结点的树，第i个结点的父亲为fa[i-1]，价值为val[i-1]。
		特别地，1表示根节点, 2 表示第二个节点，以此类推，并且保证根节点的父亲是 -1 即 fa[0] = -1。
		某子树的平均价值为，该子树所有的结点val和除以该子树的结点数。
		求该树的子树最大平均价值的为多少, 返回这颗子树的根节点编号。
 * @example
 * 		给定fa=[-1,1,1,2,2,2,3,3],代表每个点的父亲结点，和val=[100,120,80,40,50,60,50,70],代表每个结点价值，返回1。

		Sample diagram：
		                      -1  ------No.1
		                    /     \
		         No.2 ----1        1---------No.3
		               /  |  \     /  \
		              2   2   2    3   3
		No.1结点就是(100+120+80+40+50+60+50+70) / 8 = 71.25
		No.2结点就是(120 + 40 + 50 + 60) / 4 = 67.5
		No.3结点就是(80+50+70) / 3 = 66.6667
		那么就要返回1这个结点
 *
 * @Solution
 */
public class 树的问题 extends HH {
	
	/**
	 * 如果fa=[-1,1,1,2,2,2,3,3]是有顺序的这个方式是行的通的  {-1,4,1,1}这种不行
	 * @param fa
	 * @param val
	 * @return
	 */
	public int treeProblem(int[] fa, int[] val) {
        // Write your code here
		int[] sum = new int[fa.length + 1];
		int[] count = new int[fa.length + 1];
		for(int i=fa.length-1; i>=0; i--) {
			sum[i+1] += val[i];
			count[i+1] += 1;
			if(fa[i] != -1) {
				sum[fa[i]] += sum[i + 1];
				count[fa[i]] += count[i + 1];
			}
		}
		double max = 0;
		int result = 0;
		for(int i=1; i<sum.length; i++) {
			if(max < sum[i] * 1.0 /count[i]) {
				max = sum[i] * 1.0 /count[i];
				result = i;
			}
		}
		print(sum);
		print(count);
		return result;
    }
	
	public int treeProblem2(int[] fa, int[] val) {
		int[] sum = new int[fa.length + 1];
		int[] count = new int[fa.length + 1];
		for(int i=fa.length-1; i>=0; i--) {
			sum[i+1] += val[i];
			count[i+1] += 1;
			int parent = fa[i];
			while(parent != -1) {
				sum[parent] += val[i];
				count[parent] += 1;
				parent = fa[parent - 1];
			}
		}
		double max = 0;
		int result = 0;
		for(int i=1; i<sum.length; i++) {
			if(max < sum[i] * 1.0 /count[i]) {
				max = sum[i] * 1.0 /count[i];
				result = i;
			}
		}
		print(sum);
		print(count);
		return result;
	}

	
	public static void main(String[] args) {
		int[] fa = new int[] {-1,4,1,1};
		int[] val = new int[] {77,40,80,120};
		int result = new 树的问题().treeProblem2(fa, val);
		System.out.println(result);
	}
}
 
package website.lintcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author yanpf
 * @date 2019��3��21�� ����9:53:50
 * @description
 * 		����һ��n������������i�����ĸ���Ϊfa[i-1]����ֵΪval[i-1]��
		�ر�أ�1��ʾ���ڵ�, 2 ��ʾ�ڶ����ڵ㣬�Դ����ƣ����ұ�֤���ڵ�ĸ����� -1 �� fa[0] = -1��
		ĳ������ƽ����ֵΪ�����������еĽ��val�ͳ��Ը������Ľ������
		��������������ƽ����ֵ��Ϊ����, ������������ĸ��ڵ��š�
 * @example
 * 		����fa=[-1,1,1,2,2,2,3,3],����ÿ����ĸ��׽�㣬��val=[100,120,80,40,50,60,50,70],����ÿ������ֵ������1��

		Sample diagram��
		                      -1  ------No.1
		                    /     \
		         No.2 ----1        1---------No.3
		               /  |  \     /  \
		              2   2   2    3   3
		No.1������(100+120+80+40+50+60+50+70) / 8 = 71.25
		No.2������(120 + 40 + 50 + 60) / 4 = 67.5
		No.3������(80+50+70) / 3 = 66.6667
		��ô��Ҫ����1������
 *
 * @Solution
 */
public class �������� extends HH {
	
	/**
	 * ���fa=[-1,1,1,2,2,2,3,3]����˳��������ʽ���е�ͨ��  {-1,4,1,1}���ֲ���
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
		int result = new ��������().treeProblem2(fa, val);
		System.out.println(result);
	}
}
 
package website.lintcode;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 
 * @author yanpf
 * @date 2017年10月13日 上午11:43:13
 * @description
 * 		给出一个不含重复数字的排列，求这些数字的所有排列按字典序排序后该排列的编号。其中，编号从1开始。
 * @example
 * 		例如，排列 [1,2,4] 是第 1 个排列
 *
 * @Solution
 * 
 * 		1、由n个不重复数字刚才的排列有 n! 种；

		2、对所给排列从右到左(即从低位到高位)进行分析，下标i从0开始， 
		      第i位的数字和其右边的某一个数字交换后，对于新的i-1,i-2,...0位上的数字可以组成i！种不同的排列。
		      这样可以将n！作为第n位的权值。

		3、如果第 i 位的右边有low个比它小的数，
		 那么将这low个数和第 i 位的数交换之后得到的排列（i,i-1,...0）在字典序中的编号肯定比交换之前的要小。
		 又交换之后的不同排列有 i ！种，则对于第i位其对序号的增量为 i ! * low。

		总结一下，从低位到高位，
		计算其中每一位数和其右边的低位所组成的子排列进行该位和其低位某一位数交换后得到的所有可能的编号比它小的排列数目。
		要得到编号比它小的排列，而且只能让这一位数和在其低位上的（比它小的）数得到，这样可以使得对每一位进行讨论时，
		都是不相关的，即没有重复排列出现。对每一位数分析得到的增量累加可得到所有序号比所给排列小的数目，再加一即可得到结果
 */
public class 排列序号 extends HH {
	
	public long permutationIndexTimeLimt(int[] A) {
        // write your code here
		helper(A, 0);
		Comparator<Integer[]> comparator = new Comparator<Integer[]>() {

			@Override
			public int compare(Integer[] o1, Integer[] o2) {
				for(int i=0; i<o1.length; i++) {
					if(o1[i].intValue() == o2[i].intValue()) {
						continue;
					}else {
						return o1[i] - o2[i];
					}
				}
				return 0;
			}
		};
		Collections.sort(result, comparator);
		Integer[] ax =new Integer[A.length];
		for(int i=0; i<A.length; i++) {
			ax[i] = A[i];
		}
		int i = 0;
		for(int j=0; j<result.size(); j++) {
			i++;
			if(comparator.compare(result.get(j), ax) == 0) {
				break;
			}
		}
		
		return i;
    }
	
	private List<Integer[]> result = new ArrayList<>();
	
	private void helper(int[] A, int index) {
		if(index >= A.length) {
			Integer[] copy = new Integer[A.length];
			for(int j=0; j<A.length; j++) {
				copy[j] = A[j];
			}
			print(A);
			result.add(copy);
			return;
		}
		//从他自身开始算
		for(int i=index; i<A.length; i++) {
			swap(A, index, i);
			helper(A, index+1);
			swap(A, index, i);
		}
	}
	
	/**
	 * 直接计算 
	 * @param A
	 * @return
	 *  
	 * 从后往前计算，计算这个数列的前面有多少个小于他的
	 * 
	 */
	public long permutationIndex(int[] A) {
		long sum = 1;//这个1代表他自己
		for(int i=A.length-1; i>=0; i--) {
			long weight = 0;
			for(int j=i+1; j<A.length; j++) {
				if(A[i] > A[j]) {
					weight ++;
				}
			}
			//这里可以利用记忆化缓存
			sum += weight * fac(A.length - i - 1);
		}
		return sum;
	}
	
	
	int[] AX;
	private int getAx(int x) {
		if(x == 0 || x == 1) {
			return 1;
		}
		if(AX[x] != 0) {
			return AX[x];
		}
		
		int a = x * getAx(x-1);
		AX[x] = a;
		return a;
	}
	//和上面那个计算方式一样，这样不用处理小于0的情况了
	long fac(int numerator) {
		long now = 1;
		for (int i = 1; i <= numerator; i++) {
			now *= (long) i;
		}
		return now;
	}
	
	public static void main(String[] args) {
		int[] A = new int[] {4,2,1};
		new 排列序号().helper(A, 0);
		print(new 排列序号().permutationIndex(A));
	}

}

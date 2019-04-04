package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2019年4月1日 下午5:57:39
 * @description
 * 		给出一个int的数组 array, 求这个数组的最小循环节的长度
 * @example
 *		给出 array = [1,2,1,2,1,2], 返回 2。

		解释：
		最小循环节为[1,2]，长度为2。
		给出 array = [1,2,1,2,1], 返回 2。
		
		解释：
		最小循环节为[1,2]，长度为2。最后一个2虽然没有给出，但是我们还是认为循环[1,2]。
		给出 array = [1,2,1,2,1,4], 返回 6。
		
		解释：
		最小循环节为[1,2,1,2,1,4]，长度为6。
 * @Solution
 * 		可以考虑使用双指针(L,R)，并开一个变量记录len当前的循环节，由于循环节肯定是从第一位开始，所以用L记录当前匹配到的位置，
 * 		R一直往后移动，如果当前位不匹配，那么L就重置，len就加1,
 * 		这里的操作和kmp算法求next一样，循环节就是 (i - 1) - ( next[i] - 1 ) = i - next[i]。整体复杂度O(n) 。
 * 		使用未优化的求next值的方法
 */
public class 最小循环节 extends HH {
	
	/**
	 * kmp算法的变形
	 * @param array
	 * @return
	 */
	public int minimumCycleSection(int[] array) {
		int[] next = new int[array.length + 1];
		next[0] = -1;
		int i = 0, j = -1;
		while(i < array.length) {
			if(j == -1 || array[i] == array[j]) {
				i ++;
				j ++;
				next[i] = j;
			}else {
				j = next[j];
			}
		}
        return i - next[i];
    }

	/**
	 * kmp算法获取next数组
	 * @param array
	 * @return
	 */
	private int[] getNext(int[] array) {
		int[] next = new int[array.length];
		next[0] = -1;
		int i=0, j = -1;
		while(i < next.length - 1) {
			if(j == -1 || array[i] == array[j]) {
				i ++;
				j ++;
				if(array[i] != array[j]) {
					next[i] = j;
				}else {
					next[i] = next[j];
				}
			}else {
				j = next[j];
			}
		}
		
		return next;
	}
	
	public static void main(String[] args) {
		int next = new 最小循环节().minimumCycleSection(new int[] {1,2,1,2,1});
		print(next);
	}
}

package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年9月13日 下午5:27:44
 * @description 有一些原木，现在想把这些木头切割成一些长度相同的小段木头，需要得到的小段的数目至少为
 *              k。当然，我们希望得到的小段越长越好，你需要计算能够得到的小段木头的最大长度。 注意事项
 * 
 *              木头长度的单位是厘米。原木的长度都是正整数，我们要求切割得到的小段木头的长度也要求是整数。无法切出要求至少 k 段的,则返回 0
 *              即可。
 * 
 * @example 有3根木头[232, 124, 456], k=7, 最大长度为114.
 *
 * @Solution 二分法
 */
public class 木材加工 {

	public int woodCut(int[] L, int k) {
		// write your code here
		int length = 0;
		for(int i=0; i<L.length; i++) {
			length = Math.max(length, L[i]);
		}
		//切的长度是不可能为0的
		int start = 1, end = length;
		//对于start和end紧挨着的那种情况，单独来判断
		while(start+1 < end) {
			int mid = start + (end - start)/2;
			if(count(L, mid) < k) {
				end = mid;
			}else {
				start = mid;
			}
		}
		if(count(L, end) >= k) {
			return end;
		}
		if(count(L, start) >= k) {
			return start;
		}
		//前两个都不满足，说明没有匹配
		return 0;
	}
	
	int count(int[] L, int length) {
		int count = 0;
		for(int i=0; i<L.length; i++) {
			count += L[i]/length;
		}
		return count;
	}
	
	public static void main(String[] args) {
		int[] L = new int[] {232, 124, 456};
		int k =7;
		int length = new 木材加工().woodCut(L, k);
		System.out.println(length);
	}

}

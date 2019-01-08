package website.lintcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author yanpf
 * @date 2017年8月31日 上午9:43:25
 * @description
 * 
 * 				给定一个可能具有重复数字的列表，返回其所有可能的子集 注意事项
 * 
 *              子集中的每个元素都是非降序的 两个子集间的顺序是无关紧要的 解集中不能包含重复子集
 * 
 * 
 * @example 如果 S = [1,2,2]，一个可能的答案为：
 * 
 *          [ [2], [1], [1,2,2], [2,2], [1,2], [] ]
 *
 * 
 * @Solution 1、递归，当遍历长度达到数组的最后的时候，代表一个结果生成了，将这个结果的copy添加到result中。
 *           对于重复元素，如果前一个选中了，才能选这个。否则不可以。
 * 
 *           2、非递归，类似于二进制的遍历，从0~111111...（1的个数是数组长度)
 *           
 *           3、i从startIndex到nums.length遍历,每个list代表的是以i为开头的list
 */
public class 带重复元素的子集 extends HH {

	private List<List<Integer>> result = new ArrayList<>();

	public List<List<Integer>> subsetsWithDup(int[] nums) {
		// write your code here
		// subsetsWithDup(nums, 0, -1, new ArrayList<>());
		subsetsWithDupNOrecursion(nums);
		return result;
	}

	/**
	 * 递归方式1： 当遍历长度达到数组的最后的时候，代表一个结果生成了，每个元素各自管各自的状态。（选与不选)
	 * 
	 * @param nums
	 * @param index
	 * @param preIndex
	 * @param item
	 */
	private void subsetsWithDup(int[] nums, int index, int preIndex, List<Integer> item) {
		if (index >= nums.length) {
			print(item);
			if (item.isEmpty()) {
				System.out.println("null");
			}
			List<Integer> itemCopy = new ArrayList<>();
			for (Integer i : item) {
				itemCopy.add(i);
			}
			result.add(itemCopy);
			return;
		}
		item.add(nums[index]);
		subsetsWithDup(nums, index + 1, index, item);
		item.remove(item.size() - 1);
		if (preIndex == -1 || nums[index] != nums[preIndex]) {
			subsetsWithDup(nums, index + 1, preIndex, item);
		}
	}

	/**
	 * 非递归方式
	 * 有局限性。因为使用的是类似二进制的方式，用的int模拟的，所以如果数组长度大于32了就不行了
	 *
	 * @param nums
	 * @return
	 */
	private List<List<Integer>> subsetsWithDupNOrecursion(int[] nums) {
		int end = 1 << nums.length;
		for (int i = 0; i < end; i++) {
			List<Integer> item = new ArrayList<>();
			int flag = i;
			int pre = 0;
			boolean isAdd = true;
			while (flag != 0) {
				int index = flag & (~flag + 1);
				while (index != 0 && 1 << pre != index) {
					pre++;
				}
				if (pre != 0 && ((i & (index / 2)) == 0)) {
					if (nums[pre] == nums[pre - 1]) {
						isAdd = false;
						break;
					}
				}
				item.add(nums[pre]);
				flag = flag ^ index;
			}
			if (isAdd) {
				print(item);
				result.add(item);
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @param nums 从九章算法拿下来的
	 * @return
	 */
	public List<List<Integer>> subsetsWithDupFromJiuZhang1(int[] nums){
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> subList = new ArrayList<>();
		if(nums.length == 0) {
			result.add(new ArrayList<Integer>());
			return result;
		}
		helper(nums, 0, subList, result);
		
		return result;
	}
	
	/**
	 * i从startIndex到nums.length遍历,每个list代表的是以i为开头的list。
	 * @param nums
	 * @param startIndex
	 * @param subList
	 * @param result
	 */
	private void helper(int[] nums, int startIndex, List<Integer> subList, List<List<Integer>> result) {
		//从这里添加。这样可以将空包含进来，而且不必担心会丢掉最后一个元素结尾的情况。因为在i+1达到length之前，最后一个元素已经加进来了
		//只是没有进入遍历
		result.add(new ArrayList<>(subList));
		//print(subList);
		for(int i=startIndex; i<nums.length; i++) {
			
			//需要在这里去除重复的,类似于将相同的元素做打包，一起操作，关键点在startIndex，假设这个相同的串是aaaaa
			//此时startIndex是相同元素的第一个位置，先将他加入进来，然后进入递归，接着第二个也加入了进来，然后再递归
			//以此类推。达到最后一个，此时的情况其实是aaaaa开头的元素。当它遍历完成后，从最后一个算起，最后一个被抛弃，
			//然后是aaaa开头的元素。紧接着倒数第二个被抛弃，倒数第三被抛弃，假如倒数第三个被抛弃后，继续遍历后面的，那么倒数第二个
			//会被加入进来，但是这时候的情况其实是和倒数第三一样的，所以需要跳过
			if(i != startIndex && nums[i] == nums[i-1]) {
				continue;
			}
			
			subList.add(nums[i]); 
			// result.add(new ArrayList<>(subList)); 在这里也可以，但是会少一个空的情况
			helper(nums, i + 1, subList, result);
			subList.remove(subList.size() - 1);
		}
	}
	
	

	public static void main(String[] args) {
		int[] nums = new int[] { 1, 2, 3 };
		new 带重复元素的子集().subsetsWithDupFromJiuZhang1(nums);
	}
	
	//需要在这里去除重复的,类似于将相同的元素做打包，一起操作，关键点在startIndex
	//逆向思考，假如现在的startIndex是一串相同元素的最后一个位置，那么以他开头的list就有了
	//然后考虑以他前一个元素为startIndex的情况，此时，前一个元素必然会加入进来，然后进入下一个递归，
	//进入下一个递归之后，
}

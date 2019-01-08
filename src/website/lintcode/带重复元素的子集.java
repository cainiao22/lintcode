package website.lintcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author yanpf
 * @date 2017��8��31�� ����9:43:25
 * @description
 * 
 * 				����һ�����ܾ����ظ����ֵ��б����������п��ܵ��Ӽ� ע������
 * 
 *              �Ӽ��е�ÿ��Ԫ�ض��Ƿǽ���� �����Ӽ����˳�����޹ؽ�Ҫ�� �⼯�в��ܰ����ظ��Ӽ�
 * 
 * 
 * @example ��� S = [1,2,2]��һ�����ܵĴ�Ϊ��
 * 
 *          [ [2], [1], [1,2,2], [2,2], [1,2], [] ]
 *
 * 
 * @Solution 1���ݹ飬���������ȴﵽ���������ʱ�򣬴���һ����������ˣ�����������copy��ӵ�result�С�
 *           �����ظ�Ԫ�أ����ǰһ��ѡ���ˣ�����ѡ��������򲻿��ԡ�
 * 
 *           2���ǵݹ飬�����ڶ����Ƶı�������0~111111...��1�ĸ��������鳤��)
 *           
 *           3��i��startIndex��nums.length����,ÿ��list���������iΪ��ͷ��list
 */
public class ���ظ�Ԫ�ص��Ӽ� extends HH {

	private List<List<Integer>> result = new ArrayList<>();

	public List<List<Integer>> subsetsWithDup(int[] nums) {
		// write your code here
		// subsetsWithDup(nums, 0, -1, new ArrayList<>());
		subsetsWithDupNOrecursion(nums);
		return result;
	}

	/**
	 * �ݹ鷽ʽ1�� ���������ȴﵽ���������ʱ�򣬴���һ����������ˣ�ÿ��Ԫ�ظ��Թܸ��Ե�״̬����ѡ�벻ѡ)
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
	 * �ǵݹ鷽ʽ
	 * �о����ԡ���Ϊʹ�õ������ƶ����Ƶķ�ʽ���õ�intģ��ģ�����������鳤�ȴ���32�˾Ͳ�����
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
	 * @param nums �Ӿ����㷨��������
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
	 * i��startIndex��nums.length����,ÿ��list���������iΪ��ͷ��list��
	 * @param nums
	 * @param startIndex
	 * @param subList
	 * @param result
	 */
	private void helper(int[] nums, int startIndex, List<Integer> subList, List<List<Integer>> result) {
		//��������ӡ��������Խ��հ������������Ҳ��ص��Ļᶪ�����һ��Ԫ�ؽ�β���������Ϊ��i+1�ﵽlength֮ǰ�����һ��Ԫ���Ѿ��ӽ�����
		//ֻ��û�н������
		result.add(new ArrayList<>(subList));
		//print(subList);
		for(int i=startIndex; i<nums.length; i++) {
			
			//��Ҫ������ȥ���ظ���,�����ڽ���ͬ��Ԫ���������һ��������ؼ�����startIndex�����������ͬ�Ĵ���aaaaa
			//��ʱstartIndex����ͬԪ�صĵ�һ��λ�ã��Ƚ������������Ȼ�����ݹ飬���ŵڶ���Ҳ�����˽�����Ȼ���ٵݹ�
			//�Դ����ơ��ﵽ���һ������ʱ�������ʵ��aaaaa��ͷ��Ԫ�ء�����������ɺ󣬴����һ���������һ����������
			//Ȼ����aaaa��ͷ��Ԫ�ء������ŵ����ڶ������������������������������絹���������������󣬼�����������ģ���ô�����ڶ���
			//�ᱻ���������������ʱ��������ʵ�Ǻ͵�������һ���ģ�������Ҫ����
			if(i != startIndex && nums[i] == nums[i-1]) {
				continue;
			}
			
			subList.add(nums[i]); 
			// result.add(new ArrayList<>(subList)); ������Ҳ���ԣ����ǻ���һ���յ����
			helper(nums, i + 1, subList, result);
			subList.remove(subList.size() - 1);
		}
	}
	
	

	public static void main(String[] args) {
		int[] nums = new int[] { 1, 2, 3 };
		new ���ظ�Ԫ�ص��Ӽ�().subsetsWithDupFromJiuZhang1(nums);
	}
	
	//��Ҫ������ȥ���ظ���,�����ڽ���ͬ��Ԫ���������һ��������ؼ�����startIndex
	//����˼�����������ڵ�startIndex��һ����ͬԪ�ص����һ��λ�ã���ô������ͷ��list������
	//Ȼ��������ǰһ��Ԫ��ΪstartIndex���������ʱ��ǰһ��Ԫ�ر�Ȼ����������Ȼ�������һ���ݹ飬
	//������һ���ݹ�֮��
}

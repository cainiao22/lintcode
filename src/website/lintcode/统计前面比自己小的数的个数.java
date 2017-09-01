package website.lintcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author yanpf
 * @date 2017��8��22�� ����4:45:52
 * @description ����һ���������飨�±��� 0 �� n-1�� n ��ʾ����Ĺ�ģ��ȡֵ��Χ�� 0 ��10000����
 * 				���������е�ÿ�� ai Ԫ�أ������ ai ǰ�����б���С��Ԫ�ص�����
 * 
 * @example ��������[1,2,7,8,5] ������ [0,1,2,3,2]
 *
 * @Solution 1����Ϊ�����ģ�Ǹ����ģ����Թ���һ���߶����������ȡֵ��Χ����0-10000��
 * 			        Ȼ�����������ڲ����ݾ���0�������������ָ����������������飬��ÿ���ص�ֵ���µ�����߶����У���query
 * 			        ��ѯ��ʱ����ʵ�������߶����в���0-a[i]-1�ķ�Χ����������
 * 			        �������Ǳ��߱߸��µģ�����ȫ�����������Ϻ��ٴ��±����߶�����ʱ�򣬽���ͺ�ԭ����һ����
 * 			 
 * 			 2�����ÿ���˼�룬newһ��һģһ�������顣ÿ����һ��Ԫ�ؾ��Ե�ǰԪ��Ϊ��׼��һ�ο��ţ������n��Ԫ���Ѿ��ҵ��Լ���λ��k��
 * 			 	���õ���n+1��Ԫ�ص�ʱ���������n��Ԫ�����Ƚϣ����a[n+1] > a[n] ��ô����k~n���������ţ��ó��Ľ������k+x,
 * 				�������0-k���������ţ��õ��Ľ��Ϊk-x��
 * 
 */
public class ͳ��ǰ����Լ�С�����ĸ��� extends HH {
	
	//��Ϊ�߶�����һ����ȫ�����������Կ���ֱ��������ģ��,�ھ����㷨�Ĵ�����ֱ������ģ���
	private static int[] segmentTree;
	
	private static final int N = 10000;
	
	public List<Integer> countOfSmallerNumberII(int[] A) {
        // write your code here
		segmentTree = new int[4*N];
		List<Integer> result = new ArrayList<>();
		for(int i=0; i<A.length; i++) {
			int num = query(0, 0, N - 1, 0, A[i] - 1);
			result.add(num);
			modifyNode(0, 0, N-1, A[i], 1);
		}
		return result;
    }
	/**
	 * 
	 * @param root  �߶����ĸ��ڵ��±�
	 * @param begin ��ѯ����Ŀ�ʼλ��
	 * @param end   ��ѯ����Ľ���λ��
	 * @param qBegin ��ѯĿ��Ľ���λ��
	 * @param qEnd   ��ѯĿ��Ľ���λ��
	 * @return 0-num�е����ָ���
	 */
	private static int query(int root, int begin, int end, int qBegin, int qEnd) {
		if(qEnd < begin || qBegin > end) {
			return 0;
		}
		if(begin > end) {
			return 0;
		}
		if(qBegin <= begin && qEnd >= end) {
			return segmentTree[root];
		}
		int mid = begin + (end - begin)/2;
		int left = query(root*2 + 1, begin, mid, qBegin, qEnd);
		int right = query(root*2 + 2, mid+1, end, qBegin, qEnd);
		
		return left + right;
	}
	
	private void modifyNode(int root, int begin, int end, int index, int value) {
		if(begin == index && end == index) {
			segmentTree[root] ++;
			return;
		}
		int mid = (begin + end) /2;
		if(index <= mid && index >= begin) {
			modifyNode(root*2 + 1, begin, mid, index, value);
		}else if(index > mid && index <= end) {
			modifyNode(root*2 + 2, mid+1, end, index, value);
		}
		
		segmentTree[root] = segmentTree[root * 2 + 1] + segmentTree[root*2 + 2];
	}
	
	public static void main(String[] args) {
		int[] nums = new int[] {1,2,7,8,5};
		List<Integer> result = new ͳ��ǰ����Լ�С�����ĸ���().countOfSmallerNumberII(nums);
		print(result);
		for(int i=0; i<nums.length; i++) {
			int num = query(0, 0, N - 1, 0, nums[i] - 1);
			System.out.println(num);
		}
	}

}

package lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��8��14�� ����11:18:35
 * @description ����һ���������������У�������������С�����ֵ����С������������һ�����С�
 * 				���û����һ�����У�������ֵ�����С�����С�������ʹ�ö���Ŀռ䡣
 * 
 * @example	�����ԭʼ���У��ұ��Ƕ�Ӧ����һ������
				1,2,3 �� 1,3,2
				
				3,2,1 �� 1,2,3
				
				1,1,5 �� 1,5,1
 *
 * @Solution  �Ӻ���ǰ�ҵ���һ������ԡ�Ȼ���i-1��ǰ��ĵ�һ�������������λ�ã�����i~length�������������
 */
public class ��һ������ extends HH{
	public void nextPermutation(int[] nums) {
		int i = 0;
        for(i=nums.length - 1; i>0; i--) {
        	if(nums[i] > nums[i-1]) {
        		int j = nums.length - 1;
        		while(nums[j] <= nums[i-1]) {
        			j -- ;
        		}
        		nums[i-1] ^= nums[j];
        		nums[j] ^= nums[i-1];
        		nums[i-1] ^= nums[j];
        		break;
        	}
        }
        print(nums);
        for(int j= nums.length - 1; j>i; j--) {
        	if(nums[i] != nums[j]) {
        		nums[i] ^= nums[j];
        		nums[j] ^= nums[i];
        		nums[i] ^= nums[j];
        	}
        	i ++; 
        }
    }
	
	public void mergeSort(int nums[]) {
		devide(nums, 0, nums.length - 1);
		print(nums);
	}
	
	void devide(int[] nums, int start, int end) {
		if(start != end) {
			devide(nums, start, start + (end - start)/2);
			devide(nums, start + (end - start)/2 + 1, end);
			merge(nums,start, start + (end - start)/2, start + (end - start)/2 + 1, end);
		}
	}
	
	void merge(int[] nums, int startA, int endA, int startB, int endB) {
		int[] temp = new int[nums.length];
		int k = 0;
		int i = startA, j = startB;
		while(i <= endA && j <= endB) {
			if(nums[i] < nums[j]){
				temp[k] = nums[i];
				i ++;
			}else {
				temp[k] = nums[j];
				j ++;
			}
			k ++;
		}
		while(i <= endA) {
			temp[k] = nums[i];
			k ++;
			i ++;
		}
		/**
		while(j <= endB) {
			temp[k] = nums[j];
			k ++;
			j ++;
		}
		
		for(i=0; i<k; i++) {
			nums[startA + i] = temp[i];
		}
		��������Ż�Ϊ������ʽ
		**/
		while(k > 0) {
			nums[--j] = temp[--k];
		}
		
	}
	
	/**
	 * ����i�����ΪrightMax�Ļ�������һ��merge������
	 * @param nums
	 */
	public void mergeSort2(int[] nums) {
		for(int i =1; i<=nums.length; i*=2) {
			for(int j=0; j<=nums.length - i; j += i*2) {
				int rightMax = j + 2*i - 1;
				int leftMax = j + i - 1;
				int rightMin = j + i;
				if(rightMax >= nums.length) {
					rightMax = nums.length - 1;
				}
				merge(nums, j, leftMax, rightMin, rightMax);
			}
			print(nums);
		}
		
		print(nums);
	}
	
	public static void main(String[] args) {
		int[] nums = new int[] {5,4,7,5,3,2};
		new ��һ������().nextPermutation(nums);
		print(nums);
	}
}

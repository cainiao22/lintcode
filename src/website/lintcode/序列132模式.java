package website.lintcode;

import java.util.Stack;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

/**
 * 
 * @author yanpf
 * @date 2017��11��7�� ����3:30:15
 * @description
 * 		����һ�� n ������������ a1,a2,...,an��һ�� 132 ģʽ�Ƕ���һ���Ӵ� ai,aj,ak������ i < j < k �� ai < ak < aj��
 * 		���һ���㷨������������ n ���������������Ƿ����132ģʽ��
		n ��С�� 20,000
 * @example
 * 		�������� nums = [1,2,3,4]
		���� False//û��132ģʽ����������С�
		�������� nums = [3,1,4,2]
		���� True//����132ģʽ��[1,4,2]��
 *
 * @Solution
 */
public class ����132ģʽ extends HH {
	
	/**
	 * 	������������һ�����飬�������ҵ�132��ģʽ�����ǵ�һ����С�ڵڶ�����������
	 * 	�ҵ�������С�ڵڶ���������ô���ǾͰ�˳���������������������������ҵ�һ������
	 * 	�������Ҫ��С����ô����������ֵ�ǰ���ִ��ڵ��ں���һ�����֣����Ǿ����¼���������
	 * 	ֱ����ǰ����С����һ������ֹͣ��Ȼ�������ҵڶ������֣����������Ҫ���
	 * 	��ô������Ƿ��ֵ�ǰ����С�ڵ�����һ�����־ͼ���������ֱ����ǰ���ִ�����һ������ֹͣ��
	 * 	�����ҵ��������֣�������֤��������Ƿ���֮ǰ�������ֵ��м䣬���û���ҵ���
	 * 	���Ǿʹӵڶ������ֵĺ���һ��λ�ü�����ʼ����������������
	 * 
	 * @param nums
	 * @return
	 */
	public boolean find132pattern1(int[] nums) {
		int i = 0;
		while(i<nums.length) {
			//�����ҵ�һ������֤�����С����������Ǹ���
			while(i<nums.length-1 && nums[i] >= nums[i+1]) i++;
			//�ߵ���������ҵ��ˡ�Ȼ����aj��ak����֤aj>ak
			int j=i+1;
			while(j < nums.length-1 && nums[j] <= nums[j+1]) j++;
			//�ߵ������ʾ�ҵ��˷���ai < aj && ak < aj������ʡ�µľ��Ǳ�֤ak>ai
			int k = j + 1;
			while(k < nums.length) {
				if(nums[k] <nums[j] && nums[k] > nums[i]) {
					return true;
				}
				k++;
			}
			//�ߵ������ʾû�з���������(iһֱ�ڼӣ�����ǰ�������� nums[i] >= nums[i+1]�����ġ��϶�������
			//���û�з���Ҫ�����˵�����������1��nums[k] <= nums[i].��Ϊi~j��nums[i]����Сֵ��nums[j]�����ֵ��
			//��Ȼnums[k]ȫ��С��nums[i]����i~j�е����κ�һ��Ҳ�ʹ���nums[k]����Ȼ���������û�취���ϡ�
			//2��nums[k] >nums[j],�����ԡ������123ģʽ���������ˡ�ֱ�ӵ�j+1������
			i = j+1;
		}
		return false;
		
	}
	
	/**
	 * ��ջ��ʽ����132ģʽ�е����ֱַ�����Ϊfirst��Second��third.�����ⲿ����third.stack�д�ŵ���Secondֵ��
	 * �Ӻ���ǰ������������second>third��ֵ��ջ��ͬʱȥ����ǰ�����������ջ��Ԫ�ظ�ֵ��third(��֤������Second>third)��ǰ��
	 * �¡�thirdԽ��Խ�ã���Ϊ�����������ҵ�����first<third��first��
	 * @param nums
	 * @return
	 */
	public boolean find132pattern2(int[] nums) {
		Stack<Integer> stack = new Stack<>();
		int third = Integer.MIN_VALUE; //��֤��ʼʱ��û�����ݿ��Ա�thirdС
		for(int j=nums.length-1; j>=0; j--) {
			if(nums[j] < third) return true;//�ڱ�֤��ʼʱ��û�����ݿ��Ա�thirdС��ǰ���¡����������ʾ����132ģʽ
			//�����൱�������ֵ
			while(!stack.isEmpty() && nums[j] > stack.peek()) {
				third = stack.pop();
			}
			stack.push(nums[j]);
		}
		return false;
	}
	
	public boolean find132pattern3(int[] nums) {
        // write your code here
		if(nums.length < 3) {
			return false;
		}
		int[] mins = new int[nums.length];
		mins[0] = nums[0];
		for(int i=1; i<nums.length; i++) {
			mins[i] = Math.min(mins[i-1], nums[i-1]);
		}
		
		Stack<Integer> stack = new Stack<>();
		int max = Integer.MIN_VALUE;
		//�Ӻ���ǰ����i~length֮������ֵ�����������ֵ����ǰ��i��������Сֵ����ô�൱�������ģʽ
		for(int i=nums.length-1; i>=0; i--) {
			if(nums[i] > mins[i]) {
				while(!stack.isEmpty() && nums[i] > stack.peek()) {
					max = stack.pop();
				}
				if(max > mins[i]) {
					return true;
				}
				stack.push(nums[i]);
			}
		}
		return false;
		
    }

}

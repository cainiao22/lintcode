package website.lintcode;

import java.util.HashSet;
import java.util.List;

/**
 * 
 * @author yanpf
 * @date 2019��3��21�� ����8:02:54
 * @description
 * 		���һ���������г��ֵ�ֵ�������������������������Ȥ�ġ���һ������a�����������һ����Ȥ�����顣������ĳ��ȡ�
 * @example
 *		a = [1,2,1,3]
		���� 3
		a=[1,1,1,1]
		���� 4
 * @Solution
 */
public class ��Ȥ�������� {
	
	public int maxLen(int[] a) {
		// Write your code here
		int max = 0;
		for (int i = 0; i < a.length; i++) {
			HashSet<Integer> set = new HashSet<>();
			set.add(a[i]);
			for (int j = i + 1; j < a.length; j++) {
				max = Integer.max(max, j - i);
				set.add(a[j]);
				if (set.size() > 2) {
					break;
				}
			}
			if(set.size() <= 2) {
				max = Integer.max(max, a.length - i);
			}
		}

		return max;
	}
	
	/**
	 * ά��һ���������䣬���俪ͷ��a,��β��b,a��b�Ǿ�������֣�����a[i],count_b�������b���������м䲻����a,
	 * �����ֵ�������ʱ�򣬵�ǰ���鳤�����¼���Ϊcount_b+1,��Ϊ��ʱ��a�ͳ���b,b�ͱ�����¼ӽ�������
	 * @param a
	 * @return
	 */
	public int maxLen2(int[] arr) {
		int a = 0, b=0, max = 0, cur=0, count_b = 0;
		for(int num : arr) {
			if(num == a || num == b) {
				cur ++;
			}else {
				cur = count_b + 1;
			}
			if(num == b) {
				count_b ++;
			}else {
				count_b = 1;
				a = b;
				b = num;
			}
			
			max = Integer.max(max, cur);
		}
		
		return max;
	}
	
	/**
	 * 
	 * @param a
	 * @return
	 */
	int maxLen3(int[] a) {
		//һ�����������ڳ��ֵĴ���
        int[] count = new int[1000];
        //����ǲ�ͬ���ֵĸ���
        int nums = 0;
        int len = 0;
        for(int i = 0, j = 0; i < a.length; i++) {
        	//ԭ�����λ��Ϊ0����ʾ�������ԭ��û�������
            if(0 == count[a[i]]++) 
            {
                nums++;
            }
            //�����ʾ�����ڳ���������������Ҫȥ��һ��
            for(; nums > 2; j++) 
            {
                if(1 == count[a[j]] --) 
                {
                    nums--;
                }
            }
            len = Math.max(len, i - j + 1);
        }
        return len;
    }
	
	public static void main(String[] args) {
		int[] a = new int[] {1,2,1,2};
		int result = new ��Ȥ��������().maxLen3(a);
		System.out.println(result);
	}

}

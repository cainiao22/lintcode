package website.lintcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author yanpf
 * @date 2019��4��3�� ����5:46:17
 * @description
 * 		һ����԰��N��λ�á�ÿ��λ������һ�仨����N�仨����N������һʢ����ÿ�춼һ�����в���ֻ��һ�仨ʢ��������������仨��һֱ����ʢ����״̬��

		����һ��������1��N��ɵ�����flowers�������е�ÿ�����ֱ�ʾ��һ�콫��ʢ���Ļ���λ�á�
		
		���磬flowers[i] = x��ʾ��λ��x�ϵĻ����ڵ�i��ʢ��������i��x����1��N�ķ�Χ�ڡ�
		
		��һ������ m���ҵ�ͬʱ�� m �黨���ŵ����һ�죨ÿһ�������� k �軨����
		
		�������������һ�죬��ô����-1��
 * @example
 * 		�ô��������·,�Ҿ���lintcode����ĿӦ���Լ����һ�°�?
		�����Ǵ�����0��ʼ, ÿ�쿪һ��, λ�þ��������ϵ�����.
		Ȼ����m��, ÿ��������k�仨�������һ��.
		ֱ��������
		[1,2,3,6,5,4,9,8,7]
		2
		2
		
		��һ�쿪�������:
		F 0 0 0 0 0 0 0 0
		�ڶ��쿪�������:
		F F 0 0 0 0 0 0 0
		3:
		F F F 0 0 0 0 0 0
		4:
		F F F 0 0 F 0 0 0
		���ھ������黨, ��һ����3��, һ����һ��, �����ϲ���Ҫ��, ��Ϊ���ڵ�������k=2
		5:
		F F F 0 F F 0 0 0
		���˵�������Ƿ���Ҫ����, ���ǲ�ȷ���ǲ������һ��, �ȱ�������, ����������.
		6:
		F F F F F F 0 0 0
		���һ����
		7:
		F F F F F F 0 0 F
		8:
		F F F F F F 0 F F
		����,�ڰ���Ҳ����Ҫ��,�����滻��ԭ���ĵ�����
		9:
		F F F F F F F F F
		���Դ���8
 *
 * @Solution
 */
public class �������� {
	
	public int flowerProblem(int[] flowers, int k, int m) {
		// Write your code here
		int[] pos = new int[flowers.length];
		int result = -1;
		for(int i=0; i<flowers.length; i++) {
			pos[flowers[i] - 1] = 1;
			int m2 = 0;
			int count = 0;
			for(int j=0; j<pos.length; j++) {
				if(pos[j] == 0) {
					if(count >= k) {
						m2 ++;
					}
					count = 0;
				}else {
					count += 1;
				}
			}
			if(count >= k) {
				m2 ++;
			}
			if(m2 >= m) {
				result = i;
			}
			
		}
		
		return result == -1 ? -1 : result + 1;

	}
	
	/**
	 * ��Ȼ�����һ�� �Ǿ͵�����
	 * @param flowers
	 * @param k
	 * @param m
	 * @return
	 */
	public int flowerProblem2(int[] flowers, int k, int m) {
		// Write your code here 0Ϊ�ѿ�����
		if(m == 1 && flowers.length >= k) {
			return flowers.length;
		}
		int[] pos = new int[flowers.length];
		for(int i=flowers.length - 1; i >= 0; i--) {
			pos[flowers[i] - 1] = 1;
			int m2 = 0;
			int count = 0;
			for(int j=0; j<pos.length; j++) {
				if(pos[j] == 1) {
					if(count >= k) {
						m2 ++;
					}
					count = 0;
				}else {
					count += 1;
				}
			}
			if(count >= k) {
				m2 ++;
			}
			if(m2 >= m) {
				return i;
			}
			
		}
		
		return -1;

	}
	
	/**
	 * ���鼯
	 * @param flowers
	 * @param k
	 * @param m
	 * @return
	 */
	public int flowerProblem3(int[] flowers, int k, int m) {
		int[] pos = new int[flowers.length];
		for(int i=0; i<pos.length; i++) {
			pos[i] = -1;
		}
		int[] count = new int[flowers.length];
		Set<Integer> roots = new HashSet<>();
		int result = -1;
		for(int i=0; i<flowers.length; i++) {
			int flowerPos = flowers[i] - 1;
			pos[flowerPos] = -1;
			count[flowerPos] = 1;
			int pre = flowerPos - 1;
			int post = flowerPos + 1;
			if(pre >= 0 && count[pre] > 0) {
				pos[flowerPos] = pre;
				while(pre != -1) {
					count[pre] += 1;
					pre = pos[pre];
				}
			}
			if(post < flowers.length && count[post] > 0) {
				if(count[post] >= k) {
					roots.remove(new Integer(post));
				}
				pos[post] = flowerPos;
				pre = flowerPos;
				while(pre != -1) {
					count[pre] += count[post];
					pre = pos[pre];
				}
			}
			while(pos[flowerPos] != -1) {
				flowerPos = pos[flowerPos];
			}
			if(count[flowerPos] >=k) {
				roots.add(flowerPos);
			}
			if(roots.size() >= m) {
				result = i + 1;
			}
		}
		
		return result;
	}
	
	/**
	 * ���鼯���
	 * @param flowers
	 * @param k
	 * @param m
	 * @return
	 */
	//TODO ������ �ύ��ͨ��
	public int flowerProblem4(int[] flowers, int k, int m) {
		if(m == 1 && flowers.length >= k) {
			return flowers.length;
		}
		int[] count = new int[flowers.length];
		int[] pos = new int[flowers.length];
		int m2 = 1;
		for(int i=0; i<flowers.length; i++) {
			count[i] = flowers.length - i;
			pos[i] = i - 1;
		}
		for(int i=flowers.length - 1; i>=0; i--) {
			int flowerPos = flowers[i] - 1;
			if(count[flowerPos] >= k && pos[flowerPos] == -1) {
				m2 --;
			}
			int pre = flowerPos - 1;
			int post = flowerPos + 1;
			while(pre >= 0 && count[pre] != 0) {
				count[pre] -= count[flowerPos];
				if(pos[pre] == -1 && count[pre] < k) {
					m2 --;
				}
				pre = pos[pre];
			}
			count[flowerPos] = 0;
			if(post < flowers.length) {
				pos[post] = -1;
				if(count[post] >= k) {
					m2 ++;
				}
			}
			
			if(m2 >= m) {
				return i;
			}
		}
		
		return -1;
	}
	
	public static void main(String[] args) {
		int[] flowers = new int[] {3,5,10,1,7,6,4,2,8,9};
		int k = 2;
		int m = 2;
		int result = new ��������().flowerProblem4(flowers, k, m);
		System.out.println(result);
	}
}

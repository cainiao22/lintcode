package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2019��2��22�� ����4:08:50
 * @description
 * 		������� n ������һ���ۻ���(���Ϊ 0 �� n - 1)�����п��ܴ���һ�����ˡ����˵Ķ������������� n - 1 �˶���ʶ��/��������/����֪���κ�һ����
		��������Ҫ�ҳ����������˭������֤������˲����ڡ���Ψһ�������������������������⣺����ã�A������ʶB�𣿡� ����ȡA�Ƿ���ʶB������Ҫͨ��ѯ�ʾ������ٵ�����(�Խ���������)���ҳ�������˭(����֤�䲻����)��
		��õ�һ���������� bool know(a��b)�����������A�Ƿ�֪��B.ʵ��һ������ int findCelebrity(n)����ĺ���Ӧ��ʹ knows �ĵ��ô������١�
 * @example
 * 		����1

		���룺
		2 // ������n*(n-1)��
		0 knows 1
		1 does not know 0
		����� 1
		���ͣ�
		�����˶���ʶ1������1����ʶ�����ˡ�
		����2
		
		���룺
		3 // ������n*(n-1)��
		0 does not know 1
		0 does not know 2
		1 knows 0
		1 does not know 2
		2 knows 0
		2 knows 1
		�����0
		���ͣ�
		�����˶���ʶ0������0����ʶ�����ˡ�
		0����ʶ1��ͬʱ1��ʶ0��
		2��ʶ�����ˣ�����1����ʶ2��
		ע������
		���������ۻ��������ˣ� ��ô����ֻ��һ��������������ھۻ����򷵻����˵ı�ǩ�����û�����ˣ����� -1��
 *
 * @Solution
 */
public class ʶ������ {
	
	boolean knows(int a, int b) {
		return true;
	}
	
	public int findCelebrity(int n) {
		int[] mem = new int[n];
		label:
        for(int i=0; i<n; i++) {
        	if(mem[i] == -1) {
        		continue;
        	}
        	for(int j=0; j<n; j++) {
        		if(i == j) {
        			continue;
        		}
        		if(!knows(j, i)) {
        			continue label;
        		}
        		if(knows(i, j)) {
        			mem[j] = -1;
        			continue label;
        		}
        	}
        	
        	return i;
        }
	
		return -1;
    }
	
	/**
	 * �е������ڶ��ֲ��� ���˼ά��ʽ��ţ��
	 * @param n
	 * @return
	 */
	public int findCelebrity2(int n) {
		int left = 0, right = n - 1;
		while(left < right) {
			//������֪���ұߣ����һ����������
			if(knows(left, right)) {
				left ++;
			}else { //�����߲�֪���ұߣ��ұ�һ����������
				right --;
			}
		}
		
		for(int i=0; i<n; i++) {
			if(left == i || (knows(i, left) && !knows(left, i))) {
				continue;
			}
			return - 1;
		}
		return left;
	}

}

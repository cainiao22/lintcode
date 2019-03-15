package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2019��2��21�� ����3:32:58
 * @description	
 * �����������Σ��ж������������Ƿ����ص���

		l1�����һ�����ε����Ͻ�
		r1�����һ�����ε����½�
		l2����ڶ������ε����Ͻ�
		r2����ڶ������ε����½�
		
		��֤��l1 != r2 ���� l2 != r2
		
 * @example
 * 		���� l1 = [0, 8], r1 = [8, 0], l2 = [6, 6], r2 = [10, 0], ���� true
		���� l1 = [0, 8], r1 = [8, 0], l2 = [9, 6], r2 = [10, 0], ���� false
 *
 * @Solution
 */

class Point {
	int x;
	int y;

	Point() {
		x = 0;
		y = 0;
	}

	Point(int a, int b) {
		x = a;
		y = b;
	}
}
//TODO �ύ��ͨ�� ������
public class �����ص� {

	
	public boolean doOverlap(Point l1, Point r1, Point l2, Point r2) {
        if(overlap(l1, r1, l2, r2)) {
        	return true;
        }
        return overlap(l2, r2, l1, r1);
        
    }
	
	private boolean overlap(Point l1, Point r1, Point l2, Point r2) {
		if(l2.x <= r1.x && l2.x >= l1.x && l2.y >= r1.y && l2.y <= l1.y) {
        	return true;
        }
        if(r2.x <= r1.x && r2.x >= l1.x && r2.y >= r1.y && r2.y <= l1.y) {
        	return true;
        }
        
        if(r2.x >= l1.x && r2.x <= r1.x && l2.y >= r1.y && l2.y <= l1.y) {
        	return true;
        }
        
        if(l2.x >= l1.x && l2.x <= r1.x && r2.y >= r1.y && r2.y <= l1.y) {
        	return true;
        }
        
        return false;
	}
	
}

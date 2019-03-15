package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2019年2月21日 下午3:32:58
 * @description	
 * 给定两个矩形，判断这两个矩形是否有重叠。

		l1代表第一个矩形的左上角
		r1代表第一个矩形的右下角
		l2代表第二个矩形的左上角
		r2代表第二个矩形的右下角
		
		保证：l1 != r2 并且 l2 != r2
		
 * @example
 * 		给定 l1 = [0, 8], r1 = [8, 0], l2 = [6, 6], r2 = [10, 0], 返回 true
		给定 l1 = [0, 8], r1 = [8, 0], l2 = [9, 6], r2 = [10, 0], 返回 false
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
//TODO 提交不通过 有问题
public class 矩形重叠 {

	
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

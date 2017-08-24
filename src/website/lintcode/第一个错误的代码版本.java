package website.lintcode;

/**
 * 
 * @author yanpf
 * @description 
 * 代码库的版本号是从 1 到 n 的整数。某一天，有人提交了错误版本的代码，因此造成自身及之后版本的代码在单元测试中均出错。
 * 请找出第一个错误的版本号。
 * 
 * @Solution  二分查找，因为只要第一个错了，后面的所有的就全错了，判断条件 a[mid] = false -> l=mid, else r=mid
 */
public class 第一个错误的代码版本 {
	
	public int findFirstBadVersion(int n) {
		if(SVNRepo.isBadVersion(n)) {
			return n;
		}
        int l = 1, r = n - 1;
        while (l < r) {
        	//防止越界
        	int middle = l + (r -l) / 2;
			if(SVNRepo.isBadVersion(middle)) {
				r = middle;
			}else {
				l = middle + 1;
			}
		}
        
        return l;
    }
	
	public static void main(String[] args) {
		new 第一个错误的代码版本().findFirstBadVersion(10);
	}

}

class SVNRepo {
   public static boolean isBadVersion(int k) {
	   return k > 6;
   }
}

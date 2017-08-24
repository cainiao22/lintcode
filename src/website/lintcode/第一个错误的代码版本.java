package website.lintcode;

/**
 * 
 * @author yanpf
 * @description 
 * �����İ汾���Ǵ� 1 �� n ��������ĳһ�죬�����ύ�˴���汾�Ĵ��룬����������֮��汾�Ĵ����ڵ�Ԫ�����о�����
 * ���ҳ���һ������İ汾�š�
 * 
 * @Solution  ���ֲ��ң���ΪֻҪ��һ�����ˣ���������еľ�ȫ���ˣ��ж����� a[mid] = false -> l=mid, else r=mid
 */
public class ��һ������Ĵ���汾 {
	
	public int findFirstBadVersion(int n) {
		if(SVNRepo.isBadVersion(n)) {
			return n;
		}
        int l = 1, r = n - 1;
        while (l < r) {
        	//��ֹԽ��
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
		new ��һ������Ĵ���汾().findFirstBadVersion(10);
	}

}

class SVNRepo {
   public static boolean isBadVersion(int k) {
	   return k > 6;
   }
}

package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��9��13�� ����5:27:44
 * @description ��һЩԭľ�����������Щľͷ�и��һЩ������ͬ��С��ľͷ����Ҫ�õ���С�ε���Ŀ����Ϊ
 *              k����Ȼ������ϣ���õ���С��Խ��Խ�ã�����Ҫ�����ܹ��õ���С��ľͷ����󳤶ȡ� ע������
 * 
 *              ľͷ���ȵĵ�λ�����ס�ԭľ�ĳ��ȶ���������������Ҫ���и�õ���С��ľͷ�ĳ���ҲҪ�����������޷��г�Ҫ������ k �ε�,�򷵻� 0
 *              ���ɡ�
 * 
 * @example ��3��ľͷ[232, 124, 456], k=7, ��󳤶�Ϊ114.
 *
 * @Solution ���ַ�
 */
public class ľ�ļӹ� {

	public int woodCut(int[] L, int k) {
		// write your code here
		int length = 0;
		for(int i=0; i<L.length; i++) {
			length = Math.max(length, L[i]);
		}
		//�еĳ����ǲ�����Ϊ0��
		int start = 1, end = length;
		//����start��end�����ŵ�����������������ж�
		while(start+1 < end) {
			int mid = start + (end - start)/2;
			if(count(L, mid) < k) {
				end = mid;
			}else {
				start = mid;
			}
		}
		if(count(L, end) >= k) {
			return end;
		}
		if(count(L, start) >= k) {
			return start;
		}
		//ǰ�����������㣬˵��û��ƥ��
		return 0;
	}
	
	int count(int[] L, int length) {
		int count = 0;
		for(int i=0; i<L.length; i++) {
			count += L[i]/length;
		}
		return count;
	}
	
	public static void main(String[] args) {
		int[] L = new int[] {232, 124, 456};
		int k =7;
		int length = new ľ�ļӹ�().woodCut(L, k);
		System.out.println(length);
	}

}

package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��11��9�� ����2:31:08
 * @description
 * 		��һ�� n Ӣ�糤�ĸ��Ӻ�һ����������С�� n �ĳߴ�ļ۸�. ȷ��ͨ���и�˲�������Ƭ�ɻ�õ����ֵ.
 * 		���磬������ĳ���Ϊ8�����Ҳ�ͬ���Ȳ�����ֵ���£������ɻ��ֵΪ 22(ͨ���и����γ��� 2 �� 6 )
 * @example
 * 
 * 		����    | 1   2   3   4   5   6   7   8  
		--------------------------------------------
		�۸�    | 1   5   8   9  10  17  17  20
		
		���� price = [1, 5, 8, 9, 10, 17, 17, 20], n = 8 ���� 22//�гɳ���Ϊ 2 �� 6 ������
		
		����    | 1   2   3   4   5   6   7   8  
		--------------------------------------------
		�۸�    | 3   5   8   9  10  17  17  20
		
		���� price = [3, 5, 8, 9, 10, 17, 17, 20], n = 8 ���� 24//�гɳ���Ϊ 1 �� 8 ��
 *
 * @Solution
 */
public class ���ӷָ� extends HH {
	
	public int cutting(int[] prices, int n) {
        // Write your code here
		int[] max = new int[n + 1];
		max[0] = 0;
		for(int i=1; i<=n; i++) {
			int price = max[i-1];
			/*for(int j=0; j<prices.length; j++) {
				if(i >= j + 1) {
					price = Math.max(price, max[i-j - 1] + prices[j]);
				}else{
					break;
				}
			}*/
			//�Ż���1�� j����Ҫ���������
			for(int j=0; j<=i; j++) { 
				price = Math.max(price, max[i-j - 1] + prices[j]);
			}
			max[i] = price;
		}
		return max[n];
    }
	
	public static void main(String[] args) {
		int[] prices = new int[] {1, 5, 8, 9, 10, 17, 17, 20};
		print(new ���ӷָ�().cutting(prices, 8));
	}

}

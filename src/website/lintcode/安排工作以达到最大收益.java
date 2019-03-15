package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2019��2��26�� ����10:13:09
 * @description
 * 		���������¹�����difficulty[i]�ǵ�i���������Ѷȣ�profit[i]�ǵ�i������������

		����������һЩ���ˡ� worker[i]�ǵ�i�����˵�����������ζ����������������Ѷ�Ϊworker[i]�Ĺ�����
		
		ÿ���������ֻ�ܷ���һ�ݹ�������һ�ݹ������Զ����ɡ�
		
		���磬���3���˳���֧��1��Ԫ����ͬ��������ô������Ϊ3��Ԫ�� ��������޷�����κι�������������Ϊ0��Ԫ��
		
		���ǿ��Ի�õ���������Ƕ��٣�
 * @example
 * 		����: difficulty = [2,4,6,8,10], profit = [10,20,30,40,50], worker = [4,5,6,7]
		���: 100
		����: �����Ƿֱ𱻷��乤���Ѷ� [4,4,6,6]�����Ǹ���ȡ�õ�����Ϊ [20,20,30,30].
 *
 * @Solution
 */
public class ���Ź����Դﵽ������� {
	
	public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
		//����j�������ָ���i������
        int[] dp = new int[worker.length];
        for(int i=0; i<worker.length; i++) {
        	for(int j=0; j<difficulty.length; j++) {
        		if(worker[i] >= difficulty[j]) {
        			dp[i] = Math.max(profit[j], dp[i]);
        		}
        	}
        }
        
        int sum = 0;
        for(int i=0; i<dp.length; i++) {
        	sum += dp[i];
        }
         
        return sum;
    }
	
	public static void main(String[] args) {
		int[] difficulty = new int[] {2,4,6,8,10}, profit = new int[] {10,20,30,40,50}, worker = new int[] {4,5,6,7};
		int sum = new ���Ź����Դﵽ�������().maxProfitAssignment(difficulty, profit, worker);
		System.out.println(sum);
	}

}

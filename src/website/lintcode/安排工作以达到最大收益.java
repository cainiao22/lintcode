package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2019年2月26日 上午10:13:09
 * @description
 * 		我们有如下工作：difficulty[i]是第i个工作的难度，profit[i]是第i个工作的利润。

		现在我们有一些工人。 worker[i]是第i个工人的能力，这意味着这个工人最多完成难度为worker[i]的工作。
		
		每个工人最多只能分配一份工作，但一份工作可以多次完成。
		
		例如，如果3个人尝试支付1美元的相同工作，那么总利润将为3美元。 如果工人无法完成任何工作，他的利润为0美元。
		
		我们可以获得的利润最大是多少？
 * @example
 * 		输入: difficulty = [2,4,6,8,10], profit = [10,20,30,40,50], worker = [4,5,6,7]
		输出: 100
		解释: 工人们分别被分配工作难度 [4,4,6,6]，他们各自取得的利润为 [20,20,30,30].
 *
 * @Solution
 */
public class 安排工作以达到最大收益 {
	
	public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
		//将第j个工作分给第i个工人
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
		int sum = new 安排工作以达到最大收益().maxProfitAssignment(difficulty, profit, worker);
		System.out.println(sum);
	}

}

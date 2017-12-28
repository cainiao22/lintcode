package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��12��6�� ����5:28:04
 * @description
 * 		��n�����򣬱��Ϊ0��n-1��ÿ��������һ������������nums�����С�ÿ�δ�����i���Եõ��ķ���Ϊ nums[left] * nums[i] * nums[right]��
 * 		left��right�ֱ��ʾi�������ڵ��������򡣵�i���򱻴�����������������Ϊ���ڡ�Ҫ�󴵱��������򣬵õ����ķ���
 * @example
 * 		���� [4, 1, 5, 10]
		���� 270
		
		nums = [4, 1, 5, 10] burst 1, �÷� 4 * 1 * 5 = 20
		nums = [4, 5, 10]    burst 5, �÷� 4 * 5 * 10 = 200 
		nums = [4, 10]       burst 4, �÷� 1 * 4 * 10 = 40
		nums = [10]          burst 10, �÷� 1 * 10 * 1 = 10
		
		�ܹ��ķ���Ϊ 20 + 200 + 40 + 10 = 270
 *
 * @Solution
 * 		���Ȱ������⣬���ǿ�������nums�������˸���һ��1��������㡣
		dp[i , j]��ʾ������i������j�������ܻ�õ����ķ��������ڵ�i �� ��j�������У��������ȴ�������һ������k��i<=k<=j����
		������k������ʱ���ܻ�õķ���Ϊnums[k]* ���˿�k��ǰһ������* ���˿�k�ĺ�һ���������������ڲ���֪��֮ǰk��ߺ��ұߵ�������û�б�������
		���Բ���ȷ���˿����ҵ�������һ��˼·����Ȼ�������ȴ�������һ������k����ôҲ����ѡ����󴵱�����һ������k����ʱ��k���������־�ȷ���ˣ��ֱ���nums[i-1]��nums[j+1]��

		��ô��õķ�������nums[i-1]* nums[k]* nums[j+1]�����Ǵ���k��õķ������ټ��ϴ���k֮ǰ��õ�������dp[i , k-1]+dp[k+1 , j]
		������k֮ǰ�����ģ�k��ߵ�i������k-1����k�ұߵ�k+1������j������
		���ϣ�dp[i , j]=max(nums[i-1]* nums[k]* nums[j+1] + dp[i , k-1]+ dp[k+1 , j])�����������е� k : i<=k<=j��.
 */
public class ������ extends HH {
	
	public int maxCoins(int[] nums) {
        // write your code here
		int n = nums.length;
		int[] p = new int[nums.length + 2];
		n += 2;
		p[0] = p[n-1] = 1;
		int[][] f = new int[n][n];
		for(int i=0; i<nums.length; i++) {
			p[i+1] = nums[i];
			f[i+1][i+1] = nums[i];//len=0�����
		}
		//�������Ҫ��1��ʼ
		for(int len=1; len<p.length; len++) {
			for(int i=1; i<p.length - len; i++) {
				int j = i + len - 1;
				for(int k = i; k<=j; k++) {//��Ϊk����󴵱��ģ�����i~j�������Ѿ�û���ˣ�ʣ�µ�ֻ��i-1��j+1
					f[i][j] = Math.max(f[i][j], f[i][k-1] + f[k+1][j] + p[i-1]*p[k]*p[j+1]);
				}
			}
		}
		
		return f[1][n-2];
    }
	
	/**
	 * �ݹ鷽ʽ���
	 * @param nums
	 * @return
	 */
	public int maxCoinsDFS(int[] nums) {
		int n = nums.length;
		int[] p = new int[nums.length + 2];
		n += 2;
		p[0] = p[n-1] = 1;
		int[][] f = new int[n][n];
		boolean[][] visited = new boolean[n][n];
		for(int i=0; i<nums.length; i++) {
			p[i+1] = nums[i];
		}
		return search(p, f, visited, 1, nums.length);
	}
	
	private int search(int[] p, int[][] f, boolean[][] visited, int left, int right) {
		if(visited[left][right]) {
			return f[left][right];
		}
		for(int k = left; k<=right; k++) {
			int l = search(p, f, visited, left, k-1);
			int r = search(p, f, visited, k+1, right);
			f[left][right] = Math.max(f[left][right], l + r + p[k]*p[left-1]*p[right+1]);
		}
		visited[left][right] = true;
		return f[left][right];
	}
	
	public static void main(String[] args) {
		int[] nums = new int[] {4, 1, 5, 10};
		print(new ������().maxCoinsDFS(nums));
	}

}

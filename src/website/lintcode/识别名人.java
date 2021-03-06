package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2019年2月22日 下午4:08:50
 * @description
 * 		假设你和 n 个人在一个聚会中(标记为 0 到 n - 1)，其中可能存在一个名人。名人的定义是所有其他 n - 1 人都认识他/她，但他/她不知道任何一个。
		现在你想要找出这个名人是谁或者验证这个名人不存在。你唯一可以做的事情就是提出如下问题：“你好，A，你认识B吗？” 来获取A是否认识B。您需要通过询问尽可能少的问题(以渐近的意义)来找出名人是谁(或验证其不存在)。
		你得到一个辅助函数 bool know(a，b)，它会告诉你A是否知道B.实现一个函数 int findCelebrity(n)，你的函数应该使 knows 的调用次数最少。
 * @example
 * 		样例1

		输入：
		2 // 接下来n*(n-1)行
		0 knows 1
		1 does not know 0
		输出： 1
		解释：
		所有人都认识1，而且1不认识其他人。
		样例2
		
		输入：
		3 // 接下来n*(n-1)行
		0 does not know 1
		0 does not know 2
		1 knows 0
		1 does not know 2
		2 knows 0
		2 knows 1
		输出：0
		解释：
		所有人都认识0，而且0不认识其他人。
		0不认识1，同时1认识0。
		2认识所有人，但是1不认识2。
		注意事项
		如果在这个聚会中有名人， 那么有且只有一个。如果有名人在聚会中则返回名人的标签，如果没有名人，返回 -1。
 *
 * @Solution
 */
public class 识别名人 {
	
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
	 * 有点类似于二分查找 这个思维方式够牛逼
	 * @param n
	 * @return
	 */
	public int findCelebrity2(int n) {
		int left = 0, right = n - 1;
		while(left < right) {
			//如果左边知道右边，左边一定不是名人
			if(knows(left, right)) {
				left ++;
			}else { //如果左边不知道右边，右边一定不是名人
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

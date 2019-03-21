package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2019年3月20日 下午6:30:29
 * @description
 * 		给定n瓶水，其中只有一瓶水是毒药， 小白鼠会在喝下任何剂量的毒药后24小时死亡。
		请问如果需要在24小时的时候知道那瓶水是毒药，那么至少需要几个小白鼠才能保证测试成功？
		（也就是说只能给每只小白鼠喂一次水）
 * @example
 *
 * @Solution
 */
public class 毒药测试 {
	
	public int getAns(int n) {
        int result = 0;
        int cur = 1;
        while(cur < n) {
        	result ++;
        	cur = cur << 1;
        }
        return result;
    }

}

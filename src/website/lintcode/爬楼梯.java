package website.lintcode;

/**
 *
 * @author yanpf
 * @date 2017年12月31日 下午19:24:04
 * @description 有一些原木，现在想把这些木头切割成一些长度相同的小段木头，需要得到的小段的数目至少为
 *              k。当然，我们希望得到的小段越长越好，你需要计算能够得到的小段木头的最大长度。 注意事项
 *
 *              木头长度的单位是厘米。原木的长度都是正整数，我们要求切割得到的小段木头的长度也要求是整数。无法切出要求至少 k 段的,则返回 0
 *              即可。
 *
 * @example 有3根木头[232, 124, 456], k=7, 最大长度为114.
 *
 * @Solution 二分法
 */
public class 爬楼梯 {

    int climbStairs(int n) {
        // write your code here
        if(n == 1){
            return 1;
        }
        if(n == 2){
            return 2;
        }
        if(n <= 0){
            return 0;
        }
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    int climbStairs2(int n) {
        // write your code here
        if(n < 1){
            return 0;
        }
        if(n == 1){
            return 1;
        }
        if(n == 2){
            return 2;
        }
        int pre = 1;
        int cur = 2;
        for(int i=3; i<=n; i++){
            cur = pre + cur;
            pre = cur - pre;
        }

        return cur;
    }

    /**
     *
     * @param n
     * @return
     */
    public int climbStairs3(int n) {
        if (n <= 1) {
            return n;
        }
        int last = 1, lastlast = 1;
        int now = 0;
        for (int i = 2; i <= n; i++) {
            now = last + lastlast;
            lastlast = last;
            last = now;
        }
        return now;
    }
}

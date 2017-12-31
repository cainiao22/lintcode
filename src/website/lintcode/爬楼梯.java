package website.lintcode;

/**
 *
 * @author yanpf
 * @date 2017��12��31�� ����19:24:04
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
public class ��¥�� {

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

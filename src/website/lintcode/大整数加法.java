package website.lintcode;

/**
 *
 * @author yanpf
 * @date 2017年12月31日 下午19:38:29
 * @description 以字符串的形式给出两个非负整数 num1 和 num2，返回 num1 和 num2 的和。

        注意事项
        num1 和 num2 的长度都小于5100。
        num1 和 num2 都只包含数字 0-9。
        num1 和 num2 都不包含任何前导零。
        您不能使用任何内置的BigInteger库内的方法或直接将输入转换为整数。

 * @example 给定 num1 = "123"，num2 = "45" 返回 "168"
 *
 * @Solution
 */
public class 大整数加法 extends HH {

    public String addStrings(String num1, String num2) {
        // write your code here
        int c = 0;
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        StringBuffer sb = new StringBuffer();
        while(i >= 0 && j >= 0){
            int sum = (num1.charAt(i) - '0') + (num2.charAt(j) - '0') + c;
            c = sum / 10;
            sb.append(sum % 10);
            i --;
            j --;
        }
        while(i >= 0){
            int sum = (num1.charAt(i) - '0') + c;
            c = sum / 10;
            sb.append(sum % 10);
            i --;
        }
        while(j >= 0){
            int sum = (num2.charAt(j) - '0') + c;
            c = sum / 10;
            sb.append(sum % 10);
            j --;
        }
        if(c > 0){
            sb.append(c);
        }
        return sb.reverse().toString();
    }

    public String addStrings2(String num1, String num2) {
        String res = "";
        int m = num1.length(), n = num2.length(), i = m - 1, j = n - 1, flag = 0;
        while(i >= 0 || j >= 0){
            int a, b;
            if(i >= 0){
                a = num1.charAt(i--) - '0';
            }
            else{
                a = 0;
            }
            if(j >= 0){
                b = num2.charAt(j--) - '0';
            }
            else{
                b = 0;
            }
            int sum = a + b + flag;
            res =(char)(sum % 10 + '0') + res;
            flag = sum / 10;
        }
        return flag == 1 ? "1" + res: res;
    }

    public static void main(String[] args) {
        String s = new 大整数加法().addStrings("123", "45");
        System.out.println(s);
    }
}

package website.lintcode;

/**
 *
 * @author yanpf
 * @date 2017��12��31�� ����19:38:29
 * @description ���ַ�������ʽ���������Ǹ����� num1 �� num2������ num1 �� num2 �ĺ͡�

        ע������
        num1 �� num2 �ĳ��ȶ�С��5100��
        num1 �� num2 ��ֻ�������� 0-9��
        num1 �� num2 ���������κ�ǰ���㡣
        ������ʹ���κ����õ�BigInteger���ڵķ�����ֱ�ӽ�����ת��Ϊ������

 * @example ���� num1 = "123"��num2 = "45" ���� "168"
 *
 * @Solution
 */
public class �������ӷ� extends HH {

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
        String s = new �������ӷ�().addStrings("123", "45");
        System.out.println(s);
    }
}

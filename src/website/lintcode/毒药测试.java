package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2019��3��20�� ����6:30:29
 * @description
 * 		����nƿˮ������ֻ��һƿˮ�Ƕ�ҩ�� С������ں����κμ����Ķ�ҩ��24Сʱ������
		���������Ҫ��24Сʱ��ʱ��֪����ƿˮ�Ƕ�ҩ����ô������Ҫ����С������ܱ�֤���Գɹ���
		��Ҳ����˵ֻ�ܸ�ÿֻС����ιһ��ˮ��
 * @example
 *
 * @Solution
 */
public class ��ҩ���� {
	
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

package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��10��30�� ����3:38:51
 * @description
 * 		
		����һ���Ǹ��������飬�������λ������ĵ�һ��λ�á�������
		
		�����е�ÿ��Ԫ�ش��������Ǹ�λ�ÿ�����Ծ����󳤶ȡ���������
		
		�ж����Ƿ��ܵ�����������һ��λ�á�
		ע������
		
		�������������������һ����̰�ĺ� ��̬�滮��
		
		̰�ķ���ʱ�临�Ӷ�ΪO��N����
		
		��̬�滮������ʱ�临�Ӷ�ΪΪO��n^2����
		
		�����ֶ�����С�����ݼ���ʹ��ҿ���ͨ�����Ե����ַ�ʽ���������Ϊ���ô��ѧ�����ʹ�ö�̬�滮�ķ�ʽ��������⡣������ö�̬�滮�ķ�ʽ�����������Գ���̰�ķ�����ʹ���ٴ�ͨ��һ�Ρ�

 * @example
 * 		A = [2,3,1,1,4]������ true.
		A = [3,2,1,0,4]������ false.
 *
 * @Solution
 */
public class ��Ծ��Ϸ extends HH {
	
	//�����dp���
	public boolean canJump(int[] A) {
        // write your code here
		int [] jump = new int[A.length];
		jump[0] = A[0];
		for(int i=1; i<A.length-1; i++) {
			if(jump[i-1] >= i && A[i] + i > jump[i-1]) {
				jump[i] = A[i] + i;
			}else {
				jump[i] = jump[i-1];
			}
		}
		return jump[A.length-2] >= A.length-1;
    }
	
	//̰��
	public boolean canJumpGredy(int[] A) {
		//�ʼ��ʱ����Զ��������Զ
		int farthest = A[0];
		for(int i=1; i<A.length; i++) {
			if(farthest >= i && i+A[i] > farthest) {
				farthest = i + A[i];
			}
		}
		return farthest >= A.length-1;
	}
	
	//��̬�滮
	public boolean canJumpDP(int[] A) {
		boolean [] jump = new boolean[A.length];
		jump[0] = true;
		for(int i=1; i<A.length; i++) {
			for(int j=0; j<i; j++) {
				if(jump[j] && j + A[j] >= i){
					jump[i] = true;
					break;
				}
			}
		}
		return jump[A.length-1];
	}

}

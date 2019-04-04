package website.lintcode;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author yanpf
 * @date 2019��4��3�� ����5:38:06
 * @description
 * 		��һ���������������й���è���ֶ���ɹ�������������ʱ�ϸ�ִ�С��Ƚ��ȳ����Ĺ������������Ҫ�ӱ������������
 * 		��ֻ������ѡ��Ҫôѡ���������ж������������һֻ�����ݵ����������ʱ�䣬Խ�絽��Խ�����
 * 		Ҫôѡ������è�򹷣�ͬ����Ҳֻ�������������һֻ����
 * 		Ҳ����˵�������߲�������ѡ��ĳһָ�����
 * 		�뽨��һ�����ݽṹ��ʹ���������������Ϲ��򣬲���ʵ�� enqueue, dequeueAny, dequeueDog�� �� dequeueCat ������
		����ʹ�� LinkedList ��Ϊ���ݽṹʵ�֡�
 * @example
 *
 * @Solution
 */
public class ���������� {
	
	int DOG = 1;
	int CAT = 2;
	
	private static class Animal {
		String name;
		int type;
		
		public Animal(String name, int type) {
			this.name = name;
			this.type = type;
		}
	}
	
	List<Animal> list = new LinkedList<>();
	
	public void enqueue(String name, int type) {
        // write your code here
		list.add(new Animal(name, type));
    }

    /*
     * @return: A string
     */
    public String dequeueAny() {
        // write your code here
    	return list.remove(0).name;
    }

    /*
     * @return: A string
     */
    public String dequeueDog() {
        // write your code here
    	for(Animal animal : list) {
    		if(animal.type == DOG) {
    			list.remove(animal);
    			return animal.name;
    		}
    	}
    	return null;
    }

    /*
     * @return: A string
     */
    public String dequeueCat() {
        // write your code here
    	for(Animal animal : list) {
    		if(animal.type == CAT) {
    			list.remove(animal);
    			return animal.name;
    		}
    	}
    	return null;
    }
}

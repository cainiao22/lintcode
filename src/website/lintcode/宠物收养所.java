package website.lintcode;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author yanpf
 * @date 2019年4月3日 下午5:38:06
 * @description
 * 		在一个宠物避难所里，仅有狗和猫两种动物可供领养，且领养时严格执行“先进先出”的规则。如果有人想要从避难所领养动物，
 * 		他只有两种选择：要么选择领养所有动物中最资深的一只（根据到达避难所的时间，越早到的越资深），
 * 		要么选择领养猫或狗（同样，也只能领养最资深的一只）。
 * 		也就是说，领养者不能随意选择某一指定动物。
 * 		请建立一个数据结构，使得它可以运行以上规则，并可实现 enqueue, dequeueAny, dequeueDog， 和 dequeueCat 操作。
		建议使用 LinkedList 作为数据结构实现。
 * @example
 *
 * @Solution
 */
public class 宠物收养所 {
	
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

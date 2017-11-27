package website.lintcode;

import java.util.List;
import java.util.ArrayList;

public class ª∑–Œ“¿¿µºÏ≤È {

	
	private static List<Integer> elements = new ArrayList<>();
	public static boolean checkCycle(GraphNode graphNode) {
		for(GraphNode parent : graphNode.parents) {
			if(elements.contains(parent.val)) {
				elements.add(parent.val);
				return false;
			}
			elements.add(parent.val);
			boolean flag = checkCycle(parent);
			if(!flag) {
				return flag;
			}
			elements.remove(parent.val);
		}
		return true;
	}
	
	public static void main(String[] args) {
		GraphNode g1 = new GraphNode(1);
		GraphNode g2 = new GraphNode(2);
		GraphNode g3 = new GraphNode(3);
		GraphNode g4 = new GraphNode(4);
		GraphNode g5 = new GraphNode(5);
		g1.parents.add(g2);
		g1.parents.add(g3);
		g3.parents.add(g4);
		g3.parents.add(g5);
		g5.parents.add(g2);
		boolean flag = checkCycle(g1);
		System.out.println(flag);
		
	}
}

class GraphNode {
	
	public Integer val;
	public List<GraphNode> parents;
	public GraphNode(int val) {
		super();
		this.val = val;
		parents = new ArrayList<>();
	}
	
	@Override
	public String toString() {
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append(val).append(": {");
		for(GraphNode parent : parents) {
			sBuffer.append(parent.val).append(",");
		}
		sBuffer.append("}");
		return sBuffer.toString();
	}
}

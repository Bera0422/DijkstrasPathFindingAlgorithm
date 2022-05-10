
public class Edge implements Comparable<Edge> {

	//ID of the starting vertex of the edge.
	private String fromID;
	
	//ID of the ending vertex of the edge.
	private String toID;
	
	//The weight of the edge.
	private int weight;
	
	
	//Constructor method.
	public Edge(String fromID, String toID, int weight) {
		
		this.fromID = fromID;
		this.toID = toID;
		this.weight = weight;
		
	}
	
	//Getter methods for fromID, toID and weight.
	
	public String getFromID() {
		return this.fromID;
	}
	
	public String getToID() {
		return this.toID;
	}
	
	public int getWeight() {
		return this.weight;
	}

	//The Edge class implements the Comparable interface and compareTo method is overridden.
	//It prioritizes the edge which has a lower weight.
	@Override
	public int compareTo(Edge o) {
		// TODO Auto-generated method stub
		
		
		return this.weight - o.getWeight();
	}

	
}

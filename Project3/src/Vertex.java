import java.util.ArrayList;


public class Vertex {
	
	//the ID of the vertex.
	private final String ID;
	
	//The distance between this vertex and the source vertex. It's assigned to infinity initially.
	int distance = Integer.MAX_VALUE;
	
	//The ID of the vertex which has a directed connection to this vertex.
	String preID;
	
	//A boolean to check if this vertex is visited or not.
	boolean isKnown = false;
	
	//A list to store all edges from the vertex to all adjacent vertices.
	ArrayList<Edge> edges = new ArrayList<>();
			
	//Constructor method.
	public Vertex(String ID) {
		this.ID = ID;
	}
	
	//Getter method for ID.
	public String getID() {
		return this.ID;
	}
}

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;


public class Graph {
	
	//Stores the vertices in the graph. HashMap is used to access a vertex by its ID in O(1) time. 
	private HashMap<String, Vertex> vertices = new HashMap<>();
	
	/**
	 * Adds the given vertex into the vertices list.
	 * @param v the vertex to be added.
	 */
	public void addVertex(Vertex v) {
		vertices.put(v.getID() ,v);
	}
	
	/**
	 * Returns the distance between the vertex whose ID is given and the source vertex.
	 * @param t the ID of the vertex whose distance is to be found.
	 * @return the distance between the given vertex and the source vertex.
	 */
	public int getVertexDistance(String t) {
		return vertices.get(t).distance;
	}
	
	/**
	 * Finds and returns the shortest path in the graph from the source vertex to the target vertex if such a path exists. Otherwise -1 is returned.
	 * @param source ID of the source vertex.
	 * @param t ID of the target vertex.
	 * @return shortest path if exists, or -1.
	 */
	
	public String shortestPath(String source, String t) {
		Vertex s = vertices.get(source);
		s.distance = 0;
		s.preID = "";
		ArrayList<Vertex> queue = new ArrayList<Vertex>();
		queue.add(s);
		Vertex v;
		int c = 0;
		
		//By this loop for every vertex in the graph the distance from the source vertex is computed based on Dijkstra's algorithm.
		
		while(c < queue.size()) {
			
			v = queue.get(c);
			c++;
			
			for(Edge e : v.edges) {
				Vertex w = vertices.get(e.getToID());
				
				if(w.distance > e.getWeight() + v.distance) {
						
					w.distance = e.getWeight() + v.distance;
					w.preID = v.getID();
					
				}
				if(!v.isKnown && !w.getID().equals(t)) {
					queue.add(w);
				}
			}
			v.isKnown = true;
			
		
		}
		
		
		Vertex preVer = vertices.get(t);
		String path = t;
		
		//Here the shortest path is being extracted and assigned to the path variable.
		while(preVer.preID != null) {
			path = preVer.preID + " " + path;
			preVer = vertices.get(preVer.preID);
			if(preVer == null) {
				break;
			}
		}
		
		//If a path between the source and target vertices cannot be found -1 will be returned.
		if(path.equals(t)) {
			return "-1";
		}
		
	
		return path.trim();
	
	}
	
	/**
	 * Builds a minimum spanning tree starting from the given vertex and returns the sum of the edge weights of that MST.
	 * @param s the vertex from which the MST is started to be builded.
	 * @return the sum of the MST edge weights.
	 */
	
	
	public int createMST(String s) {
		
		//Here the graph is being converted to undirected.
		for(Vertex v : vertices.values()) {
			for(Edge e : v.edges)
			{	
				Vertex w = vertices.get(e.getToID());
				w.edges.add(new Edge(w.getID(), v.getID(), e.getWeight()));		
		}
			
		}
		
		
		PriorityQueue<Edge> edgeQueue = new PriorityQueue<>();
		
		vertices.get(s).isKnown = true;
		edgeQueue.addAll(vertices.get(s).edges);
		
		int totalCost = 0;
		int visiteds = 1;
		Edge e = null;
		
		//Here the MST is being builded mostly based on Prim's algorithm.
	
		while(visiteds < vertices.size()) {
			
			
			if(edgeQueue.size() == 0 ) {
				return -1;
			}
			
			e = edgeQueue.poll();
			
			if(vertices.get(e.getToID()).isKnown) {
				continue;
			}
			visiteds++;
			Vertex w = vertices.get(e.getToID());
			vertices.get(e.getToID()).isKnown = true;
			for(int i = 0; i < w.edges.size(); i++) {
				if(!vertices.get(w.edges.get(i).getToID()).isKnown) {
					edgeQueue.add(w.edges.get(i));
				}
			}
			
			totalCost += e.getWeight();
			
		}
		
		
		return totalCost;
	}

}

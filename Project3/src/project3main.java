import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class project3main {

	//The time limit that Leyla's father set.
	static int timeLimit;
	
	//mecnunGraph is for Mecnun's travel to reach Leyla.
	//honeymoonGraph is for Mecnun and Leyla's honeymoon trip if they can marry.
	static Graph mecnunGraph, honeymoonGraph;
	
	//source is the ID of the city in which Mecnun lives.
	//target is the ID of the city in which Leyla lives.
	static String source, target;
	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		mecnunGraph = new Graph();
		honeymoonGraph = new Graph();
		
		PrintStream ps = new PrintStream(new File(args[1]));
		
		//Reads the input file. Creates necessary objects and makes assignments to necessary variables.
		readInput(args[0]);
		
		
		//Printing the output file based on the values returned from the called functions.
		
		String mecnunPath = mecnunGraph.shortestPath(source, target);
		if(mecnunPath.equals("-1")) {
			ps.append("-1\n-1");
			
		}
		else {
			
			ps.append(mecnunPath + "\n");
			
			if(timeLimit < mecnunGraph.getVertexDistance(target)) {
				ps.append("-1");
			}
			else {
		ps.append(String.valueOf(honeymoonGraph.createMST(target) * 2));
		}
		}
		
		ps.close();
		
	}

	/**
	 * Reads the input file. Creates necessary objects and makes assignments to necessary variables.
	 * @param in the path of the input file.
	 * @throws FileNotFoundException
	 * 		   If the {@code in} argument is {@code null}
	 */
	
	
	private static void readInput(String in) throws FileNotFoundException {

		Scanner scanner = new Scanner(new File(in));
		
		timeLimit = Integer.valueOf(scanner.nextLine().trim());
		int count = Integer.valueOf(scanner.nextLine().trim());
		
		String[] params = scanner.nextLine().trim().split(" ");
		
		source = params[0];
		target = params[1];
		
		Vertex v;
		while(count > 0) {
			count--;
			params = scanner.nextLine().trim().split(" ");
			String id = params[0];
			
			v = new Vertex(id);
			
			
			
			for(int i = 1; i < params.length; i+=2) {
				if(!params[i].equals(id) && !(id.equals(target) && params[i].contains("c"))) {
				v.edges.add(new Edge(id, params[i], Integer.valueOf(params[i+1])));
				}
			}
			if(id.equals(target)){
				mecnunGraph.addVertex(v);
				honeymoonGraph.addVertex(v);
			}
			else if(id.contains("c")) {
				mecnunGraph.addVertex(v);
			}
			else {
				honeymoonGraph.addVertex(v);
				
			}
			
		}
		
		
		scanner.close();
	}

}

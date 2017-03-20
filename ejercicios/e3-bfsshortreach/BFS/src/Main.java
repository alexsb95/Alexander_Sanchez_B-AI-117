import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class Main {
	private static ArrayList<Graph> Queries = new ArrayList<>();
	
	 public static void main (String [ ] args) {
         
         Main newProgram = new Main();
         
 		Scanner scanner = new Scanner(System.in);
        readInput(scanner);
 	    scanner.close();

 	    for(Graph element : Queries){
 	    	BFS Algoritm = new BFS(element);
 	 	    Algoritm.travel();
 	    }
 	    


      }

	 
	private static Edge readEdges(Scanner pScanner){

		System.out.print("Edges: ");
        int numInitial = -1;
        int numFinal = -1;
       
        try {
        	numInitial = pScanner.nextInt();
        	numFinal = pScanner.nextInt();
        }
        catch (Exception e) {
        	e.printStackTrace();
            System.out.println("No se pudo convertitr a numero");
        }
        
        
        
		Edge newEdge = new Edge(); 
		newEdge.setSource(numInitial);
		newEdge.setDestination(numFinal);
		
		return newEdge;
	}
	
	private static Graph readGraph(Scanner pScanner){

		System.out.print("Nodos y aristas: ");
        int numNodes = -1;
        int numEdges = -1;
        int initialNode = -1;
       
        try {
        	numNodes = pScanner.nextInt();
        	numEdges = pScanner.nextInt();
        }
        catch (Exception e) {
        	e.printStackTrace();
            System.out.println("No se pudo convertitr a numero");
        }
        
        if((numNodes < 2 || numNodes > 1000) || (numEdges < 1 || numEdges > numNodes * (numNodes -1 ) / 2)){
        	return null;
        }else{
        	/* 		Intiate the reading of the graphs		*/
        	Graph newGraph = new Graph();
    		newGraph.setCantEdge(numEdges);
    		newGraph.setCantNode(numNodes);
    		newGraph.listNodes = new ArrayList<>();
    		newGraph.listEdges = new ArrayList<>();
    		
    		for(int index = 1; index <= numNodes; index++){
    			Node newNode = new Node();
    			newNode.setState(index);
    			newNode.adjacent = new ArrayList<>();
    			newGraph.listNodes.add(newNode);
    		}
    	
    		
        	for(int i = 0; i < numEdges; i++){
        		Edge newEdge = readEdges(pScanner);
    			newGraph.listEdges.add(newEdge);
    			Node destinationEdge = new Node();
    			destinationEdge.setState(newEdge.getDestination());
    			Node sourceEdge = new Node();
    			sourceEdge.setState(newEdge.getSource());

    			newGraph.listNodes.get(newEdge.getSource()-1).adjacent.add(destinationEdge);
    			newGraph.listNodes.get(newEdge.getDestination()-1).adjacent.add(sourceEdge);
            }
        	
        	/*		Read the initial node		*/
        	
        	 try {
             	initialNode = pScanner.nextInt();
             }
             catch (Exception e) {
             	e.printStackTrace();
                 System.out.println("No se pudo convertitr a numero");
             }
        	 
        	 if(initialNode > numNodes)
        		 return null;
        	 else{
        		 newGraph.setInitialNode(initialNode);
        	 }
        	 return newGraph;
        } 
		
	}
	
	public static void readInput(Scanner pScanner){


        System.out.print("Grafo: ");
        int numGraphs = -1;
        
        try {
        	numGraphs = pScanner.nextInt();
        }
        catch (Exception e) {
            System.out.println("No se pudo convertitr a numero");
        }


        if(numGraphs < 1 || numGraphs > 10)
        	return;
        else{
            for(int i = 0; i < numGraphs; i++){
            	Queries.add(readGraph(pScanner));
            }
        }

        
	}
	

	 
}

class BFS {
	private Graph graph;
	private boolean[] visitedNodes;
	private Queue<Node> frontier;
	private HashMap<Node, Node> preNode;
	private HashMap<Integer, Integer> distanceCost;
	
	public BFS (Graph pGraph){
		this.graph = pGraph; 
	}
	
	public void travel(){
		visitedNodes = new boolean[graph.getCantNode()];

		frontier = new LinkedList<Node>();
		preNode = new HashMap <>();
		distanceCost = new HashMap <>();
		
		Node beginig = null;
		for(Node element : graph.listNodes){
			if(element.getState() == graph.getInitialNode())
				beginig = element;
		}
	
		distanceCost.put(beginig.getState(), 0);
		visitedNodes[graph.getInitialNode() - 1] = true; 
		frontier.add(beginig);
		
		while(frontier.size() > 0){
			Node node = (Node)frontier.remove();
			visitedNodes[node.getState() - 1] = true;
			 System.out.print(node.getState()+" ");
			 
			for(Node element : node.adjacent){
				Integer cost = distanceCost.get(element.getState());
				if( cost == null || (cost != null && cost > distanceCost.get(node.getState()) + 6)){
					distanceCost.put(element.getState(), distanceCost.get(node.getState()) + 6);
				}
				
				if(visitedNodes[element.getState() - 1] == false){
					visitedNodes[element.getState() -1] = true;
					
					Node vistNode = null;
					for(Node elemNode : graph.listNodes){
						if(elemNode.getState() == element.getState())
							vistNode = elemNode;
					}
				
					frontier.add(vistNode);
				}

			}
			
		}
		
		for(int index = 0; index < visitedNodes.length; index++ ){
			if(visitedNodes[index] == false)
				distanceCost.put(index + 1, -1);
		}
		
		
		for (int value: distanceCost.values()){ 
			if(value != 0)
				System.out.print(value + " ");
			
		} 
        System.out.println("");
	}

	
}


class Node {
	private int state;
	public ArrayList<Node> adjacent;

	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
}

class Edge {
	private int source;
	private int destination;
	
	public int getSource() {
		return source;
	}
	public void setSource(int source) {
		this.source = source;
	}
	public int getDestination() {
		return destination;
	}
	public void setDestination(int destination) {
		this.destination = destination;
	}
	
	
}

class Graph {
	public ArrayList<Node> listNodes;
	public ArrayList<Edge> listEdges;
	private int cantNode;
	private int cantEdge;
	private int initialNode;
	
	public int getCantNode() {
		return cantNode;
	}
	public void setCantNode(int cantNode) {
		this.cantNode = cantNode;
	}
	public int getCantEdge() {
		return cantEdge;
	}
	public void setCantEdge(int cantEdge) {
		this.cantEdge = cantEdge;
	}
	
	public int getInitialNode() {
		return initialNode;
	}
	public void setInitialNode(int initialNode) {
		this.initialNode = initialNode;
	}

}



import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	private ArrayList<Graph> Queries = new ArrayList<>();
	
	
	private static int[] readEdges(Scanner pScanner){

		System.out.print("Edges: ");
        int numInitial = -1;
        int numFinal = -1;
       
        try {
        	numInitial = pScanner.nextInt();
        	numFinal = pScanner.nextInt();
            System.out.println("intial : " + numInitial + "  final: " +numFinal);
        }
        catch (Exception e) {
        	e.printStackTrace();
            System.out.println("No se pudo convertitr a numero");
        }
        
		int numbers [] = {numInitial, numFinal};
		
		return numbers;
	}
	
	private static void readGraph(Scanner pScanner){

		System.out.print("Nodos y aristas: ");
        int numNodes = -1;
        int numEdges = -1;
        int initialNode = -1;
       
        try {
        	numNodes = pScanner.nextInt();
        	numEdges = pScanner.nextInt();
            System.out.println("nodes : " + numNodes + "  edges: " +numEdges);
        }
        catch (Exception e) {
        	e.printStackTrace();
            System.out.println("No se pudo convertitr a numero");
        }
        
        if((numNodes < 2 || numNodes > 1000) || (numEdges < 1 || numEdges > numNodes * (numNodes -1 ) / 2)){
        	return;
        }else{
        	
        	/* 		Intiate the reading of the graphs		*/
        	Graph newGraph = new Graph();
    		newGraph.setCantEdge(numEdges);
    		newGraph.setCantNode(numNodes);
    		newGraph.listNodes = new ArrayList<>();
    		
    		for(int index = 0; index < numNodes; index++){
    			Node newNode = new Node();
    			newNode.setState(index);
    			newNode.adjacent = new ArrayList<>();
    			newGraph.listNodes.add(newNode);
    			
    		}
    		
    		int edges [];
        	for(int i = 0; i < numEdges; i++){
        		edges = readEdges(pScanner);
        		Node newNode = new Node();
        		newNode.setState(edges[1]);
        		newGraph.listNodes.get(edges[0]).adjacent.add(newNode);
            }
        	
        	/*		Read the initial node		*/
        	
        	 try {
        		System.out.println("Nodo inicial: ");
             	initialNode = pScanner.nextInt();
                 System.out.println("nodes : " + numNodes + "  edges: " +numEdges);
             }
             catch (Exception e) {
             	e.printStackTrace();
                 System.out.println("No se pudo convertitr a numero");
             }
        	 
        	 if(initialNode > numNodes)
        		 return;
        	 
        } 
		
	}
	
	public static void readInput(Scanner pScanner){


        System.out.print("Grafo: ");
        int numGraphs = -1;
        
        try {
        	numGraphs = pScanner.nextInt();
            System.out.println("graphs : " + numGraphs);
        }
        catch (Exception e) {
            System.out.println("No se pudo convertitr a numero");
        }


        if(numGraphs < 1 || numGraphs > 10)
        	return;
        else{
            for(int i = 0; i < numGraphs; i++){
            	readGraph(pScanner);
            }
        }

        
	}
	
	 public static void main (String [ ] args) {
         
         Main newProgram = new Main();
         
 		Scanner scanner = new Scanner(System.in);
        readInput(scanner);
 	    scanner.close();
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


class Graph {
	public ArrayList<Node> listNodes;
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
	

}

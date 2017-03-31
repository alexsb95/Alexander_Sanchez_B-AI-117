import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Solution {

	private static AStarSearch ASS;
	
	public static void main (String [ ] args) {
		ASS = new AStarSearch();
		
 		Scanner scanner = new Scanner(System.in);
		readInput(scanner);
		scanner.close();
		
		ASS.evaluateGrid();

    	

 	 }
	
	private static void readInput(Scanner pScanner){
		int size = -1;
        
        try {
        	size = pScanner.nextInt();
        }
        catch (Exception e) {
            System.out.println("No se pudo convertitr a numero");
        }

        if(size >= 3 && size <= 5){
        	ASS.setSize(size);
        	readCells(pScanner);
        }
	}
	
	private static void readCells(Scanner pScanner){
		int matrix[][] = new int[ASS.getSize()][ASS.getSize()];
        
        try {
        	
        	for(int i = 0; i < ASS.getSize(); i++){
        		for(int j =0; j < ASS.getSize(); j++){
        			matrix[i][j] = pScanner.nextInt();
        		}
        	}
        	
        	Puzzle oriPuzzle = new Puzzle(ASS.getSize(), matrix);
        	oriPuzzle.setMove("");
        	ASS.setOriginal(oriPuzzle);
        }
        catch (Exception e) {
            System.out.println("No se pudo convertitr a numero");
        }
	}
}


class AStarSearch{
	private int size;
	private Puzzle original;
	
	ArrayList <String> visitedPuzzle;
	PriorityList frontier;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Puzzle getOriginal() {
		return original;
	}

	public void setOriginal(Puzzle original) {
		this.original = original;
	}

	
	private void evaluateCost(Puzzle pCurrent, Puzzle pNeighbor){
		
		if(pNeighbor != null && !visitedPuzzle.contains(pNeighbor.toString())){
			int costFromStart = pCurrent.getCostFromStart() + pCurrent.getCost(pNeighbor);
			
			boolean inFrotier = frontier.contains(pNeighbor);
			if(!inFrotier || costFromStart > pNeighbor.getCostFromStart()){
				pNeighbor.setParent(pCurrent);
				pNeighbor.setCostFromStart(costFromStart);
				
				if(!inFrotier){
					frontier.add(pNeighbor);
				}
			}
		}
	}
	
	private int[][]  copyMatrix (int[][] pMatrix){
		int[][] newMatrix = new int[size][size];
		for (int i = 0; i < size; i++) {
		  newMatrix[i] = Arrays.copyOf(pMatrix[i], size);
		}
		 return newMatrix;
	} 
	
	private void switchCells(String pSide, int[][] pMatrix, int[] position){
		int temp = -1;
		switch(pSide){
		case("UP"):
			temp = pMatrix[position[0] - 1][position[1]];
			pMatrix[position[0] - 1][position[1]] = 0;
			break;
		case("LEFT"):
			temp = pMatrix[position[0]][position[1] - 1];
			pMatrix[position[0]][position[1] - 1] = 0;
			break;
		case("DOWN"):
			temp = pMatrix[position[0] + 1][position[1]];
			pMatrix[position[0] + 1][position[1]] = 0;
			break;
		case("RIGHT"):
			temp = pMatrix[position[0]][position[1] + 1];
			pMatrix[position[0]][position[1] + 1] = 0;
			break;
		}
		
		pMatrix[position[0]][position[1]] = temp;
	}
	
	private LinkedList<Puzzle> createNegiboors(Puzzle pCurrent){
		LinkedList<Puzzle> neigh = new LinkedList<>();
		
		int[] postion0 = pCurrent.get0();
		
		if(postion0[0] - 1 >= 0){
			int[][] newMatrix = copyMatrix(pCurrent.getMatrix());
			switchCells("UP", newMatrix, postion0);
			Puzzle neighboor = new Puzzle(pCurrent.getSize(), newMatrix);	
			neighboor.setMove("UP");			
			neigh.add(neighboor);
		
		}
		
		if(postion0[1]  - 1 >= 0){
			int[][] newMatrix = copyMatrix(pCurrent.getMatrix());
			switchCells("LEFT", newMatrix, postion0);
			
			Puzzle neighboor = new Puzzle(pCurrent.getSize(), newMatrix);
			neighboor.setMove("LEFT");
			//System.out.println("LEFT: "+neighboor.toString());
			neigh.add(neighboor);
		}
		
		if(postion0[0] + 1 < size){
			int[][] newMatrix = copyMatrix(pCurrent.getMatrix());
			switchCells("DOWN", newMatrix, postion0);
			
			Puzzle neighboor = new Puzzle(pCurrent.getSize(), newMatrix);
			//System.out.println("DOWN: "+neighboor.toString());
			neighboor.setMove("DOWN");
			neigh.add(neighboor);
		}
		
		if(postion0[1] + 1 < size){
			int[][] newMatrix = copyMatrix(pCurrent.getMatrix());
			switchCells("RIGHT", newMatrix, postion0);
			
			Puzzle neighboor = new Puzzle(pCurrent.getSize(), newMatrix);
			//System.out.println("RIGHT: "+neighboor.toString());
			neighboor.setMove("RIGHT");
			neigh.add(neighboor);
		}
		
		return neigh;
	}
	
	public void evaluateGrid(){
		visitedPuzzle = new ArrayList<>();
		frontier = new PriorityList();
	
		frontier.add(original);
		visitedPuzzle.add(original.toString());
		
		while(frontier.size() > 0){
			Puzzle current = (Puzzle)frontier.remove();
			
			//System.out.println("X: "+current.getX()+ " Y: " +current.getY());
			
			if(current == null){
				System.out.println("Error");
				break;
			}
				
			
			visitedPuzzle.add(current.toString());
			
			if(current.getEstimatedCostToGoal() == 0){
				printPath(current);
				return;
			}
			
			LinkedList<Puzzle> neighboors = createNegiboors(current);
			
			for(Puzzle neighboor :  neighboors){
				evaluateCost(current, neighboor);
			}
				
		}
		
		
	}
	
	private void printPath(Puzzle pObjetive){
	     Puzzle current = pObjetive;
	
	     String print = current.getMove();
	     int cost=0;
	     while(current.getParent()!=null){
	    	 print=current.getParent().getMove()+"\n"+print;
	         current = current.getParent();
	         cost++;
	     } 
	     System.out.println(cost);
	     System.out.println(print);
	}
}

class Puzzle{
	private int size;
	private int[][] matrix;
	private Puzzle parent;
	private int costFromStart;
	private int estimatedCostToGoal;
	private String move;
	
	public String getMove() {
		return move;
	}

	public void setMove(String move) {
		this.move = move;
	}

	Puzzle (int size, int[][] matrix){
		this.size = size;
		this.matrix = matrix;
		this.estimatedCostToGoal = estimatedCostToGoal();
	}
	
	public int[][] getMatrix() {
		return matrix;
	}
	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}
	public Puzzle getParent() {
		return parent;
	}
	public void setParent(Puzzle parent) {
		this.parent = parent;
	}
	public int getCostFromStart() {
		return costFromStart;
	}
	public void setCostFromStart(int costFromStart) {
		this.costFromStart = costFromStart;
	}
	public int getEstimatedCostToGoal() {
		return estimatedCostToGoal;
	}
	public void setEstimatedCostToGoal(int estimatedCostToGoal) {
		this.estimatedCostToGoal = estimatedCostToGoal;
	}
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public int getCost() {
		return costFromStart + estimatedCostToGoal;
	}


	public int getCost(Puzzle pCell){
		return costFromStart + getEstimatedCost(pCell);
	}

	  
	public int estimatedCostToGoal(){
		int estimate = 0;
	    for(int i = 0; i < size; i++){
			for(int j =0; j < size; j++){
				estimate += Math.abs(matrix[i][j] - (i*size+j));
			}
		}
	    
	    return estimate;
		  
	  }
	
	public int getEstimatedCost(Puzzle pObjetive){
		int estimate = 0;
	    for(int i = 0; i < size; i++){
			for(int j =0; j < size; j++){
				estimate += Math.abs(matrix[i][j] - pObjetive.matrix[i][j]);
			}
		}
	    
	    return estimate;
		  
	  }
	  
	  @Override
	  public String toString(){
		String str = "";
		for(int i = 0; i < size; i++){
			for(int j =0; j < size; j++){
				str += matrix[i][j]+" ";
			}
			str += '\n';
		}
		
		return str;
	  }

	public int compareTo(Object other) {
	    int v = this.getCost() - ((Puzzle)other).getCost();
	    return (v>0)?1:(v<0)?-1:0; // sign function
	}
	
	public int[] get0(){
		
		for(int i = 0; i < size; i++){
			for(int j =0; j < size; j++){
				 if( matrix[i][j] == 0){
					 int[] index = {i, j};
					 return index;
				 }

			}
		}
		
		return null;
	}
}


class PriorityList extends LinkedList {

    public void add(Comparable object) {
      for (int i=0; i<size(); i++) {
        if (object.compareTo(get(i)) <= 0) {
          add(i, object);
          return;
        }
      }
      addLast(object);
    }
    
  }
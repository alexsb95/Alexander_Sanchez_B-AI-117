import java.util.LinkedList;
import java.util.Scanner;

public class Solution {

	private static ASearch aSearch;
	
	public static void main (String [ ] args) {
		aSearch = new ASearch();
		
 		Scanner scanner = new Scanner(System.in);
		readInput(scanner);
		scanner.close();

 	 }
	 
	 private static void readInput(Scanner pScanner){
		 
		 readPacman(pScanner);
		 readFood(pScanner);
		 readSizeMatrix(pScanner);
		 readMatrix(pScanner);
		 
		
		 System.out.println("pacman x: "+aSearch.getInitialCell().getX()+" y: "+aSearch.getInitialCell().getY());  
		 System.out.println("food x: "+aSearch.getObjetiveCell().getX()+" y: "+aSearch.getObjetiveCell().getY()); 
		 System.out.println("matrix x: "+aSearch.getMatrixSize().getX()+" y: "+aSearch.getMatrixSize().getY()); 
		 
		 aSearch.getPath();
		 //aSearch.printMatrix();

	 }
	 
	 private static void readPacman(Scanner pScanner){ 
	        int x = -1;
	        int y = -1;
	        
	        try {
	        	x = pScanner.nextInt();
	        	y = pScanner.nextInt();
	        }
	        catch (Exception e) {
	            System.out.println("No se pudo convertitr a numero");
	        }

	        if(x >= 0 && y >= 0){
	        	aSearch.setInitialCell(new Cell(x, y));
	        }
	 }
	 
	 private static void readFood(Scanner pScanner){
 
	        int x = -1;
	        int y = -1;
	        
	        try {
	        	x = pScanner.nextInt();
	        	y = pScanner.nextInt();
	        }
	        catch (Exception e) {
	            System.out.println("No se pudo convertitr a numero");
	        }

	        if(x >= 0 && y >= 0){
	        	aSearch.setObjetiveCell(new Cell(x, y));
	        }
	 }
	 
	 private static void readSizeMatrix(Scanner pScanner){
		 
	        int x = -1;
	        int y = -1;
	        
	        try {
	        	x = pScanner.nextInt();
	        	y = pScanner.nextInt();
	        }
	        catch (Exception e) {
	            System.out.println("No se pudo convertitr a numero");
	        }

	        if(x >= 0 && y >= 0){
	        	aSearch.setMatrixSize(new Cell(x, y));
	        }
	 }
	 
	 private static void readMatrix(Scanner pScanner){
		 ma newMatrix = new ma();
	     aSearch.setMatrix(newMatrix.matrixPac);
	        
	 }
	
}

class ASearch{
	private Cell initialCell;
	private Cell objetiveCell;
	private Cell matrixSize;
	private Cell[][] matrix;
	
	boolean[][] visitedCells;
	PriorityList frontier;
	
	public Cell getInitialCell() {
		return initialCell;
	}
	public void setInitialCell(Cell initialCell) {
		this.initialCell = initialCell;
	}
	public Cell getObjetiveCell() {
		return objetiveCell;
	}
	public void setObjetiveCell(Cell objetiveCell) {
		this.objetiveCell = objetiveCell;
	}
	public Cell getMatrixSize() {
		return matrixSize;
	}
	public void setMatrixSize(Cell arraySize) {
		this.matrixSize = arraySize;
	}
	public Cell[][] getMatrix() {
		return matrix;
	}
	public void setMatrix(char[][] pBlocked) {
		matrix = new Cell[matrixSize.getX()][matrixSize.getY()];
		
		for(int x = 0; x < matrixSize.getX(); x++ ){
			for(int y = 0; y < matrixSize.getY(); y++){
				if(pBlocked[x][y] == '%')
					matrix[x][y] = null;
				else{
					matrix[x][y] = new Cell(x,y);
					matrix[x][y].setEstimatedCostToGoal( matrix[x][y].getEstimatedCost(objetiveCell));
				}
			}
		}
		
		matrix[initialCell.getX()][initialCell.getY()].setCostFromStart(0);
		
	}
	
	
	private void evaluateCost(Cell pCurrent, Cell pNeighbor){
		
		if(pNeighbor != null && !visitedCells[pNeighbor.getX()][pNeighbor.getY()]){
			int costFromStart = pCurrent.getCostFromStart() + pCurrent.getCost(pNeighbor);
			
			boolean inFrotier = frontier.contains(pNeighbor);
			if(!inFrotier || costFromStart > pNeighbor.getCostFromStart()){
				pNeighbor.setPathParent(pCurrent);
				pNeighbor.setCostFromStart(costFromStart);
				
				if(!inFrotier){
					frontier.add(pNeighbor);
				}
			}
		}
	}
	
	public void getPath(){
		visitedCells = new boolean [matrixSize.getX()][matrixSize.getY()];
		frontier = new PriorityList();

		frontier.add(initialCell);
		
		while(frontier.size() > 0){
			Cell current = (Cell)frontier.remove();
			
			System.out.println("X: "+current.getX()+ " Y: " +current.getY());
			
			if(current == null)
				break;
			
			visitedCells[current.getX()][current.getY()] = true;
			
			if(current.getX() == objetiveCell.getX() && current.getY() == objetiveCell.getY())
				return;
			
			if(current.getX() - 1 >= 0){
				evaluateCost(current, matrix[current.getX() - 1][current.getY()]);
			}
			
			if(current.getY() - 1 >= 0){
				evaluateCost(current, matrix[current.getX()][current.getY() - 1]);
			}
			
			if(current.getX() + 1 < matrixSize.getX()){
				evaluateCost(current, matrix[current.getX() + 1][current.getY()]);
			}
			
			if(current.getY() + 1 < matrixSize.getY()){
				evaluateCost(current, matrix[current.getX()][current.getY() + 1]);
			}
			
		}
	
		

	}
	
	public void printMatrix(){
		for(int x = 0; x < matrixSize.getX(); x++ ){
			for(int y = 0; y < matrixSize.getY(); y++){
				if(matrix[x][y]==null)
					System.out.print("% ");
				else
					System.out.print(matrix[x][y].getEstimatedCostToGoal()+" ");
	
			}
			System.out.println();
		}
		
	}
	
}

class Cell {
	private int x;
	private int y;
	private Cell pathParent;
	private int costFromStart;
	private int estimatedCostToGoal;
	
	Cell (int pX, int pY){
		x =pX;
		y = pY;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public Cell getPathParent() {
		return pathParent;
	}

	public void setPathParent(Cell pathParent) {
		this.pathParent = pathParent;
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


	public int compareTo(Object other) {
		    int v = this.getCost() - ((Cell)other).getCost();
		    return (v>0)?1:(v<0)?-1:0; // sign function
	}
	
	  public int getCost() {
	    return costFromStart + estimatedCostToGoal;
	  }


	  public int getCost(Cell pCell){
		  return costFromStart + getEstimatedCost(pCell);
	  }

	  
	  public int getEstimatedCost(Cell pObjetive){
		  return  Math.abs(x - pObjetive.getX()) + Math.abs( y - pObjetive.getY());
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

class ma{
	public char matrixPac[][] =    
	       {{'%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%'},
	       {'%','-','-','-','-','-','-','-','%','-','%','-','%','-','-','-','-','-','-','-','-','-','-','-','%','-','-','-','%','-','-','-','-','-','%','-','%'},
	       {'%','-','%','%','%','%','%','%','%','-','%','-','%','%','%','-','%','-','%','%','%','-','%','%','%','-','%','%','%','%','%','%','%','-','%','-','%'},
	       {'%','-','-','-','-','-','-','-','%','-','-','-','-','-','-','-','%','-','%','-','-','-','-','-','%','-','-','-','-','-','%','-','%','-','-','-','%'},
	       {'%','%','%','%','%','-','%','%','%','%','%','-','%','%','%','-','%','-','%','-','%','-','%','%','%','-','%','%','%','%','%','-','%','-','%','%','%'},
	       {'%','-','-','-','%','-','%','-','%','-','%','-','-','-','%','-','%','-','%','-','%','-','-','-','%','-','%','-','-','-','%','-','%','-','-','-','%'},
	       {'%','-','%','%','%','-','%','-','%','-','%','-','%','%','%','-','%','%','%','%','%','-','%','%','%','-','%','-','%','%','%','-','%','%','%','-','%'},
	       {'%','-','-','-','-','-','-','-','%','-','-','-','-','-','%','-','-','-','%','-','-','-','%','-','-','-','-','-','%','-','%','-','%','-','-','-','%'},
	       {'%','%','%','-','%','%','%','%','%','%','%','%','%','-','%','%','%','%','%','%','%','-','%','%','%','-','%','%','%','-','%','-','%','-','%','-','%'},
	       {'%','-','-','-','-','-','-','-','-','-','-','-','-','-','%','-','-','-','-','-','-','-','%','-','%','-','-','-','%','-','-','-','-','-','%','-','%'},
	       {'%','-','%','-','%','%','%','%','%','-','%','-','%','%','%','-','%','-','%','-','%','%','%','-','%','-','%','%','%','-','%','%','%','-','%','-','%'},
	       {'%','-','%','-','%','-','-','-','-','-','%','-','%','-','%','-','%','-','%','-','-','-','-','-','%','-','-','-','%','-','%','-','%','-','%','-','%'},
	       {'%','-','%','-','%','-','%','%','%','%','%','%','%','-','%','-','%','%','%','%','%','%','%','%','%','-','%','%','%','-','%','-','%','%','%','-','%'},
	       {'%','-','%','-','%','-','%','-','-','-','-','-','%','-','-','-','%','-','-','-','-','-','%','-','-','-','-','-','%','-','-','-','%','-','-','-','%'},
	       {'%','%','%','-','%','%','%','-','%','-','%','%','%','%','%','-','%','%','%','%','%','-','%','%','%','-','%','%','%','-','%','%','%','%','%','-','%'},
	       {'%','-','-','-','-','-','%','-','%','-','%','-','-','-','-','-','%','-','%','-','-','-','-','-','%','-','%','-','-','-','%','-','%','-','%','-','%'},
	       {'%','-','%','-','%','-','%','-','%','-','%','%','%','-','%','%','%','-','%','%','%','-','%','%','%','-','%','-','%','-','%','-','%','-','%','-','%'},
	       {'%','-','%','-','%','-','%','-','%','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','%','-','%','-','%','-','-','-','-','-','%'},
	       {'%','%','%','-','%','%','%','%','%','%','%','-','%','-','%','-','%','%','%','%','%','-','%','%','%','-','%','-','%','%','%','-','%','%','%','%','%'},
	       {'%','-','-','-','-','-','-','-','%','-','%','-','%','-','%','-','-','-','-','-','%','-','-','-','%','-','-','-','-','-','%','-','%','-','-','-','%'},
	       {'%','%','%','%','%','-','%','-','%','-','%','%','%','%','%','%','%','%','%','-','%','%','%','%','%','%','%','%','%','%','%','-','%','-','%','%','%'},
	       {'%','-','-','-','%','-','%','-','-','-','-','-','-','-','-','-','-','-','%','-','%','-','-','-','-','-','%','-','-','-','%','-','%','-','-','-','%'},
	       {'%','-','%','%','%','-','%','%','%','%','%','-','%','%','%','%','%','%','%','%','%','-','%','%','%','%','%','-','%','-','%','-','%','%','%','-','%'},
	       {'%','-','%','-','-','-','%','-','-','-','-','-','-','%','-','-','-','-','-','-','-','-','%','-','-','-','-','-','%','-','-','-','-','-','-','-','%'},
	       {'%','-','%','-','%','-','%','%','%','%','%','-','%','%','%','-','%','-','%','-','%','-','%','-','%','%','%','%','%','%','%','%','%','%','%','%','%'},
	       {'%','-','%','-','%','-','-','-','%','-','-','-','-','-','%','-','%','-','%','-','%','-','-','-','-','-','-','-','%','-','-','-','%','-','%','-','%'},
	       {'%','-','%','-','%','%','%','-','%','%','%','-','%','-','%','-','%','-','%','%','%','%','%','%','%','%','%','-','%','%','%','-','%','-','%','-','%'},
	       {'%','-','%','-','-','-','%','-','%','-','-','-','%','-','%','-','%','-','-','-','%','-','%','-','-','-','%','-','%','-','%','-','-','-','-','-','%'},
	       {'%','-','%','%','%','-','%','%','%','-','%','%','%','%','%','-','%','%','%','-','%','-','%','-','%','%','%','%','%','-','%','-','%','%','%','%','%'},
	       {'%','-','-','-','-','-','-','-','%','-','-','-','%','-','-','-','-','-','%','-','%','-','-','-','-','-','%','-','-','-','%','-','%','-','-','-','%'},
	       {'%','%','%','-','%','-','%','%','%','%','%','-','%','%','%','%','%','-','%','%','%','-','%','%','%','-','%','-','%','%','%','-','%','-','%','%','%'},
	       {'%','-','%','-','%','-','%','-','%','-','%','-','%','-','%','-','-','-','-','-','%','-','%','-','-','-','%','-','%','-','-','-','%','-','%','-','%'},
	       {'%','-','%','-','%','%','%','-','%','-','%','-','%','-','%','-','%','%','%','%','%','%','%','%','%','-','%','-','%','-','%','-','%','-','%','-','%'},
	       {'%','-','-','-','%','-','-','-','%','-','-','-','%','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','%','-','-','-','-','-','%'},
	       {'%','-','%','-','%','-','%','-','%','%','%','-','%','%','%','-','%','%','%','%','%','%','%','-','%','%','%','-','%','%','%','-','%','%','%','-','%'},
	       {'%','.','%','-','%','-','%','-','-','-','-','-','-','-','%','-','-','-','%','-','-','-','-','-','-','-','%','-','-','-','%','-','%','-','-','P','%'},
	       {'%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%'}};
	
}
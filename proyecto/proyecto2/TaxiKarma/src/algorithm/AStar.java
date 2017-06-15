
package algorithm;

/**
 *
 * @author Alex
 */

/*
    1. Crear la instancia con la matriz
    2. Establecer el nodo inicial y final
*/

import java.util.ArrayList;
import java.util.LinkedList;


public class AStar implements SearchAlgorithm{
    	private Cell initialCell;
	private Cell objetiveCell;
	private int xLength;
        private int yLength;
	private Cell[][] matrix;
	
	private boolean[][] visitedCells;
	private LinkedList frontier;
        
        public AStar(Cell[][] pMatrix, int pXLen, int pYLen){
            setMatrix(pMatrix, pXLen, pYLen);
        }
	
	public Cell getInitialCell() {
		return initialCell;
	}
	public void setInitialCell(Cell initialCell) {
		this.initialCell = initialCell;    
	}
	public Cell getObjetiveCell() {
		return objetiveCell;
	}
	public void setObjetiveCell(Cell pDestination) {                
		this.objetiveCell = pDestination;
        }
	public Cell[][] getMatrix() {
		return matrix;
	}
	public void setMatrix(Cell[][] pMatrix, int pXLen, int pYLen) {
            xLength = pXLen;
            yLength = pYLen;
            
            matrix = pMatrix;	
	}
	
        private void setEstimated(){
            for(int x = 0; x <xLength; x++ ){
                    for(int y = 0; y < yLength; y++){
                            if(matrix[x][y] != null)
                                matrix[x][y].setEstimatedCostToGoal( matrix[x][y].getEstimatedCost(objetiveCell));
                    }
            }
        }
	
	private void evaluateCost(Cell pCurrent, Cell pNeighbor){
		
            if(!visitedCells[pNeighbor.getX()][pNeighbor.getY()]){
                int costFromStart;
                if(pCurrent.getWeight() > 0)
                    costFromStart = pCurrent.getCostFromStart() + (int) Math.round(pCurrent.getWeight());
                else
                    costFromStart = pCurrent.getCostFromStart() + 1;

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
        
        private void iniComponents(int pIniX, int pIniY, int pFinX, int pFinY){
            //Inicializa lo necesario
            Cell initialNode = new Cell(pIniX, pIniY);
            Cell objectiveNode = new Cell(pFinX, pFinY);
            setInitialCell(initialNode);
            setObjetiveCell(objectiveNode);
            matrix[initialCell.getX()][initialCell.getY()].setCostFromStart(0);
            setEstimated();
            
        }
        
	public ArrayList<Cell> calculatePath(int pIniX, int pIniY, int pFinX, int pFinY){

            iniComponents(pIniX, pIniY, pFinX, pFinY);
            
            //Makes the calculation
            visitedCells = new boolean [xLength][yLength];
            frontier = new LinkedList();

            frontier.add(initialCell);

            while(frontier.size() > 0){
                    Cell current = (Cell)frontier.remove();

                    //System.out.println("X: "+current.getX()+ " Y: " +current.getY());
                    visitedCells[current.getX()][current.getY()] = true;

                    if(current.getX() == objetiveCell.getX() && current.getY() == objetiveCell.getY()){
                            return getPath();
                    }

                    if(matrix[current.getX() - 1][current.getY()] != null){                      
                            evaluateCost(current, matrix[current.getX() - 1][current.getY()]);
                    }

                    if(matrix[current.getX()][current.getY() - 1] != null){
                            evaluateCost(current, matrix[current.getX()][current.getY() - 1]);
                    }

                    if( matrix[current.getX() + 1][current.getY()] != null){
                            evaluateCost(current, matrix[current.getX() + 1][current.getY()]);
                    }

                    if(matrix[current.getX()][current.getY() + 1] !=  null){
                            evaluateCost(current, matrix[current.getX()][current.getY() + 1]);
                    }

            }

            return null;
			
	}
	   
	private void printPath(){
	     Cell current = matrix[objetiveCell.getX()][objetiveCell.getY()];
	
	     String print = current.toString();
	     int cost=0;
	     while(current.getPathParent()!=null){
	    	 print=current.getPathParent().toString()+ print;
	         current = current.getPathParent();
	         cost++;
	     } 
	     System.out.println("Cost: " + cost);
	     System.out.println("Path: " + print);
	}
        
        private ArrayList<Cell> getPath(){
            ArrayList <Cell> path = new ArrayList<>();
             
	    Cell current = matrix[objetiveCell.getX()][objetiveCell.getY()];
            path.add(current);
	
	     String print = current.toString();
	     while(current.getPathParent() != null){
	    	 print=current.getPathParent().toString() + print;
	         current = current.getPathParent();
                 path.add(0, current);
	     } 
	     return path;
	}
        
       private void printPath(Cell pCurrent){
	     Cell current = pCurrent;
	
	     String print = current.toString();
	     int cost=0;
	     while(current.getPathParent()!=null){
	    	 print=current.getPathParent().toString()+print;
	         current = current.getPathParent();
	         cost++;
	     } 
	     System.out.println("Cost: " + cost);
	     System.out.println("Path: " + print);
	}
	
       @Override
	public String toString(){
            String str = "";
		for(int x = 0; x < xLength; x++ ){
			for(int y = 0; y < yLength; y++){
				if(matrix[x][y]==null)
					str += "%";
				else
					str += matrix[x][y].getEstimatedCostToGoal()+" ";
	
			}
			str += "\n";
		}
                return str;
	}
        
}

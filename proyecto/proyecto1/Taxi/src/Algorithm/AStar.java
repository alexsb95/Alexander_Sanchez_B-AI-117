/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithm;

/**
 *
 * @author Alex
 */

/*
    1. Crear la instancia con la matriz
    2. Establecer el nodo inicial y final
*/

import Matrix.Cell;
import Matrix.Block;
import java.util.LinkedList;
import utils.PriorityList;

public class AStar {
    	private Cell initialCell;
	private Cell objetiveCell;
	private int xLength;
        private int yLength;
	private Cell[][] matrix;
	
	private boolean[][] visitedCells;
	private PriorityList frontier;
        
        public AStar(Cell[][] pMatrix, int pXLen, int pYLen){
            setMatrix(pMatrix, pXLen, pYLen);
        }
	
	public Cell getInitialCell() {
		return initialCell;
	}
	public void setInitialCell(Cell initialCell) {
		this.initialCell = initialCell;
                if(matrix != null){
                    matrix[initialCell.getX()][initialCell.getY()].setCostFromStart(0);

                }
                   
	}
	public Cell getObjetiveCell() {
		return objetiveCell;
	}
	public void setObjetiveCell(Cell pDestination) {                
		this.objetiveCell = pDestination;
                setEstimated();
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
			int costFromStart = pCurrent.getCostFromStart() + 1;
			
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
        
	public void evaluateGrid(){
		visitedCells = new boolean [xLength][yLength];
		frontier = new PriorityList();

		frontier.add(initialCell);
		
		while(frontier.size() > 0){
			Cell current = (Cell)frontier.remove();
			
			//System.out.println("X: "+current.getX()+ " Y: " +current.getY());
			visitedCells[current.getX()][current.getY()] = true;
			
			if(current.getX() == objetiveCell.getX() && current.getY() == objetiveCell.getY()){
				printPath();
				return;
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
		
		
	}
	
	private void printPath(){
	     Cell current = matrix[objetiveCell.getX()][objetiveCell.getY()];
	
	     String print = current.toString();
	     int cost=0;
	     while(current.getPathParent()!=null){
	    	 print=current.getPathParent().toString()+print;
	         current = current.getPathParent();
	         cost++;
	     } 
	     System.out.println(cost);
	     System.out.println(print);
	}
	
	public void printMatrix(){
		for(int x = 0; x < xLength; x++ ){
			for(int y = 0; y < yLength; y++){
				if(matrix[x][y]==null)
					System.out.print("% ");
				else
					System.out.print(matrix[x][y].getEstimatedCostToGoal()+" ");
	
			}
			System.out.println();
		}
		
	}
}

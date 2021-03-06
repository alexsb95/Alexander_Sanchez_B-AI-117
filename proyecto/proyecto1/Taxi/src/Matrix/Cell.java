/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Matrix;

import java.util.ArrayList;

/**
 *
 * @author Alex
 */
public class Cell {
    	private int x;
	private int y;
	private Cell pathParent;
	private int costFromStart;
	private int estimatedCostToGoal;
        private ArrayList<Coord> priority;
        
        public Cell(int pX, int pY){
            x = pX;
            y = pY;
            priority = new ArrayList<>();
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

        public void addPriority(ArrayList<Coord> pList){
            priority = pList;
        }
        
        public ArrayList<Coord> getPriority(){
            return priority;
        }

	public int compareTo(Object other) {
		    int v = this.getCost() - ((Cell)other).getCost();
		    return (v>0)?1:(v<0)?-1:0; // sign function
	}
	
	  public int getCost() {
	    return costFromStart + estimatedCostToGoal;
	  }

	  
	  public int getEstimatedCost(Cell pObjetive){
		  return  Math.abs(x - pObjetive.getX()) + Math.abs( y - pObjetive.getY());
	  }
	  
	  @Override
	  public String toString(){
		  return x+" "+y+"\n";
	  }
}

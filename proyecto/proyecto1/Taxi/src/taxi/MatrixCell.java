/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taxi;

/**
 *
 * @author Alex
 */
public class MatrixCell {
    	private int x;
	private int y;
	private MatrixCell pathParent;
	private int costFromStart;
	private int estimatedCostToGoal;
        
        MatrixCell(int pX, int pY){
            x = pX;
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
	
	public MatrixCell getPathParent() {
		return pathParent;
	}

	public void setPathParent(MatrixCell pathParent) {
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
		    int v = this.getCost() - ((MatrixCell)other).getCost();
		    return (v>0)?1:(v<0)?-1:0; // sign function
	}
	
	  public int getCost() {
	    return costFromStart + estimatedCostToGoal;
	  }

	  
	  public int getEstimatedCost(MatrixCell pObjetive){
		  return  Math.abs(x - pObjetive.getX()) + Math.abs( y - pObjetive.getY());
	  }
	  
	  @Override
	  public String toString(){
		  return x+" "+y+"\n";
	  }
}

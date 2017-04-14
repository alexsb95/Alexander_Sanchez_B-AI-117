/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Matrix;

/**
 *
 * @author Alex
 */
public class Street {
    private int i;
    private int j;
    private boolean occupied;
    private char destination;
    
    Street (int pI, int pJ){
        i = pI;
        j = pJ;
    }

    public char getDestination() {
        return destination;
    }

    public int getX() {
        return i;
    }

    public void setX(int i) {
        this.i = i;
    }

    public int getY() {
        return j;
    }

    public void setY(int j) {
        this.j = j;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void take(char pDestination) {
        this.destination = pDestination;
        this.occupied = true;
    }
    
    public void leave (){
        this.occupied = false;
    }
    
}

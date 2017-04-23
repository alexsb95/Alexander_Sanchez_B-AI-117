/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Matrix;

import java.util.ArrayList;

/**
 *
 * @author Alei
 */
public class Block {
    private int i;
    private int j;
    private char symbol;
    public ArrayList<Street> blockStreets;

    Block (int pX, int pY, char pSymbol){
        i = pX;
        j = pY;
        symbol = pSymbol;
        iniStreets();
    }
    
    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
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
    
    public int getDestX(){
        return i-2;
    }
   
    public int getDestY(){
        return j;
    }
    
    private void iniStreets(){
        blockStreets = new ArrayList<>();
 
        blockStreets.add(new Street(i - 1, j - 1));
        blockStreets.add(new Street(i - 1, j));
        blockStreets.add(new Street(i - 1, j + 1));
        blockStreets.add(new Street(i + 1, j - 1));
        blockStreets.add(new Street(i + 1, j));
        blockStreets.add(new Street(i + 1, j + 1));     
    }
    
    public boolean waitTaxi(char pDestination){
        boolean spaceLeft = false;
        
        for (Street str : blockStreets){
            if(!str.isOccupied()){
               str.take(pDestination);
               spaceLeft = true; 
               break;
            }
        }
        
        return spaceLeft;
    }
    
    public void takeOff(int pI, int pJ){
        
        for (Street str : blockStreets){
            if(str.getX() == pI && str.getY() == pJ){
               str.leave();
               break;
            }
        }
    }
    
    public Character pickUpClient(){   
        Character destination = null;
        
        for (Street str : blockStreets){
            if(str.isOccupied()){
                destination = str.getDestination();
                str.leave();
               break;
            }
        }
        return destination;
    }
}

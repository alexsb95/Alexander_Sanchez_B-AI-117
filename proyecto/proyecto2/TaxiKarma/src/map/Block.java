/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import entities.Person;
import java.util.LinkedList;

/**
 *
 * @author Alei
 */
public class Block {
    private int i;
    private int j;
    private char symbol;
    public LinkedList<Person> peopleWaiting;

    public Block (int pI, int pJ, char pSymbol){
        i = pI;
        j = pJ;
        symbol = pSymbol;
        peopleWaiting = new LinkedList<>();
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
     
    public void newPerson(Person pPerson){
        peopleWaiting.add(pPerson);
    }
    
    //Puede retornar null
    public Character pickUpClient(){   
        Character destination = null;
        
        if(isClientWaiting()){
            Person client = peopleWaiting.pop();
            destination = client.getDestination();
        }
        
        return destination;
    }
    
    public boolean isClientWaiting(){
        return !peopleWaiting.isEmpty();
    }
    
    @Override
    public String toString(){
        return symbol + ": " + i + "-" + j + " list: " +peopleWaiting.toString();
    }
}

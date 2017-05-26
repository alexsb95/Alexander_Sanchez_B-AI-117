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
    private LinkedList<Person> peopleWaiting;
    private int[][] sidewalks;

    public Block (int pI, int pJ, char pSymbol){
        i = pI;
        j = pJ;
        symbol = pSymbol;
        peopleWaiting = new LinkedList<>();  
        sidewalks = new int [][]{ {i - 1, j - 1}, { i - 1, j}, {i - 1 , j + 1} };
    }
    
    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    
    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }
    
    public int getDestI(){
        return i-2;
    }
   
    public int getDestJ(){
        return j;
    }
     
    public void newPerson(Person pPerson){
        peopleWaiting.add(pPerson);
    }
    
    //Puede retornar null -- analizar maquinas de estado
    public Person pickUpClient(){   
      
        if(isClientWaiting()){
            Person client = peopleWaiting.pop();
            //destination = client.getDestination();
            return client;
        }
        
        return null;
    }
    //Puede retornar null -- analizar maquinas de estado
    public boolean isClientWaiting(){
        return !peopleWaiting.isEmpty();
    }
    
    public boolean isStreet(int pI, int pJ){
        for(int index  = 0; index < sidewalks.length; index++){
            if(sidewalks[index][0] == pI && sidewalks[index][1] == pJ ){
                return true;
            }
        } 
    
        return false;
    }

    @Override
    public String toString(){
        return symbol + ": " + i + "-" + j + " list: " +peopleWaiting.toString();
    }
}


package map;

import entities.Person;
import java.util.ArrayList;

/**
 *
 * @author Alei
 */
public class Block {
    private int i;
    private int j;
    private char symbol;
    private ArrayList<Person> people;
    private int[][] sidewalks;

    public Block (int pI, int pJ, char pSymbol){
        i = pI;
        j = pJ;
        symbol = pSymbol;
        people = new ArrayList<>();  
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
        people.add(pPerson);
    }
    
    //Puede retornar null -- analizar maquinas de estado
    public Person pickUpClient(){   
      
        if(!people.isEmpty()){
            for(int index = 0; index < people.size(); index++ ){
                if("Waiting".equals(people.get(index).getCurrentState())){
                    Person client = people.get(index);
                    people.remove(index);
                    return client;
                }
            }
        }
        
        return null;
    }
    
    public boolean areClientsWaiting(){
            if(!people.isEmpty()){
            for(int index = 0; index < people.size(); index++ ){
                if("Waiting".equals(people.get(index).getCurrentState())){
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public boolean isStreet(int pI, int pJ){
        for(int index  = 0; index < sidewalks.length; index++){
            if(sidewalks[index][0] == pI && sidewalks[index][1] == pJ ){
                return true;
            }
        } 
    
        return false;
    }
    
    public int cantPerson(){
        return people.size();
    }
    
    public ArrayList<Person> getPeople(){
        return this.people;
    }

    @Override
    public String toString(){
        return symbol + ": " + i + "-" + j + " list: " +people.toString();
    }
}

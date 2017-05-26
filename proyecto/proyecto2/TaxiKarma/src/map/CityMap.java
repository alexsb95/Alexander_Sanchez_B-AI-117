
package map;

import algorithm.AStar;
import algorithm.Cell;
import entities.Person;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import utils.InputReader;

/**
 *
 * @author Alex
 */
public class CityMap {
    
   private InputReader reader;
   public Cell[][] nodeMatrix;
   private char[][] charMatrix;
   private ArrayList<Person> clientList;
   private HashMap<Character, Block> streetBlocks;
    ArrayList<Character> posibleBlocks;

   public void iniComponents(String pFileName){
       reader = new InputReader();
       charMatrix = reader.readFile(pFileName);
       
       if(charMatrix != null ){
            transformMatrix(charMatrix, charMatrix.length, charMatrix[0].length);
       }else{
           System.out.println("Error abriendo archivo");
       }

    
   }
   
   private void transformMatrix(char[][] pMatrix, int pLengthI, int pLenghtJ){
   
       /*i => 0 
               1
               2
        */
        
        /*j => 0 1 2 */
        nodeMatrix = new Cell[pLengthI][pLenghtJ + 1];
    
        System.out.println("size "+pLengthI+" "+pLenghtJ);
        
        ArrayList<Person> clients = new ArrayList<>();
        posibleBlocks = new ArrayList<>();
        streetBlocks = new HashMap<>();
        
         /*      Reas the char matrix and defines the matrixCell, cliennts and blocks        */
        for(int i = 0; i < pLengthI; i++){
            for(int j = 0; j < pLenghtJ; j++){             
                 if(pMatrix[i][j] == '-'){
                    pMatrix[i][j] = ' ';
                    nodeMatrix[i][j] = new Cell(i,j);              
                }else if( Character.toUpperCase(pMatrix[i][j]) >= 'A' && Character.toUpperCase(charMatrix[i][j]) <= 'Z' ){
                    streetBlocks.put(Character.toUpperCase(pMatrix[i][j]), new Block(i,j,Character.toUpperCase(charMatrix[i][j])));      
                    posibleBlocks.add(Character.toUpperCase(pMatrix[i][j]));
                    
                    nodeMatrix[i][j] = null;
                }else if( pMatrix[i][j] == '#'){
                    Person client = new Person(i,j);
                    clients.add(client);
                    
                    nodeMatrix[i][j] = null;
                    pMatrix[i][j] = '%';
                }else if( pMatrix[i][j] == '@' ){
                    /*taxi = new TaxiCab();
                    taxi.setCurrentPosI(i);
                    taxi.setCurrentPosJ(j);
                    taxi.setStatus("WAITING");*/
                    
                    pMatrix[i][j] = ' ';
                    nodeMatrix[i][j] = new Cell(i,j);
                }else{
                    if(pMatrix[i][j] == '_'){
                        pMatrix[i][j] = ' ';  
                    }
                    nodeMatrix[i][j] = null; 
                }
                               
            }
            
        }
        
        /*  initialization of the clients   */
        clientList = new ArrayList<>();
        setNewClients(clients, posibleBlocks);


   }
   
   private void setNewClients(ArrayList<Person> pClients, ArrayList<Character> pStreetBlock){
        
        Random rnd = new Random();
        int home;
        int work;
       for(Person cli: pClients){
            home = rnd.nextInt(pStreetBlock.size() - 1);
            do{
                work = rnd.nextInt(pStreetBlock.size() - 1);
            }while(home == work);
            
            addNewClient(cli.getCurrentI(), cli.getCurrentJ(),pStreetBlock.get(home), pStreetBlock.get(work));
        }
               
   }
   
    public boolean addNewClient(int pI, int pJ, char pOrigin, char pDestiny){
        Person client = null;

        /*      Looks for the current block         */
        for (Map.Entry<Character, Block> entry: streetBlocks.entrySet()) {
            if(entry.getValue().isStreet(pI, pJ)){
                client = new Person(pI, pJ, pOrigin, pDestiny, entry.getKey());
                //Adds it as wating client on th block
                entry.getValue().newPerson(client);
                // Adds it to the clientList 
                clientList.add(client);
                return true;
            }
        }

        return false;
    }
    
    public void addSeveralClients(int pAmount){
        int home, work;
        for(int cant=0; cant < pAmount; cant++){
            Random rnd = new Random();
            work = rnd.nextInt(posibleBlocks.size() - 1);      
             do {  
                home = rnd.nextInt(posibleBlocks.size() - 1);
            } while ( home == work && !addNewClient(streetBlocks.get(home).getI(), 
                                        streetBlocks.get(home).getJ(),
                                        streetBlocks.get(home).getSymbol(), 
                                        streetBlocks.get(work).getSymbol()) );
             
        }
        
    }
    
        
    private boolean validBlock(Character pBlock){
        return streetBlocks.containsKey(pBlock);
    }
    
    
    @Override
    public String toString(){
        return clientList.toString();
    }
}

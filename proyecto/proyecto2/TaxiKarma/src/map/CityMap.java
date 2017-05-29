
package map;

import algorithm.AStar;
import algorithm.Cell;
import entities.Person;
import entities.TaxiCab;
import fsm.EventEmiter;
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
   private ArrayList<Person> nonWaiting;
   private HashMap<Character, Block> streetBlocks;
   private ArrayList<Character> posibleBlocks;
   private EventEmiter overlord;
   private int defaultTimer;
   private ArrayList<TaxiCab> inicialTaxi;

   public CityMap(EventEmiter pEmiter){
       overlord = pEmiter;
       defaultTimer = 5;
   }

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
        inicialTaxi = new ArrayList<>();
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
                    TaxiCab taxi = new TaxiCab(overlord);
                    taxi.setCurrentI(i);
                    taxi.setCurrentJ(j);
                    inicialTaxi.add(taxi);
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
        nonWaiting = new ArrayList<>();
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
                client = new Person(pI, pJ, pOrigin, pDestiny, entry.getKey(), overlord);
                client.setTimer(defaultTimer);
                // Adds it to the corresponding block 
                entry.getValue().newPerson(client);
                System.out.println(client.toString());
                return true;
            }
        }

        return false;
    }
    
    public void addSeveralClients(int pAmount){
        int home, work;
        boolean wasAdded;
        for(int cant=0; cant < pAmount; cant++){
            Random rnd = new Random();
            work = rnd.nextInt(posibleBlocks.size() - 1);
             do {  
                home = rnd.nextInt(posibleBlocks.size() - 1);
                wasAdded = addNewClient(streetBlocks.get(posibleBlocks.get(home)).getI() - 1, streetBlocks.get(posibleBlocks.get(home)).getJ(), 
                                        posibleBlocks.get(home),  posibleBlocks.get(work));
            } while ( home == work && !wasAdded );      
             
        }
        
    }
    
    
        
    private boolean validBlock(Character pBlock){
        return streetBlocks.containsKey(pBlock);
    }
    
    public int getDefaultTimer() {
        return defaultTimer;
    }

    public void setDefaultTimer(int defaultTimer) {
        this.defaultTimer = defaultTimer;
    }
   
    public Cell[][] getNodeMatrix() {
        return nodeMatrix;
    }
    
    public ArrayList<TaxiCab> getIncialTaxis(){
        return inicialTaxi;
    }
    
    public ArrayList<Character> getPosibleBlocks() {
        return posibleBlocks;
    }
    
    public HashMap<Character, Block> getHSBlocks(){
        return streetBlocks;
    }
    @Override
    public String toString(){
        String strMap = "";
        for(int i = 0; i < charMatrix.length; i++){
            for(int j = 0; j < charMatrix[0].length; j++){ 
                strMap += charMatrix[i][j];
            }
             strMap += '\n';
        }
                 
        return strMap;
    }
}

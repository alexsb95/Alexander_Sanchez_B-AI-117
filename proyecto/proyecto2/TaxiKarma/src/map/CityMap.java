
package map;

import algorithm.Cell;
import entities.Person;
import entities.TaxiCab;
import fsm.EventEmiter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
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
   private HashMap<Character, Block> streetBlocks;
   private ArrayList<Character> residentBlocks;
   private ArrayList<Character> posibleBlocks;
   private ArrayList<Character> workBlocks;
   private final EventEmiter overlord;
   
   private ArrayList<TaxiCab> inicialTaxi;
   private final DayCycle dayCycle;

   public CityMap(EventEmiter pEmiter, int pDayTime, int pPercentageWork ){
       overlord = pEmiter;
       dayCycle = new DayCycle(pDayTime, pPercentageWork, overlord);
   }

   public void iniComponents(String pMatrixFile, String pBuildingsFile){
       reader = new InputReader();
       charMatrix = reader.readFileMatrix(pMatrixFile);
       ArrayList<String> stringList = reader.readFileSrings(pBuildingsFile);
       if(charMatrix != null && !stringList.isEmpty() ){
            setBuildings(stringList);
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
        streetBlocks = new HashMap<>();
        posibleBlocks = new ArrayList<>();
        
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
        setNewClients(clients);
   }
   
   private void setNewClients(ArrayList<Person> pClients){   
        Random rnd = new Random();
        int home;
        int work;
        for(Person cli: pClients){
            home = rnd.nextInt(residentBlocks.size() - 1);
            do{
                work = rnd.nextInt(workBlocks.size() - 1);
            }while(home == work);
            
            addNewClient(cli.getCurrentI(), cli.getCurrentJ(),residentBlocks.get(home), workBlocks.get(work));
        }
               
   }
   
   private void setBuildings(ArrayList<String> pList){
       residentBlocks = new ArrayList<>();
       workBlocks = new ArrayList<>();
       
       for(String line : pList){
           String[] words = line.split("/");
           if("Residents".equals(words[0])){
                for(int index = 1 ; index < words.length; index++){
                    residentBlocks.add(Character.toUpperCase(words[index].charAt(0)));
                }
           }else if("Workplaces".equals(words[0])){
                for(int index = 1 ; index < words.length; index++){
                    workBlocks.add(Character.toUpperCase(words[index].charAt(0)));
                }        
           }

       }
   
   }
   
      
    public boolean addNewClient(char pCurrentBlock, char pHome, char pWork){

        if(streetBlocks.containsKey(pCurrentBlock)){
            Block block = streetBlocks.get(pCurrentBlock);
            Person client = new Person(block.getI()-1, block.getJ(), pWork, pHome, pCurrentBlock, overlord, dayCycle);
            // Adds it to the corresponding block 
            block.newPerson(client);
            System.out.println("Added cli: " + client.toString());
            return true;
        }

        return false;
    }
    
   
    public boolean addNewClient(int pI, int pJ, char pHome, char pWork){
        Person client = null;
        /*      Looks for the current block         */
        for (Map.Entry<Character, Block> entry: streetBlocks.entrySet()) {
            if(entry.getValue().isStreet(pI, pJ)){
                client = new Person(pI, pJ, pWork, pHome, entry.getKey(), overlord, dayCycle);
                // Adds it to the corresponding block 
                entry.getValue().newPerson(client);
                System.out.println("Added cli: " + client.toString());
                return true;
            }
        }

        return false;
    }
    
    public boolean addNewClient(Person pPerson){
        System.out.println("Added cli: " + pPerson.toString());
        streetBlocks.get( Character.toUpperCase(pPerson.getCurrentBlock()) ).newPerson(pPerson);
        //clientList.add(pPerson);
        return true;
    }
    
    public void addSeveralClients(int pAmount){
        int home, work;
        boolean wasAdded;
        for(int cant=0; cant < pAmount; cant++){
            Random rnd = new Random();
            work = rnd.nextInt(workBlocks.size() - 1);
             do {  
                home = rnd.nextInt(residentBlocks.size() - 1);
                wasAdded = addNewClient(streetBlocks.get(residentBlocks.get(home)).getI() - 1, streetBlocks.get(residentBlocks.get(home)).getJ(), 
                                        residentBlocks.get(home),  workBlocks.get(work));
            } while ( home == work && !wasAdded );      
             
        }
        
    }
    
    public Person tryToPickUp(int pPosI, int pPosJ){
        for (Entry<Character, Block> entry: streetBlocks.entrySet()) {
            if( pPosI == entry.getValue().getDestI()&&  pPosJ == entry.getValue().getDestJ()){
                if(entry.getValue().areClientsWaiting()){
                    return entry.getValue().pickUpClient();
                }
            }
        }
        
        return null;
    }
    
    
    public ArrayList<Character> getClosetBlocks(int pPosI, int pPosJ){
        ArrayList<Character> visitedBlocks = new ArrayList<>();
        
        Character close = closetBlock(visitedBlocks, pPosI, pPosJ);
        while(close != null){
            visitedBlocks.add(close);
            close = closetBlock(visitedBlocks, streetBlocks.get(close).getDestI(), streetBlocks.get(close).getDestJ() );      
        }
        
        return visitedBlocks;
    }
    
    private Character closetBlock(ArrayList<Character> pVisited, int posI, int posJ){
        int minDistance = Integer.MAX_VALUE;
        Character block = null;

        for (Entry<Character, Block> entry: streetBlocks.entrySet()) {
            int blockDistance = Math.abs(posI - entry.getValue().getDestI()) + Math.abs(posJ - entry.getValue().getDestJ());
            if((minDistance > blockDistance && !pVisited.contains(entry.getKey())) && blockDistance != 0 ){
                minDistance = blockDistance;
                block = entry.getKey();
            }
        }

        return block;
        
    }
        
    public boolean validBlock(Character pBlock){
        return streetBlocks.containsKey(pBlock);
    }
    
    public void createTrafficJam(){
    //Un radio de 7 blocks de cercania i o j se considera denttro de un mismo sector
    
    }
    
    public void setDayTime(int pAmount) {
        dayCycle.setDayUnit(pAmount);
    }

    public void setPercentage(int pPercentage) {
        dayCycle.setPercentageWork(pPercentage);
    }
   
    public Cell[][] getNodeMatrix() {
        return nodeMatrix;
    }
    
    public ArrayList<TaxiCab> getIncialTaxis(){
        return inicialTaxi;
    }
    
    public ArrayList<Character> getWorkBlocks() {
        return workBlocks;
    }
    
    public ArrayList<Character> getResidentBlocks() {
        return residentBlocks;
    }
    
    public ArrayList<Character> getPosibleBlocks() {
        return posibleBlocks;
    }
    
    public HashMap<Character, Block> getHSBlocks(){
        return streetBlocks;
    }
    
    public char[][] getCharMatrix() {
        return charMatrix;
    }
    
    public int getTime(){
        return this.dayCycle.getClock();
    }
    
    public ArrayList<Person> getClientList(){
        ArrayList<Person> peopleList = new ArrayList<>();
        
        for (Map.Entry<Character, Block> entry: streetBlocks.entrySet()) {
            if(entry.getValue().cantPerson() > 0){
                
                ArrayList<Person> clients = entry.getValue().getPeople();
                clients.forEach((client) -> {
                    peopleList.add(client);
                }); 
            }
        }
        return peopleList;
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

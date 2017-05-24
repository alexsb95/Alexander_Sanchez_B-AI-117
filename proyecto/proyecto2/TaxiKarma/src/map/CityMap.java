
package map;

import algorithm.AStar;
import algorithm.Cell;
import entities.Person;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import utils.InputReader;

/**
 *
 * @author Alex
 */
public class CityMap {
    
   private InputReader reader;
   private Cell[][] nodeMatrix;
   private char[][] charMatrix;

   private HashMap<Character, Block> streetBlocks;

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
        ArrayList<Character> posibleBlocks = new ArrayList<>();
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
        clients = setNewClients(clients, posibleBlocks);


   }
   
   private ArrayList<Person> setNewClients(ArrayList<Person> pClients, ArrayList<Character> pStreetBlock){
        for(Person cli: pClients){
                    
            Random rnd = new Random();
            int home = rnd.nextInt(pStreetBlock.size() - 1);
            int work = rnd.nextInt(pStreetBlock.size() - 1);
            while(home == work)
                work = rnd.nextInt(pStreetBlock.size() - 1);
            
            cli.setHome(pStreetBlock.get(home));
            cli.setWorkplace(pStreetBlock.get(work));
            //Analizar el going home
            
        }
               
       return pClients;
   }
   
}

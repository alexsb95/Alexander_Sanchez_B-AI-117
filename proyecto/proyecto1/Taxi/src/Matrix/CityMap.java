/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Matrix;

import Algorithm.AStar;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;


/**
 *
 * @author Alex
 */
import taxi.TaxiCab;

public class CityMap {
    
    private Cell[][] nodeMatrix;
    private AStar aStarAlg;
    private TaxiCab taxi;
    public HashMap<Character, Block> streetBlocks;
    
    public ArrayList<Cell> geRoute(Character pKBLock){
        
        Block block = streetBlocks.get(pKBLock);
        Cell ini = new Cell(taxi.getCurrentPosI(), taxi.getCurrentPosJ());
        Cell obj = new Cell(block.getDestX(), block.getDestY());
        aStarAlg.setObjetiveCell(obj);
        aStarAlg.setInitialCell(ini);
        //aStarAlg.printMatrix();
        //System.out.println("incial: " + aStarAlg.getInitialCell().getX() + " " + aStarAlg.getInitialCell().getY());
        //System.out.println("final: "  + aStarAlg.getObjetiveCell().getX() + " " + aStarAlg.getObjetiveCell().getY());
        
        return aStarAlg.evaluateGrid();
    }
    
        public ArrayList<Cell> moveTaxi(int pI, int pJ){        
        //Move the taxi to the destination
        taxi.setCurrentPosI(pI);
        taxi.setCurrentPosJ(pJ);
        
        return aStarAlg.evaluateGrid();
    }
    
    public void parade(){
        Cell ini = new Cell(taxi.getCurrentPosI(), taxi.getCurrentPosJ());
        aStarAlg.setInitialCell(ini);
        aStarAlg.moveAround();
        
        /*
            En construccion
        */
    }
    
    public ArrayList<Character> searchClients(){
        ArrayList<Character> visitedBlocks = new ArrayList<>();
        Character close = closetBlock(visitedBlocks, taxi.getCurrentPosI(), taxi.getCurrentPosJ());
        
        while(close != '*'){
            visitedBlocks.add(close);
            close = closetBlock(visitedBlocks, streetBlocks.get(close).getDestX(), streetBlocks.get(close).getDestY() );      
        }

        return visitedBlocks;
    }
    
    
    private Character closetBlock(ArrayList<Character> pVisited, int posI, int posJ){
        int minDistance = Integer.MAX_VALUE;
        char block = '*';

        for (Entry<Character, Block> entry: streetBlocks.entrySet()) {
            int blockDistance = Math.abs(posI - entry.getValue().getDestX()) + Math.abs(posJ - entry.getValue().getDestY());
            if(minDistance > blockDistance && !pVisited.contains(entry.getKey())){
                minDistance = blockDistance;
                block = entry.getKey();
            }
        }

        return block;
        
    }
    
    public Character pickUpClient(){
        for (Entry<Character, Block> entry: streetBlocks.entrySet()) {
            if( taxi.getCurrentPosI() == entry.getValue().getDestX() &&  taxi.getCurrentPosJ() == entry.getValue().getDestY()){
                return entry.getValue().pickUpClient();
            }
        }
        return null;
    }
    
    public void setClient(int pAmount){
        ArrayList<Character> blocks = searchClients();
        Random rnd = new Random();
        int randOringin = rnd.nextInt(blocks.size());
        int randDestiny = rnd.nextInt(blocks.size());
        while(randOringin == randDestiny){
            randDestiny = rnd.nextInt(blocks.size());
        }
            
        streetBlocks.get( blocks.get(randOringin)).waitTaxi(blocks.get(randDestiny));
        /*System.out.println("To: "+blocks.get(randOringin)+" From: "+blocks.get(randDestiny));
        System.out.println(streetBlocks.get(blocks.get(randOringin)).blockStreets.get(0).getDestination() );*/
    }
    
    public void setClient(char pOrigin, char pDestiny){
        streetBlocks.get(pOrigin).waitTaxi(pDestiny);
    }
    
    public boolean validBlock(Character pBlock){
        boolean valid = false;
        for (Entry<Character, Block> entry: streetBlocks.entrySet()) {
            if(entry.getKey() == pBlock){
                valid = true;
                break;
            }
        }
        
        return valid;
    }
    
     public void readMatrix(){
        int iLen = charMatrix.length;
        int jLen = charMatrix[0].length;

        /*i => 0 
               1
               2
        */
        
        /*j => 0 1 2 */
        nodeMatrix = new Cell[iLen][jLen];
    
        System.out.println("size "+iLen+" "+jLen);
        
        ArrayList<Street> clients = new ArrayList<>();
        streetBlocks = new HashMap<>();
        
        /*      Reas the char matrix and defines the matrixCell, cliennts and blocks        */
        for(int i = 0; i < iLen; i++){
            for(int j = 0; j < jLen; j++){             
                 if(charMatrix[i][j] == '-'){
                    nodeMatrix[i][j] = new Cell(i,j);              
                }else if( Character.toUpperCase(charMatrix[i][j]) >= 'A' && Character.toUpperCase(charMatrix[i][j]) <= 'Z' ){
                    streetBlocks.put(Character.toUpperCase(charMatrix[i][j]), new Block(i,j,Character.toUpperCase(charMatrix[i][j])));      
                    nodeMatrix[i][j] = null;
                }else if( charMatrix[i][j] == '#'){
                    Street client = new Street(i,j);
                    Random rnd = new Random();
                    int destin = rnd.nextInt(24) + 65;
                    client.take((char)destin);
                    
                    clients.add(client);
                    nodeMatrix[i][j] = null;
                }else if( charMatrix[i][j] == '@' ){
                    taxi = new TaxiCab();
                    taxi.setCurrentPosI(i);
                    taxi.setCurrentPosJ(j);
                    taxi.setStatus("SEARCHING");
                    
                    charMatrix[i][j] = '-';
                    nodeMatrix[i][j] = new Cell(i,j);
                }else
                    nodeMatrix[i][j] = null;            
            }
            
        }
         
        /*      Set the client on according the block they are waiting for       */
        for(Street cli: clients){
            streetBlocks.forEach((k,v) -> {
                if(v.getX() - 1 == cli.getX() || v.getX() + 1 == cli.getX() ){
   
                    if(v.getY() - 1 == cli.getY() || v.getY() == cli.getY() || v.getY() + 1 == cli.getY()){
                        v.waitTaxi(cli.getDestination());
                    }
                }
            });            
        }
        
        /*     Adds an default taxi, if none is found     */
        if(taxi == null){
            taxi = new TaxiCab();
            taxi.setCurrentPosI(1);
            taxi.setCurrentPosJ(1);
            taxi.setStatus("SEARCHING");
        }
        
        aStarAlg = new AStar(nodeMatrix, iLen, jLen);
            
    }
     
    public char[][] getMap(){
        return charMatrix;
    }
    
    public TaxiCab getTaxi(){
        return taxi;
    }
    
    private char[][] charMatrix =         
            {{'%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
           {'%','@','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','%','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
           {'%','-','%','%','%','-','%','%','%','-','%','%','%','-','%','%','%','-','%','#','%','-','%','%','%','-','%','%','%','-','%','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
           {'%','-','%','A','%','-','%','*','%','-','%','B','%','-','%','*','%','-','%','C','%','-','%','*','%','-','%','D','%','-','%','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
           {'%','-','%','%','%','-','%','%','%','-','%','%','%','-','%','%','%','-','%','%','%','-','%','%','%','-','%','%','%','-','%','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
           {'%','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','%','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
           {'%','-','%','%','%','-','%','%','%','-','%','%','%','-','%','%','%','-','%','%','%','-','%','%','%','-','%','%','%','-','%','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
           {'%','-','%','*','%','-','%','G','%','-','%','*','%','-','%','F','%','-','%','*','%','-','%','E','%','-','%','*','%','-','%','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
           {'%','-','%','%','%','-','%','%','%','-','%','%','%','-','%','%','%','-','%','%','%','-','%','%','%','-','%','%','%','-','%','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
           {'%','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','%','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
           {'%','-','%','%','%','-','%','%','%','-','%','%','%','-','%','%','%','-','%','%','%','-','%','%','%','-','%','%','%','-','%','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
           {'%','-','%','H','%','-','%','*','%','-','%','I','%','-','%','*','%','-','%','J','%','-','%','*','%','-','%','K','%','-','%','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
           {'%','-','%','%','%','-','%','%','%','-','%','%','%','-','%','%','%','-','%','%','%','-','%','%','%','-','%','%','%','-','%','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
           {'%','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','%','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
           {'%','-','*','%','%','-','%','%','%','-','%','%','%','-','%','%','%','-','%','%','%','-','%','%','%','-','%','%','%','-','%','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
           {'%','-','%','*','%','-','%','O','%','-','%','*','%','-','%','L','%','-','%','*','%','-','%','L','%','-','%','*','%','-','%','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
           {'%','-','%','%','%','-','%','%','%','-','%','%','%','-','%','%','%','-','%','%','%','-','%','%','%','-','%','%','%','-','%','_','_','_','_','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%'},
           {'%','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','%','_','_','_','_','%','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','%'},
           {'%','-','%','%','%','-','%','%','%','-','%','%','%','-','%','%','%','-','%','%','%','-','%','%','%','-','%','%','%','-','%','%','%','%','%','%','-','%','%','%','-','%','%','%','-','%','%','%','-','%','%','%','-','%'},
           {'%','-','%','P','%','-','%','*','%','-','%','Q','%','-','%','*','%','-','%','R','%','-','%','*','%','-','%','S','%','-','-','-','-','-','-','-','-','%','T','%','-','%','U','%','-','%','V','%','-','%','W','%','-','%'},
           {'%','-','%','%','%','-','%','%','%','-','%','%','%','-','%','%','%','-','%','%','%','-','%','%','%','-','%','%','%','-','%','%','%','%','%','%','-','%','%','%','-','%','%','%','-','%','%','%','-','%','%','%','-','%'},
           {'%','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','%','_','_','_','_','%','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','%'},
           {'%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','_','_','_','_','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%'}};

}

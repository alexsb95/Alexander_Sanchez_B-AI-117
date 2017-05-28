/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taxikarma;

import algorithm.Cell;
import map.Block;
import utils.InputReader;
import entities.Person;
import fsm.EventEmiter;
import map.CityMap;

/**
 *
 * @author Alex
 */
public class TaxiKarma {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EventEmiter overLord = new EventEmiter();
        
        Person ppl = new Person(1,1,'A','B','A',overLord);
        //System.out.println(ppl.getDestination());
        System.out.println(ppl.toString());
        
        Block block = new Block(2,2,'A');
        System.out.println(block.areClientsWaiting());
        block.newPerson(ppl);
        System.out.println(block.areClientsWaiting());
        System.out.println(block.pickUpClient());
        
        InputReader inre = new InputReader();
        char[][] map = inre.readFile("Map.txt");
        
         for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){ 
                System.out.print(map[i][j]);
            }
            System.out.println();
         }
        
        CityMap cm = new CityMap(overLord);
        cm.iniComponents("Map.txt");
        Cell[][] var = cm.nodeMatrix;
        System.out.println(cm.toString());
        
    }
    
}

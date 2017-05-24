/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taxikarma;

import map.Block;
import utils.InputReader;
import entities.Person;
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
        Person ppl = new Person(1,1,'A','B');
        System.out.println(ppl.getDestination());
        System.out.println(ppl.toString());
        
        Block block = new Block(2,2,'A');
        System.out.println(block.isClientWaiting());
        block.newPerson(ppl);
        System.out.println(block.isClientWaiting());
        System.out.println(block.pickUpClient());
        
        InputReader inre = new InputReader();
        char[][] map = inre.readFile("Map.txt");
        
         for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){ 
                System.out.print(map[i][j]);
            }
            System.out.println();
         }
        
        CityMap cm = new CityMap();
        cm.iniComponents("Map.txt");
    }
    
}

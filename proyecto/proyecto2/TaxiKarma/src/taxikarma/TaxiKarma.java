/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taxikarma;

import map.Block;
import map.Person;

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
    }
    
}

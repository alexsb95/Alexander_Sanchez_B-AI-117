/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taxi;

/**
 *
 * @author Alex
 */

import Matrix.Cell;
import Matrix.CityMap;
import java.util.ArrayList;

public class Taxi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //21,29
        CityMap matrix = new CityMap();
        matrix.readMatrix();
        matrix.setClient('A','S');
        System.out.println(matrix.streetBlocks.get('A').blockStreets.get(1).getDestination());
        matrix.setClient(2);
        /*
        ArrayList<Cell> alCell = matrix.sendTaxi( matrix.streetBlocks.get('S'));
        ArrayList<Character> al = matrix.searchClients();
        
        for(Cell c : alCell ){
            System.out.println(c.toString());
        }
        
        for(Character ch : al ){
            System.out.println(ch);
        }
        */
        // TODO code application logic here
    }
    
   
    
}

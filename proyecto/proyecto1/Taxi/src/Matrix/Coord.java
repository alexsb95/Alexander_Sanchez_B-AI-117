/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Matrix;

import java.util.ArrayList;
import utils.PriorityList;

/**
 *
 * @author Alex
 */
public class Coord{
    public int i;
    public int j;
    public ArrayList<Coord> prior;
    
    public Coord(int pI, int pJ){
        i = pI;
        j = pJ;
    }
    
    @Override
    public String toString(){
        return i + "-" + j;
    }
}
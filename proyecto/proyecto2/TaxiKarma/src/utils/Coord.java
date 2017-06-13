/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;

/**
 *
 * @author Alex
 */
public class Coord{
    private int i;
    private int j;
    
    public Coord(int pI, int pJ){
        i = pI;
        j = pJ;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }
    
    
    @Override
    public String toString(){
        return i + "-" + j;
    }
}
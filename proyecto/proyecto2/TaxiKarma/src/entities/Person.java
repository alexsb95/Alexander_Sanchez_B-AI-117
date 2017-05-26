/*
 * To change this license header, choose License Headers in ProcurrentJect Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Alex
 */
public class Person implements Entities {
    private int currentI;
    private int currentJ;
    private char workplace;    
    private char home;
    private char currentBlock;
    
    public Person (int pI, int pJ, char pWorkplace, char pHome, char pCurrentBlock){
        currentI = pI;
        currentJ = pJ;
        workplace = pWorkplace;
        home = pHome;
        currentBlock = pCurrentBlock;
    }
    
    public Person (int pI, int pJ){
        currentI = pI;
        currentJ = pJ;
    }

    
    public char getCurrentBlock() {
        return currentBlock;
    }

    public void setCurrentBlock(char currentBlock) {
        this.currentBlock = currentBlock;
    }

    public int getCurrentI() {
        return currentI;
    }

    public void setCurrentI(int currentI) {
        this.currentI = currentI;
    }

    public int getCurrentJ() {
        return currentJ;
    }

    public void setCurrentJ(int currentJ) {
        this.currentJ = currentJ;
    }

    public void setWorkplace(char workplace) {
        this.workplace = workplace;
    }

    public void setHome(char home) {
        this.home = home;
    }
    
    @Override
    public String toString(){
        return currentI + "-" + currentJ+ " crntblock: " + currentBlock + " wrk: " + workplace + " home: " + home;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Alex
 */
public class Person {
    private int i;
    private int j;
    private char workplace;    
    private char home;
    private boolean goingHome;

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

    public void setWorkplace(char workplace) {
        this.workplace = workplace;
    }

    public void setHome(char home) {
        this.home = home;
    }

    
    public Person (int pI, int pJ, char pWorkplace, char pHome){
        i = pI;
        j = pJ;
        workplace = pWorkplace;
        home = pHome;
    }
    
    public Person (int pI, int pJ){
        i = pI;
        j = pJ;
    }

    public char getDestination() {
        if (goingHome)
            return home;
        else
            return workplace;
    }
    
    @Override
    public String toString(){
        return i + "-" + j + " wrk: " + workplace + " home: " + home;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

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
    
    public Person (int pI, int pJ, char pWorkplace, char pHome){
        i = pI;
        j = pJ;
        workplace = pWorkplace;
        home = pHome;
    }

    public char getDestination() {
        if (goingHome)
            return home;
        else
            return workplace;
    }

    public int getX() {
        return i;
    }

    public void setX(int i) {
        this.i = i;
    }

    public int getY() {
        return j;
    }

    public void setY(int j) {
        this.j = j;
    }
    
    @Override
    public String toString(){
        return i + "-" + j + " wrk: " + workplace + " home: " + home;
    }
    
}

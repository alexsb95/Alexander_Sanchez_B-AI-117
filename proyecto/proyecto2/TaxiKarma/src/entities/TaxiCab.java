/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import utils.Coord;
import java.util.HashMap;
import java.util.LinkedList;
import utils.Coord;

/**
 *
 * @author Alex
 */
public class TaxiCab {
    private int currentI;
    private int currentJ;
    private boolean isOccupied;
    private String status;
    private int desI;
    private int desJ;
    
    
    private  LinkedList<Coord> actualRoute;
    private static LinkedList<LinkedList<Coord>> pending;

    private  HashMap<String, Coord> actualRouteHS;
    private  LinkedList<HashMap<String, Coord>> pendingHS;
    private  HashMap<String, Coord> pathHS;

    
    public TaxiCab(){
        pending = new LinkedList<LinkedList<Coord>>();
        actualRoute = new LinkedList<Coord>();
        actualRouteHS = new HashMap<String, Coord>();
        pendingHS = new LinkedList<HashMap<String, Coord>>();
        pathHS = new HashMap<String, Coord> ();
    }
    public static LinkedList<LinkedList<Coord>> getPending() {
        return pending;
    }

    public static void setPending(LinkedList<LinkedList<Coord>> pending) {
        TaxiCab.pending = pending;
    }

    public HashMap<String, Coord> getActualRouteHS() {
        return actualRouteHS;
    }

    public void setActualRouteHS(HashMap<String, Coord> actualRouteHS) {
        this.actualRouteHS = actualRouteHS;
    }

    public LinkedList<HashMap<String, Coord>> getPendingHS() {
        return pendingHS;
    }

    public void setPendingHS(LinkedList<HashMap<String, Coord>> pendingHS) {
        this.pendingHS = pendingHS;
    }

    public HashMap<String, Coord> getPathHS() {
        return pathHS;
    }

    public void setPathHS(HashMap<String, Coord> pathHS) {
        this.pathHS = pathHS;
    }
    
    
    public LinkedList<Coord> getActualRoute() {
        return actualRoute;
    }

    public void setActualRoute(LinkedList<Coord> actualRoute) {
        this.actualRoute = actualRoute;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getDesI() {
        return desI;
    }

    public void setDesI(int desI) {
        this.desI = desI;
    }

    public int getDesJ() {
        return desJ;
    }

    public void setDesJ(int desJ) {
        this.desJ = desJ;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import map.Coord;
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author Alex
 */
public class TaxiCab {
    private int currentPosI;
    private int currentPosJ;
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

    public int getCurrentPosI() {
        return currentPosI;
    }

    public void setCurrentPosI(int currentPosI) {
        this.currentPosI = currentPosI;
    }

    public int getCurrentPosJ() {
        return currentPosJ;
    }

    public void setCurrentPosJ(int currentPosJ) {
        this.currentPosJ = currentPosJ;
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

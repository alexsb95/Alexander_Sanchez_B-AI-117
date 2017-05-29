/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import algorithm.AStar;
import algorithm.Cell;
import fsm.EventEmiter;
import fsm.FSM;
import fsm.OnRoute;
import fsm.Parading;
import fsm.Parked;
import fsm.Searching;
import fsm.State;
import java.util.ArrayList;
import utils.Coord;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.UUID;
import map.Block;
import map.CityMap;
import utils.Coord;

/**
 *
 * @author Alex
 */
public class TaxiCab {
    private int currentI;
    private int currentJ;
    private int desI;
    private int desJ;
    private FSM motor;
    private EventEmiter overlord;
    private Person client;
    private String id;
    
    private CityMap map;
    private AStar algorithm;
    private  LinkedList<Coord> actualRoute;
    private  HashMap<String, Coord> actualRouteHS;
    private static LinkedList<LinkedList<Coord>> pending;
    private  LinkedList<HashMap<String, Coord>> pendingHS;
    private  HashMap<String, Coord> pathHS;

    
    public TaxiCab(EventEmiter pEmiter){
        pending = new LinkedList<LinkedList<Coord>>();
        pendingHS = new LinkedList<HashMap<String, Coord>>();
        actualRoute = new LinkedList<Coord>();
        actualRouteHS = new HashMap<String, Coord>();
        pathHS = new HashMap<String, Coord> ();

        id = UUID.randomUUID().toString();
        
        setUpStates(pEmiter);
    }


    
    private void setUpStates(EventEmiter pEmiter){
        overlord = pEmiter;
        
        ArrayList<State> states = new ArrayList<>();
        states.add(new OnRoute());
        states.add(new Parading());
        states.add(new Searching());
        states.add(new Parked());
        
        State currentState = new Searching();
        String uniqueID = UUID.randomUUID().toString();
        motor = new FSM(this, states, currentState, uniqueID, pEmiter);
    }
    
    private void moveTaxi(int pI, int pJ){        
        //Move the taxi to the destination
        setCurrentI(pI);
        setCurrentJ(pJ);
    }
    
    public void followRoute(){
        if(!actualRoute.isEmpty()){
            Coord newPos = actualRoute.pop();
            Coord taxiPos = getPosition();
            pathHS.put(taxiPos.toString(), taxiPos);
            moveTaxi(newPos.getI(), newPos.getJ());
        }else
            System.out.println("Error: Empty route for taxi " + id);
    }
    
    public void pickUpClient(Person pClient, Block pBlock){
        if(pClient != null){
            client = pClient;
            desI = pBlock.getDestI();
            desJ = pBlock.getDestJ();
        }
    }
    
    public Person dropClient(){
        Person temp = client;
        client = null;
        return temp;
    }
    
    public void search(){
        ArrayList <Character> blockList = map.getPosibleBlocks();
        HashMap <Character, Block> blockHM = map.getHSBlocks();
     
        LinkedList<LinkedList<Coord>> pending = new LinkedList<LinkedList<Coord>>();
        LinkedList<HashMap<String, Coord>> pendingHM = new LinkedList<HashMap<String, Coord>>();
        
        Coord originPoint = getPosition();
        
        for(Character block : blockList){
            Coord objetivePoint = new Coord(blockHM.get(block).getDestI(), blockHM.get(block).getDestJ());
            
            LinkedList<Coord> pendQueue = getRoute(originPoint, objetivePoint);
            HashMap<String, Coord> pendHM = QueueToHM(pendQueue);
            pending.add(getRoute(originPoint, objetivePoint));
            pendingHM.add(pendHM);
            
            originPoint = objetivePoint;
        }
        
        setPendingHS(pendingHM);
        setPending(pending);
        
        loadNewRoute();
        System.out.println(pending.toString());
    }
    
    private LinkedList<Coord> getRoute(Coord pOrigin, Coord pObjetive){
        LinkedList<Coord> route = new LinkedList<Coord>();
        ArrayList <Cell> cellList = algorithm.calculatePath(pOrigin.getI(), pOrigin.getJ(), pObjetive.getI(), pObjetive.getJ());
    
        for(Cell cell : cellList){
            Coord coordenates = new Coord(cell.getX(), cell.getY());
            route.add(coordenates);  
        }

       return route;
    }
    
    private HashMap<String, Coord> QueueToHM(LinkedList<Coord> pQueue){
        HashMap<String, Coord> routeHM = new HashMap<String, Coord>();
        for(Coord point: pQueue){
            routeHM.put(point.toString(), point);
        }
        return routeHM;
    }
    
    private void loadNewRoute(){
        actualRouteHS = pendingHS.pop();
        actualRoute = pending.pop();
        pathHS.clear();
        
        //Eliminate the first elemnet bc is the current one
        Coord iniPos = actualRoute.pop();
        pathHS.put(iniPos.toString(), iniPos);
    }
    
    public  Coord getPosition(){
        return new Coord(getCurrentI(), getCurrentJ());
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

    public String getCurrentState() {
        return motor.getCurrent().getState();
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
    
    public String getId(){
        return id;
    }
    
    public void setMap(CityMap map) {
        this.map = map;
    }
    
    public void setAlgorithm(AStar algorithm) {
        this.algorithm = algorithm;
    }
    

}

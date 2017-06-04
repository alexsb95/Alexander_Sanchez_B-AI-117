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
import fsm.Transition;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
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
    private  LinkedList<LinkedList<Coord>> pending;
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
        states.add(new Transition());
        states.add(new Parked());
        
        State currentState = new Parked();
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
        }else if("Searching".equals(getCurrentState())){
            statusSearch();
        }else if("Parading".equals(getCurrentState())){
            if(!pendingHS.isEmpty() && !pending.isEmpty()){
                loadNewRoute();
                followRoute();
            }else{
                overlord.send("transition", motor.getId());
                overlord.send("parade", motor.getId());    
            }
        }else if("OnRoute".equals(getCurrentState())){
            overlord.send("search", motor.getId());
        }
        
    }
    
    private void statusSearch(){
        Person client = map.tryToPickUp(this.getCurrentI(), this.getCurrentJ());
        if(client != null){  
            System.out.println("PickUP " + client.toString() +  " -- Dest "+ client.getDestination());
            pickUpClient(client, map.getHSBlocks().get(client.getDestination()));
            overlord.send("pickup", motor.getId());
        }else{
              // intenta de cargar la nueva ruta
            if(!pendingHS.isEmpty() && !pending.isEmpty()){
                loadNewRoute();
                followRoute();
            }else{
                overlord.send("transition", motor.getId());
                overlord.send("search", motor.getId());
            }
        }
    }
    
    public void pickUpClient(Person pClient, Block pBlock){
        if(pClient != null){
            client = pClient;
            desI = pBlock.getDestI();
            desJ = pBlock.getDestJ();
            
            this.loadSingleRoute(desI, desJ);
        }
    }
    
    public void dropClient(){
        client.setCurrentI(this.getCurrentI() + 1);
        client.setCurrentJ(this.getCurrentJ());
        client.setCurrentBlock(client.getDestination());
        client.update();
        map.addNewClient(client);
        client = null;
    }
    
    public void park(char pDestination){  
        if(map.validBlock(pDestination)){
            Block block = map.getHSBlocks().get(pDestination);
            desI = block.getDestI();
            desJ = block.getDestJ();
            this.loadSingleRoute(desI, desJ);    
        }
    }
    
    public void search(){
        ArrayList <Character> blockList = map.getClosetBlocks(this.getCurrentI(), this.getCurrentJ());
        loadMultipleRutes(blockList);
    }
    
    public void parade(){
        ArrayList <Character> blockList = map.getPosibleBlocks();
        loadMultipleRutes(blockList);
        System.out.println("LOAD " + this.pending.toString());
    }
    
    private void loadSingleRoute(int pDestinI, int pDestinJ){
            
            LinkedList<LinkedList<Coord>> pending = new LinkedList<>();
            LinkedList<HashMap<String, Coord>> pendingHM = new LinkedList<>();
                        
            LinkedList<Coord> pendQueue =getRoute(getPosition(), new Coord(pDestinI, pDestinJ));
            HashMap<String, Coord> pendHM = QueueToHM(pendQueue);
            pending.add(pendQueue);
            pendingHM.add(pendHM);
            
            setPendingHS(pendingHM);
            setPending(pending);
        
            loadNewRoute();
    }
    
    private void loadMultipleRutes(ArrayList <Character> pBlockList){;
        HashMap <Character, Block> blockHM = map.getHSBlocks();
     
        LinkedList<LinkedList<Coord>> pending = new LinkedList<>();
        LinkedList<HashMap<String, Coord>> pendingHM = new LinkedList<>();
        
        Coord originPoint = getPosition();
        
        for(Character block : pBlockList){
            Coord objetivePoint = new Coord(blockHM.get(block).getDestI(), blockHM.get(block).getDestJ());
            
            LinkedList<Coord> pendQueue = getRoute(originPoint, objetivePoint);
            HashMap<String, Coord> pendHM = QueueToHM(pendQueue);
            pending.add(pendQueue);
            pendingHM.add(pendHM);
            
            originPoint = objetivePoint;
        }
        
        setPendingHS(pendingHM);
        setPending(pending);
        
        loadNewRoute();
    
    }
    
    private LinkedList<Coord> getRoute(Coord pOrigin, Coord pObjetive){
        LinkedList<Coord> route = new LinkedList<>();
        ArrayList <Cell> cellList = algorithm.calculatePath(pOrigin.getI(), pOrigin.getJ(), pObjetive.getI(), pObjetive.getJ());
    
        for(Cell cell : cellList){
            Coord coordenates = new Coord(cell.getX(), cell.getY());
            route.add(coordenates);  
        }

       return route;
    }
    
    private HashMap<String, Coord> QueueToHM(LinkedList<Coord> pQueue){
        HashMap<String, Coord> routeHM = new HashMap<>();
        for(Coord point: pQueue){
            routeHM.put(point.toString(), point);
        }
        return routeHM;
    }
    
    private void loadNewRoute(){   
        if(!pendingHS.isEmpty() && !pending.isEmpty()){
            actualRouteHS = pendingHS.pop();
            actualRoute = pending.pop();
            pathHS.clear();

            //Eliminate the first elemnet bc is the current one
            Coord iniPos = actualRoute.pop();
            pathHS.put(iniPos.toString(), iniPos);
        }
    }
    
    public  Coord getPosition(){
        return new Coord(getCurrentI(), getCurrentJ());
    }
    
    public LinkedList<LinkedList<Coord>> getPending() {
        return pending;
    }

    public void setPending(LinkedList<LinkedList<Coord>> pending) {
        this.pending = pending;
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
    
    public Person getClient() {
        return client;
    }

    public void setClient(Person client) {
        this.client = client;
    }
    
    @Override
    public String toString(){
        return "ID: " + id +  " Pos: " + this.currentI  + "-" + this.currentJ + " Status: " + this.getCurrentState();
    }

}

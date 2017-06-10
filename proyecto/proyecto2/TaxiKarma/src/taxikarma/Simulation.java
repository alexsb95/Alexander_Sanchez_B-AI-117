/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taxikarma;

import algorithm.AStar;
import algorithm.Cell;
import map.Block;
import utils.InputReader;
import entities.Person;
import entities.TaxiCab;
import fsm.EventEmiter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import map.CityMap;
import map.DayCycle;
import ui.MainWindow;
import utils.Coord;

/**
 *
 * @author Alex
 */
public class Simulation extends Thread {

    public CityMap map;
    private EventEmiter overlord;
    private boolean pathOn;
    private boolean routeOn;
    private int daemon;
    
    public Simulation(String pMapFile, String pBuildingFile){
        overlord = new EventEmiter();
        map = new CityMap(overlord, 5000, 30);
        map.iniComponents(pMapFile, pBuildingFile);
    }
    
    public Simulation(){
        overlord = new EventEmiter();
    }
    
    public void setDayCycle(int pDayDuration, int pWorkPercentage){
        map = new CityMap(overlord, pDayDuration, pWorkPercentage);
    }

    public void setFiles(String pMapFile, String pBuildingFile){
        map.iniComponents(pMapFile, pBuildingFile);
    }

        
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*  Set de prueba
        EventEmiter overLord = new EventEmiter();
        
        Person ppl = new Person(1,1,'A','B','A',overLord);
        System.out.println("Person: " + ppl.toString());
        ppl.startTime();
        ppl.reduceTime();
        overLord.send("wait", ppl.getBrain().getId());
        overLord.update();
        System.out.println(ppl.getCurrentState());
        
        
        Block block = new Block(2,2,'A');
        System.out.println("Wating?: " +block.areClientsWaiting());
        block.newPerson(ppl);
        System.out.println("Wating?: " + block.areClientsWaiting());
        System.out.println("Cant: " + block.cantPerson());
        System.out.println("Pick: " + block.pickUpClient());
        
        
        InputReader inre = new InputReader();
        char[][] map = inre.readFile("Map.txt");
        
         for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){ 
                System.out.print(map[i][j]);
            }
            System.out.println();
         }
        
        CityMap cm = new CityMap(overLord);
        cm.iniComponents("Map.txt");
        Cell[][] var = cm.nodeMatrix;
        System.out.println(cm.toString());
        
        
        cm.addNewClient(18, 18, 'S', 'V');
        cm.addSeveralClients(3);*/
        
       
        Simulation sim = new Simulation("Map.txt", "Buildings.txt");
        EventEmiter overlord2 = sim.getEE(); 
        System.out.println(sim.createMap());
        overlord2.send("search");
        overlord2.update();
        
        for(int i=0;i<150;i++){
            //sim.taxiList.get(0).followRoute();
            overlord2.send("update");
            overlord2.update();
        }
                sim.addClient(5);
        System.out.println(sim.createMap());
        
        System.out.println(sim.getTime());
        

        
        sim.map.defineSection();
        /*
        DayCycle day = new DayCycle(6,20,overlord2);
        System.out.println(day.getCurrentState());
         System.out.println(day.getTimer());
        overlord2.send("beproductive");
        overlord2.update();
        System.out.println(day.getCurrentState());
        System.out.println(day.getTimer());
        overlord2.send("update");
        overlord2.update();
        System.out.println(day.getCurrentState());
        System.out.println(day.getTimer());
        */
  
    }
    

    public EventEmiter getEE(){
        return overlord;
    }
    
    public String createMap(){

        String strMap = "";
        
        char[][] cityMap = map.getCharMatrix();
        int iLen = cityMap.length;
        int jLen = cityMap[0].length;
        
        HashMap<String,Person> clientListHM = TransClientHM(map.getClientList());
        HashMap<String,TaxiCab> taxiListHM = TransTaxiHM(map.getTaxiList());
        HashMap<String,Coord> pathHM = getPaths(map.getTaxiList());
        HashMap<String,Coord> routeHM = getRoutes(map.getTaxiList());
        System.out.println("Client list: "+clientListHM.size());
        //System.out.println("Client list: "+clientListHM.toString());
        //System.out.println("Path: " + pathHM.toString());
        //System.out.println("Route: " + routeHM.toString());
        
        for(int i = 0; i < iLen; i++){
            for(int j = 0; j < jLen; j++){
                //Print the taxi
                if(taxiListHM.containsKey(i+"-"+j)){
                    strMap +=  '@';
                }//Print the path
                else if(pathOn && pathHM.containsKey(i + "-" + j)){
                    strMap +=  '*';
                }//Print the route
                else if(routeOn && routeHM.containsKey(i + "-" + j)){
                     strMap +=  '+';
                }else if (clientListHM.containsKey(i + "-" + j)){
                    strMap += 'o';
                }else{      
                    strMap +=  cityMap[i][j];
                }
                
            }
           strMap += '\n';
        }
        
        return strMap;
    }
    
    private HashMap<String,Person> TransClientHM(ArrayList<Person> pClientList){
        HashMap <String,Person> clientsHM = new HashMap<>();
        for(Person client : pClientList){
            clientsHM.put(client.getCurrentI()+"-"+client.getCurrentJ(), client);
        }
        return clientsHM;
    }
    
    private HashMap<String,TaxiCab> TransTaxiHM(ArrayList<TaxiCab> pTaxiList){
        HashMap <String,TaxiCab> taxiHM = new HashMap<>();
        for(TaxiCab taxi : pTaxiList){
            taxiHM.put(taxi.getCurrentI()+"-"+taxi.getCurrentJ(), taxi);
        }
        return taxiHM;
    }
    
    private HashMap<String,Coord> getPaths(ArrayList<TaxiCab> pTaxiList){
         HashMap <String,Coord> pathListHM = new HashMap<>();
        for(TaxiCab taxi : pTaxiList){
             HashMap <String,Coord> pathHM = taxi.getPathHS();  
            for (Map.Entry<String, Coord> entry: pathHM.entrySet()) {
                pathListHM.put(entry.getKey(), entry.getValue());
            }
        }
        return pathListHM;
        
    }
    
    private HashMap<String,Coord> getRoutes(ArrayList<TaxiCab> pTaxiList){
         HashMap <String,Coord> routeListHM = new HashMap<>();
        for(TaxiCab taxi : pTaxiList){
             HashMap <String,Coord> routeHM = taxi.getActualRouteHS();        
            for (Map.Entry<String, Coord> entry: routeHM.entrySet()) {
                routeListHM.put(entry.getKey(), entry.getValue());
            }
        }
        return routeListHM;
        
    }
       
    public void switchRoute(){
        if(routeOn){
            routeOn = false;
        }else{
            routeOn = true;
        }
    }
    
    public void switchPath(){
        if(pathOn){
            pathOn = false;
        }else{
            pathOn = true;
        }
    }
    
    public int getTime(){
        return map.getTime();
    }
    
    public void addTaxi(){
        map.addTaxi();
    }
    
    public void addClient(char pCurret, char pHome, char pWork){
        map.addNewClient(pCurret, pHome, pWork);
    }
    
     public void addClient(int pCant){
        map.addSeveralClients(pCant);
    }   

    public boolean isPathOn() {
        return pathOn;
    }

    public boolean isRouteOn() {
        return routeOn;
    }

    public int getDaemon() {
        return daemon;
    }

    public void setDaemon(int daemon) {
        this.daemon = daemon;
    }
    
    
    public void animate(){
        while(true){
            if(getDaemon() > 0){
                System.out.println("Hola");
                overlord.send("update");
                overlord.update();
                String strMap = createMap();
                System.out.println(strMap);
                //MainWindow.upadateMap(strMap);
                try {
                    Thread.currentThread().sleep(getDaemon());
                } catch (InterruptedException ex) {
                    Logger.getLogger(Simulation.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                try {
                    Thread.currentThread().sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Simulation.class.getName()).log(Level.SEVERE, null, ex);
                }
            }


        }
    }
    
    @Override
    public void run() {
        animate();
    }

}

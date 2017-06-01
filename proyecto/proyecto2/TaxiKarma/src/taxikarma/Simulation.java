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
import map.CityMap;
import map.DayCycle;
import utils.Coord;

/**
 *
 * @author Alex
 */
public class Simulation {
    public ArrayList<TaxiCab> taxiList;
    private CityMap map;
    private AStar algorithm;
    private EventEmiter overlord;
    public Simulation(String pMapFile, String pBuildingFile){
        overlord = new EventEmiter();
        map = new CityMap(overlord, 5000, 30);
        map.iniComponents(pMapFile, pBuildingFile);
        Cell[][] nodes = map.getNodeMatrix();
        algorithm = new AStar(nodes, nodes.length, nodes[0].length);
        initializeTaxis(map, algorithm);
    }
    
    public void initializeTaxis(CityMap pMap, AStar pAlgorithm){
        ArrayList<TaxiCab> taxis = map.getIncialTaxis();
        for(TaxiCab taxi : taxis){
            taxi.setMap(map);
            taxi.setAlgorithm(pAlgorithm);
        }
        taxiList = taxis;
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
        System.out.println("Listneer: " +sim.getEE().getListener().toString());
         EventEmiter overlord2 = sim.getEE(); 
        overlord2.send("search");
        overlord2.update();

        System.out.println(sim.taxiList.get(0).getCurrentState());
       for(int i=0;i<235;i++){
            //sim.taxiList.get(0).followRoute();
            overlord2.send("update");
            overlord2.update();
        }
        System.out.println(sim.taxiList.get(0).getPosition().toString());
        System.out.println(sim.taxiList.get(0).getActualRoute().toString());
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
    
    public void addTaxi(){}
    
    public EventEmiter getEE(){
        return overlord;
    }
}

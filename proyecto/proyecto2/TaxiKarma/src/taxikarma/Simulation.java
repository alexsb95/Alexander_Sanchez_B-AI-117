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
import utils.Coord;

/**
 *
 * @author Alex
 */
public class Simulation {
    public ArrayList<TaxiCab> taxiList;
    private CityMap map;
    private AStar algorithm;
    
    public Simulation(String pFilename){
        EventEmiter overlord = new EventEmiter();
        map = new CityMap(overlord);
        map.iniComponents(pFilename);
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
        cm.addSeveralClients(3)*/;
        
        EventEmiter overlord2 = new EventEmiter();
        Simulation sim = new Simulation("Map.txt");
        sim.taxiList.get(0).search();
    }
    
    
    public void search(String pIdTaxi){
        TaxiCab taxi = taxiList.get(0);
        ArrayList <Character> blockList = map.getPosibleBlocks();
        HashMap <Character, Block> blockHM = map.getHSBlocks();
     
        LinkedList<LinkedList<Coord>> pending = new LinkedList<LinkedList<Coord>>();
        LinkedList<HashMap<String, Coord>> pendingHM = new LinkedList<HashMap<String, Coord>>();
        
        Coord originPoint = taxi.getPosition();
        for(Character block : blockList){
            Coord objetivePoint = new Coord(blockHM.get(block).getDestI(), blockHM.get(block).getDestJ());
            
            LinkedList<Coord> pendQueue = getRoute(originPoint, objetivePoint);
            HashMap<String, Coord> pendHM = QueueToHM(pendQueue);
            pending.add(getRoute(originPoint, objetivePoint));
            pendingHM.add(pendHM);
            
            originPoint = objetivePoint;
        }
        
        taxi.setPendingHS(pendingHM);
        taxi.setPending(pending);
        
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
    
    public void addTaxi(){}
}


package taxikarma;

import algorithm.Cell;
import map.Block;
import utils.FileManager;
import entities.Person;
import entities.TaxiCab;
import fsm.EventEmiter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import map.CityMap;
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
    private FileManager fileHandler;
    private final String FILENAME = "TrafficJam.txt";
    
    public Simulation(String pMapFile, String pBuildingFile){
        overlord = new EventEmiter();
        map = new CityMap(overlord, 5000, 30);
        map.iniComponents(pMapFile, pBuildingFile);
        fileHandler = new FileManager();
        fileHandler.createFile(FILENAME, "Archivo contiene el congestionamiento\n");
    }
    
    public Simulation(){
        overlord = new EventEmiter();
        fileHandler = new FileManager();
        fileHandler.createFile(FILENAME, "Archivo contiene el congestionamiento\n");
    }
    
    public void setDayCycle(int pDayDuration, int pWorkPercentage){
        map = new CityMap(overlord, pDayDuration, pWorkPercentage);
    }

    public void setFiles(String pMapFile, String pBuildingFile){
        map.iniComponents(pMapFile, pBuildingFile);
    }

    public void setTraficCost(int pIncrease, int pDecrease){
        map.defineSection(pIncrease, pDecrease);
    }    
        
    /**
     * @param args the command line arguments
     */
    /*
    public static void main(String[] args) {
        
       
        Simulation sim = new Simulation("Map2.txt", "Buildings2.txt");
        EventEmiter overlord2 = sim.getEE(); 
        System.out.println(sim.createMap());
        overlord2.send("search");
        overlord2.update();
        
        for(int i=0;i<20;i++){
            //sim.taxiList.get(0).followRoute();
            overlord2.send("update");
            overlord2.update();
        }

        System.out.println(sim.createMap());
        
        System.out.println(sim.getTime());

        FileManager fM = new FileManager();
        fM.appendFile("Prueba.txt", "Hola");
        
    }*/
    

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
        //System.out.println("Client list: "+clientListHM.size());
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
    
    public String createMapTraffic(){

        String strMap = "";
        
        Cell[][] nodes = map.getNodeMatrix();
        char[][] cityMap = map.getCharMatrix();
        int iLen = cityMap.length;
        int jLen = cityMap[0].length;
        
        HashMap<String,Person> clientListHM = TransClientHM(map.getClientList());
        HashMap<String,TaxiCab> taxiListHM = TransTaxiHM(map.getTaxiList());
        HashMap<String,Coord> pathHM = getPaths(map.getTaxiList());
        HashMap<String,Coord> routeHM = getRoutes(map.getTaxiList());

        
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
                }else if(nodes[i][j] != null && nodes[i][j].getWeight() > 0){
                   strMap += Math.round(nodes[i][j].getWeight());
                }else{      
                    strMap +=  cityMap[i][j];
                }
                
            }
           strMap += '\n';
        }
        
        return strMap;
    }
    
    public String createMapUI(){

        String strMap = "";
        
        char[][] cityMap = map.getCharMatrix();
        int iLen = cityMap.length;
        int jLen = cityMap[0].length;
        
        HashMap<String,Person> clientListHM = TransClientHM(map.getClientList());
        HashMap<String,TaxiCab> taxiListHM = TransTaxiHM(map.getTaxiList());
        HashMap<String,Coord> pathHM = getPaths(map.getTaxiList());
        HashMap<String,Coord> routeHM = getRoutes(map.getTaxiList());
        
        for(int i = 0; i < iLen; i++){
            for(int j = 0; j < jLen; j++){
                //Print the taxi
                strMap +=  ' ';
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
                    if(cityMap[i][j] == ' '){
                        strMap +=  "    ";
                    }else
                        strMap +=  cityMap[i][j];
                }
                strMap +=  ' ';
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
    
    public void writeTrafficFile(){
        String strMap = "\n " + getTimeFormat() + " \n";
        Cell[][] nodes = map.getNodeMatrix();        
        char[][] cityMap = map.getCharMatrix();
        
        int iLen = cityMap.length;
        int jLen = cityMap[0].length;
        
        
        for(int i = 0; i < iLen; i++){
            for(int j = 0; j < jLen; j++){
                //Print the taxi
                strMap += ' ';
                if(nodes[i][j] != null && nodes[i][j].getWeight() > 0){
                   strMap += Math.round(nodes[i][j].getWeight());
                }else{  
                    strMap +=  cityMap[i][j];
                }
                strMap += ' ';
            }
           strMap += '\n';
        }
        System.out.println(strMap);
        fileHandler.appendFile(FILENAME, strMap);
    }
    
    
    public String getTimeFormat(){
        int time = map.getTime();
        int minutes = 0;
        
        while(time >= 60){
            time = time-60;
            minutes++;
        }
        
        return minutes + ":" + time;
    }
    
    public String getBuildingAmount(){
        HashMap<Character, Integer> amountHS = getAmountPeople();
        String strAmount = "";
        
        for (Map.Entry<Character, Integer> entry: amountHS.entrySet()) {
           strAmount += entry.getKey() + " : " + entry.getValue() + "\n";
        }
        
        return strAmount;
    }
    
    private HashMap<Character, Integer> getAmountPeople(){
        HashMap<Character, Block> blocks = map.getHSBlocks();
        HashMap<Character, Integer> amountHS = new HashMap<>();
                
        for (Map.Entry<Character, Block> entry: blocks.entrySet()) {
            amountHS.put(entry.getKey(), entry.getValue().cantPerson());
        }

        return amountHS;
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
                
                System.out.println(createMapTraffic());
                MainWindow.upadateMap(createMapUI());
                MainWindow.upadateBuildings(getBuildingAmount());
                MainWindow.upadateTime(getTimeFormat());
                
                writeTrafficFile();
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

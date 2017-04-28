/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taxi;

/**
 *
 * @author Alex
 */

import Matrix.Block;
import Matrix.Cell;
import Matrix.CityMap;
import Matrix.Coord;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.PriorityList;

public class Taxi implements Runnable{

    private  CityMap matrix;
    
    private  LinkedList<Coord> actualRoute;
    private static LinkedList<LinkedList<Coord>> pending;
    //private  LinkedList<Coord> path;
    
    private  HashMap<String, Coord> actualRouteHS;
    private  LinkedList<HashMap<String, Coord>> pendingHS;
    private  HashMap<String, Coord> pathHS;
    private  boolean routeOn;
    private  boolean pathOn;
    
    private int daemon;
    
    public Taxi (){
        matrix = new CityMap();
        matrix.readMatrix();
        pending = new LinkedList<LinkedList<Coord>>();
        //path = new LinkedList<Coord>();

        actualRoute = new LinkedList<Coord>();
        actualRouteHS = new HashMap<String, Coord>();
        
        pendingHS = new LinkedList<HashMap<String, Coord>>();
        pathHS = new HashMap<String, Coord> ();
        routeOn = false;
        pathOn =  false;
        
        daemon = 0;
    }
    
    public static void main(String[] args) {
        //21,29
        /* Inicializa */

        Taxi tx = new Taxi();

        /* 
        //Prueba 0
        addClients('A','S');
        System.out.println(matrix.streetBlocks.get('A').blockStreets.get(1).getDestination());
        addClients(2);
        */
        
        /* 
        System.out.println(addClients('A','S')); 
        
         //Prueba 1
        searchClient();
        System.out.print(createMap());
        
        System.out.println(taxiPosition().i + " - "+ taxiPosition().j);
   
        System.out.println( play(8));
        
        System.out.println("Ruta: "+pathHS.toString());
        System.out.println("Ruta: "+actualRoute.toString());
        
        
        /*
        //Prueba 2
        park('E');
        System.out.print(createMap());
        
        System.out.println(taxiPosition().i + " - "+ taxiPosition().j);
   
        System.out.println( play(30));
        
        System.out.println("Ruta: "+pathHS.toString());
        System.out.println("Ruta: "+actualRoute.toString());
        */
        /*
        //Prueba 3 
        tx.addClients('A','S');
        tx.searchClient();
        System.out.println( tx.play(8));  
        System.out.print(tx.createMap());
        
        
        tx.switchRoute();
        System.out.print(tx.createMap());
         
        tx.switchPath();
        System.out.print(tx.createMap());
        */
        tx.parade();
        //tx.searchClient();
        
    }
    
    public  String play(int pAmount){
        for(int cant = 0; cant < pAmount; cant++){
            moveTaxi();
            if(actualRoute.isEmpty()){
                switch(matrix.getTaxi().getStatus()){
                    case "SEARCHING":
                        statusSearch();
                        break;
                        
                    case "DROPINGOFF":
                        searchClient();
                        break;
                        
                    case "PARKING":
                        statusWait();
                        break;
                        
                    case "WAITING":
                        //do nothing
                        break;
                        
                    case "PARADE":
                        statusParade();
                        break;
                }
            }else
                System.out.println(actualRoute.toString());
        }


        //Check if the actual route reach the end
            //If searching see if some is on the block
            //If droping drop
            //If parade new route
            //IF paking stop
        return createMap();
    }
    
    private  void statusSearch(){
        Character destination = matrix.pickUpClient();
      
        //Encontro un cliente
        if(destination != null){
            
            System.out.println("Entro cliete\n Llevando el cliente a la cuadra "+destination);
            //Limpia la lista anterior
            pendingHS.clear();
            pending.clear();
            //pathHS.clear();
            //Agrega la nueva ruta a la lista
            prepareRoute(destination);
            //Selecciona la ruta actual
            actualRouteHS = pendingHS.pop();
            actualRoute = pending.pop();
            //Elimana el primer elemento, ya que es la posicion actual
            Coord iniPos = actualRoute.pop();
            if(pathOn)
                pathHS.put(iniPos.toString(), iniPos);
            
            matrix.getTaxi().setStatus("DROPINGOFF");
        }else{
            //Escoge la ruta siguiente
            if(!pendingHS.isEmpty()){
                //Selecciona la ruta actual
                actualRouteHS = pendingHS.pop();
                actualRoute = pending.pop();
                //Elimana el primer elemento, ya que es la posicion actual
                Coord iniPos = actualRoute.pop();
                if(pathOn)
                    pathHS.put(iniPos.toString(), iniPos);
            }//Sigue buscado
            else
                searchClient();

        }
        
    }
   
    private  void statusWait(){
        pendingHS.clear();
        pending.clear();
        //pathHS.clear();
        matrix.getTaxi().setStatus("WAITING");
    }
    
    private void statusParade(){
        if(!pendingHS.isEmpty()){
            //Selecciona la ruta actual
            actualRouteHS = pendingHS.pop();
            actualRoute = pending.pop();
            //Elimana el primer elemento, ya que es la posicion actual
            Coord iniPos = actualRoute.pop();
            if(pathOn)
                pathHS.put(iniPos.toString(), iniPos);
        }//Sigue buscado
        else
            parade();
    }
    
    public  boolean park(Character pDestination){
        
        if(matrix.validBlock(pDestination)){
            //Limpia la lista anterior
            pendingHS.clear();
            pending.clear();
            //pathHS.clear();
            //Agrega la nueva ruta a la lista
            prepareRoute(pDestination);
            //Selecciona la ruta actual
            actualRouteHS = pendingHS.pop();
            actualRoute = pending.pop();
            //Elimana el primer elemento, ya que es la posicion actual
            Coord iniPos = actualRoute.pop();
             if(pathOn)
                pathHS.put(iniPos.toString(), iniPos);

            matrix.getTaxi().setStatus("PARKING");
            
            return true;
        }else
            return false;
    }
    
    public void parade(){
        matrix.getTaxi().setStatus("PARADE");
        pendingHS.clear();
        pending.clear();
        actualRoute.clear();
        actualRouteHS.clear();
        
        paradeRoute();
        
        actualRouteHS = pendingHS.pop();
        actualRoute = pending.pop();
        
        Coord iniPos = actualRoute.pop();
        if(pathOn)
            pathHS.put(iniPos.toString(), iniPos);
    }
    
    private void paradeRoute(){
        
        ArrayList <Character> blockList = matrix.parade();
        Coord originTaxi = taxiPosition();
        
        for(Character ch : blockList){
             System.out.println("OPCION: "+ch);
            //Agrega la ruta a la lista de pendiente
            prepareRoute(ch);
            matrix.moveTaxi(matrix.streetBlocks.get(ch).getDestX(), matrix.streetBlocks.get(ch).getDestY());
        }
        
         matrix.moveTaxi(originTaxi.i, originTaxi.j);
        
    }
    
    public  void searchClient(){
        matrix.getTaxi().setStatus("SEARCHING");
        pendingHS.clear();
        pending.clear();
        actualRoute.clear();
        actualRouteHS.clear();
        
        //pathHS.clear();
        System.out.println("Pending:"+pending.toString());
        //Obtiene la rutas de busqueda
        searchClientRoute();
        System.out.println("NEW Pending:"+pending.toString());
        //Selecciona la ruta actual
        actualRouteHS = pendingHS.pop();
        actualRoute = pending.pop();
        
        System.out.println("Nueva ruta:"+actualRoute.toString());
        //Elimana el primer elemento, ya que es la posicion actual
        
        Coord iniPos = actualRoute.pop();
        if(pathOn)
            pathHS.put(iniPos.toString(), iniPos);
             
  
    }
    
    private  void searchClientRoute(){
        ArrayList <Character> blockList = matrix.searchClients();
        Coord originTaxi = taxiPosition();
        
        for(Character ch : blockList){
            //Agrega la ruta a la lista de pendiente
            System.out.println("OPCION: "+ch);
            prepareRoute(ch);
            matrix.moveTaxi(matrix.streetBlocks.get(ch).getDestX(), matrix.streetBlocks.get(ch).getDestY());
        }
        
        matrix.moveTaxi(originTaxi.i, originTaxi.j);
    }
    
    public  void addClients(int pAmount){
        if(pAmount > 0)
            matrix.setClient(pAmount);
        else if(pAmount == 0)
            matrix.deleteClients();
    }
    
    public  boolean addClients(char pOrigin, char pDesnity){
        if(matrix.validBlock(pOrigin) && matrix.validBlock(pDesnity)){
            matrix.setClient(pOrigin, pDesnity);
            return true;
        }else
            return false;
    }
    
    public  void switchRoute(){
        if(routeOn)
            routeOn = false;
        else
            routeOn = true;
    }
    
    public  void switchPath(){
        if(pathOn){
            pathOn = false;
            pathHS.clear();
        }else
            pathOn = true;
    }
    
    
    private  void prepareRoute(Character destination){
        HashMap<String, Coord> routeHS = new HashMap<String, Coord>();
        LinkedList<Coord> route = new LinkedList<Coord>();

        ArrayList <Cell> cellList = matrix.geRoute(destination);
        for(Cell cell : cellList){
            Coord coordenates = new Coord(cell.getX(), cell.getY());
            coordenates.prior = cell.getPriority();
            routeHS.put(cell.getX() + "-" + cell.getY(), coordenates);
            route.add(coordenates);  
        }
        //Carga la nueva lista
        pendingHS.add(routeHS);
        pending.add(route);
    }
 
    private  void moveTaxi(){
        if(!actualRoute.isEmpty()){
            Coord newPos = actualRoute.pop();
            if(pathOn)
                pathHS.put(taxiPosition().toString(), new Coord(taxiPosition().i, taxiPosition().j));
            matrix.moveTaxi(newPos.i, newPos.j);
        }
        
    }
    
    private  Coord taxiPosition(){
        return new Coord(matrix.getTaxi().getCurrentPosI(), matrix.getTaxi().getCurrentPosJ());
    }
    
    public  String createMap(){
        String strTaxi = "";
        
        //Loads in the map 
        char [][] citymap = matrix.getMap();
        int iLen = citymap.length;
        int jLen = citymap[0].length;
        
        HashMap <String,Coord> clientList = matrix.listClients();
        HashMap <String, Character> prior = new HashMap <String, Character>();
        //Ask the taxi position
        Coord taxiPos = taxiPosition();
        System.out.println(" Taxi "+taxiPos.toString()+" List: ");
        ArrayList<Coord> priorityPath = new ArrayList<Coord>();
        if(actualRouteHS.size() > 0){
            priorityPath = actualRouteHS.get(taxiPos.toString()).prior;
            for(int index = 1; index <= priorityPath.size(); index++){
                Coord priorCell = priorityPath.get(index - 1);
                prior.put(priorCell.i + "-" + priorCell.j, (char)(index-1 + '0') );
                System.out.print(" - "+priorCell.i + "-" + priorCell.j + "/"+(char)(index + '0'));
            }
            System.out.println();
        }

        
        for(int i = 0; i < iLen; i++){
            for(int j = 0; j < jLen; j++){
                //Print the taxi
                if(taxiPos.i == i && taxiPos.j == j){
                    strTaxi +=  '@';
                }//Print the path
                else if(pathOn && pathHS.containsKey(i + "-" + j)){
                    strTaxi +=  '*';
                }else if(pathOn && prior.containsKey(i + "-" + j)){
                    strTaxi +=  prior.get(i + "-" + j);
                
                
                }//Print the route
                else if(routeOn && actualRouteHS != null && actualRouteHS.containsKey(i + "-" + j)){
                     strTaxi +=  '+';
                }else if (clientList.containsKey(i + "-" + j))
                    strTaxi += 'o';
                else      
                    strTaxi +=  citymap[i][j];
                //Print priority
                
            }
           strTaxi += '\n';
        }
  
        return strTaxi;
    
    }

    @Override
    public void run() {
        
        while(true){
            if(daemon > 0){
                System.out.println(play(1));
                try {
                    Thread.currentThread().sleep(daemon * 1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Taxi.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                try {
                    Thread.currentThread().sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Taxi.class.getName()).log(Level.SEVERE, null, ex);
                }
            }


        }
        

    }
    
    public void setDaemon(int pAmount){
        daemon = pAmount;
    }
    
    public int getDaemon(){
        return daemon;
    }
    
    public boolean getPathOn(){
        return pathOn;
    }
    
    public boolean getRouteOn(){
        return routeOn;
    }
}



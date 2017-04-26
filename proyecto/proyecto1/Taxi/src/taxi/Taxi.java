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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Taxi {

    private  CityMap matrix;
    
    private  LinkedList<Coord> actualRoute;
    private  LinkedList<LinkedList<Coord>> pending;
    //private  LinkedList<Coord> path;
    
    private  HashMap<String, Coord> actualRouteHS;
    private  LinkedList<HashMap<String, Coord>> pendingHS;
    private  HashMap<String, Coord> pathHS;
    private  boolean routeOn;
    private  boolean pathOn;
    
    private int deamon;
    
    public Taxi (){
        matrix = new CityMap();
        
        pending = new LinkedList<LinkedList<Coord>>();
        //path = new LinkedList<Coord>();
        
        pendingHS = new LinkedList<HashMap<String, Coord>>();
        pathHS = new HashMap<String, Coord> ();
        routeOn = true;
        pathOn =  true;
        
        deamon = 0;
    }
    
    public  void main(String[] args) {
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
                        
                        break;
                }
            }
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
            //Limpia la lista anterior
            pendingHS.clear();
            pending.clear();
            pathHS.clear();
            //Agrega la nueva ruta a la lista
            prepareRoute(destination);
            //Selecciona la ruta actual
            actualRouteHS = pendingHS.pop();
            actualRoute = pending.pop();
            //Elimana el primer elemento, ya que es la posicion actual
            Coord iniPos = actualRoute.pop();
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
                pathHS.put(iniPos.toString(), iniPos);
            }//Sigue buscado
            else
                searchClient();

        }
        
    }
   
    private  void statusWait(){
        pendingHS.clear();
        pending.clear();
        pathHS.clear();
        matrix.getTaxi().setStatus("WAITING");
    }
    
    public  boolean park(Character pDestination){
        
        if(matrix.validBlock(pDestination)){
            //Limpia la lista anterior
            pendingHS.clear();
            pending.clear();
            pathHS.clear();
            //Agrega la nueva ruta a la lista
            prepareRoute(pDestination);
            //Selecciona la ruta actual
            actualRouteHS = pendingHS.pop();
            actualRoute = pending.pop();
            //Elimana el primer elemento, ya que es la posicion actual
            Coord iniPos = actualRoute.pop();
            pathHS.put(iniPos.toString(), iniPos);

            matrix.getTaxi().setStatus("PARKING");
            
            return true;
        }else
            return false;
    }
    
    public  void searchClient(){
        matrix.getTaxi().setStatus("SEARCHING");
        pathHS.clear();
        
        //Obtiene la rutas de busqueda
        searchClientRoute();
        //Selecciona la ruta actual
        actualRouteHS = pendingHS.pop();
        actualRoute = pending.pop();
        //Elimana el primer elemento, ya que es la posicion actual
        Coord iniPos = actualRoute.pop();
        pathHS.put(iniPos.toString(), iniPos);
             
  
    }
    
    private  void searchClientRoute(){
        ArrayList <Character> blockList = matrix.searchClients();
        ArrayList <Cell> cellList;
        Coord originTaxi = taxiPosition();
        
        for(Character ch : blockList){
            //Agrega la ruta a la lista de pendiente
            prepareRoute(ch);
            matrix.moveTaxi(matrix.streetBlocks.get(ch).getDestX(), matrix.streetBlocks.get(ch).getDestY());
        }
        
        matrix.moveTaxi(originTaxi.i, originTaxi.j);
    }
    
    public  void addClients(int pAmount){
        if(pAmount > 0)
            matrix.setClient(pAmount);
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
        if(pathOn)
            pathOn = false;
        else
            pathOn = true;
    }
    
    
    private  void prepareRoute(Character destination){
        HashMap<String, Coord> routeHS = new HashMap<String, Coord>();
        LinkedList<Coord> route = new LinkedList<Coord>();

        ArrayList <Cell> cellList = matrix.geRoute(destination);
        for(Cell cell : cellList){
            Coord coordenates = new Coord(cell.getX(), cell.getY());
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
        
        //Ask the taxi position
        Coord taxiPos = taxiPosition();
        
        
        for(int i = 0; i < iLen; i++){
            for(int j = 0; j < jLen; j++){
                
                //Print the taxi
                if(taxiPos.i == i && taxiPos.j == j){
                    strTaxi +=  '@';
                }//Print the path
                else if(pathOn && pathHS.containsKey(i + "-" + j)){
                     strTaxi +=  '*';
                }//Print the route
                else if(routeOn && actualRouteHS.containsKey(i + "-" + j)){
                     strTaxi +=  '+';
                }else       
                    strTaxi +=  citymap[i][j];
            }
           strTaxi += '\n';
        }
  
        return strTaxi;
    
    }
    
}

class Coord{
    public int i;
    public int j;
    
    Coord(int pI, int pJ){
        i = pI;
        j = pJ;
    }
    
    @Override
    public String toString(){
        return i + "-" + j;
    }
}

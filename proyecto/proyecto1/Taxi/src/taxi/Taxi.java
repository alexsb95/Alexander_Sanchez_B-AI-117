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

    private static CityMap matrix;
    
    private static LinkedList<Coord> actualRoute;
    private static LinkedList<LinkedList<Coord>> pending;
    //private static LinkedList<Coord> path;
    
    private static HashMap<String, Coord> actualRouteHS;
    private static LinkedList<HashMap<String, Coord>> pendingHS;
    private static HashMap<String, Coord> pathHS;
    private static boolean routeOn;
    private static boolean pathOn;
    
    public static void main(String[] args) {
        //21,29
        /* Inicializa */
        matrix = new CityMap();
        
        pending = new LinkedList<LinkedList<Coord>>();
        //path = new LinkedList<Coord>();
        
        pendingHS = new LinkedList<HashMap<String, Coord>>();
        pathHS = new HashMap<String, Coord> ();
        routeOn = true;
        pathOn =  true;
        
        matrix.readMatrix();
        
         /*  
        matrix.setClient('A','S');
        System.out.println(matrix.streetBlocks.get('A').blockStreets.get(1).getDestination());
        matrix.setClient(2);
        
        ArrayList<Cell> alCell = matrix.sendTaxi( matrix.streetBlocks.get('S'));
        ArrayList<Character> al = matrix.searchClients();
        
        for(Cell c : alCell ){
            System.out.println(c.toString());
        }
        
        for(Character ch : al ){
            System.out.println(ch);
        }
        */
        
        searchClient();
        System.out.print(createMap());
        
        moveTaxi();
        
        System.out.print(createMap());
        
        System.out.print(taxiPosition().i + " - "+ taxiPosition().j);
    }
    
    public String play(int pAmount){
        for(int cant = 0; cant < pAmount; cant++){
            moveTaxi();
        }
        //Check if the actual route reach the end
        return createMap();
    }
    
    public static void moveTaxi(){
        if(!actualRoute.isEmpty()){
            Coord newPos = actualRoute.pop();
            pathHS.put(taxiPosition().i + "-"+ taxiPosition().j, new Coord(taxiPosition().i, taxiPosition().j));
            matrix.moveTaxi(newPos.i, newPos.j);
        }
        
    }
    
    public static void searchClient(){
        matrix.getTaxi().setStatus("Searching");
        
        //Obtiene la rutas de busqueda
        searchClientRoute();
        //Selecciona la ruta actual
        actualRouteHS = pendingHS.pop();
        actualRoute = pending.pop();
        //Elimana el primer elemento, ya que es la posicion actual
        actualRoute.pop();
        
        actualRouteHS.forEach((k,v) -> {
                System.out.println(v.i + " " + v.j);
            });      
        pathHS.clear();
    }
    
    private static void searchClientRoute(){
        ArrayList <Character> blockList = matrix.searchClients();
        ArrayList <Cell> cellList;
        Coord originTaxi = taxiPosition();
        
        for(Character ch : blockList){
            HashMap<String, Coord> routeHS = new HashMap<String, Coord>();
            LinkedList<Coord> route = new LinkedList<Coord>();
            
            cellList = matrix.geRoute(ch);
            for(Cell cell : cellList){
                Coord coordenates = new Coord(cell.getX(), cell.getY());
                routeHS.put(cell.getX() + "-" + cell.getY(), coordenates);
                route.add(coordenates);          
            }
            pendingHS.add(routeHS);
            pending.add(route);
            
            matrix.moveTaxi(matrix.streetBlocks.get(ch).getDestX(), matrix.streetBlocks.get(ch).getDestY());
        }
        
        matrix.moveTaxi(originTaxi.i, originTaxi.j);
    }
    
    private static Coord taxiPosition(){
        return new Coord(matrix.getTaxi().getCurrentPosI(), matrix.getTaxi().getCurrentPosJ());
    }
    
    public static String createMap(){
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
}

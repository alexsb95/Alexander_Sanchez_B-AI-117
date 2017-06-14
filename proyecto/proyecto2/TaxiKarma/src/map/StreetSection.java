
package map;

import algorithm.Cell;
import entities.TaxiCab;
import fsm.sectionstates.Decreasing;
import fsm.EventEmiter;
import fsm.FSM;
import fsm.sectionstates.Increasing;
import fsm.sectionstates.Keeping;
import fsm.State;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author Alex
 */
public class StreetSection {
    private HashMap<String, Cell> street;
    private double stress;
    private HashMap<String, TaxiCab> taxisRecord;
    private HashMap<String, TaxiCab> taxisActual;
    private double cost;
    private double relief;
    private FSM vigilante;
    private EventEmiter overlord;
    private String id;
    
    public StreetSection(HashMap<String, Cell> pStreet, EventEmiter pEmiter, double pIncrease, double pDecrease){
        street = pStreet;
        stress = 0;
        taxisRecord = new HashMap<>();
        taxisActual = new HashMap<>();
        id = UUID.randomUUID().toString();
        setUpStates(pEmiter);
        
        cost = pIncrease;
        relief = pDecrease;
    }
    
    private void setUpStates(EventEmiter pEmiter){
        ArrayList<State> states = new ArrayList<>();
        states.add(new Increasing());
        states.add(new Keeping());
        states.add(new Decreasing());
        
        State currentState = new Keeping();
        String uniqueID = UUID.randomUUID().toString();
        vigilante = new FSM(this, states, currentState, uniqueID, pEmiter);
        overlord = pEmiter;
    }

    public HashMap<String, Cell> getStreet() {
        return street;
    }

    public void setStreet(HashMap<String, Cell> street) {
        this.street = street;
    }

    public double getStress() {
        return stress;
    }

    public void setStress(double stress) {
        this.stress =  this.stress + stress;
        
        for (Map.Entry<String, Cell> entry: street.entrySet()) {
            entry.getValue().changeWeight(stress);
        }
    }


    public String addTaxi(TaxiCab pTaxi) {
        if(!taxisRecord.containsKey(pTaxi.getId()) && this.taxisActual.size() >= 1){
            increasing();
        }
        this.taxisActual.put(pTaxi.getId(), pTaxi);
        this.taxisRecord.put(pTaxi.getId(), pTaxi);
        
        return id;      
    }
    
    public boolean removeTaxi(TaxiCab pTaxi){
        
        if(this.taxisActual.containsKey(pTaxi.getId())){
            this.taxisActual.remove(pTaxi.getId());
            if(this.taxisActual.isEmpty()){
                
                this.overlord.send("decrease", vigilante.getId());
            }
            return false;
        }else{
            return true;
        }

    }
    
    public void increasing(){
        System.out.println("Subiendo");
         setStress(cost);
    }
    
    public void decreasing(){
       //System.out.print("Bajando id: " + this.id+ " stress: " + this.stress );
        if(!this.taxisActual.isEmpty()){
            this.overlord.send("keep", vigilante.getId());
        }
       
        if(this.stress >= relief){
            setStress(-1*relief);
             //System.out.println(" resultado: " + this.stress );
        }else{
            this.taxisRecord.clear();
            setStress(-this.stress);
            this.overlord.send("keep", vigilante.getId());
             //System.out.println("resultadoL " + this.stress );
        }   
    }
    
    public boolean areTaxis(){
        return !taxisActual.isEmpty();
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
    
    public boolean isStreet(String pKey){
        return street.containsKey(pKey);
    }

    public String getId() {
        return id;
    }

    public double getRelief() {
        return relief;
    }

    public void setRelief(double relief) {
        this.relief = relief;
    }
    
    
    
    @Override
    public String toString(){
        return street.toString();
    }
}

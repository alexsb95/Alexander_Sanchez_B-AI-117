
package map;

import algorithm.Cell;
import entities.TaxiCab;
import fsm.Decreasing;
import fsm.EventEmiter;
import fsm.FSM;
import fsm.Increasing;
import fsm.Keeping;
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
    private FSM vigilante;
    
    public StreetSection(HashMap<String, Cell> pStreet, EventEmiter pEmiter){
        street = pStreet;
        stress = 0;
        taxisRecord = new HashMap<>();
        taxisActual = new HashMap<>();
        cost = 0.25;
        setUpStates(pEmiter);
    }
    
    private void setUpStates(EventEmiter pEmiter){
        ArrayList<State> states = new ArrayList<>();
        states.add(new Increasing());
        states.add(new Keeping());
        states.add(new Decreasing());
        
        State currentState = new Keeping();
        String uniqueID = UUID.randomUUID().toString();
        vigilante = new FSM(this, states, currentState, uniqueID, pEmiter);
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
        this.stress = stress;
        
        for (Map.Entry<String, Cell> entry: street.entrySet()) {
            entry.getValue().changeWeight(stress);
        }
    }


    public void addTaxi(TaxiCab pTaxi) {
        this.taxisActual.put(pTaxi.getId(), pTaxi);
        this.taxisRecord.put(pTaxi.getId(), pTaxi);   
    }
    
    public void removeTaxi(TaxiCab pTaxi){
        this.taxisActual.remove(pTaxi.getId());
    }
    
    public void increasing(){
         setStress(this.stress + cost);
    }
    
    public void decreasing(){
        if(this.stress >= 0.1){
            setStress(this.stress - 0.1);
        }else{
            this.taxisRecord.clear();
            setStress(0);
        }   
    }
    
    public boolean areTaxis(){
        return !taxisActual.isEmpty();
    }
    
    
    @Override
    public String toString(){
        return street.toString();
    }
}

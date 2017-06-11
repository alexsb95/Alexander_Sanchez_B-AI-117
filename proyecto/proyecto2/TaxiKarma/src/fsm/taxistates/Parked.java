/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fsm.taxistates;

import entities.TaxiCab;
import fsm.FSM;
import fsm.State;

/**
 *
 * @author Alex
 */
public class Parked implements State{

    char objetive;
    
    @Override
    public boolean accepts(String pMessage, State pCurrentState) {
        if(pMessage.contains("-") && !"OnRoute".equals(pCurrentState.getState())){
            String [] splitMessage = pMessage.split("-");
            if("park".equals(splitMessage[0])){
                objetive = splitMessage[1].charAt(0);
                return true;
            }else{
                return false;
            }          
        }
        return false;
    }

    @Override
    public void onEnter(FSM pFsm) {
        TaxiCab taxi = (TaxiCab)pFsm.getOwner();
        taxi.park(objetive);
    }

    @Override
    public void onUpdate(FSM pFsm) { 
        TaxiCab taxi = (TaxiCab)pFsm.getOwner();
        taxi.followRoute();
    }

    @Override
    public void onExit(FSM pFsm) { }

    @Override
    public String getState() {
        return this.getClass().getSimpleName();
    }
    
}

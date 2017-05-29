/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fsm;

import entities.TaxiCab;

/**
 *
 * @author Alex
 */
public class OnRoute implements State{

    @Override
    public boolean accepts(String pMessage, State pCurrentState) {
        if("pickUp".equals(pMessage) && ("Searching".equals(pCurrentState.getState()) || "Parading".equals(pCurrentState.getState())) ){
            return true;
        }
        return false;
    }

    @Override
    public void onEnter(FSM fsm) {
        TaxiCab cab = (TaxiCab) fsm.getOwner();
        
    }

    @Override
    public void onUpdate(FSM fsm) { }

    @Override
    public void onExit(FSM fsm) { }

    @Override
    public String getState() {
        return this.getClass().getSimpleName();
    }
    
}

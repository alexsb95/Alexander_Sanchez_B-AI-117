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
public class Parading implements State{

    @Override
    public boolean accepts(String pMessage, State pCurrentState) {
        if("parade".equals(pMessage) && ("Occupied".equals(pCurrentState.getState()) || "Searching".equals(pCurrentState.getState()))){
            return true;
        }
        return false;
    }

    @Override
    public void onEnter(FSM pFsm) { }

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

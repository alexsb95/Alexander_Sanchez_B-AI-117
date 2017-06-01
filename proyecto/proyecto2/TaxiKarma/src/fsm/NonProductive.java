/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fsm;

import map.DayCycle;

/**
 *
 * @author Alex
 */
public class NonProductive implements State{

    @Override
    public boolean accepts(String pMessage, State pCurrentState) {
        if("benonproductive".equals(pMessage) && "Productive".equals(pCurrentState.getState())){
            return true;
        }
        return false;
    }

    @Override
    public void onEnter(FSM pFsm) {
        DayCycle day = (DayCycle)pFsm.getOwner();
        day.beNonProductive();
    }

    @Override
    public void onUpdate(FSM pFsm) {
        DayCycle day = (DayCycle)pFsm.getOwner();
        day.reduceTime();
    }

    @Override
    public void onExit(FSM pFsm) { }

    @Override
    public String getState() {
        return this.getClass().getSimpleName();
    }
    
}

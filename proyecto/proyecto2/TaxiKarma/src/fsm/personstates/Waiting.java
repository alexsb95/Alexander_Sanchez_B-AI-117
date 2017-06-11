/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fsm.personstates;

import fsm.FSM;
import fsm.State;

/**
 *
 * @author Alex
 */
public class Waiting implements State{

    @Override
    public boolean accepts(String pMessage, State pCurrentState) {
        if("wait".equals(pMessage) && ("Resting".equals(pCurrentState.getState()) || "Working".equals(pCurrentState.getState()))){
            return true;
        }
        return false;
    }

    @Override
    public void onEnter(FSM fsm) { }

    @Override
    public void onUpdate(FSM fsm) {  }

    @Override
    public void onExit(FSM fsm) {  }
    @Override
    public String getState(){
        return this.getClass().getSimpleName();
    }
    
}

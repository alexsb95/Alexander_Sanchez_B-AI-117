/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fsm.personstates;

import entities.Person;
import fsm.FSM;
import fsm.State;

/**
 *
 * @author Alex
 */
public class GettingReady implements State{
     @Override
    public boolean accepts(String pMessage, State currentState) {
        if("getready".equals(pMessage) && ("Working".equals(currentState.getState()) || "Resting".equals(currentState.getState()))){
            return true;
        }
        return false;
    }

    @Override
    public void onEnter(FSM fsm) {
        Person ppl = (Person)fsm.getOwner();
        ppl.setDelay();
    }

    @Override
    public void onUpdate(FSM fsm) { 
        Person ppl = (Person)fsm.getOwner();
        ppl.reduceTime();
    }

    @Override
    public void onExit(FSM fsm) {
        /*Person ppl = (Person)fsm.getOwner();
        ppl.setDestination(ppl.getWorkplace());*/
    }

    @Override
    public String getState() {
        return this.getClass().getSimpleName();
    }
    
}

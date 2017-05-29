/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fsm;

import entities.Person;

/**
 *
 * @author Alex
 */
public class Working implements State{

    @Override
    public boolean accepts(String pMessage, State currentState) {
        if("goWork".equals(pMessage) && "Waiting".equals(currentState.getState())){
            return true;
        }
        return false;
    }

    @Override
    public void onEnter(FSM fsm) {
        Person ppl = (Person)fsm.getOwner();
        ppl.startTime();
    }

    @Override
    public void onUpdate(FSM fsm) { 
        Person ppl = (Person)fsm.getOwner();
        ppl.reduceTime();
    }

    @Override
    public void onExit(FSM fsm) {
        Person ppl = (Person)fsm.getOwner();
        ppl.setDestination(ppl.getWorkplace());
    }

    @Override
    public String getState() {
        return this.getClass().getSimpleName();
    }
    
}

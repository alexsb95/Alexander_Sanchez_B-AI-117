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
public class Waiting implements State{

    @Override
    public boolean accepts(String pMessage, State pCurrentState) {
        if("wait".equals(pMessage)){
            return true;
        }
        return false;
    }

    @Override
    public void onEnter(FSM fsm) { }

    @Override
    public void onUpdate(FSM fsm) {  }

    @Override
    public void onExit(FSM fsm) {
        Person ppl = (Person)fsm.getOwner();
        ppl.setTimer();
    }
    @Override
    public String getState(){
        return this.getClass().getSimpleName();
    }
    
}

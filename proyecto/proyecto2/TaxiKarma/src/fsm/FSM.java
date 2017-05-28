/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fsm;

import java.util.ArrayList;

/**
 *
 * @author Alex
 */
public class FSM {
    private Object owner;
    private ArrayList<State> states;
    private State current;
    private String id;
    
    
    public FSM(Object owner, ArrayList<State> states, State current, String id, EventEmiter emiter){
        this.owner = owner;
        this.states = states;
        this.current = current;
        this.id = id;
        emiter.register(this);
    }
    
     public Object getOwner() {
        return owner;
    }

    public void setOwner(Object owner) {
        this.owner = owner;
    }

    public ArrayList<State> getStates() {
        return states;
    }

    public void setStates(ArrayList<State> states) {
        this.states = states;
    }

    public State getCurrent() {
        return current;
    }

    public void setCurrent(State current) {
        this.current = current;
    }
    
    public String getId(){
        return this.id;
    }
    
    public void onMessage(String pMessage){
        if(pMessage == "update"){
            if(current != null)
                current.onUpdate(this);
        }else{
            State newState = findAccept(pMessage);
            boolean accepted = newState != null && newState != current;
            if(accepted){
                if(current != null){
                    current.onExit(this);
                }
                current = newState;
                current.onEnter(this);
            }
        }
    }
    
    public State findAccept(String pMessage){
        for(State stt : states){
            if(stt.accepts(pMessage, current)){
                return stt;
            }
        }
        
        return null;
    }
    
       @Override
    public String toString(){
        return owner.toString() + " - " + current.getState();
    }
    
}

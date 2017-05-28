/*
 * To change this license header, choose License Headers in ProcurrentJect Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import fsm.EventEmiter;
import fsm.FSM;
import fsm.Resting;
import fsm.State;
import fsm.Waiting;
import fsm.Working;
import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author Alex
 */
public class Person {
    private int currentI;
    private int currentJ;
    private char workplace;    
    private char home;
    private char currentBlock;
    private char destination;
    private int timer;
    private FSM brain;
    private EventEmiter overlord;
    
    public Person (int pI, int pJ, char pWorkplace, char pHome, char pCurrentBlock, EventEmiter pEmiter){
        currentI = pI;
        currentJ = pJ;
        workplace = pWorkplace;
        home = pHome;
        currentBlock = pCurrentBlock;
        overlord = pEmiter;
        setUpStates(pEmiter);
    }
    
    public Person (int pI, int pJ){
        currentI = pI;
        currentJ = pJ;
    }
    
    private void setUpStates(EventEmiter pEmiter){
        ArrayList<State> states = new ArrayList<>();
        states.add(new Waiting());
        states.add(new Working());
        states.add(new Resting());
        
        State currentState;
        
        if(currentBlock == home){
            currentState = new Resting();
        }else if(currentBlock == workplace){
            currentState = new Working();
        }else{
            this.destination = this.home;
            currentState = new Waiting();
        }
        String uniqueID = UUID.randomUUID().toString();
        brain = new FSM(this, states, currentState, uniqueID, pEmiter);
    }

    
    public char getCurrentBlock() {
        return currentBlock;
    }

    public void setCurrentBlock(char currentBlock) {
        this.currentBlock = currentBlock;
    }

    public int getCurrentI() {
        return currentI;
    }

    public void setCurrentI(int currentI) {
        this.currentI = currentI;
    }

    public int getCurrentJ() {
        return currentJ;
    }

    public void setCurrentJ(int currentJ) {
        this.currentJ = currentJ;
    }

    public void setWorkplace(char workplace) {
        this.workplace = workplace;
    }

    public void setHome(char home) {
        this.home = home;
    }
    
    public void setDestination(String pDestination){
        switch(pDestination){
            case "goHome":
                this.destination = this.home;
            case "goWork":
                this.destination = this.workplace;
        }
    }
    
    public char getDestination(){
        return this.destination;
    }
    
    public FSM getBrain(){
        return brain;
    }
    
    public int getTimer() {
        return timer;
    }

    public void setTimer() {
        this.timer = 5;        
    }
    
    public void reduceTime(){
        this.timer--;
        if(this.timer <= 0){
            overlord.send("Wait", brain.getId());
        }
    }
    
    public String getCurrentState(){
        return brain.getCurrent().getState();
    }
            
    @Override
    public String toString(){
        return currentI + "-" + currentJ+ " crntblock: " + currentBlock + " wrk: " + workplace + " home: " + home;
    }
    
}

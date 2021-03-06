/*
 * To change this license header, choose License Headers in ProcurrentJect Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import fsm.EventEmiter;
import fsm.FSM;
import fsm.personstates.Resting;
import fsm.State;
import fsm.personstates.GettingReady;
import fsm.personstates.Waiting;
import fsm.personstates.Working;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;
import map.DayCycle;

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
    private int defaultTimer;
    private int timer;
    private FSM brain;
    private EventEmiter overlord;
    private DayCycle schedule;
    
    public Person (int pI, int pJ, char pWorkplace, char pHome, char pCurrentBlock, EventEmiter pEmiter, DayCycle pSchedule){
        currentI = pI;
        currentJ = pJ;
        workplace = pWorkplace;
        home = pHome;
        currentBlock = pCurrentBlock;
        overlord = pEmiter;
        setUpStates(pEmiter);
        schedule = pSchedule;
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
        states.add(new GettingReady());
        
        State currentState;
        
        if(currentBlock == home){
            currentState = new Resting();
            this.destination = this.workplace;
        }else if(currentBlock == workplace){
            currentState = new Working();
            this.destination = this.home;
        }else{
            this.destination = this.home;
            currentState = new Waiting();
        }
        String uniqueID = UUID.randomUUID().toString();
        brain = new FSM(this, states, currentState, uniqueID, pEmiter);
        System.out.println("Persona agregada: " + this.toString());
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
    
     public char getWorkplace() {
        return this.workplace;
    }

    public void setHome(char home) {
        this.home = home;
    }
    
    public char getHome() {
        return this.home;
    }
    
    public void setDestination(char pDestination){
        this.destination = pDestination;
    }
    
    public char getDestination(){
        return this.destination;
    }
    
    public FSM getBrain(){
        return brain;
    }
    
    public int getTimer() {
        return defaultTimer;
    }

    public void setTimer(int pAmout) {
        this.defaultTimer = pAmout;        
    }
    
    public void setDelay(){ 
        Random rnd = new Random();
        timer = rnd.nextInt((int)(schedule.getProductiveTime()*0.10));
        
        System.out.println("delay "+timer);
    }
    
    public void reduceTime(){
        this.timer--;
        if(this.timer <= 0){
            System.out.println("ESPERA");
            overlord.send("wait", brain.getId());
        }
    }
    
    public String getCurrentState(){
        return brain.getCurrent().getState();
    }
    
    public void update(){
        if(this.currentBlock == this.home){
            System.out.println("Resting::");
            overlord.send("gohome", brain.getId());
        }else if(this.currentBlock == this.workplace){
            System.out.println("Working::");
            overlord.send("gowork", brain.getId());
        }else{
            overlord.send("wait", brain.getId());
        }
    }
            
    @Override
    public String toString(){
        return currentI + "-" + currentJ+ " crntblock: " + currentBlock + " wrk: " + workplace + " home: " + home + " Status: " + getCurrentState();
    }
    
}

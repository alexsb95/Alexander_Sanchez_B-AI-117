
package map;

import fsm.EventEmiter;
import fsm.FSM;
import fsm.daystates.NonProductive;
import fsm.daystates.Productive;
import fsm.State;
import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author Alex
 */
public class DayCycle {
    private int dayUnit;
    private int clock;
    private int percentageWork;
    private int timer;
    private FSM circadian;
    private EventEmiter overlord;
    
    public DayCycle(int pDayUnit, int pPercentageWork, EventEmiter pOverlord){
        dayUnit = pDayUnit;
        clock = 0;
        percentageWork = pPercentageWork;
        overlord = pOverlord;
        setUpStates(pOverlord);
    }

    private void setUpStates(EventEmiter pEmiter){
        ArrayList<State> states = new ArrayList<>();
        states.add(new Productive());
        states.add(new NonProductive());
 
        State currentState = new NonProductive();
        String uniqueID = UUID.randomUUID().toString();
        circadian = new FSM(this, states, currentState, uniqueID, pEmiter);
    }
    
    public void beProductive(){
        timer = (int) (dayUnit * (float)(percentageWork /100.0));
        overlord.send("getReady");
    }
    
    public void beNonProductive(){
        timer = (int) (dayUnit * (float)((100 - percentageWork) /100.0));
        overlord.send("getReady");
    }
    
    public void reduceTime(){
        clock++;
        timer--;
        if(timer <= 0){
            
             //System.out.println("Entra");
            switch(circadian.getCurrent().getState() ){
                case "Productive":
                    overlord.send("benonproductive", circadian.getId());
                    break;
                case "NonProductive":
                    overlord.send("beproductive", circadian.getId());
                    break;
            }
        }
        
        if(clock >= dayUnit){
            clock = 0;
        }
    }
    
    public int getDayUnit() {
        return dayUnit;
    }

    public void setDayUnit(int pDayUnit) {
        this.dayUnit = pDayUnit;
    }
    
    public FSM getCircadian() {
        return circadian;
    }

    public void setCircadian(FSM circadian) {
        this.circadian = circadian;
    }
    
    public String getCurrentState(){
        return circadian.getCurrent().getState();
    }
    
    public int getTimer() {
        return timer;
    }
    
    public int getClock(){
        return clock;
    }
    
    public int getPercentageWork() {
        return percentageWork;
    }

    public void setPercentageWork(int percentageWork) {
        this.percentageWork = percentageWork;
    }
    
    public int getProductiveTime(){
        return (int) (dayUnit * (float)(percentageWork /100.0));
    }
    
    @Override
    public String toString(){
        return "FullDay: " + this.dayUnit  + " %%: " + this.percentageWork + " Time: " + this.timer;
    }

}

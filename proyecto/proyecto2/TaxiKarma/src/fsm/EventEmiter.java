
package fsm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author Alex
 */
public class EventEmiter {
    private ArrayList<FSM> listener;
    private HashMap<String, FSM> listenerById;
    private LinkedList<Event> queue;
    
    public EventEmiter(){
        listener = new ArrayList<FSM>();
        listenerById = new HashMap<String, FSM>();
        queue = new LinkedList<Event>();
    }
    
    public void update(){
        while(!queue.isEmpty()){
            Event element = queue.pop();
            element.getFsm().onMessage(element.getMessage());
        }
    }
    
    public void register(FSM pListener){
        listenerById.put(pListener.getId(), pListener);
        listener.add(pListener);
    }
    
    public void send(String pMessage, String pId){
        if(listenerById.containsKey(pId)){
            addToQueue(new Event(listenerById.get(pId),pMessage));
        }else{
            System.out.println("Error en el id del la maquina");
        }
    }
    
    public void send(String pMessage){
        for(int index = 0; index < listener.size(); index++){       
            addToQueue(new Event(listener.get(index), pMessage));
        }
    }
    
    private void addToQueue(Event pEvent){
        queue.add(pEvent);
    }
    
    
    public ArrayList<FSM> getListener() {
        return listener;
    }
}

class Event{
    private FSM fsm;
    private String message;
    
    public Event(FSM fsm, String message){
        this.fsm = fsm;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public FSM getFsm() {
        return fsm;
    }

    public void setFsm(FSM fsm) {
        this.fsm = fsm;
    }
    
    
}

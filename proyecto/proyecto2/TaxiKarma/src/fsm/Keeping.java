
package fsm;

import map.StreetSection;

/**
 *
 * @author Alex
 */
public class Keeping implements State{

    @Override
    public boolean accepts(String pMessage, State pCurrentState) {
        if("keep".equals(pMessage) && ("Increasing".equals(pCurrentState.getState()) || "Decreasing".equals(pCurrentState.getState())) ){
            return true;
        }
        return false;
    }

    @Override
    public void onEnter(FSM pFsm) { }

    @Override
    public void onUpdate(FSM pFsm) { }
    

    @Override
    public void onExit(FSM pFsm) { }

    @Override
    public String getState() {
        return this.getClass().getSimpleName();
    }        
}

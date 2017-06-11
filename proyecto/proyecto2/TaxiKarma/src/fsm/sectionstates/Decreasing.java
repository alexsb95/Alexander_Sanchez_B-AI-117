
package fsm.sectionstates;

import fsm.FSM;
import fsm.State;
import map.StreetSection;

/**
 *
 * @author Alex
 */
public class Decreasing implements State{

    @Override
    public boolean accepts(String pMessage, State pCurrentState) {
        if("decrease".equals(pMessage) && ("Increasing".equals(pCurrentState.getState()) || "Keeping".equals(pCurrentState.getState())) ){
            return true;
        }
        return false;
    }

    @Override
    public void onEnter(FSM pFsm) { }

    @Override
    public void onUpdate(FSM pFsm) { 
        StreetSection strSection = (StreetSection)pFsm.getOwner();
        strSection.decreasing();
    }

    @Override
    public void onExit(FSM pFsm) { }

    @Override
    public String getState() {
        return this.getClass().getSimpleName();
    }    
}

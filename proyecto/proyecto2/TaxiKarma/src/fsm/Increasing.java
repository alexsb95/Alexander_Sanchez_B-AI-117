
package fsm;

import map.StreetSection;

/**
 *
 * @author Alex
 */
public class Increasing implements State{

    @Override
    public boolean accepts(String pMessage, State pCurrentState) {
        if("increase".equals(pMessage) && ("Decreasing".equals(pCurrentState.getState()) || "Keeping".equals(pCurrentState.getState())) ){
            return true;
        }
        return false;
    }

    @Override
    public void onEnter(FSM pFsm) { }

    @Override
    public void onUpdate(FSM pFsm) { 
        StreetSection strSection = (StreetSection)pFsm.getOwner();
        strSection.increasing();
    }

    @Override
    public void onExit(FSM pFsm) { }

    @Override
    public String getState() {
        return this.getClass().getSimpleName();
    }
}
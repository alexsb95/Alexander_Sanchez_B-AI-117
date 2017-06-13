/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fsm;

/**
 *
 * @author Alex
 */
public interface State {
   /**
   * Si el estado reconoce el mensaje regresa verdadero.
   */
  public boolean accepts(String pMessage,State pCurrentState);
  /**
   * Se llama cada vez que el estado se activa
   */
  public void onEnter(FSM pFsm);
  /**
   * Si el estado esta activo se llama con cada ciclo
   */
  public void onUpdate(FSM pFsm);
  /*
   * Se llama cada vez que el estado se desactiva
   */
  public void onExit(FSM pFsm);
  public String getState();
}

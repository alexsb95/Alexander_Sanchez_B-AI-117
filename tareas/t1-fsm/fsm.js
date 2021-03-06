"use strict";

const eventEmiter = require('./event-emiter');
/**
 * Maquina de estados finita.
 */
module.exports = class Fsm {
  constructor(owner, states, current) {
    this._owner = owner;
    this._states = states;
    this._current = current;
    eventEmiter.register(this);
  }
  
  id() {
    return this._owner.id();
  }
  
  owner() {
    return this._owner;
  } 
  /**
   * Ciclo:
   * 1. Si el mensaje es "update"
   *   - mensaje especial para hacer update de los estados
   * 2. Si el mensaje no es "update"
   *   - recorrer los estados y ver si alguna lo reconoce
   *   - si lo reconoce activar el estado
   */
  onMessage(eventEmitter, event) {  
    console.log("MSG: "+event.msg);  
    if (event.msg === "update") {
      if (this._current) {
        this._current.onUpdate(eventEmitter, this);
      }
    } else {
      const state = this._states.find((s) => s.accepts(event, this._current));
      const accepted = state && state !== this._current;
      if (accepted) {
        if (this._current) {
          this._current.onExit(eventEmitter, this);
        }
        this._current = state;
        this._current.onEnter(eventEmitter, this);
      }
    }
  }  
}
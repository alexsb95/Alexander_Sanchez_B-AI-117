"use strict";

const State = require ('./state');
const Fsm = require ('./fsm');

/**
 * Estado Descansado
 */
class Rest extends State {
  accepts(event) {
    return event.msg === "Healed" || event.msg === "OffArea" ;
  }
  
  onEnter(eventEmitter, fsm) {
    fsm.owner().on();
    console.log('${fsm.owner().id()} - entrando a descansar...');
  }
  
  onUpdate(eventEmitter, fsm) {
    console.log('${fsm.owner().id()} - descansado');
  }
}

/**
 * Estado Molesto
 */
class Irritated extends State {
  
  accepts(event) {
     return event.msg === "Hurt" || event.msg === "OnArea";
  }
  
  onEnter(eventEmitter, fsm) {
    fsm.owner().on();
    console.log('${fsm.owner().id()} - entrando a molesto....');
  }
  
  onUpdate(eventEmitter, fsm) {
    console.log('${fsm.owner().id()} - molesto');
  }
}

/**
 * Los estados pueden ser de tipo Singleton.
 */
const STATES = [new Rest(), new Irritated()];

/**
 * Agente
 */
module.exports = class NPC {
 
  constructor(id) {
    this._id = id;
    this._fsm = new Fsm(this, STATES);
  }

  id() {
    return this._id;
  }
  /**
   * No mucho q ver, pero si fuera un juego por ejemplo el agente estaria
   * alumbrando el area.
   */

  on() {
    this.status = "on";
  }

  off() {
    this.status = "off";
  }
}



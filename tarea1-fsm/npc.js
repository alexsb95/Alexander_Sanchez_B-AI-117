"use strict";

const State = require ('./state');
const Fsm = require ('./fsm');

/**
 * Estado Descansado
 */
class Rested extends State {
  accepts(event, currentState) {
    return (currentState instanceof Irritated) && (event.msg === "Healed" || event.msg === "OffArea");
  }
  
  onEnter(eventEmitter, fsm) {
    fsm.owner().on();
    console.log('${fsm.id()} - entrando a descansar...');
  }
  
  onUpdate(eventEmitter, fsm) {
    console.log("${fsm.id()} - descansado");
  }
}

/**
 * Estado Molesto
 */
class Irritated extends State {
  
  accepts(event, currentState) {
     return (currentState instanceof Rested && event.msg === "OnArea") || (currentState instanceof Angry && event.msg === "Healed");
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
 * Estado Enojado
 */
class Angry extends State {
  
  accepts(event, currentState) {
     return ((currentState instanceof Rested || currentState instanceof Irritated) && event.msg === "Hurt") || (currentState instanceof Furious && event.msg === "Healed");
  }
  
  onEnter(eventEmitter, fsm) {
    fsm.owner().on();
    console.log('${fsm.owner().id()} - entrando a enojado....');
  }
  
  onUpdate(eventEmitter, fsm) {
    console.log('${fsm.owner().id()} - enojado');
  }
}


/**
 * Estado Furioso
 */
class Furious extends State {
  
  accepts(event, currentState) {
     return (currentState instanceof Furious || currentState instanceof Angry) && event.msg === "Hurt";
  }
  
  onEnter(eventEmitter, fsm) {
    fsm.owner().on();
    console.log('${fsm.owner().id()} - entrando a furioso....');
  }
  
  onUpdate(eventEmitter, fsm) {
    console.log('${fsm.owner().id()} - furioso');
  }
}

/**
 * Los estados pueden ser de tipo Singleton.
 */
const STATES = [new Rested(), new Irritated(), new Angry(), new Furious()];

/**
 * Agente
 */
module.exports = class NPC {
 
  constructor(id) {
    this._id = id;
    this._fsm = new Fsm(this, STATES, new Rested());
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



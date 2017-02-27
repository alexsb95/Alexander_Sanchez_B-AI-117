const eventEmiter = require ('./event-emiter');
const NPC = require ('./npc');

/**
 * Ciclo principal
 */
setInterval(() => {
  eventEmiter.update();
  eventEmiter.send("update");
}, 100);

/**
 * Crear una nuevo bombillo. El agente segrega al sistema solo
 */
new NPC("l1");


/**
 * Condigo de prueba.
 */
var status = false;
setInterval(() => {
    console.log('Ciclo 1');
      if (status) {
    eventEmiter.send("Healed");
    status = false;
  } else {
    eventEmiter.send("Hurt");
    status = true;
  }

}, 1000)
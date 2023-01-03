/*
    Projet SAE
    Crouskie WEB
    https://forge.univ-lyon1.fr/p2100126/crouskie-sa

    Elliot CASNA, Anne PASSELEGUE, Thomas WARRIER, Marc BERIOT, Virgile MENARD-BEDIANT
*/

//Méthodes POST et GET sous format JSON pour l'API interne du site

const headers = { "Content-Type": "application/json" };

function postJson(url, body, callback) {
  fetch(url, { method: "POST", headers, body: JSON.stringify(body) })
    .then((res) => {
      if (!res.ok) throw new Error(`httpStatusCode:${res.status}`);
      res.json().then((json) => callback(json));
    })
    .catch((error) => { callback({ success : false}); });
}

function getJson(url, callback) {
  fetch(url, { method: "GET", headers })
    .then((res) => {
      if (!res.ok) throw new Error(`httpStatusCode:${res.status}`);
      if (res.status === 204) return callback(null);
      res.json().then((json) => callback(json));
    })
    .catch((error) => { callback({ success : false }); });
}

// Méthodes pour activer ou désactiver la capacité à naviger dans une page WEB

function disableScroll() {
  // On récupère la position courante
  scrollTop = window.pageYOffset || document.documentElement.scrollTop;
  scrollLeft = window.pageXOffset || document.documentElement.scrollLeft,
  // On redéfinit l'évènement onscroll pour qu'elle reste figée
  window.onscroll = function() {
      window.scrollTo(scrollLeft, scrollTop);
  };
}

function enableScroll() {
  window.onscroll = function() {};
}

// Méthode PopUp pour afficher une alerte à l'utilisateur

var activePopUp = null

/**
 * 
 * @param {*} type Définit le type de la PopUp (Information (0),Réussite (1), Avertissement (2), Erreur (3))
 * @param {*} title Définit le titre de la PopUp
 * @param {*} message Définit le message de la PopUp
 */
function PopUp(type,title,message) {
  if (activePopUp) {
    activePopUp.remove();
  }

  const container = document.createElement('div');
  container.id = 'PopUp';
  container.classList.add('flex','center');

  const card = document.createElement('div');
  card.classList.add('rounded','flex','column','shadow');
  container.appendChild(card);

  const icon_container = document.createElement('div');
  icon_container.classList.add('fill-w','flex','center');
  card.appendChild(icon_container);

  const titleElement = document.createElement('h2');
  titleElement.innerText = title;
  card.appendChild(titleElement);

  const messageElement = document.createElement('p');
  messageElement.innerText = message;
  card.appendChild(messageElement);

  const closeButton = document.createElement('a');
  closeButton.innerText = 'Fermer';
  closeButton.classList.add('circle');
  closeButton.addEventListener('click',()=>{
    activePopUp.remove();
    activePopUp = null;
  });
  card.appendChild(closeButton);

  switch (type) {
    case 0:
      icon_container.style = 'background-color: #03a9f4';
      closeButton.style = 'background-color: #03a9f4';
      icon_container.innerHTML = '<iconify-icon icon="mdi:information-outline" style="color: white"></iconify-icon>';
      break;
    case 1:
      icon_container.style = 'background-color: #82E105';
      closeButton.style = 'background-color: #82E105';
      icon_container.innerHTML = '<iconify-icon icon="mdi:success-circle-outline" style="color: white"></iconify-icon>';
      break;
    case 2:
      icon_container.style = 'background-color: #ffb74d';
      closeButton.style = 'background-color: #ffb74d';
      icon_container.innerHTML = '<iconify-icon icon="material-symbols:warning-outline-rounded" style="color: white"></iconify-icon>';
      break;
    case 3:
      icon_container.style = 'background-color: #E10512';
      closeButton.style = 'background-color: #E10512';
      icon_container.innerHTML = '<iconify-icon icon="material-symbols:error-outline-rounded" style="color: white"></iconify-icon>';
      break;
    default:
      break;
  }

  document.body.appendChild(container);
  activePopUp = container;
}
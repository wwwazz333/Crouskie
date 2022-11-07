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
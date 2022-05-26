"use strict";

// Runs on init
// Reads all cars and updates global make and model arrays
function readAll() {
  fetch(`http://localhost:8080/car/readAll`)
    .then((response) => {
      if (response.status !== 200) {
        console.error(`status: ${response.status}`);
        return;
      }
      response.json().then((data) => {
        addListItems(data, `all`);
        populateReadBySelect(data);
      });
    })
    .catch((error) => console.error(`Request failed: ${error}`));
}

// Create car function
function createCar(make, model, wheels) {
  fetch(`http://localhost:8080/car/create`, {
    method: "post",
    headers: { "Content-type": "application/json" },
    body: JSON.stringify({ model: model, wheels: wheels, make: make }),
  })
    .then((response) => response.json())
    .then((data) => {
      console.table(data), readAll();
    })
    .then()
    .catch((error) => console.error(`Request failed: ${error}`));
}

function deleteCar(id) {
  fetch(`http://localhost:8080/car/delete/${id}`, {
    method: "delete",
  })
    .then((response) => response.json())
    .catch((error) => console.error(`Request failed: ${error}`));
}

function readBy(readByOption, option) {
  fetch(`http://localhost:8080/car/readBy/${readByOption}:${option}`)
    .then((response) => {
      if (response.status !== 200) {
        console.error(`status: ${response.status}`);
        return;
      }
      response.json().then((data) => {
        addListItems(data, `read-by`);
      });
    })
    .catch((error) => console.error(`Request failed: ${error}`));
}

function addListItems(data, table) {
  let newTableBody = document.createElement("tbody");
  newTableBody.id = `${table}-cars-body`;
  for (let i in data) {
    let tableRow = document.createElement(`tr`);
    newTableBody.appendChild(tableRow);
    tableRow.id = `row${data[i][`id`]}`;
    console.table(data);
    for (let d in data[i]) {
      let tableData = document.createElement(`td`);
      tableData.appendChild(document.createTextNode(`${data[i][d]}`));
      tableRow.appendChild(tableData);
    }
  }
  let oldTableBody = document.getElementById(`${table}-cars-body`);
  oldTableBody.parentNode.replaceChild(newTableBody, oldTableBody);
}

function populateReadBySelect(data) {
  // Unpopulate existing options from select
  let oldSelect = document.getElementById(`sub-options`);
  for (let i = oldSelect.options.length - 1; i >= 0; i--) {
    oldSelect.remove(i);
  }
  let mainSelect = document.getElementById(`main-options`);
  let select = document.getElementById(`sub-options`);
  //   console.log(mainSelect.options[mainSelect.selectedIndex].value);
  if (mainSelect.options[mainSelect.selectedIndex].value == `id`) {
    for (let i in data) {
      let option = document.createElement(`option`);
      option.value = `${data[i][`id`]}`;
      option.appendChild(document.createTextNode(`${data[i][`id`]}`));
      select.appendChild(option);
    }
  } else if (mainSelect.options[mainSelect.selectedIndex].value == `make`) {
    // Generate list of all makes in the database
    let makes = getMakes(data);
    // Iterate over array
    for (let make of makes) {
      // Create a new option for each make in the database
      let option = document.createElement(`option`);
      option.value = `${make}`;
      option.appendChild(document.createTextNode(`${make}`));
      select.appendChild(option);
    }
  } else if (mainSelect.options[mainSelect.selectedIndex].value == `model`) {
    // Generate list of all makes in the database
    let models = getModels(data);
    // Iterate over array
    for (let model of models) {
      // Create a new option for each make in the database
      let option = document.createElement(`option`);
      option.value = `${model}`;
      option.appendChild(document.createTextNode(`${model}`));
      select.appendChild(option);
    }
  }
}

function getMakes(data) {
  let makes = [];
  for (let i in data) {
    let make = data[i][`make`];
    if (!makes.includes(make)) {
      makes.push(make);
      console.log(make);
    }
  }
  return makes;
}

function getModels(data) {
    let models = [];
    for (let i in data) {
      let model = data[i][`model`];
      if (!models.includes(model)) {
        models.push(model);
        console.error(model);
      }
    }
    return models;
  }

(function () {
  document.addEventListener("DOMContentLoaded", readAll);
})();

"use strict";

// Global variable for the possible datalists
let datalists = [];
let activeDatalist;
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
        /*global*/ datalists = generateDatalists(data);
        changeDatalist(activeDatalist);
      });
    })
    .catch((error) => console.error(`Request failed: ${error}`));
}

// Create car function
function createCar() {
  let make = document.getElementById(`get-make`).value;
  let model = document.getElementById(`get-cost`).value;
  let cost = document.getElementById(`get-cost`).value;
  fetch(`http://localhost:8080/car/create`, {
    method: `post`,
    headers: { "Content-type": "application/json" },
    body: JSON.stringify({ model: model, cost: cost, make: make }),
  })
    .then((response) => response.json())
    .then((data) => {
      console.table(data);
      readAll();
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

function readBy() {
  let mainSelect = document.getElementById(`main-options`);
  let mainOption = mainSelect.options[mainSelect.selectedIndex].value;
  let subOption = document.getElementById(`sub-options-value`).value;

  fetch(`http://localhost:8080/car/readBy/${mainOption}:${subOption}`)
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
    // console.table(data);
    for (let d in data[i]) {
      let tableData = document.createElement(`td`);
      if (d !== `cost`) {
        tableData.appendChild(document.createTextNode(`${data[i][d]}`));
      } else {
        tableData.appendChild(document.createTextNode(`${data[i][d]}`));
      }
      tableRow.appendChild(tableData);
    }
  }
  let oldTableBody = document.getElementById(`${table}-cars-body`);
  oldTableBody.parentNode.replaceChild(newTableBody, oldTableBody);
}

function changeDatalist() {
  // Clear any user inputs
  document.getElementById(`sub-options-value`).value = ``;
  // Select the user's option from the DOM
  let select = document.getElementById(`main-options`);
  let option = select.options[select.selectedIndex].value;
  console.log(option);
  if (activeDatalist !== option) {
    /*global*/ activeDatalist = option;
    let oldDatalist = document.getElementById(`sub-options`);
    let datalistDestination = oldDatalist.parentElement;
    let newDatalist = datalists[option];
    oldDatalist.remove();
    datalistDestination.appendChild(newDatalist);
  }
}

// Function generates a list of datalists based on json options
function generateDatalists(data) {
  // Create possible datalists
  let idDatalist = document.createElement(`datalist`);
  let makeDatalist = document.createElement(`datalist`);
  let modelDatalist = document.createElement(`datalist`);

  // Give datalists an id
  idDatalist.id = `sub-options`;
  makeDatalist.id = `sub-options`;
  modelDatalist.id = `sub-options`;

  for (let i in data) {
    let idOption = document.createElement(`option`);
    idOption.value = `${data[i][`id`]}`;
    idOption.appendChild(document.createTextNode(`${data[i][`id`]}`));
    idDatalist.appendChild(idOption);
  }
  for (let make of getMakes(data)) {
    let makeOption = document.createElement(`option`);
    makeOption.value = `${make}`;
    makeOption.appendChild(document.createTextNode(`${make}`));
    makeDatalist.appendChild(makeOption);
  }
  for (let model of getModels(data)) {
    let modelOption = document.createElement(`option`);
    modelOption.value = `${model}`;
    modelOption.appendChild(document.createTextNode(`${model}`));
    modelDatalist.appendChild(modelOption);
  }
  console.log(idDatalist);
  console.log(makeDatalist);
  console.log(modelDatalist);
  return {
    id: idDatalist,
    make: makeDatalist,
    model: modelDatalist,
  };
}

function getMakes(data) {
  let makes = [];
  for (let i in data) {
    let make = data[i][`make`];
    if (!makes.includes(make)) {
      makes.push(make);
      //   console.log(make);
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
      //   console.error(model);
    }
  }
  return models;
}

(function () {
  document.addEventListener("DOMContentLoaded", readAll);
})();

///////////////////////////////////////////////////////

/*
function populateReadByDatalist(data) {
  // Unpopulate existing options from select

  let datalists = generateDatalists(data);

  let idOptions;

  let oldDataList = document.getElementById(`sub-options`);

  console.log(oldDataList.options);
  if (oldDataList.children.length > 0) {
    for (let i = oldDataList.options.length; i >= 0; i--) {
      oldDataList.removeChild(oldDataList.options[i]);
      // ERRORS!!!!!!!!!!
    }
  }

  let mainSelect = document.getElementById(`main-options`);
  let datalist = document.getElementById(`sub-options`);

  console.log(datalist);

  if (mainSelect.options[mainSelect.selectedIndex].value == `id`) {
    for (let i in data) {
      let option = document.createElement(`option`);
      option.value = `${data[i][`id`]}`;
      option.appendChild(document.createTextNode(`${data[i][`id`]}`));
      datalist.appendChild(option);
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
      datalist.appendChild(option);
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
      datalist.appendChild(option);
    }
  }
}
*/

///////////////////////////////////////////////////////

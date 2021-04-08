
var Data = [
]


function CreateTableFromJSON() {

    //Get Data from API
    fetch("http://localhost:8090/genre").then(res => {
        return res.json();
    })
        .then((data) => {
            console.log(data)
            Data = data;
            console.log(Data)

            //
            // EXTRACT VALUE FOR HTML HEADER. 
            // ('Deal ID', 'Deal Name', 'Category' and 'Price')
            var col = ['Name', 'Categories'];

            // CREATE DYNAMIC TABLE.
            var table = document.createElement("table");
            table.setAttribute('class', 'table table-striped')

            // CREATE HTML TABLE HEADER ROW USING THE EXTRACTED HEADERS ABOVE.

            var tr = table.insertRow(-1);                   // TABLE ROW.

            for (var i = 0; i < col.length; i++) {
                var th = document.createElement("th");      // TABLE HEADER.
                th.innerHTML = col[i];
                tr.appendChild(th);
            }
            var th = document.createElement("th");      // TABLE HEADER.
            th.innerHTML = 'Action';
            tr.appendChild(th);
            var th = document.createElement("th");
            th.innerHTML = 'Action';
            tr.appendChild(th);

            // ADD JSON DATA TO THE TABLE AS ROWS.
            console.log(47)
            console.log(Data)
            for (var i = 0; i < Data.length; i++) {
                console.log(Data[i])
                tr = table.insertRow(-1);


                var tabCell = tr.insertCell(-1);
                console.log(Data[i])
                tabCell.innerHTML = Data[i].name;

                //subtable
                var tabcell2 = tr.insertCell(-1);
                var subCategories = Data[i].categories;
                console.log('sub')
                console.log(subCategories)
                var table1 = document.createElement("table");
                for (j = 0; j < subCategories.length; j++) {
                    console.log(subCategories[j].name)
                    var tr1 = table1.insertRow(-1);
                    var tabCell1 = tr1.insertCell(-1);
                    tabCell1.innerHTML = subCategories[j].name;
                }
                console.log(table1)
                tabcell2.appendChild(table1);


                // Insert Extra Cell for the Delete Icon
                //TODO: Complete this
                var tabCell = tr.insertCell(-1);
                tabCell.innerHTML = '<button onclick="initiateUpdate(' + Data[i].id + ')"> <img src="images/4.png" style="height:30px;max-width:20px;"> </button>'
                var tabCell = tr.insertCell(-1);
                tabCell.innerHTML = '<button onclick="DeleteRow(' + Data[i].id + ')"> <img src="images/trash.svg" style="height:30px;max-width:20px;"> </button>'

            }

            // FINALLY ADD THE NEWLY CREATED TABLE WITH JSON DATA TO A CONTAINER.
            var divContainer = document.getElementById("showData");
            divContainer.innerHTML = "";
            divContainer.appendChild(table);
        })
}

var optionId = 0;
function AddNewCategory() {
    var select = document.createElement('select');
    select.setAttribute('class', 'form-select')
    select.setAttribute('class', 'select-margin')
    select.id = 'selectid' + optionId;
    console.log(select.id)
    optionId++;
    //Get Data from API
    var categories = []
    fetch("http://localhost:8090/category").then(res => {
        return res.json();
    })
        .then((data) => {
            categories = data;
            console.log(categories)
            var option;
            for (i = 0; i < categories.length; i++) {
                option = document.createElement('option');
                option.value = categories[i].id;
                option.textContent = categories[i].name;
                select.appendChild(option);
            }
            var divContainer = document.getElementById("addModalSelector");
            divContainer.appendChild(select);
        })
}

function AddNewDeal() {
    var name = document.getElementById("genreName").value;
    var id = document.getElementById("genreId").value;

    if (name == "") {
        document.getElementById("nameHelp").innerText = "Please Enter Name!"
    }
    else {
        if (id === '') {
            InsertRow(name);
        }
        else {
            console.log('hit');
            updateRow(id, name);
        }
        document.getElementById("genreName").value = "";
        document.getElementById("genreId").value = "";
        document.getElementById("nameHelp").innerText = "";
        document.getElementById("addModalSelector").innerHTML = "";
        optionId = 0;
    }
}

function InsertRow(name) {
    var categories = []
    for (i = 0; i < optionId; i++) {
        var sel = document.getElementById('selectid' + i);
        var categoryId = sel.options[sel.selectedIndex].value;
        var categoryName = sel.options[sel.selectedIndex].text;
        var Categorydata = { 'id': categoryId, 'name': categoryName };
        categories.push(Categorydata)
    }
    console.log(categories)
    let genreData = { 'name': name, 'categories': categories }
    console.log(genreData);
    fetch("http://localhost:8090/genre", {
        method: "POST",
        headers: new Headers({ 'content-type': 'application/json' }),
        body: JSON.stringify(genreData)
    }).then(res => {
        alert("Request complete!");
        $('#exampleModal').modal('hide')
        CreateTableFromJSON();
    });
}

function initiateUpdate(id) {
    console.log(Data)
    for (var i = 0; i < Data.length; i++) {

        if (Data[i].id === parseInt(id)) {
            document.getElementById("genreName").value = Data[i].name;
            document.getElementById("genreId").value = Data[i].id;
            console.log(Data[i]);
            var genrecategories = Data[i].categories
            console.log(Data[i].categories)
            //Get Data from API
            var categories = []
            fetch("http://localhost:8090/category").then(res => {
                return res.json();
            })
                .then((data) => {
                    categories = data;
                    console.log(genrecategories)
                    console.log(categories)
                    var option;
                    for (i = 0; i < genrecategories.length; i++) {
                        var select = document.createElement('select');
                        select.setAttribute('class', 'form-select')
                        select.setAttribute('class', 'select-margin')
                        select.id = 'selectid' + optionId;
                        console.log(select.id)
                        optionId++;
                        console.log(select)
                        for (j = 0; j < categories.length; j++) {
                            option = document.createElement('option');
                            option.value = categories[j].id;
                            option.textContent = categories[j].name;
                            console.log(categories[j].id)
                            console.log(genrecategories[i].id)
                            if (categories[j].id == genrecategories[i].id) {
                                console.log(option.value)
                                option.selected = 'selected'
                            }
                            select.appendChild(option);
                        }
                        var divContainer = document.getElementById("addModalSelector");
                        divContainer.appendChild(select);
                    }

                    $('#exampleModal').modal('show')
                })
        }
    }
}

function updateRow(id, name) {
    var categories = []
    for (i = 0; i < optionId; i++) {
        var sel = document.getElementById('selectid' + i);
        var categoryId = sel.options[sel.selectedIndex].value;
        var categoryName = sel.options[sel.selectedIndex].text;
        var Categorydata = { 'id': categoryId, 'name': categoryName };
        categories.push(Categorydata)
    }
    console.log(categories)
    let genreData = { 'id': id, 'name': name, 'categories': categories }
    console.log(genreData);
    fetch("http://localhost:8090/genre", {
        method: "POST",
        headers: new Headers({ 'content-type': 'application/json' }),
        body: JSON.stringify(genreData)
    }).then(res => {
        alert("Request complete!");
        $('#exampleModal').modal('hide')
        CreateTableFromJSON();
    });
}

function DeleteRow(genreId) {

    fetch("http://localhost:8090/deleteGenre", {
        method: "POST",
        headers: new Headers({ 'content-type': 'application/json' }),
        body: genreId
    }).then(res => {
        alert("Request complete!");
        $('#exampleModal').modal('hide')
        CreateTableFromJSON();
    });
}

function RemoveCategory() {
    var sel = document.getElementById('selectid' + --optionId);
    console.log(sel.id)
    sel.remove();
}

function reset() {
    document.getElementById("genreName").value = "";
    document.getElementById("genreId").value = "";
    document.getElementById("nameHelp").innerText = "";
    document.getElementById("addModalSelector").innerHTML = "";
    optionId = 0;
}


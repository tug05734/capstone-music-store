
var Data = [
]


var currentCategoryId = Data.length;



function CreateTableFromJSON() {    
    
    //Get Data from API
    fetch("http://localhost:8090/category").then(res => {
          return res.json();
        })
        .then((data) => {
            console.log(data)
            Data = data;
            console.log(Data)
        
    //
    // EXTRACT VALUE FOR HTML HEADER. 
    // ('Deal ID', 'Deal Name', 'Category' and 'Price')
    var col = ['Name'];

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

        for (var j = 0; j < col.length; j++) {
            var tabCell = tr.insertCell(-1);
            console.log(Data[i])
            tabCell.innerHTML = Data[i].name;
        }
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

function AddNewDeal() {
    var name = document.getElementById("categoryName").value;
    var id = document.getElementById("categoryId").value;

    if(name == ""){
        document.getElementById("nameHelp").innerText = "Please Enter Name!"
    }
    else{
        if(id === ''){
            InsertRow(name);
            }
            else{
                console.log('hit');
            updateRow(id, name);
            }
    document.getElementById("categoryName").value = "";
    document.getElementById("categoryId").value = "";
    document.getElementById("nameHelp").innerText = "";
    }
}

function InsertRow(name) {
    let data = {'name': name}
    fetch("http://localhost:8090/category", {
        method: "POST",
        headers: new Headers({ 'content-type': 'application/json' }),
        body: JSON.stringify(data)
      }).then(res => {
        alert("Request complete!");
        $('#exampleModal').modal('hide')
    CreateTableFromJSON();
      });
}

function initiateUpdate(id){
    for( var i = 0; i < Data.length; i++){ 
    
        if ( Data[i].id === parseInt(id)) { 
            document.getElementById("categoryName").value = Data[i].name;
            document.getElementById("categoryId").value = Data[i].id;
            $('#exampleModal').modal('show');
        }
    }
}

function updateRow(id, name){
    console.log(id)
    var data = {'id': id, 'name': name}
    fetch("http://localhost:8090/category", {
        method: "POST",
        headers: new Headers({ 'content-type': 'application/json' }),
        body: JSON.stringify(data)
      }).then(res => {
        alert("Request complete!");
        $('#exampleModal').modal('hide')
    CreateTableFromJSON();
      });
}

function DeleteRow(vendorId) {
     
    fetch("http://localhost:8090/deleteCategory", {
        method: "POST",
        headers: new Headers({ 'content-type': 'application/json' }),
        body: vendorId
      }).then(res => {
        alert("Request complete!");
        $('#exampleModal').modal('hide')
    CreateTableFromJSON();
      });
    CreateTableFromJSON();
}


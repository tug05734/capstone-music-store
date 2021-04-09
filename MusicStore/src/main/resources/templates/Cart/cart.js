
//global variables
var cartData = []
userId = 1;
var TotalAmount = 0;
function CreateCartFromJSON() {
    fetch("http://localhost:8090/getCartByUserId", {
        method: "POST",
        headers: new Headers({ 'content-type': 'application/json' }),
        body: userId
    }).then(res => {
        console.log(res)
        return res.json();
    }).then((data) => {
        cartData = data;
        CreateLineItems(cartData);
    })
}

function CreateLineItems(items) {
    var list = document.createElement("ul");
    TotalAmount = 0;
    list.setAttribute("class", "list-group")
    for (i = 0; i < items.length; i++) {
        TotalAmount = TotalAmount + items[i].price;
        var anchor = document.createElement("a");
        anchor.href = "#";
        anchor.innerText = items[i].product.productName;
        anchor.setAttribute("style", "margin-left:20px")

        var image = document.createElement("img");
        image.setAttribute("class", "cart-image")
        image.src = "images/3.png";

        var span = document.createElement("span");
        span.setAttribute("class", "badge badge-primary badge-pill")
        span.setAttribute("style", "margin-left:50px; margin-right:50px")
        span.innerText = items[i].quantity;

        var text = document.createElement("b");
        text.innerText = "Price: " + items[i].price;
        text.setAttribute("style", "margin-left:200px")

        var deleteIcon = document.createElement("img");
        deleteIcon.setAttribute("class", "delete-image")
        deleteIcon.setAttribute("onclick", "deleteCartItem(" + items[i].id + ")")
        deleteIcon.src = "images/delete.png";

        var elem = document.createElement("li");
        elem.setAttribute("class", "list-group-item shadow p-3 mb-5 bg-white rounded")
        elem.appendChild(image);
        elem.appendChild(anchor);
        elem.appendChild(text);
        elem.appendChild(span);

        elem.appendChild(deleteIcon);
        list.appendChild(elem);
    }
    var divContainer = document.getElementById("showData");
    divContainer.innerHTML = "";
    divContainer.appendChild(list);
    var h2 = document.getElementById("totalAmount");
    h2.innerText = "Total Amount: "+ TotalAmount
}

function deleteCartItem(itemId) {
    fetch("http://localhost:8090/deleteCartById", {
        method: "POST",
        headers: new Headers({ 'content-type': 'application/json' }),
        body: itemId
    }).then(res => {
        console.log(res)
        CreateCartFromJSON();
        alert("Item removed from cart Successfully!")
    })
}

function OrderNow() {
    //To be implemented
    $('#exampleModal').modal('show')

    var col = ['Product', 'Quantity', 'Price', 'Total'];

    // CREATE DYNAMIC TABLE.
    var table = document.createElement("table");
    table.setAttribute('class', 'table table-striped table-hover')

    // CREATE HTML TABLE HEADER ROW USING THE EXTRACTED HEADERS ABOVE.

    var tr = table.insertRow(-1);                   // TABLE ROW.

    for (var i = 0; i < col.length; i++) {
        var th = document.createElement("th");      // TABLE HEADER.
        th.innerHTML = col[i];
        tr.appendChild(th);
    }
    for (i = 0; i < cartData.length; i++) {
        tr = table.insertRow(-1);
        var tabCell = tr.insertCell(-1);
        tabCell.innerHTML = cartData[i].product.productName;

        tabCell = tr.insertCell(-1);
        tabCell.innerHTML = cartData[i].quantity;

        tabCell = tr.insertCell(-1);
        tabCell.innerHTML = cartData[i].product.price;

        tabCell = tr.insertCell(-1);
        tabCell.innerHTML = cartData[i].price;
    }

    var divContainer = document.getElementById("productList");
    divContainer.innerHTML = "";
    divContainer.appendChild(table);
    var h4 = document.getElementById("totalAmountModal");
    h4.innerText = "Total Amount: "+ TotalAmount
}

function PlaceOrder(){
    console.log(cartData)
    console.log(cartData.user)
    var data = {"user": cartData[0].user, "amount": TotalAmount, "cart": cartData, "status": "confirmed"}
    console.log(data)
    fetch("http://localhost:8090/order", {
        method: "POST",
        headers: new Headers({ 'content-type': 'application/json' }),
        body: JSON.stringify(data)
    }).then(res => {
        console.log(res)
        return res.json();
    }).then((data) => {
        console.log(data)
        document.getElementById("modalBody").innerHTML="";
        $('#exampleModal').modal('hide')
        alert('order placed successfully!')
        CreateCartFromJSON();
    })
}
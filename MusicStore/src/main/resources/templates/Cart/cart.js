
//global variables
var cartData = []
userId = 1;
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
    list.setAttribute("class", "list-group")
    for (i = 0; i < items.length; i++) {
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
        deleteIcon.setAttribute("onclick", "deleteCartItem("+items[i].id+")")
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
}

function deleteCartItem(itemId){
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

function OrderNow(){
    //To be implemented
    alert('order placed successfully!')
}
var orderData = []
userId = 1;
function CreateCartFromJSON() {
        fetch("http://localhost:8090/orderByUserId", {
            method: "POST",
            headers: new Headers({ 'content-type': 'application/json' }),
            body: userId
        }).then(res => {
            console.log(res)
            return res.json();
        }).then((data) => {
            orderData = data;
            CreateLineItems(orderData);
        })
}

function CreateLineItems(order) {
    console.log(order)
    var accordian = document.createElement("div");
    accordian.setAttribute("class", "accordion")
    accordian.setAttribute("id", "accordionExample")

    for (i = 0; i < order.length; i++) {
        var card = document.createElement("div");
        card.setAttribute("class", "card")
        card.setAttribute("style", "margin-bottom:20px")
        accordian.appendChild(card)

        var cardheader = document.createElement("div");
        cardheader.setAttribute("class", "card-header")
        cardheader.setAttribute("id", "heading" + i)
        card.appendChild(cardheader);

        var h2 = document.createElement("h2");
        h2.setAttribute("class", "mb-0")
        cardheader.appendChild(h2);

        var button = document.createElement("button");
        button.setAttribute("type", "button")
        button.setAttribute("class", "btn btn-link")
        button.setAttribute("data-toggle", "collapse")
        button.setAttribute("data-target", "#collapse" + i)
        button.setAttribute("aria-expanded", "true")
        button.setAttribute("aria-controls", "collapse" + i)
        button.innerText = "Order " + (i+1)
        h2.appendChild(button);

        var totalAmount = document.createElement("h5");
        totalAmount.setAttribute("style", "margin-left:10px;")
        totalAmount.innerText = "Total Amount: " + order[i].amount;
        cardheader.appendChild(totalAmount);

        var collapse = document.createElement("div");
        collapse.setAttribute("id", "collapse" + i)
        collapse.setAttribute("class", "collapse show")
        collapse.setAttribute("aria-labelledby", "heading" + i)
        collapse.setAttribute("data-parent", "#accordionExample")
        card.appendChild(collapse);

        var cardBody = document.createElement("div");
        cardBody.setAttribute("class", "card-body")
        collapse.appendChild(cardBody)

        var items = order[i].cart;
        //productItemBegin
        var list = document.createElement("ul");
        list.setAttribute("class", "list-group")
        for (j = 0; j < items.length; j++) {
            var anchor = document.createElement("a");
            anchor.href = "#";
            anchor.innerText = items[j].product.productName;
            anchor.setAttribute("style", "margin-left:20px")

            var image = document.createElement("img");
            image.setAttribute("class", "cart-image")
            image.src = "images/3.png";

            var span = document.createElement("span");
            span.setAttribute("class", "badge badge-primary badge-pill")
            span.setAttribute("style", "margin-left:50px; margin-right:50px")
            span.innerText = items[j].quantity;

            var text = document.createElement("b");
            text.innerText = "Price: " + items[j].price;
            text.setAttribute("style", "margin-left:200px")

            var elem = document.createElement("li");
            elem.setAttribute("class", "list-group-item shadow p-3 mb-5 bg-white rounded")
            elem.appendChild(image);
            elem.appendChild(anchor);
            elem.appendChild(text);
            elem.appendChild(span);

            list.appendChild(elem);
        }
        cardBody.appendChild(list);
    }
   var divContainer = document.getElementById("showData");
   divContainer.innerHTML = "";
   divContainer.appendChild(accordian);
}
document.addEventListener("DOMContentLoaded", function() {
    loadProducts();

    document.getElementById("order-form").addEventListener("submit", function(event) {
        event.preventDefault();
        createOrder();
    });

    document.getElementById("payment-form").addEventListener("submit", function(event) {
        event.preventDefault();
        createPayment();
    });

    document.getElementById("delivery-form").addEventListener("submit", function(event) {
        event.preventDefault();
        checkDeliveryStatus();
    });
});

function loadProducts() {
    fetch("/products")
        .then(response => response.json())
        .then(products => {
            const productList = document.getElementById("product-list");
            const productSelect = document.getElementById("product");

            products.forEach(product => {
                const li = document.createElement("li");
                li.textContent = `${product.name} - ${product.price} - ${product.quantity} in stock`;
                productList.appendChild(li);

                const option = document.createElement("option");
                option.value = product.id;
                option.textContent = product.name;
                productSelect.appendChild(option);
            });
        });
}

function createOrder() {
    const product = document.getElementById("product").value;
    const quantity = document.getElementById("quantity").value;

    fetch("/orders", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ product, quantity })
    })
        .then(response => response.json())
        .then(order => {
            alert(`Order created with ID: ${order.id}`);
        });
}

function createPayment() {
    const orderId = document.getElementById("order-id").value;
    const amount = document.getElementById("amount").value;

    fetch("/payments", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ orderId, amount })
    })
        .then(response => response.json())
        .then(payment => {
            alert(`Payment created with ID: ${payment.id}`);
        });
}

function checkDeliveryStatus() {
    const orderId = document.getElementById("order-id-delivery").value;

    fetch(`/deliveries?orderId=${orderId}`)
        .then(response => response.json())
        .then(delivery => {
            document.getElementById("delivery-status").textContent = `Delivery status: ${delivery.status}`;
        });
}
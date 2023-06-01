// VERIDICAR SE O HTML JÁ ESTÁ PRONTO
if (document.readyState == "loading") {
    document.addEventListener("DOMContentLoaded", ready)
}else {
    ready()
}

var totalAmount = "0,00"

function ready() {
    updateTotal()

    const removeProductButtons = document.getElementsByClassName("remove-product-button")
    for (var i=0; i < removeProductButtons.length; i++) {
        removeProductButtons[i].addEventListener("click", removeProduct)
    }

    const quantityInputs = document.getElementsByClassName("product-qtd-input")
    for (var i =0; i < quantityInputs.length; i++) {
        quantityInputs[i].addEventListener("change", checkIfInputIsNull)
    }

    const addToCartButtons = document.getElementsByClassName("btn-success")
    for (var i=0; i < addToCartButtons.length; i++) {
        addToCartButtons[i].addEventListener("click", addProductToCart)
    }

    const purchaseButton = document.getElementsByClassName("purchase-button")[0]
    purchaseButton.addEventListener("click", makePurchase)
}

// VERIFICAÇÃO PARA FINALIZAR COMPRA
function makePurchase() {
    if (totalAmount == "0,00") {
        alert("Seu carrinho está vazio! \nAdicione algo antes de finalizar sua compra.")
    }
    else {
        alert(
           `
           Valor final do pedido: R$${totalAmount}
           Agora é só preencher os dados de entrega.
           Obrigado pela sua compra, Volte sempre :)
           ` 
        )
    }

    document.querySelector(".card-table tbody").innerHTML = ""
    updateTotal()
}

// REMOVER DO CARRINHO SE QNT == 0
function checkIfInputIsNull(event) {
    if (event.target.value == "0") {
        event.target.parentElement.parentElement.remove()
    }
    updateTotal()
}

// ADICIONAR PRODUTO NO CARRINHO
function addProductToCart(event) {
    const button = event.target
    const productInfos = button.parentElement.parentElement
    const productImage = productInfos.getElementsByClassName("card-img-top")[0].src
    const productName = productInfos.getElementsByClassName("card-title")[0].innerText
    const productPrice = productInfos.getElementsByClassName("price")[0].innerText

    // SE JÁ ESTIVER NO CARRINHO AUMENTAR A QNT
    const productCartName = document.getElementsByClassName("cart-product-title")
    for (var i=0; i < productCartName.length; i++) {
        if (productCartName[i].innerText == productName) {
            productCartName[i].parentElement.parentElement.getElementsByClassName("product-qtd-input")[0].value++
            return
        }
    }
    
    let newCartProduct = document.createElement("tr")
    newCartProduct.classList.add("cart-product")

    newCartProduct.innerHTML = 
    `
        <td class="product-identification">
            <img class="cart-product-image" src="${productImage}" alt="${productName}">
            <strong class="cart-product-title">${productName}</strong>
        </td>
        <td>
            <span class="cart-product-price">${productPrice}</span>
        </td>
        <td>
            <input class="product-qtd-input" type="number" value="1" min="0">
            <button class="remove-product-button" type="button">Remover</button>
        </td>
    `
    const tableBody = document.querySelector(".card-table tbody")
    tableBody.append(newCartProduct)

    updateTotal()

    newCartProduct.getElementsByClassName("product-qtd-input")[0].addEventListener("change", checkIfInputIsNull)
    newCartProduct.getElementsByClassName("remove-product-button")[0].addEventListener("click", removeProduct)
}

// REMOVER O PRODUTO DO CARRINHO
function removeProduct(event) {
    // pegar a classe pai para remover todo o elemento
    event.target.parentElement.parentElement.remove()
    updateTotal()
}

// ATUALIZAR O VALOR FINAL
function updateTotal(){
    totalAmount = 0
    const cartProducts = document.getElementsByClassName("cart-product")
    for (var i = 0; i < cartProducts.length; i++) {
        // pegar o texto do linha preço
        const productPrice = cartProducts[i].getElementsByClassName("cart-product-price")[0].innerText.replace("R$", "").replace(".", "").replace(",", ".")

        // pegar o valor da quantidade no input
        const productQuantity = cartProducts[i].getElementsByClassName("product-qtd-input")[0].value

        totalAmount += productPrice * productQuantity
    }
    totalAmount = totalAmount.toFixed(2)
    totalAmount = totalAmount.replace(".", ",")
    document.querySelector(".cart-total-container span").innerText = "R$" + totalAmount
}

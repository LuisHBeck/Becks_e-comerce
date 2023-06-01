function buscaCep() {
    let cep = document.getElementById("strCep").value

    if (cep != ""){
        let url = "https://brasilapi.com.br/api/cep/v1/" + cep
        let req = new XMLHttpRequest()
        req.open("GET", url)
        req.send()

        req.onload = function () {
            if (req.status == 200) {
                let endereço = JSON.parse(req.response)
                document.getElementById("strRua").value = endereço.street
                document.getElementById("strBairro").value = endereço.neighborhood
                document.getElementById("strCidade").value = endereço.city
                document.getElementById("strEstado").value = endereço.state
            }
            else if(req.status == 404) {
                alert("CEP INVÁLIDO")
            }
            else {
                alert("ERRO NA REQUISIÇÃO")
            }
        }
    }
}

window.onload = function () {
    let strCep = document.getElementById("strCep")
    strCep.addEventListener("blur", buscaCep)
}
//type guard de atribuição
let admiral: number | string
admiral = "Grace Hopper"
console.log(admiral.toUpperCase())
//Abaixo dá erro pois quando atribuímos uma string à variável, 
//o tipo dela passa a ficar restrito a string. 
//Portanto ela não é mais um number e logo não tem o método toFixed()
console.log(admiral.toFixed())


//type guard de condição
let scientist = Math.random() > 0.5 ? "Rosalind Franklin" : 51
if (scientist === "Rosalind Franklin") {
  scientist.toUpperCase()
  scientist.toFixed() //Erro pois a condição do bloco é que a scientist seja uma string
}
scientist.toUpperCase() //Erro pois nesse ponto o tipo é string | number
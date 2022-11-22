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

//typeof
let researcher = Math.random() > 0.5 ? "Rosalind Franklin" : 51;
if (typeof researcher === "string") {
  researcher.toUpperCase();
  researcher.toFixed();  //Erro pois nesse escopo researcher vai ser sempre string
}

if (!(typeof researcher === "string")) {
  researcher.toUpperCase(); //Erro pois nesse escopo researcher nunca será string
  researcher.toFixed(); //Correto pois na declaração o typescript infere que o tipo o union de string | number. Como nesse escopo researcher nunca será string logo será sempre number.
} else {
  researcher.toUpperCase();
  researcher.toFixed(); //Aqui dá erro pois researcher será string
}
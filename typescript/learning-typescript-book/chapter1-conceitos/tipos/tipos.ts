console.log("Nothing is worth more than laughter.");

//tipo da variavél é inferido caso ela seja inicializada com um valor
let song = Math.random() > 0.5 ? "chaon" : "teste";

//Abaixo, o typescript consegue saber que o tipo não é number e portanto vai mostrar erro.
//Erros desse tipo não impedem que o compilador gere o javascript. Mas é melhor resolver o erro pois com certeza do contrário algum erro em produção vai existir
//song = 2

//Abaixo, o typescript em tempo de compilação consegue saber que length não é um método e portanto vai mostrar um erro
// song.length()

//É possível definir um tipo explicitamente.
let rocker: string;
rocker = "Fernando Batista"
//Com let, eu posso mudar o valor da variável
rocker = "Batista Fernando"
//Abaixo, o typescript dá erro pois a variável não é do tipo number
// rocker = 1

//A Anotação de tipo abaixo é desnecessária pois o typescript consegue inferir o tipo. Porém, futuramente o livro vai mostrar casos em que anotações assim podem ser úteis.
let age: number = 17

let birthName = {
  firstName: "Fernando",
  lastName: "Batista",
}

//Abaixo vai dar erro. O typescript consegue dizer que o "middleName" não existe no objeto declarado acima.
// console.log(birthName.middleName)


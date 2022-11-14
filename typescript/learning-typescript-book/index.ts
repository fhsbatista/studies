console.log("Nothing is worth more than laughter.");

//tipo da variavél é inferido caso ela seja inicializada com um valor
let song = Math.random() > 0.5 ? "chaon" : "teste";

//Abaixo, o typescript consegue saber que o tipo não é number e portanto vai mostrar erro.
//song = 2

//Abaixo, o typescript em tempo de compilação consegue saber que length não é um método e portanto vai mostrar um erro
// song.length()

let name: string

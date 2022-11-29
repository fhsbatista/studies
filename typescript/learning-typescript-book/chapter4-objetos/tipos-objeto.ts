const poet = {
  born: 1935,
  name: "Mary Oliver",
}

console.log(poet['born']) //Vai imprimir 1935
console.log(poet.name) //Vai imprimir Mary Oliver
console.log(poet.end) //Typescript dá erro dizendo que a propriedade não existe


//Declarando o tipo para só depois passar um valor
let newPoet: {
  born: number;
  name: string;
}

newPoet = {
  born: 1935,
  name: "Mary Oliver"
}

//Alias de tipo
type Poet = {
  born: number;
  name: string;
}

let poetWithType: Poet;
poetWithType = {
  born: 1935,
  name: "Sara Teasdale",
  newField: "teste", //Erro pois essa propriedade não existe no tipo que criamos
}


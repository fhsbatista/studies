let mathematician = Math.random() > 0.5 ? undefined : "um texto qualquer"

//Tanto faz o tipo que é colocado primeiro
let thinker: string | null
if (Math.random() > 0.5) {
  thinker = "Susanne Langer"
}

let physicist = Math.random() > 0.5 ? "Marie Curie" : 100;
//Abaixo não dá erro pois toString existe tanto para string como para number
physicist.toString()

//Abaixo dá erro pois o toUpperCase existe apenas para string
physicist.toUpperCase()
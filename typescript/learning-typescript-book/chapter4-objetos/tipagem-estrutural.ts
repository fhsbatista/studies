type WithFirstName = {
  firstName: string
}

type WithLastName = {
  lastName: string
}

const hasBoth = {
  firstName: "Lucille",
  lastName: "Clifton"
}

let withFirstName: WithFirstName = hasBoth //Permitido pois hasBoth contém firstName
let withLastName: WithLastName = hasBoth //Permitido pois hasBoth contém lastName

type FirstAndLastNames = {
  first: string
  last: string
}

const hasBothNames: FirstAndLastNames = {
  first: "Sarojini",
  last: "Naidu"
} //Funciona pois o objeto passa contém todas as propriedades que FirstAndLastNames pede

const hasOnlyOne: FirstAndLastNames = {
  first: "Sappho"
} //Erro pois o objeto passado não contém a propriedade last.

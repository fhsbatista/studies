const philosopher = "Hypathia" //Tipo literal
let philosopher2 = "Hypathia" //Tipo string

let statusCarro: "Andando" | "Dirigingo" | "Ré" | undefined | boolean
statusCarro = "Andando"
statusCarro = "Ré"
statusCarro = undefined
statusCarro = false
statusCarro = "Estacionado" //Erro pois o literal "Estacionado" não faz parte do union de literais definidos ao declarar a variável

let carName: "dodge ram"
carName = "polo" //Erro pois a variável vai aceitar apenas o literal "dodge ram"
let otherCarName: string = carName //Dá certo, carName pode ser atribuído à uma string pois o seu literal faz parte do conjunto de infinitas strings possíveis




const philosopher = "Hypathia" //Tipo literal
let philosopher2 = "Hypathia" //Tipo string

let statusCarro: "Andando" | "Dirigingo" | "Ré" | undefined | boolean
statusCarro = "Andando"
statusCarro = "Ré"
statusCarro = undefined
statusCarro = false
statusCarro = "Estacionado" //Erro pois o literal "Estacionado" não faz parte do union de literais definidos ao declarar a variável



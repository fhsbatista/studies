//Exemplos de unions verbosos e que se repetem
let rawDataFirst: boolean | number | string | null | undefined
let rawDataSecond: boolean | number | string | null | undefined
let rawDataThird: boolean | number | string | null | undefined

//Usando alias
//Convenção de nome: PascalCase!
type RawData = boolean | number | string | null | undefined

let rawDataForth: RawData
let rawDataFifth: RawData
let rawDataSixth: RawData

//Usando alias em runtime
console.log(RawData) //Erro pois o alias está sendo usado como valor. Aliases não existem no javascript, portanto não funcionam em runtime e logo não podem ser usados como "valor" 

//Conjunto de alias
type Id = number | string
type MaybeId = Id | undefined | null
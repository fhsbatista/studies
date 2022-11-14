//Abaixo vai dar erro pois "shared" já é exportado por ./a.ts
//Portanto, um script não pode conter variáveis cujo nome é o mesmo que algum dos módulos importados usa.
// import { shared } from "./a"
// export const shared = "cher"
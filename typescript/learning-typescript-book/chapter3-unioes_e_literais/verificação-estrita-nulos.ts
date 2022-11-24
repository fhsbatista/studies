let geneticist =  Math.random() > 0.5 ? "Barbara McClintock" : undefined
if (geneticist) {
  geneticist.toUpperCase() //Não dá erro pois aqui o tipo vai ser string, pois a condição seria false caso a variável fosse undefined
}
geneticist.toUpperCase() //Erro pois a variável pode ser undefined

if (!geneticist) {
  geneticist.toUpperCase() //Aqui dá erro pois o tipo é string | undefined
}

let fighter: string
fighter.toUpperCase() //Erro pois fighter ainda não teve um valor atribuído

let dancer: string | undefined
dancer?.toUpperCase() //Não dá erro pois como o tipo inclui undefined, o typescript permite usar o operador "?" que torna seguro tentar acessar algo da variável
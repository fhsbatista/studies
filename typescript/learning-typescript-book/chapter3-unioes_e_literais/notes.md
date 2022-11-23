# Uniões e literais
São as 2 formas que o typescript tem para poder inferir o tipo de uma variável.
Estes são recursos bastante poderosos e algo que outras linguagens convencionais não conseguem fazer.

## Uniões
Quando o tipo é expandido para mais de 1 opção possível.

Ex:

```typescript
let mathematician = Math.random() > 0.5 ? undefined : "um texto qualquer"
```

No exemplo acima, o tipo de `mathematician` não é nem undefined nem string. O tipo é algo como "ou um ou outro" ("either or"). Esse tipo é o `union type`.

O typescript vai mostrar o tipo nesse caso como por exemplo: `string | undefined`

### Declarando uma variável de tipo `union`
Obs: Este é um exemplo de caso em que é útil especificar o tipo mesmo que a variável já esteja sendo inicializada. Se não especificarmos o tipo fica como "restrito" ao tipo do valor usado para inicializar.

```typescript
//A ordem dos tipos não importa, ou seja, abaixo `null | string` daria na mesma
let thinker: string | null
if (Math.random() > 0.5) {
  thinker = "Susanne Langer"
}
```

### Acessando valores de tipo `union`
O typescript só vai permitir acessar valores que estejam disponíveis para todos os tipos permitidos pelo `union`. Ou seja, se o tipo for `string | number` por exemplo, o typescript permitiria `toString` pois tanto `string` como `number` possuem esse método. Por outro lado, o método `toUpperCase` por exemplo não seria permitido pois ele não existe no tipo `number`.

### Notas pessoais
Este tipo parece ser uma opção já nativa equivalente as "tuplas" ou o tipo `Either` que o package `dartz` no dart dá. 

## Estreitamento (narrowing)
As vezes podemos declarar uma variável como um union de vários tipos e mesmo assim o typescript pode entender a partir de uma verificação lógica que um conjunto menor de tipos é o que pode ser na verdade atribuido à variável. Quando o typescript faz isso, ele permite a gente a tratar essa variável como sendo desse conjunto mais restrito usando verifações lógicas. Uma dessas verificações é o type guard.

### Type guard
Existem vários tipos. Vamos tratar os dois mais comuns.

#### Estreitamento por atribuição
Acontece quando atribuímos o valor à uma variável. Nesse caso, o tipo ficará restrito ao tipo do valor que foi atribuído mesmo que o tipo da variável tenha sido declarado como um union de vários tipos.

Ex:
```typescript
let admiral: number | string
admiral = "Grace Hopper"
console.log(admiral.toUpperCase())
console.log(admiral.toFixed()) //Erro:Property 'toFixed' does not exist on type 'string'.
```

No bloco acima, o typescript entendeu que embora o tipo tenha sido declarado como um `union` de `string` e `number`, no momento em que o método `toFixed` foi chamado o único valor possível da variável era do tipo `string` [^1] , por isso ele dá o erro.

#### Verificação condicional
Acontece quando o uso da variável é dentro de um bloco condicional como um `if` cujo a condição é que a variável seja de um tipo. Nesse caso, o tipo da variável dentro do bloco passar o tipo usado para a condição ser verdadeira.

```typescript
let scientist = Math.random() > 0.5 ? "Rosalind Franklin" : 51 //string | number
if (scientist === "Rosalind Franklin") {
  scientist.toUpperCase()
  scientist.toFixed() //Erro pois a condição do bloco é que scientist seja uma string
}
scientist.toUpperCase() //Erro pois nesse ponto o tipo é string | number
```

#### Verificações com typeof
De maneira resumida, é a mesma coisa que o operador `is` no dart, java etc.
Essas verificações são bastante flexíveis (dá pra usar !, else etc) e isso faz com que elas acabem sendo uma das mais práticas no dia a dia.
ex:
```typescript
let researcher = Math.random() > 0.5 ? "Rosalind Franklin" : 51;
if (typeof researcher === "string") {
  researcher.toUpperCase();
  researcher.toFixed();  //Erro pois nesse escopo researcher vai ser sempre string
}

if (!(typeof researcher === "string")) {
  researcher.toUpperCase(); //Erro pois nesse escopo researcher nunca será string
  researcher.toFixed(); //Correto pois na declaração o typescript infere que o tipo um union de string | number. Como nesse escopo researcher nunca será string logo será sempre number.
} else {
  researcher.toUpperCase();
  researcher.toFixed(); //Aqui dá erro pois researcher será string
}
```

## Tipos Literais
O tipo literal é o tipo mais restrito possível no typescript. O "literal" representa um valor específico de um tipo primitivo, o que é diferente de um tipo primitivo, pois esse poderia ser qualquer valor desse primitivo.

Veja o código abaixo por exemplo:
```typescript
const philosopher = "Hypatia"
```

Poderíamos dizer que philosopher é uma `string`, e isso está certo. Porém, não é qualquer string, é algo mais especial que isso. Philosopher é o tipo literal `"Hypatia"`, isso pois ela é uma `const`, ou seja, não pode ser alterada, e o valor atribuído é uma `string`.

Nesses casos, se passarmos o ponteiro do mouse na variável, a IDE irá mostrar que o tipo é `"Hypathia"` em vez de `string`.

### `Union` de literais
Temos uma flexibilidade bem interessante que é a de poder usar `union` de literais para enumerar opções.
Isso deve ser feito colocando os literais como o **tipo** da variável, o que é diferente de atribuir o valor. Para definir o tipo, usamos o `:` em vez de `=`.

Ex:
```typescript
let statusCarro: "Andando" | "Dirigingo" | "Ré" | undefined | boolean
statusCarro = "Andando"
statusCarro = "Ré"
statusCarro = undefined
statusCarro = false
statusCarro = "Estacionado" //Erro pois o literal "Estacionado" não faz parte do union de literais definidos ao declarar a variável
```

### Atribuindo literais à variáveis
Um ponto para ficar atento é quando atribuímos um literal como valor de uma variável.
Quando fazemos isso, o typescript não vai permitir que um literal seja atribuído à uma variável quando esse é diferente do literal que foi usado na hora de declarar a variável.

Ex:

```typescript
let carName: "dodge ram"
carName = "polo" //Erro pois a variável vai aceitar apenas o literal "dodge ram"
```

Porém, uma variável que é de um tipo literal pode ser atribuído à uma variável cujo tipo é o mesmo tipo do literal pois ambos os tipos são iguais

Ex:

```typescript
let carName: "dodge ram"
let otherCarName: string = carName //Dá certo, carName pode ser atribuído à uma string pois o seu literal faz parte do conjunto de infinitas strings possíveis
```

---
**Footnotes**

[^1]: Inteligente esse typescript em? 🤯

---




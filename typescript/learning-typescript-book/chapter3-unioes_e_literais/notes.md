# Uniões e literais
São as 2 formas que o typescript tem para poder inferir o tipo de uma variável.
Estes são recursos bastante poderosos e algo que outras linguagens convencionais não conseguem fazer.

## Uniões
Quando o tipo é expandido para mais de 1 opção possível.

Ex:

```
let mathematician = Math.random() > 0.5 ? undefined : "um texto qualquer"
```

No exemplo acima, o tipo de `mathematician` não é nem undefined nem string. O tipo é algo como "ou um ou outro" ("either or"). Esse tipo é o `union type`.

O typescript vai mostrar o tipo nesse caso como por exemplo: `string | undefined`

### Declarando uma variável de tipo `union`
Obs: Este é um exemplo de caso em que é útil especificar o tipo mesmo que a variável já esteja sendo inicializada. Se não especificarmos o tipo fica como "restrito" ao tipo do valor usado para inicializar.

```
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
Quando o tipo é restrito em apenas uma opção.


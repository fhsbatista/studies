Comandos:
 - `./gradlew tasks` - Lista todas as tasks do projeto
  Obs: Neste comando vão aparecer somentes as tasks que estão dentro de um grupo.
 - `./gradlew tasks -all` - Lista todas as tasks, mesmo as que não estão dentro de um grupo.

 Glossário
 - "build script": é o arquivo `build.gradle`
 - "plugin": é um código "pre-packaged" que aplicamos no build script para ter acesso a algumas tasks. Ex: o plugin `base` disponibiliza a task `clean` que é usada para limpar o diretório `build`.


Grupos:
 - Para adicionar uma task dentro de um grupo, basta colocar o parametro `group` na definicao da task.
 Ex:
 ```groovy
 tasks.register('generateDescriptions', Copy) {
  group 'theme park'
  description 'Generates rides descriptions including token substitution'
  from 'descriptions'
  into "$buildDir/descriptions"
  filter(ReplaceTokens, tokens: [THEME_PARK_NAME: "Grelephants's Wonder World",])
}
 ```


 Abreviando nome das tasks nos comandos
  - Para evitar digitar o nome inteiro da task, vc pode usar a primeira letra do nome dela ou a primeira letra de cada palavra no caso de camelCase. O Gradle automaticamente vai identificar qual task vc quer e vai executar ela.
  Ex: 
    - `./gradlew gD` = `./gradlew generateDescriptions`
    - `./gradlew zD` = `./gradlew zipDescriptions`

Log do Build
 - Execute a task usando a flag `--info` para ter informações detalhadas do que tá acontecendo ao longo do build.
 - Ou use `--console` para definir o nível do log. As opções são: `plain`, `auto`, `rich`, `verbose`. O default é `auto`.
 Ex: `./gradlew gD --info --console=plain`

## Ciclo de vida do build
Saber em qual etapa do ciclo um problema está acontecendo pode ajudar muito a encontrar a solução.
Ciclos: inicialização, configuracão e execução

### Inicialização
 - Aqui é onde o gradle identifica quais são os projetos que vão participar do build.
 - É quando o `settings.gradle` é executado.

### Configuração
 - Momento em que o gradle vai identificar as tasks dos projetos
 - É quando o `build.gradle` é executado.

### Execução
 - Momento em que as tasks são executadas de fato.

## Groovy
 - Roda na JVM.
 - O código usado pelo gradle é código Java.
 - Dinamicamente tipada.

### Groovy x java: 4 principais diferenças
#### Groovy roda como um script. Não é necessário criar uma classe para tudo como no java.
 Ex:

 Groovy
 ```Groovy
 println('eu sou groovy')
 ```

 Java
 ```Java
 package com.fbatista

 public class PrintMessage {
  public static void main(String[] args) {
    System.out.println("eu sou o java");
  }
 }
 ```

#### Parenteses são opcionais
 Ex:

 Groovy
 ```Groovy
 def sum(first, second) {
  println first + second
 }

 sum 2, 5
 ```

 Java
 ```Java
 package com.fbatista

 public class Sum {
  public void sum(int first, int second) {
    System.out.println(first + second);
  }

  public static void main(String[] args) {
    new Sum().sum(2, 3)
  }
 }
 ```

Exemplo no `build.gradle` 
```Groovy
plugins {
  id 'base'
}
```

#### Suporte a closures
Entenda closure como uma função que pode ser passa via parâmetro

Ex:
```Groovy
def callMeLater = {
  println 'ring ring'
}
callMeLater() //prints 'ring ring'
```

Ex2:
```Groovy
def pickupPhone(name, phoneNoise) {
  phoneNoise()
  println 'Hello? ' + name + ' here'
}
pickupPhone 'Fernando', { println 'trim trim'}
```

Exemplo no `build.gradle` 
```Groovy
tasks.register('closureInAction') {
  doLast {
    println 'task definition using closures'
  }
}

tasks.register 'closureInAction', {
  doLast {
    println 'task definition using closures'
  }
}
```

#### Closure fora de parenteses
Quando o último parametro é uma função, vc pode passar os primeiros parametros dentro de parenteses e deixar o último, que é a função, fora dos parenteses

```Groovy
def pickupPhone(name, phoneNoise) {
  phoneNoise()
  println 'Hello? ' + name + ' here'
}
pickupPhone('Fernando') { 
  println 'trim trim'
}
```

Exemplo no `build.gradle` 
```Groovy
tasks.register('closureInAction') {
  doLast {
    println 'task definition using closures'
  }
}
```

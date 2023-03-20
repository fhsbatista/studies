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

## Configurando o projeto
Existem várias maneiras de se configurar um projeto.
Para começar, existem várias propriedades que podem ser usadas na configuração. Para mais detalhes sobre cada propriedade, veja a documentação da classe `Project` em https://docs.gradle.org/current/javadoc/org/gradle/api/Project.html

### Propriedades básicas
`description`: Descrição do projeto. É o que aparece quando executamentos a task "projects"
`group`: Um identificador do "owner" do projeto. É usado para publicar o artefato (ex: .jar) posteriormente. Ex: com.fbatista, org.edu etc.
`version`: Versão que vai ser usada na publicação do artefato.

## Tasks
São usadas para performar alguma ação na pasta "build" do projeto. Como zipar arquivos, copiar etc.

### Documentação que mostra as tasks que já vem com o gradle
https://docs.gradle.org/current/dsl/

### Task `class` e Task `definition`
Obs: Tenho a impressão de que entendi um pouco errado pois as duas coisas ainda parecem ambíguas. Portanto a definição abaixo pode estar um pouco errada.

No contexto de Task, class e definition são coisas diferentes.

class: é o "blueprint" da task. É a classe em si. Normalmente estão dentro do próprio gradle.
definition: é a configuração de uma task no projeto.

Ex: A task "copy" já existe no gradle, isso seria a class. Quando eu configuro essa task no meu projeto para copiar arquivos, essa configuração seria o "definition".

### Como configurar uma task no projeto
Existe duas maneiras. Uma delas é mais performática, a outra é mais lenta e mais antiga e está a "deprecated", porém é importante entende-la já que vários projetos ainda podem estar usando.

- Utilizando o .register (maneira mais atual)

```groovy
task.register('generateDescriptions', Copy) {
  //configuration
}
```
Obs: Essa é a maneira mais recomendada por conta de performance. A diferença dela em relação ao modo antigo (abaixo) é que SEM o `register`, ou seja, modo antigo, a configuração da Task era feita na fase de  "configuração" do lifecycle. O problema é que pode acontecer de a Task nem precisar ser executada (pois pode estar desabilitada por exemplo), assim adicionando um tempo que pode ser grande ao build. Com o `register`, a configuração da Task vai para a segunda fase do life cycle que é a de execução, e aí nesse caso, a Task vai ser configurada somente se ele realmente for executada.

- Modo antigo (deprecated)
```groovy
task('generateDescriptions', Copy) {
  //configuration
}
```
Configurações comuns:
 - group
 - description
 - enabled (por default é true, quando false, a task não é executada)
 - onlyIf (parecido com o enabled, mas vai usar uma lógica para definir se executa ou não)
 - Para consultar todas as configurações possíveis, acessar: https://docs.gradle.org/current/javadoc/org/gradle/api/Task.html

Nas configurações existem vários campos que podemos passar e eles vão variar de acordo com a Task que estamos trabalhando. Existem 2 campos que sempre estão presentes nas Tasks, que são: `group` e `description`.
Ex:
```groovy
tasks.register('generateDescriptions', Copy) {
  group 'ThemePark'
  description 'Copies the theme park ride descriptions'
  enabled false
  // Igual o enabled, mas usando uma condição em vez do bool diretamente
  // onlyIf {
  //   2 == 3 * 2
  // }
  from 'descriptions'
  into "$buildDir/descriptions"
}
```

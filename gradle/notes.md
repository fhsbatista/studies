Comandos:
 - `./gradlew tasks` - Lista todas as tasks do projeto
  Obs: Neste comando vão aparecer somentes as tasks que estão dentro de um grupo.
 - `./gradlew tasks -all` - Lista todas as tasks, mesmo as que não estão dentro de um grupo.


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


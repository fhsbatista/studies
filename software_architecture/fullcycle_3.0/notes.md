# Notas sobre arquitetura de software

## Fundamentos

### Introdução
- arquitetura é fundamental para trabalhar em grandes projetos

### Arquitetura tecnológica e corporativa
 tipos:
  - software
  - soluçao
  - tecnológica
  - corporativa

Essas nomenclaturas são comuns em grandes empresas.

#### Arquitetura tecnológica
- focado em tecnologias específicas de mercado
- o arquiteto desse tipo é focado em alguma tecnologia, ex: arquiteto aws, azure, java, elastic, SAP, oracle etc.
- ex: arquiteto java se difere tento conhecimento de detalhes bem específicos, como algo de JVM.
- um time pode ter vários desses arquitetos no mesmo tipo, cada uma dessas pessoas agregando valor em alguma especeficidade diferente.

#### Arquitetura corporativa
- impacta de maneira estratética na empresa como um todo
- avaliacao de custos com devs, software licenciados etc 
- avaliam adoção de novas tecnologias, novas versões de produtos que já sao usados etc.
- padronização de tecnologias entre os times
- planejamento de implantações grandes
  - implamtar um Salesforce por exemplo. Isso pode impactar em processos de várias áreas, fazendo com que o pessoal tenha que mudar a maneira de trabalhar
  - decomposiçào de monolitos
  - outros sistemas que atingem o dia a dia da empresa

#### Minhas observações
Interessante estar ciente de que existem vários tipos de arquitetura, pois eu já me confundi bastante com esse termo, por exemplo ao ver que uma pessoa se denomina arquiteta mas fala de assuntos que não são diretamente o dia a dia do programador. Agora entendo que essa pessoa pode ser arquiteta em um ramo diferente, como o corporativo, analisando e tomando decisões que impactam a empresa como um tudo e não impactam diretamente um projeto em específico. Por exemplo, a decisão de padronização de uma linguagem ou outra tecnologia na empresa toda não é uma decisão que impacta exclusivamente um projeto que está sendo desenhado para um cliente.

### Arquitetura de soluções
- fica entre área de negócio e a de software
- transforma requisitos de negócio em soluções de software
- por exemplo, analisam uma determinada demanda de um stakeholder e desenham uma solução que resolva essa demanda trazendo isso para a linguagem de software.
- parte do dia a dia é desenho de soluçoes, então uma habilidade muito importante pra essa pessoa é expressar um pensamento em diagramas, desenhos etc.
- tipos de diagramas
  - C4
  - UML
  - BPMN
- analisa os impactos comerciais de uma escolha no curto, médio e longo prazo
  - ex: por exemplo, em um cliente que está todo na AWS, esse profissional não iria simplesmente decidir migrar para Azure somente porque acha que é "melhor", mas sim embasaria essa decisão colocando os custos na mesa e eventualmente entendo que faz sentido a migração por fatores mais concretos do que uma preferência pessoal por uma tecnologia ou outra.
- pode participar de reuniões de pré-venda com cliente para acompanhar vendedores ajudando a mostrar como tal solução irá funcionar
- faz análise de custos de um determinado software para o negócio
  - licenças
  - integrações com parceiros (saas, crm, ferramentas de análise)

Habilidades desse tipo de profissional
- gosta de negócio
- habilidades técnicas
- se comunica bem
- consegue se expressar com números e diagramas as necessidades de um stakeholder

### Arquitetura de software
- é uma das áreas da disciplina de "engenharia de software"
- ta ligada com o desenvolvimento do software diretamente
- impacta a maneira de se organizar áreas da empresa ligadas à software
  - formação dos times
  - processos dos times
  - componentes de software
  - "lei de conway" -> "Organizações que desenvolvem sistemas de software tendem a produzir sistemas que são cópias das estruturas de comunicação dessa empresa".
- está sempre equilibrando os objetivos de negócio com a evolução de software (design, manutentabilidade, qualidade etc)
- definição formal na IEEE STANDARD
  -> "É a organização fundamento de um sistema e seus componentes, suas relações, seu ambiente, bem como os princípios que guiam seu design e evolução"

### Papel do arquiteto de software
- transformar requisitos em padrões arquiteturais
- fazer entre as pessoas técnicas de e de negócio
  - ou seja, vai convensar com
   - desenvolvedores
   - experts do domínio
   - etc
- tem que entender de forma profunda conceitos e modelos arquiteturais
  - entender quando usar e principalmente quando NAO usar uma solução
  - exemplo:
    - a vida inteira a pessoa resolver problemas com um sistema monolítico e sempre deu certo
    - mas não é porque sempre deu certo, que essa é a solução que deve ser usada em todos os lugares
- Os conceitos de arquitetura não servem somente para arquitetos
  - esses conceitos podem ser úteis para os desenvolvedores, techleads etc pois com alguma frequência essas pessoas precisam tomar decisões arquiteturais
- Auxilia na tomada de decisões em momentos de crise
- Reforçar boas práticas de desenvolvimento
  - testes
  - cumprir convenções acordadas
  - etc
- Code reviews
  - por normalmente ser dev e estar em mais de um projeto ao mesmo tempo, essa pessoa pode não ter tempo para programar
  - por conta disso, fazer code review é uma maneira de verificar se as decisões arquiteturais que foram feitas estão sendo respeitadas no código
- Muitas empresas não tem uma pessoa específica com esse papel, mas outras pessoas acabam fazendo esses papeis, como seniors e tech leads.
- Algumas empresas também podem ter um departamento de arquitetura que auxilia os times com questões arquiteturais. Então antes de iniciar um determinado projeto, esse departamento pode prestar uma espécie de consultoria. Por exemplo, um time pode estar querendo fazer um projeto que outro time já fez. Nesse caso, esse departamento por ter essa visão mais sistemica, pode auxiliar orientando esse time a usar o projeto que havia sido feito.

### Vantagens em se aprender sobre arquitetura de software
- Entender o sistema em que trabalhamos como um todo em vez de só enxergar aquele componente individualmente que estamos programando
- Enxergar as tecnologias de uma maneira mais madura não entrando na onda de qualquer hype do momento
- Pensar no desenvolvimento do software a longo prazo não trocando pequenas vantagens a curto prazo que custem o sucesso do software no longo prazo
- Conseguir tomar melhores decisões de padrões de projeto e práticas
- Não confundir pastas "domain", "infra", "models" com arquitetura de software. Arquitetura vai mundo além disso.
- Mais visão sistêmica do software em relação à empresa, dando mais ciência do impacto que o software tem na empresa toda.
  - Alguns devs por não terem a noção disso, acabam não tendo a sensação do quao importante o trabalho dele está sendo e acabam até saindo da empresa por conta disso.
- Tomar decisões com mais confiança. O conhecimento de arquitetura vai ajudar a ter um ponto de partida para as decisões mesmo em situações de crise.





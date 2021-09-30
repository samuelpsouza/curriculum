# Curriculum

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=samuelpsouza_matriz-curricular&metric=alert_status)](https://sonarcloud.io/dashboard?id=samuelpsouza_matriz-curricular)

Aplicação web responsável por montar a matriz curricular de um curso.

### Prerequisitos
* Maven
* Spring Boot 2.5.5
* Spring data JPA
* ReactJs
* Material Design
* Java JDK 17
* Flyway (Usado para versionar o banco. Sempre uma boa prática utlizar um versionador de
banco e não deixar essa resposabilidade para a dll do hibernate, e 
 assim conseguimos evitar conflitos de tipos, campos removidos, etc... em novas versões da aplicação)

### Instalando
git clone https://github.com/samuelpsouza/matriz-curricular.git

cd curriculum/

docker-compose up -d

deve acessar http:localhost:8080/init para criar o usuario inicial.
usuario: demo
senha: demo

acesso a ui: http://localhost:3000
deve ser criado um usuario coordenador para add cursos, disciplinas, semestres e montar a matriz.

## Suposições realizadas

Usei o conceito de TDD para o desenvolvimento da aplicação backend.

Os primeiros requisitos levantados foram as entidades que seriam utilizadas.
As entidades identificas, por ordem do documento de especificação da atividade, foram:

* Usuario
* Papel do Usuario
* Semestres
* Cursos
* Disciplinas
* Matriz

Nos relacionamentos entre as entidades foi levado em conta as opçoes de mover disciplinas entre semestres,
de adcionar ou remover semestres do curso e a remoção da disciplina do curso. Usaremos composição para formar a entida
de matriz.

* Matriz => Matrix
* Curso => Major
* Disciplina => Curso
* Semestre => Semester
# Feito

Todas as funcionalidades requeridas foram implemetadas no backend.
Algumas funcionalidades não foram feitas no frontend. Entre elas:

Editar Curso, Disciplina, Semestre, Usuario.
Excluir Semestre
Desvincular disciplina do semestre.

Cada curso tem uma matriz por default. As disciplinas são vinculadas ao curso e associadas ou não a um semestre.
Se a disciplina esta vinculada ao curso e não está associada a um semestre, ela automaticamente é classificada como
optativa.

No inicio do projeto foi utilizado TDD, mas com o prazo aproximando, alguns testes não foram implementados. Foi utilizado um banco embarcado para testes, mas teve ele teve um problema de rodar sem ser root, então foi necessario pular os testes na hora do package do
maven.

No inicio do projeto foi decidido utilizar React sem Redux porque aparentemente era suficiente manipular o estado da aplicação apenas passando ele como props. Porem no decorrer da implementação ficou claro que a opção mais viável seria usar Redux. Não foi feito devido
ao timeline da entrega.

O próximo passo é implementar as funções requeridas, adicionar respostas ao usuario final, adicionar Redux ao projeto.




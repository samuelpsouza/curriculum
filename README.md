# Gerenciador de Matriz Curricular

Aplicação web responsável por montar a matriz curricular
de um curso

### Prerequisitos
* Maven (Compilar e executar testes)
* Spring Boot 2.1.3.RELEASE (Ultima versão disponível do framework)
* Spring data JPA (Usado para mapear os objetos para o db)
* ReactJs
* Material Design
* Java JDK 1.8 (Usar, se necessario, novas features como lambda, optional entre outras)
* Flyway (Usado para versionar o banco. Sempre uma boa prática utlizar um versionador de
banco e não deixar essa resposabilidade para a dll do hibernate, e 
 assim conseguimos evitar conflitos de tipos, campos removidos, etc... em novas versões da aplicação)

### Instalando


## Testando


## Deployment

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


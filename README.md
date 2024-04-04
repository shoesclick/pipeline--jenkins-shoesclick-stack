# Projeto Para efetuar Deploy de aplicações no Ambiente EKS

Projeto em Grovvy para executar pipeline de apis:

### Pré requisitos

```
Java 17 : ([https://www.oracle.com/br/java/technologies/javase/jdk17-archive-downloads.html]);

Spring Boot na versão 3.1.9:  (https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-dependencies/3.1.9);

Maven 3.9.5: (https://dlcdn.apache.org/maven/maven-3/3.9.5/binaries/apache-maven-3.9.5-bin.tar.gz)

Node: v18.15. (https://nodejs.org/en/)

Python: (https://www.python.org/downloads/)

Golang: (https://go.dev/dl/)

Docker: (https://www.docker.com/products/docker-desktop/)

Kubernetes: (https://kubernetes.io/releases/download/) 
```

OBS: O Docker desktop já vem com o Kubernetes instalado.

## Estrutura:

```
src : Classes dos projetos
```

```
Var : onde ficará o script do pipeline.
```

## Fluxo:


```
Compila e testa o projeto

Gera uma imagem no Docker

efetua um Push no No repositório de imagem.

Efetua deploy no Kubernetes.

```

## Configuração:


#### Gerenciar Jenkins >> Configurar Sistema (System) >> Global Pipeline Libraries

Definir:

* **Name: jenkinspipelinelib - Será utilizado para importar no arquivo Jenkinsfile**
* **Default version: master - branch do projeto**
* **Retrieval method: Modern SCM**
* **Source Code Management: Git**
* **Project Repository: git@github.com:shoesclick/pipeline--jenkins-shoesclick-stack.git - Utilzando o SSH**
* **Credential: git**

#### Gerenciar Jenkins >> Configurar Sistema (System) >> Propriedades globais

Definir:

* **Name/Value: DCK_ACCOUNT_ID = <accountid do dockerhub ou repositório de imagem>**
* **Name/Value: DCK_REPOSITORY = <repositório de imagem>**

## Salvar.



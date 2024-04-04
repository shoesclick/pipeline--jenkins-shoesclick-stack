# Projeto Para efetuar Deploy de aplicações no Ambiente EKS

Projeto em Grovvy para executar pipeline de apis:

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

efetua um Push no ECR.

Efetua deploy no EKS.

```

## Configuração:


```
Gerenciar Jenkins >> Configurar Sistema (System) >> Global Pipeline Libraries

```
Definir:

* ***Name: jenkinspipelinelib - Será utilizado para importar no arquivo Jenkinsfile***
* ***Default version: master - branch do projeto***
* ***Retrieval method: Modern SCM***
* ***Source Code Management: Git***
* ***Project Repository: git@github.com:shoesclick/pipeline--jenkins-shoesclick-stack.git - Utilzando o SSH***
* ***Credential: git***


## EX: 

```
@Library('jenkinspipelinelib') _
```

## Definir o link do projeto no github (necessário configurar ssh)

## Definir a Branch - master

## Salvar.



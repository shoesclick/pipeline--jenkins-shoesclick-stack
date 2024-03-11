# Projeto Para efetuar Deploy de aplicações no Ambiente EKS

Projeto em Grovvy para executar pipeline de apis:

## Estrutura:

```
src/: Classes dos projetos
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
Gerenciar Jenkins >> Configurar Sistema >> Global Pipeline Libraries

Definir o nome: ex: jenkinspipelinelib - Será utilizado para importar no arquivo Jenkinsfile

```

## EX: 

```
@Library('jenkinspipelinelib') _
```

## Definir o link do projeto no github (necessário configurar ssh)

## Definir a Branch - master

## Salvar.



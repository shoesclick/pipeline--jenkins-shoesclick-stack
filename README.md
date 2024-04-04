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



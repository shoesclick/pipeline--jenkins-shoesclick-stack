# Projeto Para efetuar Deploy de aplicações no Ambiente EKS

Projeto em Grovvy para executar pipeline de apis:

### Pré requisitos

```
Jenkins: (https://www.jenkins.io/download/)

Jenkins cli: (<SUA URL DO JENKINS>/jnlpJars/jenkins-cli.jar)

Java 17 : ([https://www.oracle.com/br/java/technologies/javase/jdk17-archive-downloads.html]);

Maven 3.9.5: (https://dlcdn.apache.org/maven/maven-3/3.9.5/binaries/apache-maven-3.9.5-bin.tar.gz)

Node: v18.15. (https://nodejs.org/en/)

Python: (https://www.python.org/downloads/)

Golang: (https://go.dev/dl/)

Docker: (https://www.docker.com/products/docker-desktop/)

Kubernetes: (https://kubernetes.io/releases/download/)

GWAK (https://gnuwin32.sourceforge.net/packages/gawk.htm) - Somente para Windows
```

OBS: O Docker desktop já vem com o Kubernetes instalado.
     Configurar variaveis de Ambiente para o Java, Maven e Gawk

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

#### Gerenciar Jenkins >> Credentials >> System >> Global credentials (unrestricted)

Definir:

* **Scope: Global**
* **ID: <definir um id ex: Github-dev>**
* **Username: git**
* **Private Key: <Inserir private key SSH> "**

#### Gerenciar Jenkins >> Credentials >> Users

Definir:


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

## Criando os jobs utilizando o Jenkins Cli:

Importante: Execute os jobs onde se encontram os arquivos xml (diretório jenkins)

```
java -jar jenkins-cli.jar -s http://server create-job provider--shoesclick-login < provider--shoesclick-login.xml

java -jar jenkins-cli.jar -s http://server create-job server--config-service < server--config-service.xml

java -jar jenkins-cli.jar -s http://server create-job server--discovery-stack < server--discovery-stack.xml

java -jar jenkins-cli.jar -s http://server create-job api--customer < api--customer.xml

java -jar jenkins-cli.jar -s http://server create-job api--customer < api--customer.xml

java -jar jenkins-cli.jar -s http://server create-job api--product < api--product.xml

java -jar jenkins-cli.jar -s http://server create-job api--order < api--order.xml

java -jar jenkins-cli.jar -s http://server create-job service--notification < service--notification.xml

java -jar jenkins-cli.jar -s http://server create-job service--payment < service--payment.xml

java -jar jenkins-cli.jar -s http://server create-job bff--site-shoes < bff--site-shoes.xml

```


Referencia: (<SUA URL DO JENKINS>/jnlpJars/jenkins-cli.jar)



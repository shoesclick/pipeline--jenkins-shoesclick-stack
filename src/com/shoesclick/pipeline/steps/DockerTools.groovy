package com.shoesclick.pipeline.steps

import com.shoesclick.pipeline.model.Parameters

class DockerTools {

    def steps

    DockerTools(steps) { this.steps = steps }


    def build(Parameters model){
        steps.stage('Build') {
           buildImage(model)
           loginAwsEcr(model)
           createTag(model)
           pushImage(model)
        }
    }

    def removeImagesDocker(Parameters model){
        steps.stage('Finish') {
            steps.sh "docker rmi \$(docker images ${model.projectName} | awk 'NR > 1 { print \$3 }' ) --force"
        }
    }

    def buildImage(Parameters model){
        steps.sh "docker build -t ${model.projectName} ."
     }

    def loginAwsEcr(Parameters model){
        steps.sh "aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin ${model.awsAccountId}.dkr.ecr.us-east-1.amazonaws.com"
    }

    def createTag(Parameters model){
        steps.sh "docker tag \$(docker images ${model.projectName} | awk 'NR > 1 { print \$3 }' ) ${model.awsAccountId}.dkr.ecr.us-east-1.amazonaws.com/${model.awsEcrRepository}:${model.projectName}-${model.tagHash}"
    }

    def pushImage(Parameters model){
        steps.sh "docker push ${model.awsAccountId}.dkr.ecr.us-east-1.amazonaws.com/${model.awsEcrRepository}:${model.projectName}-${model.tagHash}"
    }


}


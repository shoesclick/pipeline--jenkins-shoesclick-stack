package com.shoesclick.pipeline.steps

import com.shoesclick.pipeline.model.Parameters

class DockerTools {

    def steps

    DockerTools(steps) { this.steps = steps }


    def build(Parameters model){
        steps.stage('Build') {
           buildImage(model)
           loginDockerHub(model)
           createTag(model)
           pushImage(model)
        }
    }

    def removeImagesDocker(Parameters model){
        steps.stage('Finish') {
            steps.bat "docker rmi \$(docker images ${model.projectName} | awk 'NR > 1 { print \$3 }' ) --force"
        }
    }

    def buildImage(Parameters model){
        steps.bat "docker build -t ${model.projectName} ."
     }

    def loginDockerHub(Parameters model){
        steps.bat "docker login docker.io"
    }

    def createTag(Parameters model){
        steps.bat "docker tag \$(docker images ${model.projectName} | awk 'NR > 1 { print \$3 }' ) claytonmorais/shoesclickrepo:${model.projectName}-${model.tagHash}"
    }

    def pushImage(Parameters model){
        steps.bat "docker push claytonmorais/shoesclickrepo:${model.projectName}-${model.tagHash}"
    }


}


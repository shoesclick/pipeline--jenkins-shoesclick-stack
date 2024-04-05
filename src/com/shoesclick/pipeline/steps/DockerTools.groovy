package com.shoesclick.pipeline.steps

import com.shoesclick.pipeline.model.Parameters
import com.shoesclick.pipeline.strategy.SystemCmd

class DockerTools {

    def systemCmd

    DockerTools(SystemCmd systemCmd) { this.systemCmd = systemCmd }


    def build(Parameters model){
        systemCmd.steps().stage('Build') {
           buildImage(model)
           loginDockerHub(model)
           createTag(model)
           pushImage(model)
        }
    }

    def removeImagesDocker(Parameters model){
        systemCmd.steps().stage('Finish') {
            systemCmd.cmd("docker rmi ${getImageId(model)} --force")
        }
    }

    def buildImage(Parameters model){
        systemCmd.cmd("docker build -t ${model.projectName} .")
     }

    def loginDockerHub(Parameters model){
        systemCmd.cmd("docker login docker.io")
    }

    def createTag(Parameters model){
        systemCmd.cmd("docker tag ${getImageId(model)} ${model.dckAccountId}/${model.dckRepository}:${model.projectName}-${model.tagHash}")
    }

    def pushImage(Parameters model){
        systemCmd.cmd("docker push ${model.dckAccountId}/${model.dckRepository}:${model.projectName}-${model.tagHash}")
    }

    def getImageId(Parameters model){
        return  systemCmd.cmdReturn("docker images ${model.projectName} | awk \'NR \\> 1 { print \$3 } \' ")
    }


}


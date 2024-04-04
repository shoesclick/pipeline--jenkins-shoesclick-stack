package com.shoesclick.pipeline.steps

import com.shoesclick.pipeline.model.Parameters
import com.shoesclick.pipeline.utils.FileUtils

class KubernatesTools {

    def steps

    KubernatesTools(step) {this.steps = step}

    def deployEKS(Parameters model){
        steps.stage('Deploy') {
            replaceDeploymentFile(model)
            steps.dir("k8s") {
                steps.bat 'kubectl apply -k .'
            }
        }
    }

    def replaceDeploymentFile(Parameters model){
        def fileUtils = new FileUtils("${model.workspaceJob}/k8s/deployment.yaml")
        fileUtils.replaceDeploymentYaml(model)
    }

}

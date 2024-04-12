package com.shoesclick.pipeline.steps

import com.shoesclick.pipeline.model.Parameters
import com.shoesclick.pipeline.strategy.SystemCmd
import com.shoesclick.pipeline.utils.FileUtils

class KubernatesTools {

    def systemCmd

    KubernatesTools(SystemCmd systemCmd) { this.systemCmd = systemCmd }

    def deployK8s(Parameters model) {
        systemCmd.steps().stage('Deploy') {
            replaceDeploymentFile(model)
            systemCmd.steps().dir("k8s") {
                systemCmd.cmd('kubectl apply -k .')
            }
        }
    }

    def replaceDeploymentFile(Parameters model) {
        def fileUtils = new FileUtils("${model.workspaceJob}/k8s/deployment.yaml")
        fileUtils.replaceDeploymentYaml(model)
    }

}

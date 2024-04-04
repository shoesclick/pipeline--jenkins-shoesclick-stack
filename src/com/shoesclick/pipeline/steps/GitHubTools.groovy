package com.shoesclick.pipeline.steps

import com.shoesclick.pipeline.strategy.SystemCmd

class GitHubTools{

    def systemCmd

    GitHubTools(SystemCmd systemCmd) { this.systemCmd = systemCmd }


    def checkoutSCM(){


        systemCmd.steps().stage('Checkout SCM') {

            systemCmd.steps().checkout([
                    $class: 'GitSCM',
                    branches: steps.scm.branches,
                    doGenerateSubmoduleConfigurations: steps.scm.doGenerateSubmoduleConfigurations,
                    extensions: steps.scm.extensions,
                    userRemoteConfigs: steps.scm.userRemoteConfigs
            ]);
        }
    }

    def getRevision(){
        return  systemCmd.cmd(script: "git log -n 1 --pretty=format:'%H'", returnStdout: true)
    }
}

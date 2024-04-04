package com.shoesclick.pipeline.steps

class GitHubTools{

    def steps

    GitHubTools(steps){this.steps = steps}



    def checkoutSCM(){


        steps.stage('Checkout SCM') {

            steps.checkout([
                    $class: 'GitSCM',
                    branches: steps.scm.branches,
                    doGenerateSubmoduleConfigurations: steps.scm.doGenerateSubmoduleConfigurations,
                    extensions: steps.scm.extensions,
                    userRemoteConfigs: steps.scm.userRemoteConfigs
            ]);
        }
    }

    def getRevision(){
        return steps.bat (script: "git log -n 1 --pretty=format:'%H'", returnStdout: true)
    }
}

package com.shoesclick.pipeline.steps;

import com.shoesclick.pipeline.model.Parameters;

class SonarTools {

    def steps

    SonarTools(steps) {this.steps = steps;}

    def scanProject(Parameters model) {

        steps.stage('SonarQube analysis') {
            steps.sh "${model.sonarScannerPath}/sonar-scanner -Dsonar.projectKey=${model.projectName} -Dsonar.sources=${model.workspaceJob} -Dsonar.host.url=${model.sonarServerUrl} -Dsonar.login=${model.sonarToken}"
        }
    }
}

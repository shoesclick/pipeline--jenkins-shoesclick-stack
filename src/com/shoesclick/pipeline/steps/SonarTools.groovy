package com.shoesclick.pipeline.steps;

import com.shoesclick.pipeline.model.Parameters
import com.shoesclick.pipeline.strategy.SystemCmd;

class SonarTools {

    def systemCmd

    SonarTools(SystemCmd systemCmd) { this.systemCmd = systemCmd }

    def scanProject(Parameters model) {

        systemCmd.steps().stage('SonarQube analysis') {
            systemCmd.cmd( "${model.sonarScannerPath}/sonar-scanner -Dsonar.projectKey=${model.projectName} -Dsonar.sources=${model.workspaceJob} -Dsonar.host.url=${model.sonarServerUrl} -Dsonar.login=${model.sonarToken}")
        }
    }
}

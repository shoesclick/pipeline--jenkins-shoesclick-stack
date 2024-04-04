package com.shoesclick.pipeline.languages

import com.shoesclick.pipeline.strategy.SystemCmd

class MavenTools implements LanguageTools {

    def systemCmd
    
    MavenTools(SystemCmd systemCmd) { this.systemCmd = systemCmd }

    def install() {
        systemCmd.steps().stage('Get Dependencies') {
            systemCmd.cmd("mvn clean install")
        }
    }

    def build() {
        systemCmd.steps().stage('Maven Build') {
            systemCmd.cmd("mvn clean package")
        }
    }

    def test() {
        systemCmd.steps().stage('Maven Test') {
            systemCmd.cmd("mvn clean package")
        }
    }
}
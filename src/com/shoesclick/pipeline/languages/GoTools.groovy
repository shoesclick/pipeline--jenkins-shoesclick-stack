package com.shoesclick.pipeline.languages

import com.shoesclick.pipeline.strategy.SystemCmd

class GoTools implements LanguageTools {

    def systemCmd

    GoTools(SystemCmd systemCmd) { this.systemCmd = systemCmd }

    def install() {
        systemCmd.steps().stage('Get Dependencies') {
            systemCmd.cmd("go download")
        }
    }

    def build() {
        systemCmd.steps().stage('GO Build') {
            systemCmd.cmd("go build")
        }
    }

    def test() {
        systemCmd.steps().stage('GO Test') {
            systemCmd.cmd("go test")
        }
    }
}
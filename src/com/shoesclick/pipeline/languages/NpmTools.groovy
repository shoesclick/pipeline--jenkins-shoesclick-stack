package com.shoesclick.pipeline.languages

import com.shoesclick.pipeline.strategy.SystemCmd

class NpmTools implements LanguageTools {

    def systemCmd

    NpmTools(SystemCmd systemCmd) { this.systemCmd = systemCmd }

    def install() {
        systemCmd.steps().stage('Get Dependencies') {
            systemCmd.cmd("npm install")
        }
    }

    def build() {
        systemCmd.steps().stage('NPM Build') {
            systemCmd.cmd("npm run swagger-autogen")
        }
    }

    def test() {
        systemCmd.steps().stage('NPM Test') {
            systemCmd.cmd("npm test")
        }
    }
}
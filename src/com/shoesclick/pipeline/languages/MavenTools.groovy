package com.shoesclick.pipeline.languages

class MavenTools implements LanguageTools {
    def steps
    MavenTools(steps) { this.steps = steps }

    def install() {
        steps.stage('Get Dependencies') {
            steps.sh "mvn clean install"
        }
    }

    def build() {
        steps.stage('Maven Build') {
            steps.sh "mvn clean package"
        }
    }

    def test() {
        steps.stage('Maven Test') {
            steps.sh "mvn clean package"
        }
    }
}
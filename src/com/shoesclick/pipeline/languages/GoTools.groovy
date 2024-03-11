package com.shoesclick.pipeline.languages

class GoTools implements LanguageTools {
    def steps

    GoTools(steps) { this.steps = steps }

    def install() {
        steps.stage('Get Dependencies') {
            steps.sh "go download"
        }
    }

    def build() {
        steps.stage('GO Build') {
            steps.sh "go build"
        }
    }

    def test() {
        steps.stage('GO Test') {
            steps.sh "go test"
        }
    }
}
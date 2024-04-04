package com.shoesclick.pipeline.languages

class GoTools implements LanguageTools {
    def steps

    GoTools(steps) { this.steps = steps }

    def install() {
        steps.stage('Get Dependencies') {
            steps.bat "go download"
        }
    }

    def build() {
        steps.stage('GO Build') {
            steps.bat "go build"
        }
    }

    def test() {
        steps.stage('GO Test') {
            steps.bat "go test"
        }
    }
}
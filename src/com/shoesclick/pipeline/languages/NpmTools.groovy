package com.shoesclick.pipeline.languages

class NpmTools implements LanguageTools {
    def steps
    NpmTools(steps) { this.steps = steps }

    def install() {
        steps.stage('Get Dependencies') {
            steps.bat "npm install"
        }
    }

    def build() {
        steps.stage('NPM Build') {
            steps.bat "npm run swagger-autogen"
        }
    }

    def test() {
        steps.stage('NPM Test') {
            steps.bat "npm test"
        }
    }
}
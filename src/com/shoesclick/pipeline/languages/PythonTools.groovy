package com.shoesclick.pipeline.languages

import com.shoesclick.pipeline.strategy.SystemCmd

class PythonTools implements LanguageTools {

    def systemCmd

    PythonTools(SystemCmd systemCmd) { this.systemCmd = systemCmd }

    def install() {
        systemCmd.steps().stage('Get Dependencies') {
            systemCmd.cmd("pip install -r requirements.txt")
        }
    }

    def build() {
        systemCmd.steps().stage('Python Build') {
            systemCmd.cmd("python3 manage.py makemigrations")
            systemCmd.cmd("python3 manage.py migrate")
        }
    }

    def test() {
        systemCmd.steps().stage('Python Test') {
            systemCmd.cmd("python3 manage.py test")
        }
    }
}
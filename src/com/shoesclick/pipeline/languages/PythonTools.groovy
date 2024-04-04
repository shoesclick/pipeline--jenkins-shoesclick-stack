package com.shoesclick.pipeline.languages

class PythonTools implements LanguageTools {
    def steps
    PythonTools(steps) { this.steps = steps }

    def install() {
        steps.stage('Get Dependencies') {
            steps.bat "pip install -r requirements.txt"
        }
    }

    def build() {
        steps.stage('Python Build') {
            steps.bat "python3 manage.py makemigrations"
            steps.bat "python3 manage.py migrate"
        }
    }

    def test() {
        steps.stage('Python Test') {
            steps.bat "python3 manage.py test"
        }
    }
}
package com.shoesclick.pipeline.languages

class PythonTools implements LanguageTools {
    def steps
    PythonTools(steps) { this.steps = steps }

    def install() {
        steps.stage('Get Dependencies') {
            steps.sh "pip install -r requirements.txt"
        }
    }

    def build() {
        steps.stage('Python Build') {
            steps.sh "python3 manage.py makemigrations"
            steps.sh "python3 manage.py migrate"
        }
    }

    def test() {
        steps.stage('Python Test') {
            steps.sh "python3 manage.py test"
        }
    }
}
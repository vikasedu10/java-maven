def gv

pipeline {
    agent any

    stages {
        stage("Init") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }

        stage("Test") {
            steps {
                script {
                    gv.testApp()
                }
            }
        }

        stage("Build") {
            steps {
                script {
                    gv.buildApp() 
                }
            }
        }

        stage("Deploy") {
            steps {
                script {
                    gv.deployApp()
                }
            }
        }
    }
}
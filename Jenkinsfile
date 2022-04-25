def gv

pipeline {
    agent any

    tools {
        maven "maven-3"
    }

    stages {
        stage("Init") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }

        stage("Test") {
            when {
                expression {
                    BRANCH_NAME = 'master'
                }
            }
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

    // post {
    //     always {}
    //     success {}
    //     failure {}
    // }
}
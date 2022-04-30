def gv

pipeline {
    agent any

    tools {
        maven "maven-3"
    }
    environment {
        EC2_USER = "ubuntu"
        EC2_IP = "54.242.167.113"
    }

    stages {
        stage("Initialize") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }
        
        stage("Increment the version") {
            steps {
                script {
                    echo "Incrementing the version"
                    sh "mvn builder-helper:parse-version versions:set \
                        -DnewVersion=\${parsedVersion.majorVersion}.\${parsedVersion.minorVersion}.\${parsedVersion.nextIncrementalVersion} versions:commit"
                    def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
                    def version = matcher[0][1]
                    env.IMAGE_NAME = "vikas1412/java-maven:${version}-${BUILD_NUMBER}"
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
                    gv.buildImage()
                }
            }
        }

        stage("Deploy") {
            when {
                expression {
                    BRANCH_NAME = 'master'
                }
            }
            steps {
                script {
                    gv.deployImage()
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
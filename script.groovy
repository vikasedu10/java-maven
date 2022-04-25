def testApp() {
    echo "Testing app"
    sh "mvn -v"
    sh "mvn test"
    echo "Test finished"
}

def buildApp() {
    echo "Building app"
    sh "mvn -B -DskipTests clean package"
}

def deployApp() {
    echo "Deploying app"
    def dockerCmd = "docker run vikas1412/java-maven:1.0"
    sshagent(['ssh-keypair']) {
        sh "ssh -o StrictHostKeyChecking=no ec2-user@13.235.78.154 ${dockerCmd}"
    }
}

return this

def testApp() {
    echo "Testing app"
    sh "mvn -v"
    echo "Test finished"
}

def buildApp() {
    echo "building app"
    sh "mvn package"
}

def deployApp() {
    echo "Deploy app"
    echo "deploy about to happen"
    def dockerCmd = "docker run -p 3080:3080 -d nanajanashia/demo-app:1.0"
    sshagent(['ssh-keypair']) {
        sh "ssh -o StrictHostKeyChecking=no ec2-user@13.235.78.154 ${dockerCmd}"
    }
}

return this
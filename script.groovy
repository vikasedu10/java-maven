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
    sh "deploy about to happen"
}

return this
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

def buildImage() {
    echo "Building Image"
    withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')]) {
        sh "docker build -t ${IMAGE_NAME} ."
        sh "echo $PASSWORD | docker login -u $USERNAME --password-stdin"  // Login in so deploy command in deployApp() can work without providing credentials
    }
}

def deployImage() {
    echo "Deploying image to docker-hub"
    sh "docker push $IMAGE_NAME"
}

def deployApp() {
    echo "Deploying app to ec2"
    def dockerCmd = "docker run ${IMAGE_NAME}"
    sshagent(['ec2-ssh-keypair']) {
        sh "ssh -o StrictHostKeyChecking=no $EC2_USER@$EC2_IP ${dockerCmd}"
    }
}

return this

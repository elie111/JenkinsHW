pipeline {
    
  
    
       agent {label 'node-01'}
    stages {
        stage('Clone') {
            steps {
                    git branch: "main",url:'https://github.com/elie111/JenkinsHW.git'
            }
        }
        
            
        stage('Build') {
            steps {
                sh 'mvn clean install assembly:single'
            }
        }

        
        stage('Artifact') {
            steps{
                archiveArtifacts artifacts: '**/*.jar',followSymlinks: false
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
                sh 'java -jar target/simplehttpserver-1.0-SNAPSHOT-jar-with-dependencies.jar'
               
            }
        }
        stage('Slack Notifications') {
            steps{
                slackSend baseUrl: 'https://hooks.slack.com/services/',
                channel: '#fursa-hw3',
                color: 'good',
                message: 'project has been built',
                teamDomain: 'fursaHW',
                tokenCredentialId: 'slack-hw'
            }
        }

    }
}

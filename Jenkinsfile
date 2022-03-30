pipeline {
    agent {
        docker {
            image 'maven:3.8.1-adoptopenjdk-11'
        }
    }
    stages {
        //Build jar
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }

        //Execute unit test
        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }


        //Execute jar
        stage('Run'){
            steps{
                sh 'java -jar  ./target/netflix-1.0.0.jar  netflix_titles.csv'
            }
        }
        //Copy file html on the server
        stage('Move HTML content in apache2'){
            steps{
                sh 'rm -rf /out/*'
                sh 'mv ./out /out'
            }
        }

        stage('SonarQube Analysis'){
            steps{
                withSonarQubeEnv('sonar_netflix'){
                    sh "mvn verify sonar:sonar"
                }
            }
        }
    }
    post {
        cleanup {
            cleanWs deleteDirs: true
        }
        success{
            step([$class: 'JacocoPublisher'])
        }
    }
}
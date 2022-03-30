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
                sh 'java -jar  ./target/sonar_netflix_victor-1.0.0.jar  netflix_titles.csv'
            }
        }


        //Copy file html on the server
        //stage('Move HTML content in apache2'){
        //    steps{
        //        sh 'mv ./out /out'
        //        sh 'rm -rf /out/*'
        //    }
        //}

        //Copy file html on the server nicolas rousseau
        stage('Move HTML content in server_rousseau'){
            steps{
                //Copy file ssh
                sshPublisher(
                    continueOnError: false, failOnError: true,
                    publishers: [
                      sshPublisherDesc(
                       configName: "server_rousseau",
                       verbose: true,
                       transfers: [
                        sshTransfer(
                         sourceFiles: "./out/*.html",
                         remoteDirectory: "/"
                        )
                       ])
                    ]
                )

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
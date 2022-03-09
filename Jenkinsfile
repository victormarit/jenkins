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

        //Faire les tests
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

    }
}
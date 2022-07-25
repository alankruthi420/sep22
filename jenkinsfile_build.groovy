pipeline{
    agent any
    parameters{
        string(name: 'BRANCH', defaultValue: '', description: '')
    }
    stages{
        stage("clone a code"){
            steps{
                println "clone a code"
                git branch:"${BRANCH}",
                url:'https://github.com/KuruvaSomaSekhar/boxfuse-sample-java-war-hello.git'
            }
        }
        stage("build"){
            steps{
                println "build a code"
                sh "mvn clean package"
                sh "ls -l"
            }
        }
        stage("uploading the artifacts"){
            steps{
                println "uploading the artifacts to s3"
                sh "aws s3 cp target/hello-*.war s3://brandss/${BRANCH}/"
            }
        }
    }
}
pipeline {

    agent any

    tools {
        jdk 'Jdk-21'
        maven 'Maven-3.9'
    }

    environment {
        DB_USERNAME = credentials('db-username')
        DB_PASSWORD = credentials('db-password')
    }

    stages {

        stage('Checkout Source Code') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/nandini207/billdesk-jenkins.git'
            }
        }

        stage('Compile') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Package Application') {
            steps {
                bat 'mvn package -DskipTests'
            }
        }

        stage('Stop Existing Application') {
    steps {
        bat '''
        @echo off

        set PID=

        for /f "tokens=5" %%a in ('netstat -ano ^| findstr :9090') do (
            set PID=%%a
        )

        if defined PID (
            echo Stopping application running on port 9090...
            taskkill /F /PID %PID%
        ) else (
            echo No application is running on port 9090.
        )

        exit /b 0
        '''
    }
}

        stage('Deploy Application') {
            steps {
                bat '''
                @echo off
                echo Starting Spring Boot Application...

                powershell -Command "Start-Process java -ArgumentList '-jar','target\\products-crud-0.0.1-SNAPSHOT.jar' -WindowStyle Hidden"

                timeout /t 10 > nul

                echo Application Started Successfully.
                exit /b 0
                '''
            }
        }

    }

    post {

        success {
            echo 'Pipeline executed successfully.'
        }

        failure {
            echo 'Pipeline failed.'
        }

    }
 }
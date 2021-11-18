node {
    stage('git clone') {
        git branch: 'main',
            url: 'https://github.com/khursik/MyProject/'

    }
    stage('build') {
        sh 'mvn -f pom.xml clean verify'
    }
    stage('test') {
        sh 'mvn -f pom.xml test'
    }
    stage('sonar_qube') {
        def scannerHome = tool 'MySonar';
        withSonarQubeEnv('MySonar') {
            sh "${scannerHome}/bin/sonar-scanner \
            -Dsonar.projectKey=pipeline \
            -Dsonar.sources=java/src/main \
            -Dsonar.tests=java/src/test \
            -Dsonar.java.binaries=java/target/classes \
            -Dsonar.junit.reportPaths=java/target/surefire-reports \
            -Dsonar.coverage.jacoco.xmlReportPaths=java/target/site/jacoco/jacoco.xml"
        }
    }
    stage('allure') {
        allure([
                includeProperties: false,
                jdk: '',
                properties: [],
                reportBuildPolicy: 'ALWAYS',
                results: [[path: 'java/target/allure-results']]
            ])
    }
    stage('deploy') {
        withCredentials([file(credentialsId: 'aazhogov_ansible_pass', variable: 'VAULT_PASSWORD')]) {
            sh 'ansible-playbook deploy/playbook.yml --vault-password-file $VAULT_PASSWORD -i deploy/servers.hosts'
        }
    }
}
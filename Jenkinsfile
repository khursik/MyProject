node {
    stage('git clone') {
        git branch: 'master',
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
            -Dsonar.projectKey=khursik:FreestyleJob \
            -Dsonar.sources=src/main \
            -Dsonar.tests=src/test \
            -Dsonar.java.binaries=target/classes \
            -Dsonar.junit.reportPaths=target/surefire-reports \
            -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml"
        }
    }
    stage('allure') {
        allure([
                includeProperties: false,
                jdk: '',
                properties: [],
                reportBuildPolicy: 'ALWAYS',
                results: [[path: 'target/allure-results']]
            ])
    }
    stage('Ansible') {
            ansiblePlaybook(vaultCredentialsId: 'json-card-reader',
                            inventory: 'Ansible/servers.hosts',
                            playbook: 'Ansible/playbook.yml')
        }
/*     stage('deploy') {
        withCredentials([file(credentialsId: 'json-card-reader', variable: 'VAULT_PASSWORD')]) {
            sh 'ansible-playbook Ansible/playbook.yml --vault-password-file $VAULT_PASSWORD -i Ansible/servers.hosts'
        }
    } */
}
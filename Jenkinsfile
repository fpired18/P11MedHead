node{
  stage('SCM Checkout') {
    git 'https://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@github.com/fpired18/P11MedHead.git'
  }
  stage('Compile-Package') {
     def mvnHome = tool name: 'maven-3', type: 'maven'
    sh "${mvmHome}/bin/mvn package"
  }
  
}

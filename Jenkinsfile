node{
  stage('SCM Checkout') {
    git 'https://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@github.com/fpired18/P11MedHead.git'
  }
  stage('Compile-Package') {
     def mvnHome = tool name: 'maven-3', type: 'maven'
    sh "${mvmHome}/bin/mvn package"
  }
  
}

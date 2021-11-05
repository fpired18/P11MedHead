node{
  stage('SCM Checkout') {
    git 'https://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@github.com/fpired18/P11MedHead.git'
  }
  stage('Compile-Package') {
     def mvnHome = tool name: 'maven-3', type: 'maven'
    sh "${mvmHome}/bin/mvn package"
  }
  
}

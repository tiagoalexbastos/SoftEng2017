node {
    
    sh 'sudo rm -rf *'
    
    def mvnHome
    
    
   stage('Preparation') { // for display purposes
      // Get some code from a GitHub repository
      // Get the Maven tool.
      // ** NOTE: This 'M3' Maven tool must be configured
      // **       in the global configuration.           

        git 'https://github.com/Pedro-Matos/ES_G0402.git'
      
      dir('docker-compose'){
        sh 'sudo pip install docker-compose'
        sh 'sudo chmod +x /usr/local/bin/docker-compose'
        sh 'sudo -H docker-compose up -d'
      }
     
      sh 'sleep 20'
      
      mvnHome = tool 'maven'

     sh 'sudo mkdir -p dashboard'
     
     dir ('dashboard'){
         sh 'sudo mkdir -p dashboard'
     }
      
      dir('dashboard_jsf'){
          sh 'mvn package'
      }
      
      dir('dashboard_jsf/target'){
          sh 'sudo mv dashboard-1.0-SNAPSHOT/*  ../../dashboard/dashboard'
      }
      
      
      sh 'sudo mkdir -p SensorGateway'
     
     dir ('SensorGateway'){
         sh 'sudo mkdir -p SensorGateway'
     }
      
      dir('SensorGateway_code'){
          sh 'mvn package'
      }
      
      dir('SensorGateway_code/target'){
          sh 'sudo mv SensorGateway-1.0-SNAPSHOT/*  ../../SensorGateway/SensorGateway'
      }
      
   }
}

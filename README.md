# simple_selenide_test

Require:
  Java 
  TestNG
  Maven;
  Chrome,
  Allure.
  
Instalation:
  Java:
    First, update the package list:

     $ sudo apt-get update
  
    Then install Java. This command will install the Java Runtime Environment (JRE):

     $ sudo apt-get install default-jre

    Now install JDK using the following command:

     $ sudo apt-get install default-jdk
     
  TestNg:
    
     $ sudo apt-get install testng
     
  Maven:
    
     https://maven.apache.org/install.html
     
  Install Chrome:
  
      $ wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | sudo apt-key add -
  
    Set repository:
      $ sudo sh -c 'echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list'
  
    Install package:
      $ sudo apt-get update
      $ sudo apt-get install google-chrome-stable

  Install chromedriver:

      $ npm install -g chromedriver
   
How to run:
    $ mvn test
   For genarating report:
    $ mvn site
    $ mvn jetty:run

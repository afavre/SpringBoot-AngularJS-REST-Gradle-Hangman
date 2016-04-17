# Spring boot - AngularJS - REST - Gradle - Hangman Sample


##### Sample for the use of Spring Boot, AngularJS with REST exchange for a Hangman Game with a minimalist UI


**Usage**

* Download bower dependencies

    bower install
	
* Generate the war archive with Gradle to deploy in Tomcat
    
    gradle build
	
* Deploy the war archive in your Tomcat
	
* Open a browser at the address: http://localhost:8080/hangman

* To import the project inside Eclipse:

    gradle cleanEclipse
    gradle eclipse
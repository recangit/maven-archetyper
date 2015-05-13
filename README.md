Om förändringar görs i denna mall så måste den installeras på nytt: mvn install

För att använda mina archetyper:

Denna skapar en enkel Java-applikation.
mvn archetype:generate -DarchetypeGroupId=se.recan -DarchetypeArtifactId=create-app-archetype -DarchetypeVersion=1.0.0 -DgroupId=se.recan.pm -DartifactId=selenium-devel
Exekvera den med: mvn clean compile exec:java

Denna skapar en Cucumber-applikation.
mvn archetype:generate -DarchetypeGroupId=se.recan -DarchetypeArtifactId=create-cucumber-archetype -DarchetypeVersion=1.0.0 -DgroupId=se.recan.bla -DartifactId=NameOfMyNewApp

Denna skapar en Selenium-applikation.
mvn archetype:generate -DarchetypeGroupId=se.recan -DarchetypeArtifactId=create-selenium-archetype -DarchetypeVersion=1.0.0 -DgroupId=se.recan.selenium -DartifactId=framework

Denna skapar en Mojo
mvn archetype:generate -DarchetypeGroupId=se.recan -DarchetypeArtifactId=create-plugin-archetype -DarchetypeVersion=1.0.0 -DgroupId=se.recan.bla -DartifactId=NameOfMyNewMojo
mvn archetype:create -DarchetypeGroupId=se.recan -DarchetypeArtifactId=create-plugin-archetype -DarchetypeVersion=1.0.0 -DgroupId=se.recan.bla -DartifactId=NameOfMyNewMojo
mvn archetype:create -DarchetypeGroupId=se.recan  -DarchetypeArtifactId=create-plugin-archetype -DgroupId=se.recan.bla -DartifactId=NameOfMyNewMojo

Denna skapar en Web-applikation
mvn archetype:generate -DarchetypeGroupId=se.recan -DarchetypeArtifactId=create-webapp-archetype -DarchetypeVersion=1.0.0 -DgroupId=se.recan.bla -DartifactId=NameOfMyWebapp

## Introduction
This project tests the flow of selecting a lowest fixed price from the selected event/race in unibet racing web application and validates the elements on bet slip

## Pre-requisites
1) Java  - Recommend using any of the versions above 9.

2) Maven - used for managing all the dependencies of this project.
    * Download maven @ https://maven.apache.org/download.cgi
    * To setup maven, refer:
        - https://mkyong.com/maven/how-to-install-maven-in-windows/

        - https://www.baeldung.com/install-maven-on-windows-linux-mac
        
3) IDE - Recommend installing Eclipse IDE for simplicity purposes. Any other compatible IDEs can be used as well Eg: IntelliJ, Visual Studio code

Note: Used JDK 14, Maven 3.5.2 and Eclipse IDE for setting up this project.

## Project dependencies
* Framework: TestNG
* Reporting library: ExtentReports
* WebDriver binding: Selenium-Java
* WebDriver Management: WebDriverManager

Refer pom.xml for specific versions of these dependencies

Note: Execution/Runtime Environment (in IDE) should point to JRE corresponding to the latest JAVA version installed Eg: JavaSE-14

## Design patterns followed
Page object model

## Setup
If maven is setup on your machine, all you have to do is clone this project in your machine and import this as maven project in your IDE


## How to run the tests
### via cmd
- Navigate to the project root folder
- Run the command `mvn clean test` ( As the testng.xml is specified in pom.xml, this command will pick testng.xml under the project directly)
- View the test run summary

### via Eclipse
- Install TestNG-Eclipse plugin ( Refer `Eclipse plug-in` section in https://testng.org/doc/download.html)
- Right click testng.xml and select the option `Run as TestnG Suite`
- View the test run summary in the IDE console

Once the test(s) is/are run, execution report will be generated and pushed to `Reports` folder under the project. Navigate to the project location and validate the `ExecutionReport.html` file

Note: Once the test(s) is/are executed, browser session is not closed intentionally. This is just to give a reference to the automation flow. If the line of code `driver.quit();` is uncommented in `BaseTest.java` file, browser session will be closed once the execution is complete.


## Considerations
1) On smaller screens i.e. laptops, `next to go events` section is not displayed. For the time being, this issue is dealt by zooming out once programatically. Usually on bigger screens, this may not be a problem. Reducing browser size is not a sustainable solution as tests should run at the default zoom i.e. 100% else, there can be unexpected results sometimes.

2) To get similar results across the browsers, specific resolution will have to be set manually (or) programatically. This is not dealt in the project as that is a decision made with consent.

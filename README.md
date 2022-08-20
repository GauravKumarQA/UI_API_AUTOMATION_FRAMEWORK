<h1 align="center">
	Unzer Automation project
</h1>

<h3 align="center">
	This project is for Unzer automation assignment
</h3>

<h4 align="center">
	Status: 🚀 Finished
</h4>

## Solution for Assignment
 - Created a Test case as per the description.[API automation and UI automation]
   - Test cases include Precondition : Deleting all boards to make test enviroment clean before new execution,This step we have   Automated via API automation.
   - Assignement part 1[API automation]: Create a board using API's.
   - Assignement part 2[ UI automation]: Now login with UI and perform few actions on created board. [UI Automation] , For this implimentation we lauched the    chrome browser using selenium

- Technologies involved
    - Core Language: Java [Open JDK for maven]
    - UI automation tools: Selenium , webdrivermanager
    - API Automation tools: Rest Assured
    - Test runner : TestNG
    - Reporting tool: Extent Report
    - Build tool: Maven 
    - Logging tool: Log4J

## Tech Stack
<img src="https://img.shields.io/badge/Git-05122A?style=flat&logo=git" alt="git Badge" height="25">&nbsp;
<img src="https://img.shields.io/badge/Java-05122A?style=flat&logo=java" alt="java Badge" height="25">&nbsp;

## How to run
To Run a test, follow the steps above:
```JAVA
#Steps:
    - Prerequisites: JAVA and Maven should be installed
    - You need to add following details in constant.java location:AutomationProject\src\test\java\utils
        - TRELLO_APIKEY
        - TRELLO_Token
        - TRELLO_USERNAME
        - TRELLO_PASSWORD
    - Clone the repository
    - open cmd and navigate to the cloned repository
    - Go inside AutomationProject folder [Make sure you are in the same directory where we have Pom.xml]
    - Run command : mvn test [it will take few minute for first time run]
    - Execution results can be viewed from the CLI
    - Extest report link is present in the console logs , you can copy paste and open it in browser
    

```
## ExecutionExample
<img src="/images/ReportExample.png" alt="extent report image" height="200" width="400">&nbsp;
<img src="/images/executionData.png" alt="execution image" height="200" width="400">&nbsp;


## Architechture
<img src="/images/Architecture.png" alt="execution image" height="200" width="400">&nbsp;

This is the first step to create a mulitplatform platform with one test case design pattern.
This framework is highly scalable and easy to maintain.
For details we can discuss.

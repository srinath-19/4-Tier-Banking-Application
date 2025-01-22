# Team Number : Final Project Team 24 
# Team Members : 
* Shashidhar Reddy Ananthula(shan4427)
* Srinath Muppala (srmu3557)
    

# 4-tier Banking Application
### Project Overview
In this project, we have facilitated a seamless banking application with a 4-tier architecture.
	 
		Layer-1: Presentation Layer (Frontend)
		Layer-2: Application Layer (Business logic)
		Layer-3: Message Queue Layer (Redis)
		Layer-4: Data Layer (database)

We have utilized Spring Boot, MySQL, Redis, Gcloud SQL, Gcloud Redis, Google App Engine.

#### PreRequisites
* Java
* SpringBoot
* MySQL Workbench
* Redis Server
* IntelliJ Idea
* Apache maven
* Google Cloud

### How to run the application

#### Running the application locally
1. Clone the repository into intelliJ Idea
2. Now go to pom.xml file and configure the dependencies
3. Add the username and password of your SQL WorkBench in application.properties
4. Start the Redis server
5. Now start the application using BankappApplication.java, application will start at
   ```
   http://localhost:8080
   ```
7. Open the Redis terminal and use the following commands to the application is connected to the server or not
   ```
   $ redis-cli monitor
   $ SUBSCRIBE Redis-Queue
   ```
8. Once the application started open the MySQL Workbench and give the following queries to check if the database is getting populated or not
   ```
   show databases;
   use bankappdb;
   show tables;
   select * from account;
   select * from transaction;

#### Deploying the application on google cloud

1. Make a jar file of the application using the maven command 
   ```
   $ mvn clean install -Dskiptests -X
   ```
    open the cmd at home directory then use the above command
    The jar file will be created in the target folder (**WE HAVE ALREADY ATTACHED THE JAR FILE IN TARGET FOLDER**)
2. Download the google SDK and login to your google account under which the projects are hosted
3. Configure the google cloud SQL and Redis in your google cloud account and add the details of the instances in your appliaction.properties for connection establishment
4. Now, open the command prompt inside the target folder and use the below commands
   ```
   $ gcloud app deploy bankapp-0.0.1-SNAPSHOT.jar
   ```
5. Now, go to google app engine dashboard and open services, there you can find the link for the application click on the link so that the appliation will open
6. use the above mentioned SQL and Redis queries in the instances cmd, with these you can check for the data and actions performed in the application  
   



## CRUD OPS + RMI + GUI

A simple application that performs the basic CRUD operations to a normal user registration form coupled with the basics of RMI to have remote access to the functionalities of the application from a remote or local device.

## Folder Structure

    RMI_Calculator/
    |-- src/
    | |-- server/
    | | |-- DbCrud.java // Remote interface
    | | |-- DbCrudImpl.java // Remote interface implementation
    | | |-- DbConnector.java // Db connector
    | | |-- RMIServer.java // RMI server
    |
    | |-- client/
    | | |-- RMIClient.java // RMI client
    | | |-- GUI/
    | | | |-- ClientMainGUI.java
    | | | |-- CreateGUI.java
    | | | |-- RetrieveGUI.java
    | | | |-- UpdateGUI.java
    | | | |-- DeleteGUI.java
    |
    | |-- .env
    |
    |-- build/
    | |-- (compiled .class files)
    |
    |-- README.md

## Dependency Management

-   [mysql connector to java]() jar
    -   protobuf java jar
-   [dotenv](https://jar-download.com/artifact-search/java-dotenv) jar , to get the env variables into the program
    -   annotations jar
    -   kotlin stdlib jar
    -   kotlin stdlib common jar

Just copy thus jar files into the lib folder and your all set 😊

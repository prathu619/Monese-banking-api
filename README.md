# Monese Banking API

A sample banking API which can be used for the below use cases:

* Money Transfer between accounts.
* Requesting the Account statement

# Methods

* Exposes below HTTP ENDPOINTS:
  - PUT - Sending money between accounts
  - GET - Fetch account statement with account balance and list of transactions

# LOCAL SETUP

## Database Setup

It uses in memory h2 database with default credentials hence no external installations required.
Tables and data would be initialised once database is up and running.

Note : For Actual implementation in memory databases are not recommended 

## Code setup

### Step 1 :

* Checkout the source code using below command in your IDE:
```
https://github.com/prathu619/Monese-banking-api.git
```

### Step 2
* Navigate to the root folder and execute below command (maven and java 8 should with installed on your system):

```
mvn clean package
```

### Step 3

* On a successful build execute the below command in terminal:
```
java -jar target/monese-banking-api-0.0.1-SNAPSHOT.jar

```

### Step 4

* Open URL 'http://localhost:8080/actuator/info'. We should get a json response with application information.

### Step 5

Application is now up and running.

## Testing the application

### Step 1

* Download postman from internet

### Step 2

* Import the collection monese-banking-api.postman_collection.json

### Step 3

* Make sure that the Application is running and then execute the Request in collection

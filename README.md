Things to finish:
* README
* [WIP] Swagger
* Test coverage
* [WIP] caching of the exchange rates
* moving the API key to a secure place
* commenting the code

Implemented web service should be resilient to 3rd party service unavailability:
1. Using RetryTemplate to retry the Exchange Rate API request for 5 times 
2. Using timeouts of 5 seconds for the HTTP request to the Exchange Rate API
3. [WIP] Persisting the exchange rates in case of unavailability of the API to work with the latest available rates

Using Liquibase for the DB schema versioning should be implemented

## Running the project
create database called mintos

The application will create the necessary database tables and add sample accounts to the accounts table. It's possible to make transfers only from the EUR accounts as that's the API limitation from the API in use (https://api.exchangerate.host/latest)

## List of API requests
I've added a Postman collection to the project so that you could easily import the collection to make the API requests.


### Get Transaction history 

Path: GET /v1/transactions/history/{accountID}

Note: If you have two transactions, pass offset = 1, limit = 1 to get the older one
and just limit=1 or with offset=0 to get the newer one

### Make a transfer 

Path: POST /v1/transactions/create

### Find an account by client ID
Path: GET /v1/accounts/find/clientID/{clientID}

### Extra call: get list of all accounts

Path: GET /v1/accounts

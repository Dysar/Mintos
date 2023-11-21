Things to finish:
* README
* Swagger
* Test coverage
* caching of the exchange rates
* moving the API key to a secure place
* commenting the code

Implemented web service should be resilient to 3rd party service unavailability:
1. Using RetryTemplate to retry the Exchange Rate API request for 5 times 
2. Using timeouts of 5 seconds for the HTTP request to the Exchange Rate API
3. Persisting the exchange rates in case of unavailability of the API to work with the latest available rates

Using Liquibase for the DB schema versioning should be implemented

How to run
create database called mintos


Transaction history:
Note: offset does not work without the limit parameter.
If you have two transactions, I pass offset = 1, limit = 1 to get the older one
and just limit=1 or with offset=0 to get the newer one
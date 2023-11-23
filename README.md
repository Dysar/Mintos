# Mintos Test Assignment

Non-functional requirements:
1. Test coverage should be not less than 80% - I was able to cover the code with 64% coverage and that's the only requirement that was unmet. My problem was the @Value annotations that I used in the `ExchangeRateServiceImpl`. I tried multiple approaches but was unable to make the tests set the `@Values` as needed.
2. Implemented web service should be resilient to 3rd party service unavailability:
   1. Using `RetryTemplate` to retry the Exchange Rate API HTTP requests for 5 times 
   2. Using timeouts of 5 seconds for the HTTP request to the Exchange Rate API
   3. Caching - Persisting the exchange rates in case of unavailability of the API to work with the latest available rates
3. DB schema versioning should be implemented - Here I used **Liquibase** to version the database schema.

## Running the project

1. Create database called mintos
2. Create a file under `src/main/resources/secrets.properties` with the following content and replace the placeholder with the https://exchangeratesapi.io API key

```properties
api.key=<the API key>
```

3. Run the app with Intellij IDEA or the Maven CLI

```bash
mvn spring-boot:run
```

4. Open up the Postman collection or use the http://localhost:8080/ URL in the browser to make the API calls that are listed below.

The application will create the necessary database tables and add sample accounts to the accounts table. 
It's possible to make transfers only from the EUR accounts as that's the API limitation from the API in use (https://api.exchangerate.host/latest)

## List of API requests
I've added a Postman collection to the project so that you could easily import the collection to make the API requests.


### Get Transaction history 

Path: `GET /v1/transactions/history/{accountID}`

Note: If you have two transactions, pass `offset = 1`, `limit = 1` to get the older one
and just `limit=1` or with `offset=0` to get the newer one

### Make a transfer 

Path: `POST /v1/transactions/create`

### Find an account by client ID
Path: `GET /v1/accounts/find/clientID/{clientID}`

### Extra call: get list of all accounts

Path: `GET /v1/accounts`



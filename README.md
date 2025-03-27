# bank-account-handling
# Description
REST API for handling bank account functions. Spring Boot application with H2 database.


Ability to create new accounts.  
Able to request all Account balance which includes all currencies as seperate balances.  
Add funds to a specific currency in an account.   
Debit funds from a specific currency from an account.   
Able to exchange from one currency to another with a fixed rate if funds are available.  
Currently supports only 4 currencies for simplicity. (EUR, USD, SEK, RUB).  
Currently Database is automatically filled with one Account on start up and refreshes to that state on each restart  

# Setup Instructions

Clone repository to local machine.  
Update dependencies.  
Run BankAccountHandlingApplication.  
H2 db starts automatically with console enabled at localhost:8080/h2-console. Password: "pass" Username: "root".  
To test requests can use Postman.  

# API Documentation

| Method  | URL | Description | Controller | Request Body | Request Example | Response Example | Response |
| ------------- | ------------- | ------------- | ------------- | ------------- | ------------- | ------------- | ------------- |
| GET  | /api/account  | Simulates an external system call  | AccountController  | - | - | "{"code":200,"description":"OK"}"  | String  |
| POST  | /api/account  | Creates a new Balance Account in DB  | AccountController  | {"accountNumber": String} | {"accountNumber": "0000_TEST1"} |  "Account created successfully." | String |
| GET  | /api/balance/{accountNumber}  | Returns list of AccountBalance objects which contain their currency and amount in cents | AccountBalanceController  | - | - | {"accountBalances": [{"accountNumber": "0000_TEST","currency": "USD","amountInCents": 0},{"accountNumber": "0000_TEST","currency": "EUR","amountInCents": 0},{"accountNumber": "0000_TEST","currency": "SEK","amountInCents": 0}{"accountNumber": "0000_TEST","currency": "RUB","amountInCents": 0}]}  | Account Balance List |
| POST  | /api/balance/add  | Deposit a specific amount of money to an account in specified currency | AccountBalanceController  | {"accountNumber": String, "currency": String, "amountInCents": Long} | {"accountNumber": "0000_TEST","currency": "USD","amountInCents": 500} |  "Added funds successfully." | String |
| POST  | /api/balance/debit  | Debit a specific amount of money from an account in specified currency if possible | AccountBalanceController  | {"accountNumber": String, "currency": String, "amountInCents": Long} | {"accountNumber": "0000_TEST","currency": "USD","amountInCents": 100} |  "Debited funds successfully!" | String |
| POST  | /api/balance/exchange  | Exchange a specific amount of money from one currency to another  | AccountBalanceController  | {"accountNumber": String, "currencyFrom": String, "currencyTo": String, "amountInCents": Long} | {"accountNumber": "0000_TEST","currencyFrom": "USD","currencyTo": "EUR","amountInCents": 100} |  "Exchanged currencies successfully!." | String |
# Additional Notes
Currently supported currencies are. (USD, EUR, SEK, RUB). In a real situation would have kept them in a seperate database table with an additional table for conversion rates to either USD or to every currency.
Currently they are hard coded.
Probably should have wanted to add Swagger support for simpler REST API documentation and testing.

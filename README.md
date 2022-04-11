# Bank API

## Steps to replicate the environment

1 - On the DatabaseInstructions.md have the steps to create and insert the default datas on the database.

2 - After create and insert the default datas, run this application on the src/main/java/main/App.java

3 - It is recommended to use the postman app to use all the endpoints.

## Endpoints

1 - Create checking account

URL: `http://localhost:8080/checkingAccount/create`

BodyRequest:
>{  
>"accountHolderDTO": {  
>"name": "Edu",  
>"cpf": "987654",  
>"birthDay": "2000-07-17"  
>},  
>"agencyDTO": {  
>"id": 2  
>},  
>"limit": 0.0,  
>"balance": 1000.0,  
>"isActive": "T"  
>}

2 - Get balance account

URL: `http://localhost:8080/checkingAccount/getBalance`

BodyRequest:
>{  
>"id": 10,  
>"agencyDTO": {  
>"id": 2  
>}  
>}

3 - Make deposit

URL `http://localhost:8080/checkingAccount/deposit/100`

BodyRequest:
>{  
>"id": 10,  
>"agencyDTO": {  
>"id": 2  
>}  
>}

4 - Make withdrawal

URL `http://localhost:8080/checkingAccount/withdrawal/100`

BodyRequest:
>{  
>"id": 10,  
>"agencyDTO": {  
>"id": 2  
>}  
>}

5 - Make transference

(Change the originId and value in the URL)
URL `http://localhost:8080/checkingAccount/transference?originId=10&value=100.0`

BodyRequest:
>[{  
>"id": 10,  
>"agencyDTO": {  
>"id": 2  
>}  
>},{  
>"id": 11,  
>"agencyDTO": {  
>"id": 1  
>}  
>}]

6 - Find account holder information

(You can change the URl to change the cpf)
URL `http://localhost:8080/checkingAccount/654321`

7 - Disable account

URL `http://localhost:8080/checkingAccount/disableCheckingAccount`

RequestBody
>{  
>"id": 10,  
>"agencyDTO": {  
>"id": 2  
>}  
>}
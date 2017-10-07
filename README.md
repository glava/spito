# spito

# http api

1. create transaction

```
POST http://127.0.0.1:8080/transactions/new HTTP/1.1
content-type: application/json

{
 "sender": "my address",
 "recipient": "someone else's address",
 "amount": 5
}
```

2. mine endpoint

```
GET http://127.0.0.1:8080/mine HTTP/1.1
```

3. chain endpint

```
GET http://127.0.0.1:8080/chain HTTP/1.1
```

# spito

I created this project by following the great article [Learn Blockchains by Building One](https://hackernoon.com/learn-blockchains-by-building-one-117428612f46).

I documented learning process in commit messages. The project name is of course chosen by the great http://polumenta.zardina.org/

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

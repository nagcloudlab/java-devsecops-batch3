



curl -X POST \
http://localhost:8080/authenticate \
-H 'Content-Type: application/json' \
-H 'Accept: application/json' \
-d '{
"username": "joe",
"password": "password"
}' -i


curl -X POST \
http://localhost:8080/api/v1/transfer \
-H 'Content-Type: application/json' \
-H 'Accept: application/json' \
-d '{
"fromAccount": "123456789012",
"toAccount": "123456789013",
"amount": 100.00
}' -i 
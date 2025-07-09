


```bash
curl -X POST \
http://localhost:8080/api/v1/transfer \
-H 'Content-Type: application/json' \
-H 'Accept: application/json' \
-d '{
"fromAccountNumber": "1234567890",
"toAccountNumber": "0987654321",
"amount": 100.00
}' -i 
```
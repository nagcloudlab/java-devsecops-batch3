



curl -X POST \
http://localhost:8080/authenticate \
-H 'Content-Type: application/json' \
-H 'Accept: application/json' \
-d '{
"username": "joe",
"password": "password"
}' -i


curl -X POST \
http://localhost:8181/api/v1/transfer \
-H 'Content-Type: application/json' \
-H 'Accept: application/json' \
-H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJucGNpIiwic3ViIjoiam9lIiwiYXVkIjpbImF1ZGllbmNlLWV4YW1wbGUiXSwiaWF0IjoxNzUxNDM2MjAyLCJleHAiOjE3NTE0Mzk4MDIsImp0aSI6ImI4MjBjYTlkLTVjZGItNDVkMC04ZTYwLTViOGZiODY5ODhiZSJ9.WxxFgJPzelvFLIArQgYTJQX8dJzWE6QftvvYNEx7RtI' \
-d '{
"fromAccount": "123456789012",
"toAccount": "123456789013",
"amount": 100.00
}' -i 
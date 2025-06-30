


create postgres database container

```bash
docker run --name postgres -e POSTGRES_PASSWORD=postgres -d -p 5433:5432 postgres
docker exec -it postgres psql -U postgres
docker start postgres
```

create database

```sql
CREATE TABLE ACCOUNTS
(
    account_number      VARCHAR(12) PRIMARY KEY,
    account_holder_name VARCHAR(100)   NOT NULL,
    BALANCE             NUMERIC(10, 2) NOT NULL
);
insert into ACCOUNTS (account_number, account_holder_name, balance) values ('' ||
                                                                            '', 'John Doe', 1000.00);
insert into ACCOUNTS (account_number, account_holder_name, balance) values ('123456789013', 'jane Doe', 1000.00);
```

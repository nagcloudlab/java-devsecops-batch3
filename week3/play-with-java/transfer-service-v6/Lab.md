

# install docker

```bash
sudo apt update -y
sudo apt install apt-transport-https ca-certificates curl software-properties-common -y
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu focal stable"
apt-cache policy docker-ce
sudo apt install docker-ce -y
sudo systemctl status docker
sudo usermod -aG docker ${USER}
docker version
````

# create postgres container

```bash
docker run --name postgres -e POSTGRES_PASSWORD=postgres -d -p 5433:5432 postgres
docker exec -it postgres psql -U postgres
docker start postgres
```

# create database and table

```sql
CREATE TABLE ACCOUNTS
(
    account_number      VARCHAR(12) PRIMARY KEY,
    account_holder_name VARCHAR(100)   NOT NULL,
    BALANCE             NUMERIC(10, 2) NOT NULL
);
insert into ACCOUNTS (account_number, account_holder_name, balance) values ('123456789012', 'john Doe', 1000.00);
insert into ACCOUNTS (account_number, account_holder_name, balance) values ('123456789013', 'jane Doe', 1000.00);
```





create cassandra container

```bash
docker run --name cassandra -p 9042:9042 -d cassandra:4.1
docker exec -it cassandra cqlsh
```

# create keyspace and table

```sql
CREATE KEYSPACE IF NOT EXISTS transfer_app
WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

USE transfer_app;

CREATE TABLE transfer_history (
  id uuid PRIMARY KEY,
  fromaccount text,
  toaccount text,
  amount decimal,
  timestamp timestamp
);

SELECT * FROM transfer_history;

```

package com.npci.model;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Table("transfer_history_by_hour")
public class TransferHistory_By_Hour {

    @PrimaryKey
    private TransferKey key;

    @Column("from_account")
    private String fromAccount;

    @Column("to_account")
    private String toAccount;

    @Column("amount")
    private BigDecimal amount;

    // ---- Getters & Setters ----
    public TransferKey getKey() {
        return key;
    }

    public void setKey(TransferKey key) {
        this.key = key;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    // ---- Composite Key Class ----
    @PrimaryKeyClass
    public static class TransferKey {
        @PrimaryKeyColumn(name = "hour_bucket", type = PrimaryKeyType.PARTITIONED)
        private String hourBucket;

        @PrimaryKeyColumn(name = "timestamp", type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
        private Instant timestamp;

        @PrimaryKeyColumn(name = "transaction_id", type = PrimaryKeyType.CLUSTERED)
        private UUID transactionId;

        public TransferKey() {}

        public TransferKey(String hourBucket, Instant timestamp, UUID transactionId) {
            this.hourBucket = hourBucket;
            this.timestamp = timestamp;
            this.transactionId = transactionId;
        }

        public String getHourBucket() {
            return hourBucket;
        }

        public void setHourBucket(String hourBucket) {
            this.hourBucket = hourBucket;
        }

        public Instant getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Instant timestamp) {
            this.timestamp = timestamp;
        }

        public UUID getTransactionId() {
            return transactionId;
        }

        public void setTransactionId(UUID transactionId) {
            this.transactionId = transactionId;
        }
    }
}

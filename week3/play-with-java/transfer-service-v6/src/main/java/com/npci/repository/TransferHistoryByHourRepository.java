package com.npci.repository;

import com.npci.model.TransferHistory_By_Hour;
import com.npci.model.TransferHistory_By_Hour.TransferKey;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferHistoryByHourRepository extends CassandraRepository<TransferHistory_By_Hour, TransferKey> {

    // Find all transactions for a given hour bucket
    List<TransferHistory_By_Hour> findByKeyHourBucket(String hourBucket);

    // Optionally: Filter by fromAccount within an hour
    List<TransferHistory_By_Hour> findByKeyHourBucketAndFromAccount(String hourBucket, String fromAccount);

    // Optionally: Filter by toAccount within an hour
    List<TransferHistory_By_Hour> findByKeyHourBucketAndToAccount(String hourBucket, String toAccount);

}

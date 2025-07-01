package com.npci.repository;

import com.npci.model.TransferHistory;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransferHistoryRepository extends CassandraRepository<TransferHistory, UUID> {
    // You can add custom query methods if needed
}

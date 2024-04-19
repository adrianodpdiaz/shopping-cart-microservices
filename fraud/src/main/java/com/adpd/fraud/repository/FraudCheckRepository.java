package com.adpd.fraud.repository;

import com.adpd.fraud.entity.FraudCheckHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FraudCheckRepository extends JpaRepository<FraudCheckHistory, Integer> {

}

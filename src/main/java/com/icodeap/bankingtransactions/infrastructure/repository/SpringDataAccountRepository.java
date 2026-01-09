package com.icodeap.bankingtransactions.infrastructure.repository;

import com.icodeap.bankingtransactions.infrastructure.persistence.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataAccountRepository extends JpaRepository<AccountEntity, String> {
}

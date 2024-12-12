package com.inditex.price.modules.infrastructure.persistence.repository;

import com.inditex.price.modules.infrastructure.persistence.entity.PriceJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface PriceJpaRepository extends JpaRepository<PriceJpaEntity, UUID>, JpaSpecificationExecutor {
}

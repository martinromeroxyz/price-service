package com.inditex.price.modules.infrastructure.persistence.specification;

import com.inditex.price.modules.infrastructure.persistence.entity.BrandJpaEntity;
import com.inditex.price.modules.infrastructure.persistence.entity.PriceJpaEntity;
import com.inditex.price.modules.infrastructure.web.filter.PriceFilter;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Builder
@Data
@AllArgsConstructor
public class PriceSpecification implements Specification<PriceJpaEntity> {

    private PriceFilter priceFilter;

    @Override
    public Predicate toPredicate(Root<PriceJpaEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (Objects.nonNull(priceFilter.getProductId())) {
            predicates.add(cb.equal(root.get(PriceJpaEntity.Fields.productId), priceFilter.getProductId()));
        }

        if (Objects.nonNull(priceFilter.getBrandId())) {
            predicates.add(cb.equal(root.get(PriceJpaEntity.Fields.brand).get(BrandJpaEntity.Fields.id), priceFilter.getBrandId()));
        }

        if(Objects.nonNull(priceFilter.getDate())) {
            Predicate startDatePredicate = cb.lessThanOrEqualTo(root.get(PriceJpaEntity.Fields.startDate), priceFilter.getDate());
            Predicate endDatePredicate = cb.greaterThanOrEqualTo(root.get(PriceJpaEntity.Fields.endDate), priceFilter.getDate());
            predicates.add(cb.and(startDatePredicate, endDatePredicate));
        }

        return cb.and(predicates.toArray(new Predicate[0]));
    }
}

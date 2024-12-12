package com.inditex.price.modules.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldNameConstants;

@Entity
@Table( name = "BRANDS" )
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@FieldNameConstants
public class BrandJpaEntity {

    @Id
    private Long id;

    @Column(unique = true)
    private String brand;
}

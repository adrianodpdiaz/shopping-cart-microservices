package com.adpd.product.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "product")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "productCache")
public class Product implements Serializable {

    @Id
    @Column
    @SequenceGenerator(name = "product_id_sequence", sequenceName = "product_id_sequence")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "product_id_sequence")
    private Long id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private String sku;
    @Column
    private String category;
    @NotNull(message = "price column must not be null")
    @Column
    private BigDecimal price;

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        Product other = (Product) o;
        return getId() != null && Objects.equals(getId(), other.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

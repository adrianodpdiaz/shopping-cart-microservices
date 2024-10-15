package com.adpd.cart.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@DynamicInsert
@Table(name = "item", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"cart_id", "product_id"})
})
public class Item implements Serializable {

    @Id
    @Column
    @SequenceGenerator(name = "item_id_sequence", sequenceName = "item_id_sequence")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "item_id_sequence")
    private Long id;
    @NotNull(message = "product_id column must not be null")
    @Column(name = "product_id")
    private Long productId;
    @NotNull(message = "unit_price column must not be null")
    @Column(name = "unit_price", columnDefinition = "decimal(19,2)")
    private BigDecimal unitPrice;
    @Column(columnDefinition = "integer default 1")
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        Item other = (Item) o;
        return getId() != null && Objects.equals(getId(), other.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

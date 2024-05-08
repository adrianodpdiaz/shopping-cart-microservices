package com.adpd.cart.entity;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "cart")
public class Cart implements Serializable {

    @Id
    @Column
    @SequenceGenerator(name = "cart_id_sequence", sequenceName = "cart_id_sequence")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "cart_id_sequence")
    private Long id;
    @Column(name = "customer_id")
    private Long customerId;
    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy="cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Item> items;

    @PostConstruct
    void a() {
        setCreatedAt(createdAt);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        Cart other = (Cart) o;
        return getId() != null && Objects.equals(getId(), other.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

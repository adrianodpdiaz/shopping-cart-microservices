package com.adpd.customer.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "address")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "addressCache")
public class Address implements Serializable {

    @Id
    @Column
    @SequenceGenerator(name = "address_id_sequence", sequenceName = "address_id_sequence")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "address_id_sequence")
    private Long id;
    @Column
    @NotNull(message = "address column cannot be null")
    private String streetAddress;
    @Column
    @NotNull(message = "city column cannot be null")
    private String city;
    @Column
    private String state;
    @NotNull(message = "e-mail column cannot be null")
    @Column
    private String country;
    @NotNull(message = "zip-code column cannot be null")
    @Column(name = "zip_code")
    private String zipCode;

    @NotNull(message = "address must have a customer")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        Address other = (Address) o;
        return getId() != null && Objects.equals(getId(), other.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

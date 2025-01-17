package com.adpd.customer.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "customer")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "customerCache")
public class Customer implements Serializable {

    @Id
    @Column
    @SequenceGenerator(name = "customer_info_id_sequence", sequenceName = "customer_info_id_sequence")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "customer_info_id_sequence")
    private Long id;
    @Column
    @NotNull(message = "first name column cannot be null")
    private String firstName;
    @Column
    @NotNull(message = "last name column cannot be null")
    private String lastName;
    @NotNull(message = "e-mail column cannot be null")
    @Column(unique = true)
    private String email;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @Column(name = "tax_id")
    private String taxId;

    @OneToMany(mappedBy="customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Address> addresses;

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        Customer other = (Customer) o;
        return getId() != null && Objects.equals(getId(), other.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

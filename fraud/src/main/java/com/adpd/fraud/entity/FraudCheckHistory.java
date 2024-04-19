package com.adpd.fraud.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FraudCheckHistory {

    @Id
    @SequenceGenerator(name = "fraud_id_sequence", sequenceName = "fraud_id_sequence")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "fraud_id_sequence")
    private Integer id;
    private String customerId;
    private Boolean isFraudster;
    private LocalDateTime createdAt;

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        FraudCheckHistory other = (FraudCheckHistory) o;
        return getId() != null && Objects.equals(getId(), other.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

package com.adpd.notification.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "notification")
public class Notification implements Serializable {

    @Id
    @Column
    @SequenceGenerator(name = "notification_id_sequence", sequenceName = "notification_id_sequence")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "notification_id_sequence")
    private Long id;
    @Column(name = "to_customer_id")
    private Long toCustomerId;
    @Column(name = "to_customer_email")
    private String toCustomerEmail;
    @NotNull
    private String sender;
    @NotNull
    private String message;
    @CreationTimestamp
    @Column(name = "sent_at")
    private LocalDateTime sentAt;

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        Notification other = (Notification) o;
        return getId() != null && Objects.equals(getId(), other.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

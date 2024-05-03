package com.adpd.notification.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "notification")
public class Notification implements Serializable {

    @Id
    private String id;
    @Field(name = "to_customer_id")
    private Long toCustomerId;
    @Field(name = "to_customer_email")
    private String toCustomerEmail;
    @NotNull
    private String sender;
    @NotNull
    private String message;
    @Field(name = "sent_at")
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

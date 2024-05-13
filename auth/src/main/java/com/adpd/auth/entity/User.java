package com.adpd.auth.entity;

import com.adpd.auth.annotation.ValidPassword;
import com.adpd.feignclients.resource.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user_info")
public class User implements Serializable {

    @Id
    @Column
    @SequenceGenerator(name = "user_info_id_sequence", sequenceName = "user_info_id_sequence")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "user_info_id_sequence")
    private Long id;
    @Email
    @NotNull(message = "e-mail column cannot be null")
    @Column(unique = true)
    private String email;
    @ValidPassword
    @NotNull(message = "password column cannot be null")
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;


    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        User other = (User) o;
        return getId() != null && Objects.equals(getId(), other.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

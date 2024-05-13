package com.adpd.auth.mapping;

import com.adpd.auth.entity.User;
import com.adpd.auth.resource.form.CreateUserForm;
import com.adpd.feignclients.customer.resource.dto.UserDetailsDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User requestToEntity(CreateUserForm form);

    UserDetailsDTO toDTO(User user);

}

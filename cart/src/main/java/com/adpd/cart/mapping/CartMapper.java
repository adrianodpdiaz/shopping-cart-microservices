package com.adpd.cart.mapping;

import com.adpd.cart.entity.Cart;
import com.adpd.cart.resource.form.CreateCartForm;
import com.adpd.cart.resource.dto.CartDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {

    CartDTO toDTO(Cart customer);

    Cart requestToEntity(CreateCartForm form);

}

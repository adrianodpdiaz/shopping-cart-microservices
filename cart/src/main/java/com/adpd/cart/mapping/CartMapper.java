package com.adpd.cart.mapping;

import com.adpd.cart.entity.Cart;
import com.adpd.cart.resource.inbound.CreateCartInbound;
import com.adpd.cart.resource.outbound.CartDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {

    CartDTO toDTO(Cart customer);

    Cart requestToEntity(CreateCartInbound inbound);

}

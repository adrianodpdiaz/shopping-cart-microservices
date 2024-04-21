package com.adpd.product.mapping;

import com.adpd.product.entity.Product;
import com.adpd.product.resource.outbound.ProductDTO;
import com.adpd.product.resource.inbound.RegisterProductInbound;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDTO toDTO(Product customer);

    Product requestToEntity(RegisterProductInbound inbound);

}
